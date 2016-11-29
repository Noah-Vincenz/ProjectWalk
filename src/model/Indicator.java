package model;

/**
 * Created by alexclp on 28/11/2016.
 */

public class Indicator {
    private String name;
    private String code;
    private String value;

    public Indicator(String name, String code, String value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
