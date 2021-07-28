package main.java.rental;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreementProcessor {

    public BigDecimal calculateDiscount(BigDecimal preDiscountTotal, int discountPercent) {
        BigDecimal discountDecimal = BigDecimal.valueOf((double) discountPercent/100);
        return preDiscountTotal.multiply(discountDecimal);
    }

    public String formatMoney(BigDecimal bigDecimal) {
        NumberFormat df = DecimalFormat.getCurrencyInstance(Locale.US);
        return df.format(bigDecimal);
    }

    public String formatDate(LocalDate localDate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateFormat.format(localDate);
    }
}
