package test.java;

import main.java.rental.RentalAgreementProcessor;
import main.java.rental.RentalProcessor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class RentalProcessorTest {

    @Test
    public void testRentalForZeroDays() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.now(), 0, 0);
        assert result.contains(RentalAgreementProcessor.INVALID_DAYS_WARNING);
    }

    @Test
    public void testRentalForNegativeDays() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.now(), -5, 0);
        assert result.contains(RentalAgreementProcessor.INVALID_DAYS_WARNING);
    }

    @Test
    public void testRentalForInvalidDiscount() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.now(), 1, 150);
        assert result.contains(RentalAgreementProcessor.INVALID_DISCOUNT_WARNING);
    }

    @Test
    public void testRentalForNegativeDiscount() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.now(), 1, -50);
        assert result.contains(RentalAgreementProcessor.INVALID_DISCOUNT_WARNING);
    }
}