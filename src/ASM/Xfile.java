package ASM;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Xfile {

    public static void writeToFile(String path, ArrayList<SinhVienReport> data) {
        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Ghi dữ liệu từ ArrayList vào tệp tin
            for (SinhVienReport report : data) {
                String line = report.toString(); // Chuyển đổi đối tượng thành chuỗi
                bufferedWriter.write(line);
                bufferedWriter.newLine(); // Thêm dòng mới sau mỗi phần tử
            }

            bufferedWriter.close();
            writer.close();
            System.out.println("Ghi file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
