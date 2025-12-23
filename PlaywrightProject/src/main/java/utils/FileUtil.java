package utils;

import java.io.File;

public class FileUtil {

    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) return file.delete();
        return false;
    }
}
