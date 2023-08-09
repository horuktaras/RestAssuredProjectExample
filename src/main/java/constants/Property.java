package constants;

public enum Property {

    BASE_URL("base.url", "https://waarkoop-server.herokuapp.com/api/v1/search/demo/"),
    RETRIES_NUM("retries", "3"),
    EXTENT_REPORT("report.output", "target/test-output/extent-report.html");

    private final String propertyName;
    private final String defaultValue;

    Property(String propertyName, String defaultValue) {
        this.propertyName = propertyName;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return propertyName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}