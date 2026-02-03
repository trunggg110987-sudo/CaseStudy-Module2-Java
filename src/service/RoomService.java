package service;

import model.Room;
import utils.FileUtil;
import utils.IdGenerator;
import utils.InputValidator;
import exception.InvalidInputException;
import exception.BusinessException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private final List<Room> rooms = new ArrayList<>();
    private static final String FILE_PATH = "rooms.csv";

    public RoomService() {
        loadRoomsFromFile();
    }

    private void loadRoomsFromFile() {
        rooms.clear();
        List<String> lines = FileUtil.loadLines(FILE_PATH);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String roomNumber = parts[1].trim();
                    String type = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    boolean available = Boolean.parseBoolean(parts[4].trim());
                    rooms.add(new Room(id, roomNumber, type, price ));
                } catch (Exception e) {
                    System.err.println("Dòng lỗi trong file: " + line);
                }
            }
        }
    }

    void saveRoomsToFile() {
        List<String> lines = new ArrayList<>();
        for (Room r : rooms) {
            lines.add(r.getId() + "|" + r.getRoomNumber() + "|" + r.getType() + "|" +
                    r.getPrice() + "|" + r.isAvailable());
        }
        FileUtil.saveLines(FILE_PATH, lines);
    }

    public void addRoom(String roomNumber, String type, double price) throws InvalidInputException {
        InputValidator.validatePositiveDouble(price, "Giá phòng");
        int newId = IdGenerator.generateNextId(FILE_PATH);
        Room newRoom = new Room(newId, roomNumber, type, price);
        rooms.add(newRoom);
        saveRoomsToFile();
    }

    public void updateRoom(int id, String newType, double newPrice) throws BusinessException, InvalidInputException {
        Room room = findRoomById(id);
        InputValidator.validatePositiveDouble(newPrice, "Giá phòng");
        room.setType(newType);
        room.getPrice();
        saveRoomsToFile();
    }

    public void deleteRoom(int id) throws BusinessException {
        Room room = findRoomById(id);
        rooms.remove(room);
        saveRoomsToFile();
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public Room findRoomById(int id) throws BusinessException {
        return rooms.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BusinessException("Không tìm thấy phòng với ID: " + id));
    }

    public List<Room> searchByType(String type) {
        return rooms.stream()
                .filter(r -> r.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Room> sortByPrice(boolean ascending) {
        List<Room> sorted = new ArrayList<>(rooms);
        sorted.sort(Comparator.comparingDouble(Room::getPrice));
        if (!ascending) sorted.sort(Comparator.comparingDouble(Room::getPrice).reversed());
        return sorted;
    }

    public List<Room> getAvailableRooms() {
        return rooms.stream().filter(Room::isAvailable).collect(Collectors.toList());
    }
}