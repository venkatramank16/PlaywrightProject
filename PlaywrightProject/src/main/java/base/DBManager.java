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


    public  void uploadBulkExcelIntoDB(List<HashMap<String,String>> excelData,String query,String[] columnList){


        try{
            PreparedStatement ps = conn.prepareStatement(query);

            for(HashMap<String,String> m:excelData){
                int i=1;
                for (Map.Entry<String, String> entry : m.entrySet()) {

                    ps.setString(i,entry.getValue());
                    i++;
                }
ps.executeUpdate();
            }
            System.out.println("Data loaded successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
