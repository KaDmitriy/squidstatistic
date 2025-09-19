package ru.ntc.csir.squid.squidstatistic.configure;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Info {
    private final Logger log = LoggerFactory.getLogger(Info.class);

    private String version = "DEV VERSION";

    @PostConstruct
    public void init() {
        InputStream fis;
        Properties properties = new Properties();
        try {
            fis = this.getClass().getResourceAsStream("info");
            if (fis != null)
                properties.load(fis);
        } catch (IOException e) {
            log.error("Файл свойств (info) отсуствует!");
        }
        version = properties.getProperty("app.version", "DEV VERSION");
    }

    public String getVersion() {
        return version;
    }
}
