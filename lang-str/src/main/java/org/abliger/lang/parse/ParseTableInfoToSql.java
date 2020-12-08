package org.abliger.lang.parse;

import org.abliger.lang.counst.AttributeMapping;
import org.abliger.lang.entity.TableFiled;
import org.abliger.lang.entity.TableInfo;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abliger
 * @date 2020/12/4
 * @description 根据TableInfo信息解析成对应的sql语句
 */

public class ParseTableInfoToSql {
    public static String[] parse(TableInfo tableInfo) {
        final String tableName = tableInfo.getTableName();
        final List<TableFiled> tableFiled = tableInfo.getTableFiled();
        final String dropTableIfExistSql = dropTableIfExist(tableName);
        final String tableCreateSql = createTable(tableName, tableFiled);
        return new String[]{dropTableIfExistSql, tableCreateSql};
    }

    private static String dropTableIfExist(String tableName) {
        return "DROP TABLE IF EXISTS `" + tableName + "`;";
    }

    private static String createTable(String tableName, List<TableFiled> tableFiled) {
        final StringBuilder stringBuilder = new StringBuilder("CREATE TABLE `" + tableName + "`  (\n");
        List<String> list = new ArrayList<>();
        for (TableFiled filed : tableFiled) {
            final String tableFiledName = filed.getTableFiled();
            final String description = filed.getDescription();
            final Class<?> clazz = filed.getClazz();
            final boolean primaryKey = filed.isPrimaryKey();
            if (primaryKey) {
                list.add("`" + tableFiledName + "` " + AttributeMapping.map.get(clazz.getName()) + "(100) NOT NULL AUTO_INCREMENT COMMENT '" + description+"'\n");
                list.add("  PRIMARY KEY (`" + tableFiledName + "`) USING BTREE\n");
            }else{
                list.add("`" + tableFiledName + "` " + AttributeMapping.map.get(clazz.getName()) + "(100) NOT NULL COMMENT '" + description+"'\n");
            }
        }
        final String join = String.join(",", list);
        return stringBuilder.append(join).append(");").toString();
    }
}
