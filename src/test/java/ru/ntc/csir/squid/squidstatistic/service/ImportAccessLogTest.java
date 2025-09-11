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

    @Test
    void urlT(){
        String[] url1Str = {"watson.events.data.microsoft.com:443",
                "error:transaction-end-before-headers",
                "http://gwk.csir.reb:3128/squid-internal-static/icons/SN.png",
                "http://149.154.167.151/api"};

        Integer[] url1StrRes = {443, 0, 3128, 0};

        for(int i=0;i<url1Str.length; i++){
            String[] url = url1Str[i].split(":");
            int urlPort = 0;
            if(url.length>1){
                if(url.length==2 && url[1].matches("-?\\d+")) urlPort = Integer.parseInt(url[1]);
                if(url.length==3 ){
                    var strs = url[2].split("/");
                    if(strs.length>1 && strs[0].matches("-?\\d+")) urlPort = Integer.parseInt(strs[0]);
                }
            }
            assertEquals(url1StrRes[i], urlPort);

            System.out.println(Integer.MAX_VALUE);
            System.out.println(Long.MAX_VALUE);

            //if( url.length==3) urlPort = Integer.parseInt(url[2]);
            //if( url.length==2 && !url[0].equals("http") && url[1].matches("-?\\d+")) urlPort = Integer.parseInt(url[1]);
        }

    }

}