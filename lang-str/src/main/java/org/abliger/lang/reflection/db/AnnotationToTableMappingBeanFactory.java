package org.abliger.lang.reflection.db;


import org.abliger.lang.counst.SystemConst;
import org.abliger.lang.db.Table;
import org.abliger.lang.db.TableName;
import org.abliger.lang.entity.TableFiled;
import org.abliger.lang.entity.TableInfo;
import org.abliger.lang.util.ClassPathUtil;
import org.abliger.lang.util.Strings;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */

public class AnnotationToTableMappingBeanFactory {
    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }


    private String packagePath;
    private final Map<String, Object> map = new HashMap<>();
    private final List<Class<?>> classList = new ArrayList<>();

    public void setClassList(String packagePath) {
        String root = ClassPathUtil.getClassPath();
        String strs = packagePath.replace('.', '/');
        strs = root + strs;
        final String[] strings = Files.fileName(strs);
        System.out.println(Arrays.toString(strings));
        try {
            for (String string : strings) {
                final Class<?> aClass = Class.forName(packagePath + "." + string.subSequence(0, string.lastIndexOf(".")));
                classList.add(aClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<TableInfo> getBeanToDbTableMappingList() {
        List<TableInfo> list=new ArrayList<>();
        classList.forEach((clazz) -> {
            final TableInfo tableInfo = new TableInfo();
            try {
                final Annotation[] annotations = Optional.of(clazz.getAnnotations()).orElseThrow(() -> {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                });
                boolean falg = false;
                //获得tableName注解
                for (Annotation annotation : annotations) {
                    if (annotation instanceof TableName) {
                        falg=true;
                        //映射表明，如果注解value有值把value映射表明，否者把类名映射成表名
                        TableName tableName = (TableName) annotation;
                        if (!StringUtils.isEmpty(tableName.value())) {
                            tableInfo.setTableName(tableName.value());
                        } else {
                            final String s = Strings.underscoreName(clazz.getName(),"\\.");
                            tableInfo.setTableName(s.substring(s.lastIndexOf(".")+1,s.length()));
                        }
                        continue;
                    }
                }
                //如果falg为false，说明该类没有TableName注解，直接跳过
                final ArrayList<TableFiled> tableFileds = new ArrayList<>();
                if (falg) {
                    //获得映射表字段
                    final Field[] declaredFields = clazz.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        final TableFiled tableFiled = new TableFiled();
                        System.out.println(declaredField);
                        final Annotation[] annotations1 = declaredField.getAnnotations();
                        for (Annotation annotation : annotations1) {
                            System.out.println("\t" + annotation);
                            if(annotation instanceof Table){
                                final String name = declaredField.getName();
                                final String value = ((Table) annotation).value();
                                final boolean primaryKey = ((Table) annotation).isPrimaryKey();
                                if(StringUtils.isEmpty(value)){
                                    tableFiled.setTableFiled(name);
                                }else{
                                    tableFiled.setTableFiled(value);
                                }
                                final String description = ((Table) annotation).description();
                                tableFiled.setDescription(description);
                                tableFiled.setClazz(declaredField.getType());
                                tableFiled.setPrimaryKey(primaryKey);
                                tableFileds.add(tableFiled);
                                continue;
                            }
                        }
                    }
                }
                tableInfo.setTableFiled(tableFileds);
                list.add(tableInfo);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        return list;
    }
}
