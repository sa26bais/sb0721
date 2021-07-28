package main.java.tools;

import java.math.BigDecimal;

public class Ladder extends ITool {

    @Override
    public ToolType getToolType() {
        return ToolType.LADDER;
    }

    @Override
    public BigDecimal dailyCharge() {
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
