package main.java.tools;

import java.math.BigDecimal;

public class Ladder extends ITool {

    @Override
    public String getDisplayName() {
        return "Ladder";
    }

    @Override
    public BigDecimal getDailyCharge() {
        return new BigDecimal("1.99");
    }

    @Override
    public boolean isChargeWeekends() {
        return true;
    }

    @Override
    public boolean isChargeHolidays() {
        return false;
    }
}
