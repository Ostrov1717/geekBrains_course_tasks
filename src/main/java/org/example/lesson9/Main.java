package org.example.lesson9;

import org.example.lesson9.db.EntityTableCreator;
import org.example.lesson9.db.EntityTableInserter;
import org.example.lesson9.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {

    private static Connection connection;

    private static PreparedStatement preparedStatement;

    private static String createTableSQL = "CREATE TABLE IF NOT EXISTS %s (%s)";


    public static void main(String[] args) {

        EntityTableCreator.createTable(Client.class);

        Client client = new Client(1, "John Doe", 17, true, "123-456-7890");

        EntityTableInserter.insert(client);


//        Class clientClass = Client.class;
//        Field[] fields = clientClass.getDeclaredFields();
//
//        Table annotation = (Table) clientClass.getAnnotation(Table.class);
//        String tableName = annotation.title();
//
//        StringBuilder columnsDefinition = new StringBuilder();
//        for (Field o : fields) {
//            if (o.isAnnotationPresent(Column.class)) {
//                columnsDefinition.append(o.getName()).append(" ").append(mapJavaToSQLType(o.getType().getSimpleName())).append(", ");
//            }
//        }
//        if (columnsDefinition.length() > 0) {
//            columnsDefinition.setLength(columnsDefinition.length() - 2);
//        }
//        String columnsDefStr = columnsDefinition.toString();
//
//        try {
//            connect();
//            String sql = String.format(createTableSQL, tableName, columnsDefStr);
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            disconnect();
//        }
//
//
//    }
//
//    private static String mapJavaToSQLType(String javaType) {
//        return switch (javaType) {
//            case "int" -> "INTEGER";
//            case "String" -> "VARCHAR(255)";
//            case "boolean" -> "BOOLEAN";
//            default -> "TEXT";
//        };
//    }
//
//    private static void connect() throws SQLException {
//        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_db", "postgres", "1");
//    }
//
//    private static void disconnect() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
