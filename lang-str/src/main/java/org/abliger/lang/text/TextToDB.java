package org.abliger.lang.text;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TextToDB {
    public static void getTextStreamByNIO(String path, String enCoder) {
        try (FileChannel open = FileChannel.open(Paths.get(path), new StandardOpenOption[]{StandardOpenOption.READ})) {
            final MappedByteBuffer map = open.map(MapMode.READ_ONLY, 0, open.size());
            System.out.println(map.toString());
            final CharBuffer decode = Charset.forName(enCoder).decode(map);
            final String str = decode.toString();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用正则表达式，读入文件按行进行正则过滤,匹配成功不进行存储
     */
    private String textInputStreamNotRegex;
    /**
     * 使用正则表达式，对读入进来的行，进行匹配，匹配成功，进行处理替换
     */
    private String inputLineReplacePattern;
    /**
     * 替换的内容
     */
    private String lineReplace;


    public HashMap<Integer, String> getTextLineToMap(String path, String enCoder) throws IOException {
        HashMap<Integer, String> stringHashMapap = null;
        BufferedReader br=null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), enCoder));
            String next = br.readLine();
            Integer count = 0;
            stringHashMapap = new HashMap<>();
            while (next != null) {
                count++;
                Pattern p = Pattern.compile(textInputStreamNotRegex);
                Matcher m = p.matcher(next);
                if (!m.matches()) {
                    String s = Pattern.compile(inputLineReplacePattern)
                            .matcher(next).replaceAll(lineReplace).trim();
                    stringHashMapap.put(count, s);
                }
                next = br.readLine();
            }
        } finally {
            br.close();
        }
        return stringHashMapap;
    }
}
