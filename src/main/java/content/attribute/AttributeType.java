package content.attribute;

public enum AttributeType {

    USER("user"),
    BISYCLE("bicycle"),
    RENTSERVICE("rentservice"),
    FAILURE_MESSAGE("failureMessage"),
    INVALID_STATE("invalidState"),
    LANGUAGE("language"),
    LOCALE("locale"),
    EN_LOCALE("en_US"),
    RU_LOCALE("ru_RU"),
    RU_LANGUAGE("RU"),
    LAST_PAGE("lastPage");

    
    private String attribute;

    private AttributeType() {
    }

    private AttributeType(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
