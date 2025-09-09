package ru.ntc.csir.squid.squidstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ntc.csir.squid.squidstatistic.service.ImportAccessLog;

import java.io.IOException;

@RestController
public class ImportFile {

    @Autowired
    private ImportAccessLog importAccessLog;

    @PostMapping("/uploadaccess")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        importAccessLog.addLog(file.getInputStream(),(short)1);
        return "File uploaded successfully: " + file.getOriginalFilename();
    }
}

