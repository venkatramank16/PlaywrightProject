package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import config.ConfigReader;
import utils.ExcelUtil;

public class TestDataManager {

    private static Map<String, String> testData;

    public static void loadTestData(String sheetName) {
        testData = ExcelUtil.readExcelSheet(sheetName);
    }

    public static String get(String key) {
        if (testData == null) {
            throw new RuntimeException("Test data not loaded. Call loadTestData(sheetName) first.");
        }
        return testData.get(key);
    }
    
    public static List<Map<String, String>> getDBData(String query) {
        DBManager db = new DBManager(
                "jdbc:mysql://localhost:3306/sys",
                "root",
                ""
        );

        return db.getTestData(query);
    }
    
    public static void main(String[] args) {
	//	List<Map<String, String>> dbData = getDBData(ConfigReader.getProperty("query"));
	//	System.out.println(dbData.get(1));
    	
    	
	}
}
