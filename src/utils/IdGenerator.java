package utils;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class IdGenerator {


    public static int generateNextId(String filePath) {
        int maxId = 0;

        if (!Files.exists(Paths.get(filePath))) {
            return 1;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id > maxId) {
                            maxId = id;
                        }
                    } catch (NumberFormatException ignored) {

                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file để tạo ID: " + filePath);
        }

        return maxId + 1;
    }


    public static void resetIdFile(String filePath) {
        try {
            Files.writeString(Paths.get(filePath), "");
        } catch (IOException e) {
            System.err.println("Không reset được file: " + filePath);
        }
    }
}