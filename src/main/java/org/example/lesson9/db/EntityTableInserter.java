package org.example.lesson9.db;

import org.example.lesson9.annotations.Column;
import org.example.lesson9.annotations.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class EntityTableInserter {
    public static void insert(Object entity) {

        Class<?> entityClass = entity.getClass();

        if (!entityClass.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Класс " + entityClass.getName() +
                    " не содержит аннотацию @Table");
        }

        Table tableAnn = entityClass.getAnnotation(Table.class);
        String tableName = tableAnn.title();

        List<String> columnNames = new ArrayList<>();
        List<Object> columnValues = new ArrayList<>();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {

                columnNames.add(field.getName());

                field.setAccessible(true);
                try {
                    columnValues.add(field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String placeholders = String.join(", ", columnNames.stream().map(x -> "?").toList());
        String columns = String.join(", ", columnNames);

        String sql = "INSERT INTO \"" + tableName + "\" (" + columns + ") VALUES (" + placeholders + ")";

        try (Connection conn = DbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < columnValues.size(); i++) {
                ps.setObject(i + 1, columnValues.get(i));
            }

            ps.executeUpdate();
            System.out.println("✅ Объект добавлен в таблицу:");
            System.out.println(sql);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка вставки объекта: " + sql, e);
        }
    }
}
