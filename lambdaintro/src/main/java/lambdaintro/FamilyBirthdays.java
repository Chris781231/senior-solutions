package lambdaintro;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public class FamilyBirthdays {

    List<LocalDate> birthdays;

    public FamilyBirthdays(LocalDate... birthdays) {
        if (birthdays == null) {
            throw new IllegalArgumentException("Invalid birthdays");
        }

        this.birthdays = List.of(birthdays);
    }

    public boolean isFamilyBirthday(TemporalAccessor date) {
        return birthdays.stream()
                .anyMatch(b ->
                        b.getMonthValue() == date.get(ChronoField.MONTH_OF_YEAR) &&
                                b.getDayOfMonth() == date.get(ChronoField.DAY_OF_MONTH));
    }

    public int nextFamilyBirthDay(TemporalAccessor date) {
        int year = date.get(ChronoField.YEAR);
        LocalDate actualDate = LocalDate.of(year, date.get(ChronoField.MONTH_OF_YEAR), date.get(ChronoField.DAY_OF_MONTH));

        int min = Integer.MAX_VALUE;
        for (LocalDate birthday : birthdays) {
            LocalDate nextBirthday = birthday.withYear(year);
            if (actualDate.isAfter(nextBirthday)) {
                nextBirthday = birthday.withYear(year + 1);
            }
            int diff = (int) ChronoUnit.DAYS.between(actualDate, nextBirthday);
            if (diff < min) {
                min = diff;
            }
        }

        return min;
    }
}
