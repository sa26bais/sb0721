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
