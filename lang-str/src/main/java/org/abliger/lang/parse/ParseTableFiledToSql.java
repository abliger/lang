package org.abliger.lang.parse;

/**
 * @author abliger
 * @date 2020/12/5
 * @description
 */

public class ParseTableFiledToSql {
    //INSERT INTO `base_attr_info` VALUES (1, '价格', 61, 3);
    public static String parseTableFieldToSql(int count,String field) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into `question`(question,type) values('"+field+"','0');");
        return stringBuilder.toString();
    }
}
