package org.abliger.lang.entity;

import lombok.Data;

import java.util.List;

/**
 * @author abliger
 * @date 2020/12/4
 * @description
 */
@Data
public class TableInfo {
    private String TableName;

    private List<TableFiled> TableFiled;
}
