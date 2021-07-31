package main.java.rental;

import main.java.tools.ITool;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalAgreement {
    private ITool tool;
    private LocalDate checkoutDate;
    private int days;
    private int discountPercentage;

    public ITool getTool() {
        return tool;
    }

    public void setTool(ITool tool) {
        this.tool = tool;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getReturnDate() {
        return checkoutDate.plus(days, ChronoUnit.DAYS);
    }
}
