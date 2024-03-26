package application;

public enum MenuOption {
    LIST_MAGES("m", "List all mages"),
    LIST_TOWERS("t", "List all towers"),
    ADD_MAGE("am", "Add mage"),
    ADD_TOWER("at", "Add tower"),
    REMOVE_MAGE("rm", "Remove mage"),
    REMOVE_TOWER("rt", "Remove tower"),
    PRINT_TOWERS_WITH_HEIGHT_GREATER_THAN("p", "Print towers with height greater than"),
    QUIT("q", "Quit");

    private final String code;
    private final String description;

    MenuOption(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption getByCode(String code) {
        for (MenuOption option : MenuOption.values()) {
            if (option.code.equals(code)) {
                return option;
            }
        }
        return null;
    }
}
