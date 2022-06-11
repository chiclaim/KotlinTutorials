package tutorials.lesson25;

import java.io.*;
import java.nio.file.Files;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class Main {

    private void printFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printFile2() {
        // try-with-resource
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copy() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream("D:\\imagetest\\desk.jpg");
            os = new FileOutputStream("E:\\desk1.jpg");
            byte[] buffer = new byte[1024];
            int readNum = 0;
            int a = 0;
            while ((readNum = is.read(buffer, 0, buffer.length)) != -1) {
                System.out.println(a++);
                os.write(buffer, 0, readNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
