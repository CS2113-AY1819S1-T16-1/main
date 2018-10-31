package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.Module;

public class TypicalModules {

    public static final Module CS2113 = new ModuleBuilder().withModuleCode("CS2113")
            .withModuleName("Software Engineering & Object-Oriented Programming").build();
    public static final Module GEQ1000 = new ModuleBuilder().withModuleCode("GEQ1000")
            .withModuleName("Asking Questions").build();
    public static final Module MA1508E = new ModuleBuilder().withModuleCode("MA1508E")
            .withModuleName("Linear Algebra for Engineering").build();
    public static final Module CG1112 = new ModuleBuilder().withModuleCode("CG1112")
            .withModuleName("Engineering Principles and Practices II").build();
    public static final Module ST2334 = new ModuleBuilder().withModuleCode("ST2334")
            .withModuleName("Probability and Statistics").build();
    public static final Module GER1000X = new ModuleBuilder().withModuleCode("GER1000X")
            .withModuleName("Quantitative Reasoning").build();
    public static final Module CFG1010 = new ModuleBuilder().withModuleCode("CFG1010")
            .withModuleName("Roots & Wings").build();
    public static final Module CS2040C = new ModuleBuilder().withModuleCode("CS2040C")
            .withModuleName("Data Structures & Algorithms").build();

    private TypicalModules() { } // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2113, GEQ1000, MA1508E, CG1112, ST2334, GER1000X, CFG1010));
    }
}
