package ru.ntc.csir.squid.squidstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ntc.csir.squid.squidstatistic.dto.ResultImport;
import ru.ntc.csir.squid.squidstatistic.service.ImportAccessLog;

import java.io.IOException;

@RestController
public class ImportFile {

    @Autowired
    private ImportAccessLog importAccessLog;

    @PostMapping("/uploadaccess")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Short node) throws IOException {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        if (node == null) return "Error request";
        ResultImport ri = importAccessLog.addLog(file.getInputStream(),node);
        return "File uploaded successfully: " + file.getOriginalFilename() + " CountProcessed:" + ri.getCountProcessed();
    }
}

