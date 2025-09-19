package ru.ntc.csir.squid.squidstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ntc.csir.squid.squidstatistic.model.*;

import java.util.List;

@RestController
public class Information {

    @Autowired
    private List<Node> listNode;

    @Autowired
    private List<ResultCode> listResultCode;

    @Autowired
    List<RequestMethod> listRequestMethod;

    @Autowired
    List<HierarchyCode> listHierarchyCode;

    @Autowired
    List<ContentType> listContentType;

    @GetMapping("/info/node")
    public List<Node> listNode(){
        return listNode;
    }

    @GetMapping("/info/resultcode")
    public List<ResultCode> listResultCode(){
            return listResultCode;
    }

    @GetMapping("/info/requestmethod")
    public List<RequestMethod> listRequestMethod(){
        return listRequestMethod;
    }

    @GetMapping("/info/hierachycode")
    public List<HierarchyCode> ListHierarchyCode(){
        return listHierarchyCode;
    }

    @GetMapping("/info/contenttype")
    public List<ContentType> listContentType() {
        return listContentType;
    }

}
