/**
 * Created by Alex on 28/11/2016.
 */
package model;

/**
 * Wrapper class for a country
 */

public class Country {
    private String name;
    private String code;

    /**
     * Constructs an instance of a country with a name and a code
     * @param name String of the country's name
     * @param code String of the country's code
     */

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Getter for the name field
     * @return String of the name
     */

    public String getName() {
        return name;
    }

    /**
     * Getter for the code field
     * @return String for the code
     */

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
