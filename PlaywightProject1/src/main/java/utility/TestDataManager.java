package utility;

import java.util.Map;

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
}
