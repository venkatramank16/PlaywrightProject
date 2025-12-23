package base;



import java.sql.*;
import java.util.*;

public class DBManager {

    private Connection conn;

    public DBManager(String url, String user, String pass) {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> getTestData(String query) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    row.put(meta.getColumnName(i), rs.getString(i));
                }
                dataList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
