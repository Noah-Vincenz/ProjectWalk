package controllers;

/**
 * Created by Jaitens on 08/12/2016.
 */
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import model.SearchData;

public class SearchDataController {
    private static String appid = "ALJHQ8-X2R4Q758RU"; //Global Config Key for Wolfram API
    private SearchData model;


    /** 
     * Constructor to create SearchData Model to get wolfram
     */
    public SearchDataController() {
        model = new SearchData();
    }

    /**
     * Calls model to get data
     * @param String
     */
    public void getData(String s) {
        model.getResult(s);
    }
}
