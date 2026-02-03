package utils;

import exception.InvalidInputException;

public class InputValidator {

    public static void validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Tên không được để trống.");
        }
        if (!name.matches("^[\\p{L}\\s]+$")) {
            throw new InvalidInputException("Tên chỉ được chứa chữ cái và khoảng trắng.");
        }
    }

    public static void validatePhone(String phone) throws InvalidInputException {
        if (phone == null || !phone.matches("^0[3|5|7|8|9]\\d{8}$")) {
            throw new InvalidInputException("Số điện thoại không hợp lệ (10 số, bắt đầu bằng 0).");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if (email != null && !email.isEmpty() && !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidInputException("Email không hợp lệ.");
        }
    }

    public static void validatePositiveInt(int value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " phải là số nguyên dương.");
        }
    }

    public static void validatePositiveDouble(double value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " phải lớn hơn 0.");
        }
    }

    public static void validateDateFormat(String date) throws InvalidInputException {
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidInputException("Ngày phải theo định dạng yyyy-MM-dd.");
        }
    }
}
