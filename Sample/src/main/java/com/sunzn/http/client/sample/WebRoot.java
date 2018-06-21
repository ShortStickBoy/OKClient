package com.sunzn.http.client.sample;

/**
 * Created by sunzn on 2016/8/16.
 */
public class WebRoot {

    public static final String CORPUS1 = "http://bianke.cnki.net";
    public static final String CORPUS2 = "http://e.bianke.cnki.net";
    public static final String CORPUS3 = "http://pay.cnki.net";
    public static final String CORPUS4 = "http://z.bianke.cnki.net";
    public static final String CORPUS5 = "http://p.bianke.cnki.net";

    static String getGlobalRoot() {
        if (true) {
            return "http://xyz.cnki.net/";
//            return "http://192.168.107.112:8340/";
        } else {
            return "http://192.168.107.112:8340/";
        }
    }

    static String getWapRoot() {
        return "http://wap.cnki.net/";
    }

    static String getImageRoot() {
        return "http://c61.cnki.net/";
    }

    static String getEpubRoot(boolean isPrior) {
        return isPrior ? "http://ecppdown.cnki.net" : "http://epub.d.cnki.net";
    }

}
