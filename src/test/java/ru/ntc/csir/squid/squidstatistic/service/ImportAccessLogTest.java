package ru.ntc.csir.squid.squidstatistic.service;

import org.junit.jupiter.api.Test;
import ru.ntc.csir.squid.squidstatistic.model.Access;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@ContextConfiguration
class ImportAccessLogTest {

    @Test
    void parsing() {
        String lineLog1 = "1757106048.542      1 192.168.12.87 TCP_DENIED/403 3867 CONNECT watson.events.data.microsoft.com:443 - HIER_NONE/- text/html";
        String lineLog2 = "1756767843.864      0 192.168.12.57 NONE_NONE/000 0 - error:transaction-end-before-headers - HIER_NONE/- -";
        String lineLog3 = "1756786282.038      0 192.168.12.56 TCP_HIT/200 13069 GET http://gwk.csir.reb:3128/squid-internal-static/icons/SN.png - HIER_NONE/- image/png";
        ImportAccessLog ial = new ImportAccessLog();
        Access access = null;
        try {
            access = ial.parsing(lineLog3, (short) 1);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, access.getDuration());

    }

}