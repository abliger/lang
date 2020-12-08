package org.abliger.lang.exec;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.abliger.lang.db.MyConnection;
import org.abliger.lang.parse.ParseTableFiledToSql;
import org.abliger.lang.text.TextToDB;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author abliger
 * @date 2020/12/5
 * @description
 */

public class Exector {
    public static void main(String[] args) throws IOException, SQLException {
        final TextToDB textToDB = new TextToDB();
        textToDB.setInputLineReplacePattern("^\\s*[.|\\d]+[、|，|:]?")
                .setLineReplace("")
                .setTextInputStreamNotRegex("^[(时间:)|(公司：)|(面试问题:)|(---)].*|\\d+?");
        String path = "C:\\Users\\abliger\\Desktop\\0621(2).txt";
        final HashMap<Integer, String> questionMap = textToDB.getTextLineToMap(path, "utf-8");
        System.out.println(questionMap);

        final Connection connection = MyConnection.getConnection();
        final Statement statement = connection.createStatement();
        Integer count = 0;
        for (Map.Entry<Integer, String> integerStringEntry : questionMap.entrySet()) {
            final String question = integerStringEntry.getValue().trim();
            if (StringUtils.isEmpty(question)) {
                continue;
            } else {
                try {
                    statement.addBatch(ParseTableFiledToSql.parseTableFieldToSql(++count, question));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        statement.executeBatch();
    }
}
