package test.java.rental;

import main.java.rental.RentalAgreement;
import main.java.rental.RentalAgreementProcessor;
import main.java.rental.RentalProcessor;
import main.java.tools.Chainsaw;
import main.java.tools.Jackhammer;
import main.java.tools.Ladder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class RentalAgreementProcessorTest {

    // Requested Test cases

    @Test
    public void testCase1() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.of(2015, Month.SEPTEMBER, 3), 5, 101);
        Assertions.assertEquals(result, RentalAgreementProcessor.INVALID_DISCOUNT_WARNING);
    }

    @Test
    public void testCase2() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("LADW", LocalDate.of(2020, Month.JULY, 2), 3, 10);
        String expectedResult = "Tool code: LADW" + System.lineSeparator() +
                "Tool type: Ladder" + System.lineSeparator() +
                "Tool brand: Werner" + System.lineSeparator() +
                "Rental days: 3" + System.lineSeparator() +
                "Check out date: 07/02/2020" + System.lineSeparator() +
                "Due date: 07/05/2020" + System.lineSeparator() +
                "Daily rental charge: $1.99" + System.lineSeparator() +
                "Charge days: 2" + System.lineSeparator() +
                "Pre-discount charge: $3.98" + System.lineSeparator() +
                "Discount percent : 10%" + System.lineSeparator() +
                "Discount amount: $0.40" + System.lineSeparator() +
                "Final charge: $3.58";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase3() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("CHNS", LocalDate.of(2015, Month.JULY, 2), 5, 25);
        String expectedResult = "Tool code: CHNS" + System.lineSeparator() +
                "Tool type: Chainsaw" + System.lineSeparator() +
                "Tool brand: Stihl" + System.lineSeparator() +
                "Rental days: 5" + System.lineSeparator() +
                "Check out date: 07/02/2015" + System.lineSeparator() +
                "Due date: 07/07/2015" + System.lineSeparator() +
                "Daily rental charge: $1.49" + System.lineSeparator() +
                "Charge days: 3" + System.lineSeparator() +
                "Pre-discount charge: $4.47" + System.lineSeparator() +
                "Discount percent : 25%" + System.lineSeparator() +
                "Discount amount: $1.12" + System.lineSeparator() +
                "Final charge: $3.35";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase4() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKD", LocalDate.of(2015, Month.SEPTEMBER, 3), 6, 0);
        String expectedResult = "Tool code: JAKD" + System.lineSeparator() +
                "Tool type: Jackhammer" + System.lineSeparator() +
                "Tool brand: DeWalt" + System.lineSeparator() +
                "Rental days: 6" + System.lineSeparator() +
                "Check out date: 09/03/2015" + System.lineSeparator() +
                "Due date: 09/09/2015" + System.lineSeparator() +
                "Daily rental charge: $2.99" + System.lineSeparator() +
                "Charge days: 3" + System.lineSeparator() +
                "Pre-discount charge: $8.97" + System.lineSeparator() +
                "Discount percent : 0%" + System.lineSeparator() +
                "Discount amount: $0.00" + System.lineSeparator() +
                "Final charge: $8.97";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase5() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.of(2015, Month.JULY, 2), 9, 0);
        String expectedResult = "Tool code: JAKR" + System.lineSeparator() +
                "Tool type: Jackhammer" + System.lineSeparator() +
                "Tool brand: Ridgid" + System.lineSeparator() +
                "Rental days: 9" + System.lineSeparator() +
                "Check out date: 07/02/2015" + System.lineSeparator() +
                "Due date: 07/11/2015" + System.lineSeparator() +
                "Daily rental charge: $2.99" + System.lineSeparator() +
                "Charge days: 5" + System.lineSeparator() +
                "Pre-discount charge: $14.95" + System.lineSeparator() +
                "Discount percent : 0%" + System.lineSeparator() +
                "Discount amount: $0.00" + System.lineSeparator() +
                "Final charge: $14.95";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase6() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.of(2020, Month.JULY, 2), 4, 50);
        String expectedResult = "Tool code: JAKR" + System.lineSeparator() +
                "Tool type: Jackhammer" + System.lineSeparator() +
                "Tool brand: Ridgid" + System.lineSeparator() +
                "Rental days: 4" + System.lineSeparator() +
                "Check out date: 07/02/2020" + System.lineSeparator() +
                "Due date: 07/06/2020" + System.lineSeparator() +
                "Daily rental charge: $2.99" + System.lineSeparator() +
                "Charge days: 1" + System.lineSeparator() +
                "Pre-discount charge: $2.99" + System.lineSeparator() +
                "Discount percent : 50%" + System.lineSeparator() +
                "Discount amount: $1.50" + System.lineSeparator() +
                "Final charge: $1.50";

        Assertions.assertEquals(result, expectedResult);
    }

    // Personal Test cases

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

    @Test
    public void testRentalWithoutWeekendsOrHolidays() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Chainsaw());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.JULY, 25));
        rentalAgreement.setDays(5);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 5);
    }

    @Test
    public void testRentalOverWeekendWithChargeWithoutHolidays() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Ladder());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.JULY, 29));
        rentalAgreement.setDays(4);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 4);
    }

    @Test
    public void testRentalOverWeekendWithoutChargeWithoutHolidays() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Chainsaw());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.JULY, 29));
        rentalAgreement.setDays(4);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 2);
    }

    @Test
    public void testRentalOverHolidayWithCharge() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Chainsaw());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.JULY, 1));
        rentalAgreement.setDays(4);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 2);
    }

    @Test
    public void testRentalOverHolidayWithoutCharge() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Jackhammer());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.JULY, 1));
        rentalAgreement.setDays(4);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 1);
    }

    @Test
    public void testRentalCrossingYearRentalWithWeekendCharge() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Ladder());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.DECEMBER, 30));
        rentalAgreement.setDays(5);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 5);
    }

    @Test
    public void testRentalCrossingYearRentalWithoutWeekendCharge() {
        RentalAgreementProcessor rentalAgreementProcessor = new RentalAgreementProcessor();
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(new Jackhammer());
        rentalAgreement.setCheckoutDate(LocalDate.of(2021, Month.DECEMBER, 30));
        rentalAgreement.setDays(5);
        int chargeDays = rentalAgreementProcessor.calculateChargeDays(rentalAgreement);
        Assertions.assertEquals(chargeDays, 3);
    }
}