import loklak.java.api.*;
import java.util.ArrayList;
class Test {
	public static void main(String args[]) throws Exception {
		Loklak l = new Loklak();
		//String r = l.aggregations("spacex", "2015-01-01", "", "mentions,hashtags", "2", "0");
        /*ArrayList<String> p = new ArrayList<String> ();
        p.add("Singapore");
		String r = l.geocode(p, true);
		System.out.println(r);*/

		String r = l.search("linux", "", "", "", "2");
		System.out.println(r);
	}
}
