package Noah;

/**
 * Created by Alex on 28/11/2016.
 */

/**
 * Wrapper class for the indicator
 * Has a name, code and a value
 */

public class Indicator {
    private String name;
    private String code;
    private double value;

    /**
     * Constructor for an Indicator
     *
     * @param name  the indicator's name
     * @param code  the indicator's code
     * @param value the value of the indicator
     */

    public Indicator(String name, String code, double value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }

    /**
     * Getter for the name field
     *
     * @return String of the name
     */

    public String getName() {
        return name;
    }

    /**
     * Getter for the code field
     *
     * @return String for the code
     */

    public String getCode() {
        return code;
    }

    /**
     * Getter for the code field
     *
     * @return double for the value
     */

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
