package com.sunzn.http.client.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by sunzn on 2017/6/13.
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Column {

    private String name;
    private String mapper;
    private int limit;

    public Column(String name, String mapper) {
        this.name = name;
        this.mapper = mapper;
    }
    
}
