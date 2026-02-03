package utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtil {

    public static List<String> loadLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {

            System.err.println("Không đọc được file: " + filePath);
        }
        return lines;
    }

    public static void saveLines(String filePath, List<String> lines) {
        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.err.println("Không ghi được file: " + filePath);
        }
    }

    public static void appendLine(String filePath, String line) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Lỗi append file: " + filePath);
        }
    }
}