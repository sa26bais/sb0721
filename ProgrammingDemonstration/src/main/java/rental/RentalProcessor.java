package main.java.rental;

import main.java.rental.dao.RentalToolDao;
import main.java.tools.ITool;

import java.time.LocalDate;

public class RentalProcessor {

    private final RentalToolDao rentalToolDao;

    // Would auto-wire the dao into this constructor in field using Spring
    public RentalProcessor() {
        rentalToolDao = new RentalToolDao();
    }

    public String processRentalRequest(String toolCode, LocalDate checkoutDate, int rentalDays, int discountPercent) {
        ITool rentalTool = rentalToolDao.getToolByToolCode(toolCode);
        if (rentalTool == null) {
            // Would want to output some sort of message to the user at this point in the UI that the tool doesn't exist
            return "Tool with tool code " + toolCode + " was unable to be found";
        }

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(rentalTool);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDays(rentalDays);
        rentalAgreement.setDiscountPercentage(discountPercent);

        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        ProcessedRentalResult processedRentalResult = rentalAgreementProcessor.processRentalAgreement(rentalAgreement);
        return processedRentalResult.getRentalMessage();
    }
}
