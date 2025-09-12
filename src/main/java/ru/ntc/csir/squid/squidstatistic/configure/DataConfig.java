package ru.ntc.csir.squid.squidstatistic.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ntc.csir.squid.squidstatistic.model.*;
import ru.ntc.csir.squid.squidstatistic.repository.*;

import java.util.List;

/**
 * Конфигурация основных структур данных
 * Описывают ссылочные целостности записи лога
 */

@Configuration
public class DataConfig {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ResultCodeRepository resultCodeRepository;

    @Autowired
    private RequestMethodRepository requestMethodRepository;

    @Autowired
    private HierarchyCodeRepository hierarchyCodeRepository;

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Bean
    public List<Node> listNode(){
        return nodeRepository.findAll();
    }

    @Bean
    public List<ResultCode> listResultCode(){
        return resultCodeRepository.findAll();
    }

    @Bean
    public List<RequestMethod> listRequestMethod() {
        return requestMethodRepository.findAll();
    }

    @Bean
    public  List<HierarchyCode> listHierarchyCode() {
        return hierarchyCodeRepository.findAll();
    }

    @Bean
    public List<ContentType> listContentType(){
        return contentTypeRepository.findAll();
    }

    @Bean
    public List<Domain> listDomain(DomainRepository domainRepository) {
        return domainRepository.findAll();
    }

}
