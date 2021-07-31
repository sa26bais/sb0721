package test.java.rental;

import main.java.rental.RentalAgreementProcessor;
import main.java.rental.RentalProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class RentalProcessorTest {

    public static final String VALID_TOOL_CODE = "JAKR";


    @Test
    public void testInvalidToolCode() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("", LocalDate.now(), 1, 0);
        Assertions.assertEquals(result, RentalAgreementProcessor.INVALID_TOOL);
    }

    @Test
    public void testRentalForZeroDays() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest(VALID_TOOL_CODE, LocalDate.now(), 0, 0);
        Assertions.assertEquals(result, RentalAgreementProcessor.INVALID_DAYS_WARNING);
    }

    @Test
    public void testRentalForNegativeDays() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest(VALID_TOOL_CODE, LocalDate.now(), -5, 0);
        Assertions.assertEquals(result, RentalAgreementProcessor.INVALID_DAYS_WARNING);
    }

    @Test
    public void testRentalForInvalidDiscount() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest(VALID_TOOL_CODE, LocalDate.now(), 1, 150);
        Assertions.assertEquals(result, RentalAgreementProcessor.INVALID_DISCOUNT_WARNING);
    }

    @Test
    public void testRentalForNegativeDiscount() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest(VALID_TOOL_CODE, LocalDate.now(), 1, -50);
        Assertions.assertEquals(result, RentalAgreementProcessor.INVALID_DISCOUNT_WARNING);
    }

    @Test
    public void testRentalWithMultipleWarnings() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("", LocalDate.now(), -3, -50);
        Assertions.assertTrue(result.contains(RentalAgreementProcessor.INVALID_TOOL));
        Assertions.assertTrue(result.contains(RentalAgreementProcessor.INVALID_DISCOUNT_WARNING));
        Assertions.assertTrue(result.contains(RentalAgreementProcessor.INVALID_DAYS_WARNING));
    }
}