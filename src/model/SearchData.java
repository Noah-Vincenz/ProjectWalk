package model;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;

/**
 * Created by Jaitens on 08/12/2016.
 */
public class SearchData {

    private static String appid = "ALJHQ8-X2R4Q758RU";
    private WAEngine engine;

    /**
     * Constructs the model to search on Wolfram Alpha
     * Sets the App Id to use for the API and configures the engine
     */

    public SearchData() {
        engine = new WAEngine();
        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(appid);
        engine.addFormat("plaintext");
        engine.addFormat("image");
    }

    /**
     * Method to query Wolfram Alpha
     * @param s - String to query
     * @return - WAQueryResult type of the result
     */

    public WAQueryResult getResult(String s) {
        WAQuery query = engine.createQuery();
        query.setInput(s);
        try {
            WAQueryResult queryResult = engine.performQuery(query);
            if(queryResult.isError()) {
                return queryResult;
            } else if (!queryResult.isSuccess()) {
                return queryResult;
            } else {
                return queryResult;
            }
        } catch (WAException e) {
            e.printStackTrace();
            return null;
        }
    }

}
