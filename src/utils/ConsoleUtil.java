package utils;

import java.util.Scanner;

public class ConsoleUtil {

    private static final Scanner scanner = new Scanner(System.in);


    public static void printHeader(String title) {
        System.out.println("\n=====================================");
        System.out.println("          " + title.toUpperCase());
        System.out.println("=====================================");
    }


    public static void printMainMenu() {
        printHeader("QUẢN LÝ KHÁCH SẠN");
        System.out.println("1. Quản lý Phòng");
        System.out.println("2. Quản lý Khách hàng");
        System.out.println("3. Quản lý Đặt phòng");
        System.out.println("4. Tìm kiếm & Sắp xếp");
        System.out.println("0. Thoát chương trình");
        System.out.print("Chọn chức năng: ");
    }


    public static void printRoomMenu() {
        printHeader("QUẢN LÝ PHÒNG");
        System.out.println("1. Thêm phòng mới");
        System.out.println("2. Sửa thông tin phòng");
        System.out.println("3. Xóa phòng");
        System.out.println("4. Xem danh sách phòng");
        System.out.println("5. Tìm phòng theo loại");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");
    }

    public static void printTableHeader(String... headers) {
        System.out.println("-------------------------------------------------------------");
        StringBuilder sb = new StringBuilder();
        for (String header : headers) {
            sb.append(String.format("%-15s", header));
        }
        System.out.println(sb.toString());
        System.out.println("-------------------------------------------------------------");
    }

    public static int readIntChoice(String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Không được để trống. " + prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
