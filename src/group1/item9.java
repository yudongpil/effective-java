package group1;

import java.io.*;

public class item9 {

    static final int BUFFER_SIZE = 100;

    static String firstLineOf(String path) throws IOException {
        // 만약 어플리케이션이 도는 기기에 문제가 생겨 readLine 메소드가 예외를 던지고
        // 같은 이유로 br.close()도 실패한다면, readLine 메소드가 남긴 예외가 남지 않는다.
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    static void copy(String src, String dst) throws IOException {
        // 자원이 두 개 이상 나오니 코드가 너무 복잡하다.
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);

            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    static String firstLineOfWithTryWithResources(String path) throws IOException {
        // try-with-resources도 catch문을 추가할 수 있다.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return "defaultVal";
        }
    }

    static void copyWithTryWithResources(String src, String dst) throws IOException {
        try (
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dst);
        ) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }
}
