package test.java.rental;

import main.java.rental.ProcessedRentalResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

class ProcessedRentalResultTest {

    @Test
    public void testMoneyFormatter() {
        String formattedMoney = ProcessedRentalResult.formatMoney(new BigDecimal("9999.99"));
        Assertions.assertEquals(formattedMoney, "$9,999.99");
    }

    @Test
    public void testDateFormatter() {
        String formattedDate = ProcessedRentalResult.formatDate(LocalDate.of(2021, Month.DECEMBER, 31));
        Assertions.assertEquals(formattedDate, "12/31/2021");
    }

    @Test
    public void testRoundingDown() {
        BigDecimal value = new BigDecimal("3.99");
        BigDecimal roundedResult = ProcessedRentalResult.getPercentOfValue(value, 75);
        Assertions.assertEquals(roundedResult, new BigDecimal("2.99"));
    }

    @Test
    public void testRoundingUp() {
        BigDecimal value = new BigDecimal("3.99");
        BigDecimal roundedResult = ProcessedRentalResult.getPercentOfValue(value, 25);
        Assertions.assertEquals(roundedResult, new BigDecimal("1.00"));
    }


    @Test
    public void testRoundingEven() {
        BigDecimal value = new BigDecimal("3.99");
        BigDecimal roundedResult = ProcessedRentalResult.getPercentOfValue(value, 50);
        Assertions.assertEquals(roundedResult, new BigDecimal("2.00"));
    }
}