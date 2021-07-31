package main.java.tools;

import java.math.BigDecimal;

public abstract class ITool {
    private String toolCode;
    private String manufacturer;

    public abstract String getDisplayName();

    public abstract BigDecimal getDailyCharge();

    @SuppressWarnings("SameReturnValue")
    public boolean isChargeWeekdays() {
        // As of now there are no tools that are free for a weekday. Likely this always going to be the
        // case, but would confirm with BA before assuming that in production code.
        return true;
    }

    public abstract boolean isChargeWeekends();

    public abstract boolean isChargeHolidays();

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
