package com.takeaway.commons.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:
 * @Author: Administrator
 */
public class ViewClient {
    private static final Logger log = Logger.getLogger(ViewClient.class);

    public static Properties prop = new Properties();

    static {
        InputStream inputStream = ViewClient.class
                .getResourceAsStream("/project.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            log.error("加载project.properties失败", e);
        }

        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
    }

    public static String getHost(){
        return prop.getProperty("host");
    }
}
