package com.sunzn.http.client.sample;

import java.util.ArrayList;

/**
 * Created by sunzn on 2017/6/23.
 */

public class CookieManager {

    public static AdvertParam getAdvertParam(int platform, int type, int page, int rows) {

        AdvertParam param = new AdvertParam();

        ArrayList<Column> columns = new ArrayList<>();

        param.setPlateForm(platform);
        param.setType(type);
        param.setPage(page);
        param.setRows(rows);
        param.setColumns(columns);

        columns.add(new Column("Type", "var1"));
        columns.add(new Column("FileName", "var2"));
        columns.add(new Column("Title", "var3"));
        columns.add(new Column("TargetOther", "var4"));
        columns.add(new Column("TargetUrl", "var5"));
        columns.add(new Column("Summary", "var6"));

        return param;
    }

}
