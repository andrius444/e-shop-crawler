package nutrition.enumerator;

/**
 * Created by andrius on 3/26/17.
 */

public enum Nutritions {
    CARBS("Angliavandeniai"),
    FATS("Riebalai"),
    PROTEINS("Baltymai"),
    KCALS("Energinė vertė");

    private String value;

    Nutritions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
