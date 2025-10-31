package org.example.lesson9.db;

import org.example.lesson9.annotations.Column;
import org.example.lesson9.annotations.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntityTableCreator {

    public static void createTable(Class<?> entityClass) {
        if (!entityClass.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Класс " + entityClass.getName() +
                    " не содержит аннотацию @Table");
        }

        Table tableAnn = entityClass.getAnnotation(Table.class);
        String tableName = tableAnn.title().isEmpty() ? entityClass.getSimpleName() : tableAnn.title();

        List<String> columnDefs = new ArrayList<>();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnn = field.getAnnotation(Column.class);

                String sqlType = mapJavaToSQLType(field.getType());

                String columnDef = field.getName() + " " + sqlType;

                columnDefs.add(columnDef);
            }
        }

        if (columnDefs.isEmpty()) {
            throw new IllegalArgumentException("Нет колонок для таблицы: " + entityClass.getName());
        }

        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS \"%s\" (%s);",
                tableName,
                String.join(", ", columnDefs)
        );

        executeDDL(sql);
    }

    private static String mapJavaToSQLType(Class<?> type) {
        return switch (type.getSimpleName()) {
            case "int", "Integer" -> "INTEGER";
            case "String" -> "VARCHAR(255)";
            case "boolean", "Boolean" -> "BOOLEAN";
            default -> "TEXT";
        };
    }

    private static void executeDDL(String sql) {
        try (Connection conn = DbManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("✅ Таблица успешно создана:\n" + sql);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения DDL: " + sql, e);
        }
    }
}
