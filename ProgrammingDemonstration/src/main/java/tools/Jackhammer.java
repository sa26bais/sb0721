package main.java.tools;

import java.math.BigDecimal;

public class Jackhammer extends ITool {

    @Override
    public ToolType getToolType() {
        return ToolType.JACKHAMMER;
    }

    @Override
    public BigDecimal dailyCharge() {
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
