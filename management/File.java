package management;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class File {
    public static void cleanDirectory(String pathname) {
        java.io.File directory = new java.io.File(pathname);

        java.io.File[] files = directory.listFiles();
        for (java.io.File file : files) {
             if (!file.delete()) {
                 System.out.println("Failed to delete " + file);
             }
        }
    }

    public static String read(String pathname) {
        java.io.File file = new java.io.File(pathname);
        FileInputStream fis = null;
        String content = "";
        try {
            fis = new FileInputStream(file);

            int readLine;
            while ((readLine = fis.read()) != -1) {
                content += (char) readLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return content;
    }

    public static void write(String pathname, String content) {
        java.io.File file = new java.io.File(pathname);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, true);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
