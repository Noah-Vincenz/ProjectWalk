package model;

/**
 * Created by Alex on 09/12/2016.
 */
public class Billionaire {
    private String name;
    private String company;
    private String industry;
    private double worthValue;
    private String imageURL;

    /**
     * Constructor for a billionaire
     * @param name - String of the name
     * @param company - String of the company
     * @param industry - String of the industry
     * @param worthValue - double of their worth
     */

    public Billionaire(String name, String company, String industry, double worthValue) {
        this.name = name;
        this.company = company;
        this.industry = industry;
        this.worthValue = worthValue;
    }

    /**
     * Constructor for a billionaire if it has an imageURL
     * @param name - String of the name
     * @param company - String of the company
     * @param industry - String of the industry
     * @param worthValue - double of their worth
     * @param imageURL - String for the imageURL
     */

    public Billionaire(String name, String company, String industry, double worthValue, String imageURL) {
        this.name = name;
        this.company = company;
        this.industry = industry;
        this.worthValue = worthValue;
        this.imageURL = imageURL;
    }

<<<<<<< HEAD
=======
        /**
     * Getter for Image Url field
     * @return - String of their image url
     */

    public String getImageURL() {
        return imageURL;
    }

>>>>>>> 6fd47f2fd1b8838f723248e9ab3f61a0c37f3c64
    /**
     * Getter for name field
     * @return - String of their name
     */

    public String getName() {
        return name;
    }

    /**
     * Setter for the name field
     * @param name - String of their name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the company
     * @return - String of the company
     */

    public String getCompany() {
        return company;
    }

    /**
     * Setter for the company
     * @param company - String of the company
     */

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Getter for the industry
     * @return - String of the industry
     */

    public String getIndustry() {
        return industry;
    }

    /**
     * Setter for the industry
     * @param industry - String of the industry
     */

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * Getter for the worth value
     * @return double of worth
     */

    public double getWorthValue() {
        return worthValue;
    }

    /**
     * Setter for the worth value
     * @param worthValue double of the worth
     */

    public void setWorthValue(double worthValue) {
        this.worthValue = worthValue;
    }

    /**
     * Overriding toString
     * @return String representation of the class
     */

    @Override
    public String toString() {
        return "Billionaire{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", industry='" + industry + '\'' +
                ", worthValue=" + worthValue +
                '}';
    }
}
