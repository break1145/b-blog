package com.bb.blog.entity.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnLabelUtil {

    /**
     * 将一个类中所有标记了 @ColumnLabel 的属性转换为 类似{
     *      "column": "id",
     *      "value": "ID"
     * },
     * 其中column为属性名，value为注解的值
     * 的格式。
     * @param clazz 传入一个待转换
     * @return 转换后的json，以List<Map<String, String>> 格式
     */
    public static List<Map<String, String>> buildColumnDefinition(Class<?> clazz) {
        List<Map<String, String>> columns = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            ColumnLabel label = field.getAnnotation(ColumnLabel.class);
            if (label != null) {
                Map<String, String> col = new HashMap<>();
                col.put("column", field.getName());
                col.put("value", label.value());
                columns.add(col);
            }
        }

        return columns;
    }
}
