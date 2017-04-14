import loklak.java.api.*;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

class Sample {
    public static void main(String args[]) throws Exception {
        Loklak loklak = new Loklak();
        String response = "";

        /* Loklak status API */

        response = loklak.status();
        System.out.println(response);
        System.out.println("\n \n");

        /* Loklak hello API */

        response = loklak.hello();
        System.out.println(response);
        System.out.println("\n \n");

        /* Loklak settings API */

        response = loklak.settings();
        System.out.println(response);
        System.out.println("\n \n");

        /* Loklak peers API */

        response = loklak.peers();
        System.out.println(response);
        System.out.println("\n \n");

        /* Loklak users API */

        String userResponse1 = loklak.user("djm_dev96", "", "");
        System.out.println(userResponse1);
        System.out.println("\n \n");

        String userResponse2 = loklak.user("djm_dev96", "5", "7");
        System.out.println(userResponse2);
        System.out.println("\n \n");

        /* Susi API */

        String susiResponse = loklak.susi("Hi");
        System.out.println(susiResponse);
        System.out.println("\n \n");

        /* Loklak geocode API */

        ArrayList<String> places = new ArrayList<String>();
        places.add("Singapore");
        places.add("Barcelona");

        String geocodeResponse1 = loklak.geocode(places, false);
        System.out.println(geocodeResponse1);
        System.out.println("\n \n");

        String geocodeResponse2 = loklak.geocode(places, true);
        System.out.println(geocodeResponse2);
        System.out.println("\n \n");

        /* Loklak search API */

        String searchResponse1 = loklak.search("linux", "", "", "", "");
        System.out.println(searchResponse1);
        System.out.println("\n \n");

        String searchResponse2 = loklak.search("linux", "2016-06-01", "2017-03-34", "", "");
        System.out.println(searchResponse2);
        System.out.println("\n \n");

        String searchResponse3 = loklak.search("linux", "", "", "", "5");
        System.out.println(searchResponse3);
        System.out.println("\n \n");

        /*String searchResponse4 = loklak.search("fossasia", "", "", "fossasia", "5");
        System.out.println(searchResponse4);
        System.out.println("\n \n");*/

        /* Loklak suggest API */

        String suggestResponse1 = loklak.suggest("fossasia", "", "", "", "", "");
        System.out.println(suggestResponse1);
        System.out.println("\n \n");

        String suggestResponse2 = loklak.suggest("fossasia", "5", "", "", "", "");
        System.out.println(suggestResponse2);
        System.out.println("\n \n");

        String suggestResponse3 = loklak.suggest("fossasia", "5", "desc", "", "", "");
        System.out.println(suggestResponse3);
        System.out.println("\n \n");

        String suggestResponse4 = loklak.suggest("fossasia", "5", "desc", "retrieval_next", "2014-01-01", "now");
        System.out.println(suggestResponse4);
        System.out.println("\n \n");

        /* Loklak aggregation API */

        String aggregationsResponse = loklak.aggregations("fossasia", "", "", "hashtags,mentions", "10", "0");
        System.out.println(aggregationsResponse);
        System.out.println("\n \n");
        
        /* Parsing json data using simple json java library */
        
        String searchResponse = loklak.search("linux", "", "", "", "2");

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(searchResponse);
        JSONObject jsonObj = (JSONObject)obj;
        System.out.println(jsonObj);
        System.out.println("\n \n");

        JSONArray statuses = (JSONArray)jsonObj.get("statuses");
        for(int i=0; i<2; i++) {
            JSONObject jObj = (JSONObject)statuses.get(i);
            String text = (String)jObj.get("text");
            System.out.println("Tweet #" + (i + 1) + ": " + text);
        }

    }
}