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

    private String baseUrl = "http://api.loklak.org/";
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

	public Loklak() {}

	public Loklak(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String buildReponse(HttpURLConnection con) throws IOException {
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
}