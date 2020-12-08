package org.abliger.pojo;

import lombok.Data;
import org.abliger.lang.db.Table;
import org.abliger.lang.db.TableName;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */
@Data
@TableName
public class Question {
    @Table(isPrimaryKey = true,description = "主键id")
    private Integer id;
    @Table(description = "问题描述")
    private String question;
    @Table(description = "问题分类")
    private String type;
}
