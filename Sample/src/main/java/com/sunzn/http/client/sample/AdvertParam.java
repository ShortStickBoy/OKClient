package com.sunzn.http.client.sample;

import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by sunzn on 2017/8/4.
 */

@Data
@ToString
@NoArgsConstructor
public class AdvertParam {

    private ArrayList<Column> columns;
    private int PlateForm;
    private int Type;
    private int page;
    private int rows;

}
