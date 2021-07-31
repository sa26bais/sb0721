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

    public String getRentalMessage() {
        // Check to see if there was a validation that failed
        if (warningMessage != null && !warningMessage.isEmpty()) {
            return warningMessage;
        }

        StringBuilder invoice = new StringBuilder();
        ITool tool = rentalAgreement.getTool();
        addInvoiceLine(invoice, "Tool code: ", tool.getToolCode());
        addInvoiceLine(invoice, "Tool type: ", tool.getDisplayName());
        addInvoiceLine(invoice, "Tool brand: ", tool.getManufacturer());
        addInvoiceLine(invoice, "Rental days: ", String.valueOf(rentalAgreement.getDays()));
        addInvoiceLine(invoice, "Check out date: ", formatDate(rentalAgreement.getCheckoutDate()));
        addInvoiceLine(invoice, "Due date: ", formatDate(rentalAgreement.getReturnDate()));
        addInvoiceLine(invoice, "Daily rental charge: ", formatMoney(tool.getDailyCharge()));
        addInvoiceLine(invoice, "Charge days: ", String.valueOf(chargeDays));
        addInvoiceLine(invoice, "Pre-discount charge: ", formatMoney(getPreDiscountCharge()));
        addInvoiceLine(invoice, "Discount percent : ", rentalAgreement.getDiscountPercentage() + "%");
        addInvoiceLine(invoice, "Discount amount: ", formatMoney(getDiscount()));
        addInvoiceLine(invoice, "Final charge: ", formatMoney(getFinalCharge()));
        return invoice.toString();
    }

    private void addInvoiceLine(StringBuilder stringBuilder, String label, String data) {
        stringBuilder.append(label).append(data).append(System.lineSeparator());
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
