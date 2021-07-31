package main.java.tools;

import java.math.BigDecimal;

public class Chainsaw extends ITool {

    @Override
    public String getDisplayName() {
        return "Chainsaw";
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
