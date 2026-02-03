package utils;



import exception.InvalidInputException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static LocalDate parseDate(String dateStr) throws InvalidInputException {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Ngày không hợp lệ. Định dạng phải là yyyy-MM-dd.");
        }
    }


    public static void validateDateString(String dateStr) throws InvalidInputException {
        if (!dateStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidInputException("Ngày phải theo định dạng yyyy-MM-dd.");
        }
        parseDate(dateStr);
    }


    public static void validateCheckInOut(LocalDate checkIn, LocalDate checkOut) throws InvalidInputException {
        if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)) {
            throw new InvalidInputException("Ngày check-in phải trước ngày check-out.");
        }
    }


    public static int calculateNights(LocalDate checkIn, LocalDate checkOut) {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public static boolean isDateRangeOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
