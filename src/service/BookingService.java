package service;

import model.Booking;
import model.Customer;
import model.Room;
import utils.DateTimeUtil;
import utils.FileUtil;
import utils.IdGenerator;
import exception.BusinessException;
import exception.InvalidInputException;
import utils.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private final RoomService roomService;
    private final CustomerService customerService;
    private static final String FILE_PATH = "bookings.csv";

    public BookingService(RoomService roomService, CustomerService customerService) {
        this.roomService = roomService;
        this.customerService = customerService;
        loadBookingsFromFile();
    }

    private void loadBookingsFromFile() {
        bookings.clear();
        List<String> lines = FileUtil.loadLines(FILE_PATH);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    int customerId = Integer.parseInt(parts[1].trim());
                    int roomId = Integer.parseInt(parts[2].trim());
                    String checkIn = parts[3].trim();
                    String checkOut = parts[4].trim();
                    String status = parts[5].trim();
                    double totalPrice = Double.parseDouble(parts[6].trim());

                    Customer customer = customerService.findCustomerById(customerId);
                    Room room = roomService.findRoomById(roomId);

                    Booking booking = new Booking(id, customer, room, checkIn, checkOut);
                    booking.setStatus(status);
                    booking.setTotalPrice(totalPrice);
                    bookings.add(booking);
                } catch (Exception e) {
                    System.err.println("Dòng lỗi: " + line);
                }
            }
        }
    }

    private void saveBookingsToFile() {
        List<String> lines = new ArrayList<>();
        for (Booking b : bookings) {
            lines.add(b.getId(1) + "|" + b.getCustomer().getId() + "|" + b.getRoom().getId() + "|" +
                    b.getCheckInDate() + "|" + b.getCheckOutDate() + "|" + b.getStatus() + "|" + b.getTotalPrice());
        }
        FileUtil.saveLines(FILE_PATH, lines);
    }

    public void createBooking(int customerId, int roomId, String checkIn, String checkOut) throws InvalidInputException, BusinessException {
        InputValidator.validateDateFormat(checkIn);
        InputValidator.validateDateFormat(checkOut);
        LocalDate ci = DateTimeUtil.parseDate(checkIn);
        LocalDate co = DateTimeUtil.parseDate(checkOut);
        DateTimeUtil.validateCheckInOut(ci, co);

        Customer customer = customerService.findCustomerById(customerId);
        Room room = roomService.findRoomById(roomId);

        if (!room.isAvailable()) {
            throw new BusinessException("Phòng đã được đặt.");
        }

        // Kiểm tra conflict ngày
        for (Booking b : bookings) {
            if (b.getRoom().getId() == roomId) {
                LocalDate bCi = DateTimeUtil.parseDate(b.getCheckInDate());
                LocalDate bCo = DateTimeUtil.parseDate(b.getCheckOutDate());
                if (DateTimeUtil.isDateRangeOverlap(ci, co, bCi, bCo)) {
                    throw new BusinessException("Phòng đã được đặt trong khoảng thời gian này.");
                }
            }
        }

        int nights = DateTimeUtil.calculateNights(ci, co);
        double total = nights * room.getPrice();

        int newId = IdGenerator.generateNextId(FILE_PATH);
        Booking newBooking = new Booking(newId, customer, room, checkIn, checkOut);
        newBooking.setStatus("BOOKED");
        newBooking.setTotalPrice(total);

        bookings.add(newBooking);
        room.setAvailable(false);
        saveBookingsToFile();
        roomService.saveRoomsToFile();  // Lưu lại trạng thái phòng
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

}