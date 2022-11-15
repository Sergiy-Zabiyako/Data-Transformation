import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String str;
        byte[] arr;
        String patch = "C:\\Users\\sergi\\---Java---\\-Example_Intellij_Ultimate-\\splitBlock\\src\\Data.txt";
        String patchOut = "C:\\Users\\sergi\\---Java---\\-Example_Intellij_Ultimate-\\splitBlock\\src\\New_Data.txt";
        try {
            //читаем исходный файл и записываем в строку
            try (FileInputStream fis = new FileInputStream(patch)) {
                int len = fis.available();
                arr = new byte[len];
                fis.read(arr);
            }
            String strFile = new String(arr);
            //применяем StringTokenizer т.к в исходном файле используется разное количество пробелов, новой строки,
            // возврата каретки и табуляции
            StringTokenizer result = new StringTokenizer(strFile);
            List<String> tokens = new ArrayList<>();
            while (result.hasMoreTokens()) {
                tokens.add(result.nextToken());
            }
            //создаем файл для записи обновленных данных с разделителем табуляции и новой строки
            try (FileOutputStream fos = new FileOutputStream(patchOut)) {
                for (int i = 0; i < tokens.size(); i += 65) {
                    for (int j = 0; j <= 64; j++) {
                        str = tokens.get(i + j) + "\t";
                        byte[] buffer = str.getBytes();
                        fos.write(buffer, 0, buffer.length);
                        System.out.print(str);
                    }
                    fos.write("\n".getBytes());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}