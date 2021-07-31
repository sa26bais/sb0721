package main.java.rental;

import main.java.tools.ITool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalAgreementProcessor {

    public static final String INVALID_DISCOUNT_WARNING = "Discount percent invalid, must be between 0% and 100%";
    public static final String INVALID_DAYS_WARNING = "Rental days invalid, must be for 1 day or more";
    private static final String INVALID_TOOL = "Tool code that was provided was not valid";

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

    private List<String> validateRental(RentalAgreement rentalAgreement) {
        List<String> warnings = new ArrayList<>();

        ITool rentalTool = rentalAgreement.getTool();
        if (rentalTool == null) {
            // Would want to output some sort of message to the user at this point in the UI that the tool doesn't exist
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
