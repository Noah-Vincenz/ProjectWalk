package model;

/**
 * Created by Alex on 09/12/2016.
 */
public class Billionaire {
    private String name;
    private String company;
    private String industry;
    private int rank;
    private double worthValue;

    public Billionaire(String name, String company, String industry, int rank, double worthValue) {
        this.name = name;
        this.company = company;
        this.industry = industry;
        this.rank = rank;
        this.worthValue = worthValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getWorthValue() {
        return worthValue;
    }

    public void setWorthValue(double worthValue) {
        this.worthValue = worthValue;
    }

    @Override
    public String toString() {
        return "Billionaire{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", industry='" + industry + '\'' +
                ", rank=" + rank +
                ", worthValue=" + worthValue +
                '}';
    }
}
