package Noah;

/**
 * Created by Alex on 29/11/2016.
 */

/**
 * Wrapper class of a Data Set (Indicator + year)
 */

public class DataSet {
    String year;
    Indicator indicator;

    /**
     * Will construct an instance of Data Set with an year and an Indicator
     *
     * @param year      String of the year
     * @param indicator Indicator instance
     */

    public DataSet(String year, Indicator indicator) {
        this.year = year;
        this.indicator = indicator;
    }

    /**
     * Getter for the year
     *
     * @return String for the year
     */

    public String getYear() {
        return year;
    }

    /**
     * Getter for the indicator
     *
     * @return Indicator instance
     */

    public Indicator getIndicator() {
        return indicator;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "year='" + year + '\'' +
                ", indicator=" + indicator +
                '}';
    }
}
