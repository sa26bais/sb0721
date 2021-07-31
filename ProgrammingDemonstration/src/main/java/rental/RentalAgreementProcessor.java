package main.java.rental;

import main.java.tools.ITool;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RentalAgreementProcessor {

    public static final String INVALID_TOOL = "Tool code that was provided was not valid";
    public static final String INVALID_DISCOUNT_WARNING = "Discount percent invalid, must be between 0% and 100%";
    public static final String INVALID_DAYS_WARNING = "Rental days invalid, must be for 1 day or more";

    public ProcessedRentalResult processRentalAgreement(RentalAgreement rentalAgreement) {
        ProcessedRentalResult rentalResult = new ProcessedRentalResult(rentalAgreement);

        List<String> validationMessage = validateRental(rentalAgreement);

        if (validationMessage.isEmpty()) {
            int chargeDays = calculateChargeDays(rentalAgreement);
            rentalResult.setChargeDays(chargeDays);
        } else {
            rentalResult.setWarningMessage(validationMessage);
        }

        return rentalResult;
    }

    private int calculateChargeDays(RentalAgreement rentalAgreement) {
        int chargeDays = 0;
        int weekDays = 0;
        int weekendDays = 0;
        int holidays = 0;

        LocalDate checkoutDate = rentalAgreement.getCheckoutDate();
        LocalDate returnDate = rentalAgreement.getReturnDate();


        // To quiet inspections of changes; FIXME: remove
        weekDays++;
        weekendDays++;
        holidays++;
        // end code to mute warnings

        ITool tool = rentalAgreement.getTool();
        if (tool.isChargeWeekdays()) {
            chargeDays+=weekDays;
        }
        if (tool.isChargeWeekends()) {
            chargeDays+=weekendDays;
        }
        if (tool.isChargeHolidays()) {
            chargeDays+=holidays;
        }
        return chargeDays;
    }

    private boolean isWeekendDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static Set<LocalDate> getHolidaysForYear(int year) {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(getLaborDay(year));
        holidays.add(getObservedIndependenceDay(year));

        //Would adjust the holidays that are observed here as needed. Would likely also want to add configs to have
        //support for enabling/disabling holiday observance as needed.

        return holidays;
    }

    public static LocalDate getLaborDay(int year) {
        LocalDate firstDayOfSeptember = LocalDate.of(year, Month.SEPTEMBER, 1);

        DayOfWeek dayOfWeekOfFirst = firstDayOfSeptember.getDayOfWeek();
        if (dayOfWeekOfFirst == DayOfWeek.MONDAY) {
            return firstDayOfSeptember;
        }

        // Sunday is defined as the last day of the week in DayOfWeek. We calculate days to get to Sunday and then
        // add another day to get to Monday.
        int daysToNextMonday = DayOfWeek.SUNDAY.getValue() - dayOfWeekOfFirst.getValue() + 1;
        return firstDayOfSeptember.plusDays(daysToNextMonday);
    }

    public static LocalDate getObservedIndependenceDay(int year) {
        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            // Observed on Friday if on Saturday
            return independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            // Observed on Monday if on Sunday
            return independenceDay.plusDays(1);
        } else {
            return independenceDay;
        }
    }

    private List<String> validateRental(RentalAgreement rentalAgreement) {
        List<String> warnings = new ArrayList<>();

        ITool rentalTool = rentalAgreement.getTool();
        if (rentalTool == null) {
            warnings.add(INVALID_TOOL);
        }
        if (rentalAgreement.getDiscountPercentage() > 100 || rentalAgreement.getDiscountPercentage() < 0) {
            warnings.add(INVALID_DISCOUNT_WARNING);
        }
        if (rentalAgreement.getDays() < 1) {
            warnings.add(INVALID_DAYS_WARNING);
        }

        return warnings;
    }
}
