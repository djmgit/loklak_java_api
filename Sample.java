import loklak.java.api.*;
import java.util.ArrayList;

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

    }
}