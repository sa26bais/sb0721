package main.java.rental;

import main.java.tools.ITool;

import java.time.LocalDate;

public class RentalAgreementProcessor {

    public ProcessedRentalResult processRentalAgreement(RentalAgreement rentalAgreement) {
        ProcessedRentalResult rentalResult = new ProcessedRentalResult(rentalAgreement);

        String validationMessage = validateRental(rentalAgreement, rentalResult);

        if (validationMessage != null && !validationMessage.isEmpty()) {
            rentalResult.setWarningMessage(validationMessage);
            return rentalResult;
        } else {
            int chargeDays = calculateChargeDays(rentalAgreement);
            rentalResult.setChargeDays(chargeDays);
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

    private String validateRental(RentalAgreement rentalAgreement, ProcessedRentalResult rentalResult) {
        if (rentalAgreement.getDiscountPercentage() > 100 || rentalAgreement.getDiscountPercentage() < 0) {
            return "Discount percent invalid, must be between 0% and 100%";
        } else if (rentalAgreement.getDays() < 1) {
            return "Rental days invalid, must be for 1 day or more";
        } else {
            return null;
        }
    }
}
