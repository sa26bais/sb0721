package main.java.rental;

import main.java.tools.ITool;

import java.math.BigDecimal;

public class RentalAgreement {
    private ITool tool;
    private int days;
    private int discountPercentage;

    public RentalAgreement(ITool tool, int days, int discountPercentage) {
        this.tool = tool;
        this.days = days;
        this.discountPercentage = discountPercentage;
    }
}
