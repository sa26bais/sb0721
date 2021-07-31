package main.java.rental.dao;

import main.java.tools.Chainsaw;
import main.java.tools.ITool;
import main.java.tools.Jackhammer;
import main.java.tools.Ladder;

import java.util.ArrayList;
import java.util.List;

public class RentalToolDao {

    private static final List<ITool> EXAMPLE_TOOL_LIST;

    static {
        EXAMPLE_TOOL_LIST = new ArrayList<>();

        Ladder ladder = new Ladder();
        ladder.setToolCode("LADW");
        ladder.setManufacturer("Werner");
        EXAMPLE_TOOL_LIST.add(ladder);

        Chainsaw chainsaw = new Chainsaw();
        chainsaw.setToolCode("CHNS");
        chainsaw.setManufacturer("Stihl");
        EXAMPLE_TOOL_LIST.add(chainsaw);

        Jackhammer jackhammer1 = new Jackhammer();
        jackhammer1.setToolCode("JAKR");
        jackhammer1.setManufacturer("Ridgid");
        EXAMPLE_TOOL_LIST.add(jackhammer1);

        Jackhammer jackhammer2 = new Jackhammer();
        jackhammer2.setToolCode("JAKD");
        jackhammer2.setManufacturer("DeWalt");
        EXAMPLE_TOOL_LIST.add(jackhammer2);
    }

    public ITool getToolByToolCode(String toolCode) {
        if (toolCode == null || toolCode.isEmpty()) {
            return null;
        }

        return EXAMPLE_TOOL_LIST.stream()
                .filter(tool -> toolCode.equals(tool.getToolCode()))
                .findFirst().orElse(null);
    }
}
