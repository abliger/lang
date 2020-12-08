package org.abliger.lang.config;

import org.abliger.lang.counst.SystemConst;
import org.abliger.lang.db.MyConnection;
import org.abliger.lang.entity.TableInfo;
import org.abliger.lang.parse.ParseTableInfoToSql;
import org.abliger.lang.reflection.db.AnnotationToTableMappingBeanFactory;

import javax.swing.plaf.multi.MultiLabelUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 * @author abliger
 * @date 2020/12/4
 * @description
 */

public class Configetion {

    public static void main(String[] args) throws SQLException {
        final AnnotationToTableMappingBeanFactory annotationToTableMappingBeanFactory = new AnnotationToTableMappingBeanFactory();
        annotationToTableMappingBeanFactory.setClassList(SystemConst.ENTITYBASEPACKAGESCAN);
        final List<TableInfo> beanToDbTableMappingList = annotationToTableMappingBeanFactory.getBeanToDbTableMappingList();
        String createTableSql = "";

        final Connection connection = MyConnection.getConnection();
        final Statement statement = connection.createStatement();

        for (TableInfo tableInfo : beanToDbTableMappingList) {
            final String[] parse = ParseTableInfoToSql.parse(tableInfo);
            for (String s : parse) {
                statement.addBatch(s);
            }
        }
        System.out.println(createTableSql);
        statement.executeBatch();
    }
}
