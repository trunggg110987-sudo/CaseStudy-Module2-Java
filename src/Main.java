import service.*;
import utils.*;
import exception.*;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static RoomService roomService;
    private static CustomerService customerService;
    private static BookingService bookingService;

    public static void main(String[] args) {
        roomService = new RoomService();
        customerService = new CustomerService();
        bookingService = new BookingService(roomService, customerService);

        ConsoleUtil.printHeader("CHÀO MỪNG ĐẾN VỚI HỆ THỐNG QUẢN LÝ KHÁCH SẠN");
        System.out.println("             Đã load dữ liệu thành công!\n");

        while (true) {
            ConsoleUtil.printMainMenu();
            int choice = ConsoleUtil.readIntChoice("Nhập lựa chọn: ");

            try {
                switch (choice) {
                    case 1 -> quanLyPhong();
                    case 2 -> quanLyKhachHang();
                    case 3 -> quanLyDatPhong();
                    case 4 -> timKiemVaSapXep();
                    case 0 -> {
                        System.out.println("Cảm ơn bạn đã sử dụng hệ thống. Tạm biệt!");
                        ConsoleUtil.closeScanner();
                        return;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
            System.out.println("\nNhấn Enter để tiếp tục...");
            sc.nextLine();
        }
    }

    private static void quanLyPhong() {
        while (true) {
            ConsoleUtil.printRoomMenu();
            int c = ConsoleUtil.readIntChoice("Nhập lựa chọn: ");
            try {
                switch (c) {
                    case 1 -> themPhong();
                    case 2 -> suaPhong();
                    case 3 -> xoaPhong();
                    case 4 -> xemDanhSachPhong();
                    case 5 -> timPhongTheoLoai();
                    case 0 -> { return; }
                    default -> System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private static void themPhong() throws InvalidInputException {
        String roomNumber = ConsoleUtil.readString("Nhập số phòng (vd: 101): ");
        String type = ConsoleUtil.readString("Nhập loại phòng (Standard/Deluxe/Suite): ");
        double price = Double.parseDouble(ConsoleUtil.readString("Nhập giá phòng/đêm: "));
        roomService.addRoom(roomNumber, type, price);
        System.out.println("✓ Thêm phòng thành công!");
    }

    private static void suaPhong() throws Exception {
        xemDanhSachPhong();
        int id = Integer.parseInt(ConsoleUtil.readString("Nhập ID phòng cần sửa: "));
        String newType = ConsoleUtil.readString("Nhập loại phòng mới: ");
        double newPrice = Double.parseDouble(ConsoleUtil.readString("Nhập giá mới: "));
        roomService.updateRoom(id, newType, newPrice);
        System.out.println("✓ Sửa phòng thành công!");
    }

    private static void xoaPhong() throws Exception {
        xemDanhSachPhong();
        int id = Integer.parseInt(ConsoleUtil.readString("Nhập ID phòng cần xóa: "));
        roomService.deleteRoom(id);
        System.out.println("✓ Xóa phòng thành công!");
    }

    private static void xemDanhSachPhong() {
        var rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("Chưa có phòng nào.");
            return;
        }
        ConsoleUtil.printTableHeader("ID", "Số phòng", "Loại", "Giá/đêm", "Trạng thái");
        for (var r : rooms) {
            System.out.printf("%-10d %-12s %-12s %,15.0f %-10s%n",
                    r.getId(), r.getRoomNumber(), r.getType(), r.getPrice(),
                    r.isAvailable() ? "Trống" : "Đã đặt");
        }
    }

    private static void timPhongTheoLoai() {
        String type = ConsoleUtil.readString("Nhập loại phòng cần tìm: ");
        var result = roomService.searchByType(type);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy phòng loại " + type);
        } else {
            ConsoleUtil.printTableHeader("ID", "Số phòng", "Giá/đêm", "Trạng thái");
            for (var r : result) {
                System.out.printf("%-10d %-12s %,15.0f %-10s%n",
                        r.getId(), r.getRoomNumber(), r.getPrice(),
                        r.isAvailable() ? "Trống" : "Đã đặt");
            }
        }
    }

    private static void quanLyKhachHang() {
        // Bạn tự viết tương tự như quanLyPhong (thêm, xem, tìm theo tên...)
        System.out.println("Chức năng quản lý khách hàng đang phát triển...");
    }

    private static void quanLyDatPhong() {
        while (true) {
            ConsoleUtil.printHeader("QUẢN LÝ ĐẶT PHÒNG");
            System.out.println("1. Tạo đặt phòng mới");
            System.out.println("2. Xem tất cả đặt phòng");
            System.out.println("0. Quay lại");
            int c = ConsoleUtil.readIntChoice("Chọn: ");
            if (c == 1) taoDatPhong();
            else if (c == 2) xemTatCaDatPhong();
            else if (c == 0) return;
        }
    }

    private static void taoDatPhong() {
        try {
            xemDanhSachPhong();
            int roomId = Integer.parseInt(ConsoleUtil.readString("Chọn ID phòng: "));
            customerService.getAllCustomers().forEach(System.out::println);
            int custId = Integer.parseInt(ConsoleUtil.readString("Chọn ID khách hàng: "));
            String checkIn = ConsoleUtil.readString("Ngày check-in (yyyy-MM-dd): ");
            String checkOut = ConsoleUtil.readString("Ngày check-out (yyyy-MM-dd): ");

            bookingService.createBooking(custId, roomId, checkIn, checkOut);
            System.out.println("✓ Đặt phòng thành công!");
        } catch (Exception e) {
            System.out.println("✗ Đặt phòng thất bại: " + e.getMessage());
        }
    }

    private static void xemTatCaDatPhong() {
        var list = bookingService.getAllBookings();
        if (list.isEmpty()) {
            System.out.println("Chưa có đặt phòng nào.");
            return;
        }
        ConsoleUtil.printTableHeader("ID", "Khách", "Phòng", "Check-in", "Check-out", "Tổng tiền", "Trạng thái");
        for (var b : list) {
            System.out.printf("%-5d %-20s %-10s %-12s %-12s %,15.0f %-10s%n",
                    b.getId(1),
                    b.getCustomer().getName(),
                    b.getRoom().getRoomNumber(),
                    b.getCheckInDate(),
                    b.getCheckOutDate(),
                    b.getTotalPrice(),
                    b.getStatus());
        }
    }

    private static void timKiemVaSapXep() {
        System.out.println("1. Sắp xếp phòng theo giá tăng dần");
        System.out.println("2. Sắp xếp phòng theo giá giảm dần");
        System.out.println("0. Quay lại");
        int c = ConsoleUtil.readIntChoice("Chọn: ");
        if (c == 1 || c == 2) {
            var sorted = roomService.sortByPrice(c == 1);
            ConsoleUtil.printTableHeader("ID", "Số phòng", "Loại", "Giá/đêm");
            for (var r : sorted) {
                System.out.printf("%-10d %-12s %-12s %,15.0f%n",
                        r.getId(), r.getRoomNumber(), r.getType(), r.getPrice());
            }
        }
    }
}