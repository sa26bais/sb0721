package main.java.rental;

import main.java.tools.ITool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ProcessedRentalResult {
    private final RentalAgreement rentalAgreement;
    private List<String> warningMessages;
    private int chargeDays;

    public ProcessedRentalResult(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public static String formatDate(LocalDate localDate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateFormat.format(localDate);
    }

    public static String formatMoney(BigDecimal bigDecimal) {
        NumberFormat df = DecimalFormat.getCurrencyInstance(Locale.US);
        return df.format(bigDecimal);
    }

    public static BigDecimal getPercentOfValue(BigDecimal value, int percent) {
        BigDecimal discountDecimal = BigDecimal.valueOf((double) percent / 100);
        return value.multiply(discountDecimal).setScale(2, RoundingMode.HALF_UP);
    }

    public String getRentalMessage() {
        StringBuilder invoice = new StringBuilder();

        // Check to see if there was a validation that failed
        if (warningMessages == null || warningMessages.isEmpty()) {
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
            addInvoiceLine(invoice, "Final charge: ", formatMoney(getFinalCharge()), false);
        } else {
            // In production code I would use StringUtils for this + all the null/empty checking done.
            boolean first = true;
            for (String message : warningMessages) {
                if (!first) {
                    invoice.append(System.lineSeparator());
                }
                invoice.append(message);
                first = false;
            }
        }
        return invoice.toString();
    }

    private void addInvoiceLine(StringBuilder stringBuilder, String label, String data) {
        addInvoiceLine(stringBuilder, label, data, true);
    }

    private void addInvoiceLine(StringBuilder stringBuilder, String label, String data, boolean addNewLine) {
        stringBuilder.append(label).append(data);
        if (addNewLine) {
            stringBuilder.append(System.lineSeparator());
        }
    }

    private BigDecimal getPreDiscountCharge() {
        ITool tool = rentalAgreement.getTool();
        BigDecimal dailyCharge = tool.getDailyCharge();
        return dailyCharge.multiply(BigDecimal.valueOf(chargeDays));
    }

    private BigDecimal getDiscount() {
        return getPercentOfValue(getPreDiscountCharge(), rentalAgreement.getDiscountPercentage());
    }

    private BigDecimal getFinalCharge() {
        return getPreDiscountCharge().subtract(getDiscount());
    }

    public void setWarningMessage(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }
}
