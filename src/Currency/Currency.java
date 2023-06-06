package Currency;

public enum Currency {
    TRY("Turkish Lira"),
    EUR("Euro"),
    USD("US Dollar"),
    XAU("Gold");

    private final String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
