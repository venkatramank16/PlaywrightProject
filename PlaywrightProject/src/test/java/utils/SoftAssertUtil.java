package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertUtil {

    private static ThreadLocal<SoftAssert> softAssertThread = ThreadLocal.withInitial(SoftAssert::new);

    public static SoftAssert getSoftAssert() {
        return softAssertThread.get();
    }

    public static void assertAll() {
        softAssertThread.get().assertAll();
        softAssertThread.remove();
    }
}
