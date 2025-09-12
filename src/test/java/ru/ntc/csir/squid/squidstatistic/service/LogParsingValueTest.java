package ru.ntc.csir.squid.squidstatistic.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogParsingValueTest {

    @Test
    void getUrlPort() {
        String[] urlStr = {"watson.events.data.microsoft.com:443",
                "error:transaction-end-before-headers",
                "http://gwk.csir.reb:3128/squid-internal-static/icons/SN.png",
                "http://149.154.167.151/api"};

        Integer[] urlStrRes = {443, 0, 3128, 0};

        for(int i=0;i<urlStr.length; i++) {
            int port = LogParsingValue.getUrlPort(urlStr[i]);
            assertEquals(urlStrRes[i], port);
        }

    }

    @Test
    void getDomainName() {
        String[] domainList = {
                "http://site.name.tst/api/v1/getlist", //1
                "http://gwe.name.tst:3128/squid-internal-static/icons/SN.png", //2
                "watson.events.data.microsoft.com:443", //3
                "http://123.123.123.123:433/api", //4
                "123.123.123.123", //5
                "123.123.123.123:433", //6
                "36ltwco2hg.a.name.net:443", //7
                "http://clck.name.tst/click/dtype=stred/pid=12/cid=72955/path=injector.load.0/vars=-dayuse=1070,-ver=5_5_0_1923,-ui={75CA9177-EF28-47E9-A21C-4C439451236D},-voiceid=C1BC7E2C4B42423C8EED732FBA9BE264,-machineid=9bd95cc500b24e7ce2280aa23c703945/*", //8
                "error:transaction-end-before-headers" //9
                };

        String[] domainRes = {
                "name.tst", //1
                "name.tst", //2
                "microsoft.com", //3
                "123.123.123.123", //4
                "123.123.123.123", //5
                "123.123.123.123", //6
                "name.net", //7
                "name.tst", //8
                "" //9
        };

        for(int i=0;i<domainList.length; i++) {
            String domain = LogParsingValue.getDomainName(domainList[i]);
            assertEquals(domainRes[i], domain);
        }
    }
}