package nutrition.enumerator;

/**
 * Created by andrius on 3/27/17.
 */

public enum Category {

    VEGS_AND_FRUITS("DARŽOVĖS IR VAISIAI"),
    MILKS_EGGS("PIENO GAMINIAI IR KIAUŠINIAI"),
    BREADS_CONFECTIONERY("DUONOS GAMINIAI IR KONDITERIJA"),
    MEATS_FISHES("MĖSA, ŽUVYS IR KULINARIJA"),
    GROCERIES("BAKALĖJA");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
