package org.abliger.lang.reflection.db;

import java.io.File;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */

public class Files {
    public static String[] fileName(String packagePath){
        final File file = new File(packagePath);
        return file.list();
    }
}
