package ru.ntc.csir.squid.squidstatistic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ntc.csir.squid.squidstatistic.dto.ResultImport;
import ru.ntc.csir.squid.squidstatistic.model.*;
import ru.ntc.csir.squid.squidstatistic.repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * !!! Thread safety broken !!!
 */

@Component
public class ImportAccessLog {

    Logger log = LoggerFactory.getLogger(ImportAccessLog.class);

    @Autowired
    private List<ResultCode> listResultCode;

    @Autowired
    private ResultCodeRepository resultCodeRepository;

    @Autowired
    private List<RequestMethod> listRequestMethod;

    @Autowired
    private RequestMethodRepository requestMethodRepository;

    @Autowired
    private List<HierarchyCode> listHierarchyCode;

    @Autowired
    private HierarchyCodeRepository hierarchyCodeRepository;

    @Autowired
    private List<ContentType> listContentType;

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private List<Domain> listDomain;

    @Autowired
    private DomainRepository domainRepository;

    private Access lastAccess;

    @Value("${squidstatictic.countrowsave}")
    Integer initialCapacity;

    @Transactional
    public Lastupdate addLog(InputStream is, Short node){
        Instant dateUpdate = Instant.now();
        long timeStart = System.currentTimeMillis();
        lastAccess = accessRepository.getLastInNode(node);
        int countProcessed = 0;
        int countSave = 0;
        log.info("initialCapacity:{}", initialCapacity);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            List<Access> listAddAccess = new ArrayList<Access>(initialCapacity);
            while ((line = reader.readLine()) != null) {
                Access currentAccess = parsing(line, node);
                if(currentAccess == null) break;
                listAddAccess.add(currentAccess);
                if(listAddAccess.size()==initialCapacity){
                    accessRepository.saveAll(listAddAccess);
                    listAddAccess.clear();
                    countSave++;
                }
                countProcessed++;
            }
            if(listAddAccess.size()>0){
                accessRepository.saveAll(listAddAccess);
                listAddAccess.clear();
                countSave++;
            }
        } catch (IOException e) {
            log.error("End add log, countrow:{}", countProcessed);
            e.printStackTrace();
        }
        log.info("End add log, countrow:{}; countsave:{}", countProcessed, countSave);
        log.info("Lead time:{} s", ((System.currentTimeMillis() - timeStart)/1000));
        return new Lastupdate(node, dateUpdate, 0L, countProcessed, ((Long)((System.currentTimeMillis() - timeStart)/1000)).intValue());
    }

    /**
     * Parsing of one line
     * @param dataline One line
     * @return Model Access
     */
    public Access parsing(String dataline, Short node) throws UnknownHostException {
        String[] values = dataline.replaceAll("\\s{2,}", " ").split(" ");

        long datetimeUnixTimestampMillis = Long.parseLong( values[0].replace(".", "") );
        Instant datetime = Instant.ofEpochMilli(datetimeUnixTimestampMillis);
        LocalDate date = LocalDate.ofInstant(datetime, ZoneId.of("UTC"));
        Integer duration = Integer.parseInt(values[1]);
        InetAddress clientAddress = InetAddress.getByName(values[2]);
        String[] resultcode = values[3].split("/");
        Short resultCodeNumber = Short.parseShort(resultcode[1]);
        Long size = Long.parseLong(values[4]);
        String requestMethod = values[5];
        String urlDomain = LogParsingValue.getDomainName(values[6]);
        int urlPort = LogParsingValue.getUrlPort(values[6]);
        String urlStr = values[6];
        if(urlPort==443) urlStr = values[6].replaceAll(":urlPort", "");
        String user = values[7];
        String[] hierarchyCode = values[8].split("/");
        String contentType = values[9];

        Short resultCodeID = resultCodeID(resultcode[0]);
        Short requestMethodID = resultRequestMethodID(requestMethod);
        Short hierarchyCodeID = resultHierarchyCodeID(hierarchyCode[0]);
        Short contentTypeID = resultContentType(contentType);
        Integer resultDomainID = resultDomainId(urlDomain);


        var access = new Access(node,
                datetime,
                date,
                duration,
                clientAddress,
                resultCodeID,
                resultCodeNumber,
                size,
                requestMethodID,
                resultDomainID,
                urlStr,
                urlPort,
                user,
                hierarchyCodeID,
                hierarchyCode[1],
                contentTypeID
        );

       if( lastAccess!=null && lastAccess.getDatetime().isAfter(access.getDatetime()) ) return null;

        return access;
    }

    public Short resultCodeID(String code){
        Short result = -1;
        if(!listResultCode.isEmpty()) {
            Optional<ResultCode> resultCode = listResultCode.stream().filter(rc -> rc.getName().equals(code)).findFirst();
            if (resultCode.isEmpty()) {
                ResultCode newResultCode = new ResultCode(code);
                ResultCode dbResultCode = resultCodeRepository.save(newResultCode);
                listResultCode.add(dbResultCode);
                result = dbResultCode.getId();
            } else result = resultCode.get().getId();
        } else {
            ResultCode newResultCode = new ResultCode(code);
            ResultCode dbResultCode = resultCodeRepository.save(newResultCode);
            listResultCode.add(dbResultCode);
            result = dbResultCode.getId();
        }
        return result;
    }

    public Short resultRequestMethodID(String code){
        Short result = -1;
        if(! listRequestMethod.isEmpty()){
            Optional<RequestMethod> requestMethod = listRequestMethod.stream().filter(rm -> rm.getName().equals(code)).findFirst();
            if(requestMethod.isEmpty()){
                RequestMethod dbRequestMethod = requestMethodRepository.save(new RequestMethod(code));
                listRequestMethod.add(dbRequestMethod);
                result = dbRequestMethod.getId();
            } else result = requestMethod.get().getId();
        } else {
            RequestMethod dbRequestMethod = requestMethodRepository.save(new RequestMethod(code));
            listRequestMethod.add(dbRequestMethod);
            result = dbRequestMethod.getId();
        }
        return result;
    }

    public Short resultHierarchyCodeID(String code) {
        if(! listHierarchyCode.isEmpty()){
            Optional<HierarchyCode> hierarchyCode = listHierarchyCode.stream().filter(hc -> hc.getName().equals(code)).findFirst();
            if(hierarchyCode.isEmpty()){
                HierarchyCode dbHierarchyCode = hierarchyCodeRepository.save(new HierarchyCode(code));
                listHierarchyCode.add(dbHierarchyCode);
                return dbHierarchyCode.getId();
            }else return hierarchyCode.get().getId();
        } else {
            HierarchyCode dbHierarchyCode = hierarchyCodeRepository.save(new HierarchyCode(code));
            listHierarchyCode.add(dbHierarchyCode);
            return dbHierarchyCode.getId();
        }
    }

    public Short resultContentType(String code){
        if(! listContentType.isEmpty()){
            Optional<ContentType> contentType = listContentType.stream().filter(ct -> ct.getName().equals(code)).findFirst();
            if(contentType.isEmpty()){
                ContentType dbContentType = contentTypeRepository.save(new ContentType(code));
                listContentType.add(dbContentType);
                return dbContentType.getId();
            } else return contentType.get().getId();
        } else {
            ContentType dbContentType = contentTypeRepository.save(new ContentType(code));
            listContentType.add(dbContentType);
            return dbContentType.getId();
        }
    }

    public Integer resultDomainId(String domainName){
        if(! listDomain.isEmpty()){
            Optional<Domain> domainFind = listDomain.stream().filter(ct -> ct.getName().equals(domainName)).findFirst();
            if(domainFind.isEmpty()){
                Domain domainCreate = domainRepository.save(new Domain(domainName));
                listDomain.add(domainCreate);
                return domainCreate.getId();
            } else return domainFind.get().getId();
        } else {
            Domain domainCreate = domainRepository.save(new Domain(domainName));
            listDomain.add(domainCreate);
            return domainCreate.getId();
        }
    }

}
