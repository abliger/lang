package org.abliger.pojo;

import lombok.Data;
import org.abliger.lang.db.Table;
import org.abliger.lang.db.TableName;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@Data
@TableName
public class Type {
    @Table(isPrimaryKey = true,description = "主键id")
    private String id;
    @Table(description = "分类")
    private String type;
}
