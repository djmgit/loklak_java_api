# loklak_java_api

This is a java library to access the Loklak API services.

## Quick Start Guide

In order to use this library, an object of the Loklak class has to be created. The Loklak object provides methods
to access the various Loklak API services.The loklak-java-api-1.0.jar has to be included in the class path to use
this library.

### A short example

Create a directory named loklak_java_example and make it the present working directory

```
mkdir loklak_java_example
```
Create a file named LoklakQuickStart.java and paste the following in it :

```
import loklak.java.api.*;
class Test {
	public static void main(String args[]) throws Exception {
		Loklak l = new Loklak();
		String r = l.search("linux", "", "", "", "2");
		System.out.println(r);
	}
}
```

Put a copy of loklak-java-api-1.0.jar (present in target directory) in this directory

Now compile the java file using the following:

```
javac -cp .:loklak-java-api-1.0.jar Test.java
```

Now execute the class file using the following:

```
java -cp .:loklak-java-api-1.0.jar Test
```






