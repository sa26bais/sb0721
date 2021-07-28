package main.java.tools;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum ToolType {
    CHAINSAW("CHN", Chainsaw.class, "Chainsaw"),
    JACKHAMMER("JAK", Jackhammer.class, "Jackhammer"),
    LADDER("LAD", Ladder.class, "Ladder");

    private final String baseToolCode;
    private final Class<? extends ITool> toolClass;
    private final String displayName;

    ToolType(String baseToolCode, Class<? extends ITool> toolClass, String displayName) {
        this.baseToolCode = baseToolCode;
        this.toolClass = toolClass;
        this.displayName = displayName;
    }

    public String getBaseToolCode() {
        return baseToolCode;
    }

    public Class<? extends ITool> getToolClass() {
        return toolClass;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ToolType getToolByCode(String code) {
        return Arrays.stream(ToolType.values())
                .filter(toolType -> toolType.getBaseToolCode().equals(code))
                .findFirst().orElseThrow(() -> new InvalidParameterException("Code " + code + " d"));
    }

    public static void main(String[] args) {
        ToolType.CHAINSAW.getToolClass();
    }
}
