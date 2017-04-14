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
cd loklak_java_example
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
javac -cp .:loklak-java-api-1.0.jar LoklakQuickStart.java
```

Now execute the class file using the following:

```
java -cp .:loklak-java-api-1.0.jar LoklakQuickStart
```

## API Usage and Documentation

In order to use this library first it has to be included in the class path.
For example, let us consider that the name of the file where we want to use this library is LoklakTweet.java

So in order to compile the java file we need to execute the following:

```
javac -cp .:</path/to/loklak-java-api-1.0.jar> LoklakTweet.java
```
Then we can run the class file using the following:

```
java -cp .:</path/to/loklak-java-api-1.0.jar> LoklakTweet
```

To acces the library and its methods first we need to import it :

```
import loklak.java.api.*;
```
Then we need to instantiate a new Loklak object:

```
Loklak loklak = new Loklak();

```

This will use the default base url : http://api.loklak.org/

In order to use your own loklak instance you can do the following:

```
Loklak loklak = new Loklak("<http://my_loklak_instance_name/");
```

Once the loklak object is created its various methods can be used

### status API

This returns a status of the server. It can be used as follows:

```
loklak.status()
```
### hello API

Checks if server is responding properly and is online

```
loklak.hello()
```

### settings API

Returns a String containing settings of the server in json format

```
loklak.settings()
```

### peers API

Returns a String containg list of loklak peers in json format

```
loklak.peers()
```

### user API

What this can do :

- Fetch the details of one user
- Fetch the details of the user along with number of their followes and following
- Fetch only the followers / following of a particular user

Method signature :

```
public String user(String name, String followers, String following) throws Exception {
```

Query Structure:

```
loklak.user(<name>, <followers>, <following>)
```

For example

```
loklak.user("fossasia", "5", "5")
```

Name field is mandatory others are optional




