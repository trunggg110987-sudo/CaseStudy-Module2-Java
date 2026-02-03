package service;

import model.Customer;
import utils.FileUtil;
import utils.IdGenerator;
import utils.InputValidator;
import exception.InvalidInputException;
import exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    private static final String FILE_PATH = "customers.csv";

    public CustomerService() {
        loadCustomersFromFile();
    }

    private void loadCustomersFromFile() {
        customers.clear();
        List<String> lines = FileUtil.loadLines(FILE_PATH);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String phone = parts[2].trim();
                    String email = parts[3].trim();
                    String idCard = parts[4].trim();
                    customers.add(new Customer(id, name, phone, email, idCard));
                } catch (Exception e) {
                    System.err.println("Dòng lỗi: " + line);
                }
            }
        }
    }

    private void saveCustomersToFile() {
        List<String> lines = new ArrayList<>();
        for (Customer c : customers) {
            lines.add(c.getId() + "|" + c.getName() + "|" + c.getPhone() + "|" +
                    c.getEmail() + "|" + c.getIdCard());
        }
        FileUtil.saveLines(FILE_PATH, lines);
    }

    public void addCustomer(String name, String phone, String email, String idCard) throws InvalidInputException {
        InputValidator.validateName(name);
        InputValidator.validatePhone(phone);
        InputValidator.validateEmail(email);
        int newId = IdGenerator.generateNextId(FILE_PATH);
        Customer newCustomer = new Customer(newId, name, phone, email, idCard);
        customers.add(newCustomer);
        saveCustomersToFile();
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public Customer findCustomerById(int id) throws BusinessException {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BusinessException("Không tìm thấy khách hàng với ID: " + id));
    }

    public List<Customer> searchByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}