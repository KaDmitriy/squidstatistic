package ru.ntc.csir.squid.squidstatistic.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LogParsingValue {

    static public int getUrlPort(String value) {
        String[] url = value.split(":");
        int urlPort = 0;
        if(url.length>1){
            if(url.length==2 && url[1].matches("-?\\d+")) urlPort = Integer.parseInt(url[1]);
            if(url.length==3 ){
                var strs = url[2].split("/");
                if(strs.length>1 && strs[0].matches("-?\\d+")) urlPort = Integer.parseInt(strs[0]);
            }
        }
        return urlPort;
    }

    /**
     * Result domain 2 level
     * @param value String url
     * @return String domain level 2
     */
    static public String getDomainName(String value) {
        String host = "-";
        URL uri;
        try {
            if(value.indexOf("http://")==-1) uri = new URL("http://"+value);
            else uri = new URL(value);
            String hostName = uri.getHost();
            if(hostName == null) hostName = uri.getPath();
            if(hostName!=null) {
                String ipv4Regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
                if (hostName.matches(ipv4Regex)) {
                    host = hostName;
                } else if (hostName != null) {
                    String[] level = hostName.split("\\.");
                    if (level.length > 1) {
                        host = level[level.length - 2] + "." + level[level.length - 1];
                    }
                }
            }
        } catch (MalformedURLException e) {
            host = "-";
            //throw new RuntimeException(e);
        }
        return host;
    }

}
