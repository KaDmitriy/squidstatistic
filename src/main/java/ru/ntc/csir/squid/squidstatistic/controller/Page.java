package ru.ntc.csir.squid.squidstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ntc.csir.squid.squidstatistic.configure.Info;
import ru.ntc.csir.squid.squidstatistic.model.Access;
import ru.ntc.csir.squid.squidstatistic.repository.AccessRepository;

import java.util.List;

@Controller
public class Page {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private Info info;

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("version", info.getVersion());
        return "index";
    }

    @GetMapping("/day.html")
    public String getDay(Model model){
        model.addAttribute("version", info.getVersion());
        return "day";
    }

    @GetMapping("/week.html")
    public String getWeek(Model model){
        model.addAttribute("version", info.getVersion());
        return "week";
    }

    @GetMapping("/month.html")
    public String getMonth(Model model){
        model.addAttribute("version", info.getVersion());
        return "month";
    }

    @GetMapping("/listalllogs")
    public String geyListAllLogs(Model model){
        List<Access> accessList = accessRepository.findAll();
        //model.addAttribute("name", accessList.get(0));
        model.addAttribute("accessList", accessList);
        return "listalllogs";
    }

}
