package main.java.tools;

import java.math.BigDecimal;

public class Jackhammer extends ITool {

    @Override
    public String getDisplayName() {
        return "Jackhammer";
    }

    @Override
    public BigDecimal getDailyCharge() {
        return new BigDecimal("1.49");
    }

    @Override
    public boolean isChargeWeekends() {
        return false;
    }

    @Override
    public boolean isChargeHolidays() {
        return true;
    }
}
