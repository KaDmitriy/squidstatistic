package ru.ntc.csir.squid.squidstatistic.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.ntc.csir.squid.squidstatistic.model.Access;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@ContextConfiguration
class ImportAccessLogTest {

    @Test
    void parsing() {
        String lineLog = "1757106048.542      1 192.168.12.87 TCP_DENIED/403 3867 CONNECT watson.events.data.microsoft.com:443 - HIER_NONE/- text/html";
        ImportAccessLog ial = new ImportAccessLog();
        Access access = null;
        try {
            access = ial.parsing(lineLog, (short) 1);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, access.getDuration());

    }

}