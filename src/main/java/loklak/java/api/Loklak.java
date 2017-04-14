package loklak.java.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class Loklak {
	/* These fields are for the Loklak object

	It also contains methods that can be used to access
	the Loklak API services

	*/

    private String baseUrl = "";
    private String susiUrl = "https://api.asksusi.com/susi/chat.json";

    private String name = "";
    private String followers = "";
    private String following = "";
    private String query = "";
    private String since = "";
    private String until = "";
    private String source = "";
    private String count = "";
    private String fromUser = "";
    private String limit = "";
    private String action = "";
    private String order = "";
    private String orderBy = "";
    private String fields;

    private final String USER_AGENT = "Mozilla/5.0";

	public Loklak() {
		/* Sets default baseUrl
		*/
		
		this.baseUrl = "http://api.loklak.org/";
	}

	public Loklak(String baseUrl) {
		/* Sets user defined baseUrl
		*/

		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		/* Returns baseUrl
        */
		
		return this.baseUrl;
	}

	public String buildReponse(HttpURLConnection con) throws IOException {
		/* Utility function: Parses the HTTP response and 
		   builds the output string
		*/

        BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public String search(String query, String since, String until, String fromUser, String count) throws Exception {
		/* Handle the searching

		   Args:
		       query (String):        Search term
		       since (String):        Only messages after the date (including the date), <date>=yyyy-MM-dd or yyyy-MM-dd_HH:mm.
		       until (String):        Only messages before the date (excluding the date), <date>=yyyy-MM-dd or yyyy-MM-dd_HH:mm.
		       fromUser (String):     Only messages published by the user.
		       count (String):        The wanted number of results.

		   Returns:
		       String:  Search results from API in json format    
		*/
		
		String apiUrl = "api/search.json?";
		this.query = query;
		this.since = since;
		this.until = until;
		this.fromUser = fromUser;
		this.count = count;
		if (this.query != "") {
			apiUrl = apiUrl + "query=" + this.query;
			if (this.since != "") {
				apiUrl = apiUrl + " since:" + this.since;
			}
			if (this.until != "") {
				apiUrl = apiUrl + " until:" + this.until;
			}
			if (this.fromUser != "") {
				apiUrl = apiUrl + " from:" + this.fromUser;
			}
			if (this.count != "") {
				apiUrl = apiUrl + "&count=" + this.count;
			}

            String url = this.baseUrl + apiUrl;
            System.out.println(url);

            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            String response = "";
			if (responseCode == 200) {
                response = buildReponse(con);
			} else {
		        response = "{'error': 'Something went wrong, looks like the server is down.'}";
			}

			return response;

		} else {
			return "{'error': 'No Query string has been sent to query for an account'}";
		}
	}

	public String suggest(String query, String count, String order, String orderBy, String since, String until)
	                                                    throws Exception {
	    /* Retrieve a list of queries based on a given criteria

	    Args:
	        query (String):        To get a list of queries which match; to get all latest: leave query empty.
	        count (String):        Number of queries
	        order (String):        Possible values: desc, asc; default: desc.
	        orderBy (String):      A field name of the query index schema, i.e. retrieval_next or query_count.
	        since (String):        Left bound for a query time.
	        until (String):           Right bound for a query time.

	    Returns:
	        String:  A list of queries in the given order in json format
	    */

		String apiUrl = "api/suggest.json?";

		this.query = query;
		this.count = count;
		this.order = order;
		this.orderBy = orderBy;
		this.since = since;
		this.until = until;

		if (this.query != "") {
			apiUrl = apiUrl + "query=" + this.query + "&";
		}
		if (this.count != "") {
			apiUrl = apiUrl + "count=" + this.count + "&";
		}
		if (this.order != "") {
			apiUrl = apiUrl + "order=" + this.order + "&";
		}
		if (this.orderBy != "") {
			apiUrl = apiUrl + "orderBy=" + this.orderBy + "&";
		} 
		if (this.since != "") {
			apiUrl = apiUrl + "since=" + this.since + "&";
		}
		if (this.until != "") {
			apiUrl = apiUrl + "until=" + this.until + "&";
		}

		apiUrl = apiUrl.substring(0, apiUrl.length() - 1);
		String url = this.baseUrl + apiUrl;

		System.out.println(url);

		URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        String response = "";
		if (responseCode == 200) {
            response = buildReponse(con);
		} else {
	        response = "{'error': 'Something went wrong, looks like the server is down.'}";
		}

		return response;
 	}

 	public String aggregations(String query, String since, String until, String fields, String limit, String count)
 	                                        throws Exception {
        /* Give the aggregations of the application.

        Args:
            query (String):    Query term.
            since (String):    Only messages after the date (including the date), <date>=yyyy-MM-dd or yyyy-MM-dd_HH:mm.
            until (String):    Only messages before the date (excluding the date), <date>=yyyy-MM-dd or yyyy-MM-dd_HH:mm.
            fields (String):   Aggregation fields for search facets, like "created_at,mentions".
            limit (String):    A limitation of number of facets for each aggregation.
            count (String):    The wanted number of results.

        Returns:
            String: Aggregations of the application in json format

        */
 		String apiUrl = "api/search.json?";

 		this.query = query;
 		this.since = since;
 		this.until = until;
 		this.fields = fields;
 		this.limit = limit == "" ? "6" : limit;
 		this.count = count == "" ? "0" : count;

 		if (query != "") {
 			apiUrl = apiUrl + "query=" + this.query;
            if (since != "") {
            	apiUrl = apiUrl + " since:" + this.since;
            }
            if (until != "") {
            	apiUrl = apiUrl + " until:" + this.until;
            }
            apiUrl = apiUrl + "&source=cache";
            apiUrl = apiUrl + "&count=" + this.count;
            if (fields != "") {
            	apiUrl = apiUrl + "&fields=" + this.fields;
            }
            apiUrl = apiUrl + "&limit=" + this.limit;

            String url = this.baseUrl + apiUrl;
            System.out.println(url);

            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            String response = "";
			if (responseCode == 200) {
                response = buildReponse(con);
			} else {
		        response = "{'error': 'Something went wrong, looks like the server is down.'}";
			}

			return response;
 		} else {
			return "{'error': 'No Query string has been sent to query for an account'}";
		}
 	}

 	public String status() throws Exception {
        /* Retrieve a json response about the status of the server.
        */

 		String apiUrl = "api/status.json";
 		
 		String url = this.baseUrl + apiUrl;
        System.out.println(url); 

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        String response = "";
		if (responseCode == 200) {
            response = buildReponse(con);
		} else {
	        response = "{'error': 'Something went wrong, looks like the server is down.'}";
		}

		return response;   
 	}

 	public String hello() throws Exception {
        /* Retrieve a json response about the basic status of the server.
        */

 		String apiUrl = "api/hello.json";
 		
 		String url = this.baseUrl + apiUrl;
        System.out.println(url); 

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        String response = "";
		if (responseCode == 200) {
            response = buildReponse(con);
		} else {
	        response = "{'error': 'Something went wrong, looks like the server is down.'}";
		}

		return response;   
 	}

 	public String peers() throws Exception {
         /*Retrieve the peers of a user.
         */

 		String apiUrl = "api/peers.json";
 		
 		String url = this.baseUrl + apiUrl;
        System.out.println(url); 

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        String response = "";
		if (responseCode == 200) {
            response = buildReponse(con);
		} else {
	        response = "{'error': 'Something went wrong, looks like the server is down.'}";
		}

		return response;   
 	}

 	public String user(String name, String followers, String following) throws Exception {
 		/*Retrieve Twitter user information.

        Args:
            name (String):         Twitter screen name of the user.
            followers (String):    Followers of the user.
            following (String):    Accounts the user is following.

        Returns:
            String: User information, including who they are following, and who follows them
                    in json format.

        */

 		String apiUrl = "api/user.json?";

 		this.name = name;
 		this.followers = followers;
 		this.following = following;

 		if (name != "") {
 			apiUrl = apiUrl + "screen_name=" + this.name;
 			if (followers != "") {
 				apiUrl = apiUrl + "&followers=" + this.followers;
 			}
 			if (following != "") {
 				apiUrl = apiUrl + "&following=" + this.following;
 			}

 			String url = this.baseUrl + apiUrl;
 			System.out.println(url);

 			URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            String response = "";
			if (responseCode == 200) {
                response = buildReponse(con);
			} else {
		        response = "{'error': 'Something went wrong, looks like the server is down.'}";
			}

			return response;
 		} else {
 			return "{'error': 'No user name given to query. Please check and try again'}";
 		}
 	}

 	public String settings() throws Exception {
 		/*Retrieve the settings of the application.

        Note:
            The API of this function has a restrictions
            which only localhost clients are granted.

        Returns:
            String: The settings of the application in json format

        */

 		String apiUrl = "api/settings.json";
 		
 		String url = this.baseUrl + apiUrl;
        System.out.println(url); 

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        String response = "";
		if (responseCode == 200) {
            response = buildReponse(con);
		} else {
	        response = "{'error': 'Something went wrong, looks like the server is down.'}";
		}

		return response;   
 	}

 	public String susi(String query) throws Exception {
 		/*Retrieve Susi query response.

        Args:
            query (String): Query term.

        Returns:
            String: Susi response in json format

        */

 		this.query = query;
 		if (this.query != "") {
 			String url = this.susiUrl + "?q=" +this.query;
 			System.out.println(url);

 			URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            String response = "";
			if (responseCode == 200) {
                response = buildReponse(con);
			} else {
		        response = "{'error': 'Looks like there is a problem in susi replying.'}";
			}

			return response;
 		} else {
 			return "{'error': 'Please ask susi something.'}";
 		}
 	}

 	public String geocode(ArrayList<String> places, boolean minified) throws Exception {
        /*Provide geocoding of place names to location coordinates.

        Args:
            minified (boolean):   Whether result should be minified or not
            places (list):        A list of place names.

        Returns:
            String: The geocoding results based on the given place(s) name in json format

        */

 		String apiUrl = "api/geocode.json?";

        String minifiedString = minified == true ? "true" : "false";

 		if (places != null) {
 			String placeList = "";
            for (String place:places) {
            	placeList = placeList + "'" + place + "',";
            }
            placeList = "[" + placeList + "]";

            String data = "{'places':" + placeList + "}";
            apiUrl = apiUrl + "data=" + data;

            apiUrl = apiUrl + "&minified=" + minifiedString;
            
            String url = this.baseUrl + apiUrl;
            System.out.println(url);

            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            String response = "";
			if (responseCode == 200) {
                response = buildReponse(con);
			} else {
		        response = "{'error': 'Something went wrong, looks like the server is down.'}";
			}

			return response;
 		} else {
 			return "{'error': 'Please specify places.'}";
 		}
 	}
}