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
    private String fields = "";
    private String fromUser = "";
    private String limit = "";
    private String action = "";

    private final String USER_AGENT = "Mozilla/5.0";

	public Loklak() {}

	public Loklak(String baseUrl) {
		this.baseUrl = baseUrl;
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
}