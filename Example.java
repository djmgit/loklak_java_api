import loklak.java.api.*;
import java.util.ArrayList;

class Example {
	public static void main(String args[]) throws Exception {
		Loklak loklak = new Loklak();

		/* Loklak status API */

		String response = loklak.status();
		System.out.prinln(response);

		
	}
}