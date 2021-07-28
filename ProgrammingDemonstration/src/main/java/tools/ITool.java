package main.java.tools;

import java.math.BigDecimal;

public abstract class ITool {
    private String manufacturer;

    public abstract ToolType getToolType();

    public abstract BigDecimal dailyCharge();

    public boolean isChargeWeekdays() {
        // As of now there are no tools that are free for a weekday. Likely this always going to be the
        // case, but would confirm with BA before assuming that in production code.
        return true;
    }

    public abstract boolean isChargeWeekends();

    public abstract boolean isChargeHolidays();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
