package testing;

import model.Billionaire;
import model.Country;
import model.Indicator;
import model.Networking;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Alex on 12/12/2016.
 */
public class ModelTesting {

    @Test
    public void testCreateNetworkinSingleton() {
        Assert.assertNotNull(Networking.getInstance());
    }

    @Test
    public void testGettingCountries() {
        try {
            ArrayList<Country> countries = Networking.getInstance().getListOfCountries();
            Assert.assertNotNull(countries);
            Assert.assertTrue(!countries.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLastIndicatorForCountry() {
        ArrayList<String> countryCodes = new ArrayList<String>();
        countryCodes.add("RO");
        String indicatorCode = "FP.CPI.TOTL.ZG"; // Inflation
        try {
            HashMap<String, Indicator> result = Networking.getInstance().getLastIndicatorForCountries(countryCodes, indicatorCode);
            Indicator indicator = result.get("RO");

            Assert.assertTrue(indicator.getCode().equals(indicatorCode));
            Assert.assertTrue(result.keySet().contains("RO"));
            Assert.assertTrue(result.keySet().size() == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetIndicatorForRange() {
        try {
            ArrayList<String> countryCodes = new ArrayList<String>();
            countryCodes.add("RO");
            String indicatorCode = "FP.CPI.TOTL.ZG";
            HashMap<String, TreeMap<String, Indicator>> result = Networking.getInstance().getRangeOfIndicatorsForCountries(countryCodes, indicatorCode, "2000", "2015");

            Assert.assertTrue(result.keySet().size() == 1);
            Assert.assertTrue(result.keySet().contains("RO"));

            Assert.assertTrue(result.get("RO").keySet().size() == 16);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGettingBillionaires() {
        try {
            ArrayList<Billionaire> billionaires = Networking.getInstance().getBillionairesRange(20);

            Assert.assertTrue(!billionaires.isEmpty());
            Assert.assertTrue(billionaires.size() == 20);
            Assert.assertNotNull(billionaires.get(0));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
