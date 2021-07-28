package main.java.rental;

import main.java.tools.ITool;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ProcessedRentalResult {
    private final RentalAgreement rentalAgreement;
    private String warningMessage;
    private int chargeDays;

    public ProcessedRentalResult(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public void printAgreementToConsole() {
        if (warningMessage != null && !warningMessage.isEmpty()) {
            System.out.println(warningMessage);
            return;
        }

        ITool tool = rentalAgreement.getTool();
        System.out.println("Tool code: " + tool.getToolType()); //FIXME: Tool code
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getManufacturer());
        System.out.println("Rental days: " + rentalAgreement.getDays());
        System.out.println("Check out date: " + formatDate(rentalAgreement.getCheckoutDate()));
        System.out.println("Due date: " + formatDate(rentalAgreement.getReturnDate()));
        System.out.println("Daily rental charge: " + formatMoney(tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + formatMoney(getPreDiscountCharge()));
        System.out.println("Discount percent : " + rentalAgreement.getDiscountPercentage());
        System.out.println("Discount amount: " + formatMoney(getDiscount()));
        System.out.println("Final charge: " + formatMoney(getFinalCharge()));
    }

    private BigDecimal getPreDiscountCharge() {
        ITool tool = rentalAgreement.getTool();
        BigDecimal dailyCharge = tool.getDailyCharge();
        return dailyCharge.multiply(BigDecimal.valueOf(chargeDays));
    }

    public BigDecimal getDiscount() {
        int discountPercent = rentalAgreement.getDiscountPercentage();
        BigDecimal discountDecimal = BigDecimal.valueOf((double) discountPercent/100);
        return getPreDiscountCharge().multiply(discountDecimal);
    }

    private BigDecimal getFinalCharge() {
        return getPreDiscountCharge().subtract(getDiscount());
    }

    public static String formatDate(LocalDate localDate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateFormat.format(localDate);
    }

    public static String formatMoney(BigDecimal bigDecimal) {
        NumberFormat df = DecimalFormat.getCurrencyInstance(Locale.US);
        return df.format(bigDecimal);
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }
}
