import loklak.java.api.*;
class Test {
	public static void main(String args[]) throws Exception {
		Loklak l = new Loklak();
		//String r = l.aggregations("spacex", "2015-01-01", "", "mentions,hashtags", "2", "0");
		String r = l.settings();
		System.out.println(r);
	}
}
