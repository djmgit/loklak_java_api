import loklak.java.api.*;
class Test {
	public static void main(String args[]) throws Exception {
		Loklak l = new Loklak();
		String r = l.search("linux", "", "", "", "");
		System.out.println(r);
	}
}
