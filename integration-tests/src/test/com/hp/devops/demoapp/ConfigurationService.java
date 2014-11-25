package com.hp.devops.demoapp;

import javax.annotation.PostConstruct;

/**
 * User: belozovs
 * Date: 11/25/14
 * Description
 */
public class ConfigurationService {

    static private String protocol = "http";
    static private String hostName = "localhost";
    static private int port = 9999;
    static private String basePath = "/api";

    @PostConstruct
    public void init(){
        if(System.getProperty("hostname")!=null){
            hostName = System.getProperty("hostname");
        }
        if(System.getProperty("port")!=null){
            port = Integer.getInteger(System.getProperty("port"));
        }
        if(System.getProperty("protocol")!=null){
            protocol = System.getProperty("protocol");
        }
        if(System.getProperty("basepath")!=null){
            basePath = System.getProperty("basepath");
        }
        System.out.println("Starting the test for " + protocol + "://" + hostName + ":" + port + basePath);
    }

    public static String getProtocol() {
        return protocol;
    }

    public static String getHostName() {
        return hostName;
    }

    public static int getPort() {
        return port;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static String getBaseUri(){
        return protocol + "://" + hostName;
    }
}
