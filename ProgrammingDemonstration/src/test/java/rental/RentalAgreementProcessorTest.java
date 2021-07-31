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
        String expectedResult = "Tool code: LADW\n" +
                "Tool type: Ladder\n" +
                "Tool brand: Werner\n" +
                "Rental days: 3\n" +
                "Check out date: 07/02/2020\n" +
                "Due date: 07/05/2020\n" +
                "Daily rental charge: $1.99\n" +
                "Charge days: 2\n" +
                "Pre-discount charge: $3.98\n" +
                "Discount percent : 10%\n" +
                "Discount amount: $0.40\n" +
                "Final charge: $3.58";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase3() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("CHNS", LocalDate.of(2015, Month.JULY, 2), 5, 25);
        String expectedResult = "Tool code: CHNS\n" +
                "Tool type: Chainsaw\n" +
                "Tool brand: Stihl\n" +
                "Rental days: 5\n" +
                "Check out date: 07/02/2015\n" +
                "Due date: 07/07/2015\n" +
                "Daily rental charge: $1.49\n" +
                "Charge days: 3\n" +
                "Pre-discount charge: $4.47\n" +
                "Discount percent : 25%\n" +
                "Discount amount: $1.12\n" +
                "Final charge: $3.35";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase4() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKD", LocalDate.of(2015, Month.SEPTEMBER, 3), 6, 0);
        String expectedResult = "Tool code: JAKD\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: DeWalt\n" +
                "Rental days: 6\n" +
                "Check out date: 09/03/2015\n" +
                "Due date: 09/09/2015\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 3\n" +
                "Pre-discount charge: $8.97\n" +
                "Discount percent : 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $8.97";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase5() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.of(2015, Month.JULY, 2), 9, 0);
        String expectedResult = "Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Rental days: 9\n" +
                "Check out date: 07/02/2015\n" +
                "Due date: 07/11/2015\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 5\n" +
                "Pre-discount charge: $14.95\n" +
                "Discount percent : 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $14.95";

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void testCase6() {
        RentalProcessor rentalProcessor = new RentalProcessor();
        String result = rentalProcessor.processRentalRequest("JAKR", LocalDate.of(2020, Month.JULY, 2), 4, 50);
        String expectedResult = "Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Rental days: 4\n" +
                "Check out date: 07/02/2020\n" +
                "Due date: 07/06/2020\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 1\n" +
                "Pre-discount charge: $2.99\n" +
                "Discount percent : 50%\n" +
                "Discount amount: $1.50\n" +
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