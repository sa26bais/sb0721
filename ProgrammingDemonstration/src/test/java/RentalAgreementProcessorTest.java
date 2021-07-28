package test.java;

import main.java.rental.RentalAgreementProcessor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class RentalAgreementProcessorTest {

    @Test
    public void testDiscountCalculator() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        BigDecimal discount = rentalAgreementProcessor.calculateDiscount(new BigDecimal("1000.00"), 1);
        assert discount.compareTo(new BigDecimal("10.00")) == 0;
    }

    @Test
    public void testMoneyFormatter() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        String formattedMoney = rentalAgreementProcessor.formatMoney(new BigDecimal("9999.99"));
        assert formattedMoney.equals("$9,999.99");
    }

    @Test
    public void testDateFormatter() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        String formattedDate = rentalAgreementProcessor.formatDate(LocalDate.of(2021, Month.DECEMBER, 31));
        assert formattedDate.equals("12/31/2021");
    }
}
