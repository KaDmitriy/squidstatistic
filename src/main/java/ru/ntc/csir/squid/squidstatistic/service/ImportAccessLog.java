package ru.ntc.csir.squid.squidstatistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ntc.csir.squid.squidstatistic.model.*;
import ru.ntc.csir.squid.squidstatistic.repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * !!! Thread safety broken !!!
 */

@Component
public class ImportAccessLog {

    @Autowired
    private List<ResultCode> listResultCode;

    @Autowired
    private ResultCodeRepository resultCodeRepository;

    @Autowired
    List<RequestMethod> listRequestMethod;

    @Autowired
    private RequestMethodRepository requestMethodRepository;

    @Autowired
    List<HierarchyCode> listHierarchyCode;

    @Autowired
    HierarchyCodeRepository hierarchyCodeRepository;

    @Autowired
    List<ContentType> listContentType;

    @Autowired
    ContentTypeRepository contentTypeRepository;

    @Autowired
    AccessRepository accessRepository;

    public void addLog(InputStream is, Short node){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parsing(line, node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Integer duration = Integer.parseInt(values[1]);
        InetAddress clientAddress = InetAddress.getByName(values[2]);
        String[] resultcode = values[3].split("/");
        Short resultCodeNumber = Short.parseShort(resultcode[1]);
        Integer size = Integer.parseInt(values[4]);
        String requestMethod = values[5];
        String[] url = values[6].split(":");
        int urlPort = 0;
        if( url.length==3) urlPort = Integer.parseInt(url[2]);
        if( url.length==2 && !url[0].equals("http")) urlPort = Integer.parseInt(url[1]);
        String user = values[7];
        String[] hierarchyCode = values[8].split("/");
        String contentType = values[9];

        Short resultCodeID = resultCodeID(resultcode[0]);
        Short requestMethodID = resultRequestMethodID(requestMethod);
        Short hierarchyCodeID = resultHierarchyCodeID(hierarchyCode[0]);
        Short contentTypeID = resultContentType(contentType);

        return accessRepository.save(new Access(node,
                datetime,
                duration,
                clientAddress,
                resultCodeID,
                resultCodeNumber,
                size,
                requestMethodID,
                url[0],
                urlPort,
                user,
                hierarchyCodeID,
                hierarchyCode[1],
                contentTypeID
                ));
    }

    public Short resultCodeID(String code){
        Short result = 0;
        if(!listResultCode.isEmpty()) {
            Optional<ResultCode> resultCode = listResultCode.stream().filter(rc -> rc.getName().equals(code)).findFirst();
            if (resultCode.isEmpty()) {
                ResultCode newResultCode = new ResultCode(code);
                ResultCode dbResultCode = resultCodeRepository.save(newResultCode);
                listResultCode.add(dbResultCode);
                result = dbResultCode.getId();
            } else result = resultCode.get().getId();
        }
        return result;
    }

    public Short resultRequestMethodID(String code){
        Short result = 0;
        if(! listRequestMethod.isEmpty()){
            Optional<RequestMethod> requestMethod = listRequestMethod.stream().filter(rm -> rm.getName().equals(code)).findFirst();
            if(requestMethod.isEmpty()){
                RequestMethod dbRequestMethod = requestMethodRepository.save(new RequestMethod(code));
                listRequestMethod.add(dbRequestMethod);
                result = dbRequestMethod.getId();
            } else result = requestMethod.get().getId();
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
        }
        return 0;
    }

    public Short resultContentType(String code){
        if(! listContentType.isEmpty()){
            Optional<ContentType> contentType = listContentType.stream().filter(ct -> ct.getName().equals(code)).findFirst();
            if(contentType.isEmpty()){
                ContentType dbContentType = contentTypeRepository.save(new ContentType(code));
                listContentType.add(dbContentType);
                return dbContentType.getId();
            } else return contentType.get().getId();
        }
        return 0;
    }

}
