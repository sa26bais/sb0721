package test.java.rental;

import main.java.rental.RentalAgreementProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class RentalAgreementProcessorTest {

    @Test
    public void testLaborDayOnSeptemberFirst() {
        LocalDate laborDay = RentalAgreementProcessor.getLaborDay(2014);
        Assertions.assertEquals(laborDay, LocalDate.of(2014, Month.SEPTEMBER, 1));
    }

    @Test
    public void testLaborDayNotOnSeptemberFirst() {
        LocalDate laborDay = RentalAgreementProcessor.getLaborDay(2021);
        Assertions.assertEquals(laborDay, LocalDate.of(2021, Month.SEPTEMBER, 6));
    }

    @Test
    public void testWeekdayIndependenceDay() {
        LocalDate observedIndependenceDay = RentalAgreementProcessor.getObservedIndependenceDay(2019);
        Assertions.assertEquals(observedIndependenceDay, LocalDate.of(2019, Month.JULY, 4));
    }

    @Test
    public void testSaturdayIndependenceDay() {
        LocalDate observedIndependenceDay = RentalAgreementProcessor.getObservedIndependenceDay(2020);
        Assertions.assertEquals(observedIndependenceDay, LocalDate.of(2020, Month.JULY, 3));
    }

    @Test
    public void testSundayIndependenceDay() {
        LocalDate observedIndependenceDay = RentalAgreementProcessor.getObservedIndependenceDay(2021);
        Assertions.assertEquals(observedIndependenceDay, LocalDate.of(2021, Month.JULY, 5));
    }
}