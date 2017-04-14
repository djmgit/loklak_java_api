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

Name field is mandatory others are optional, empty strings can be used if they are not required.

### search API

This is the public search API for the scrapped twitter data

Method signature:

```
public String search(String query, String since, String until, String fromUser, String count) throws Exception {
```

Query structure:

```
loklak.search(<query_content>, <since_date>, <until_date>, <from_user>, <count>
```

Example with query term and count:

```
loklak.search('loklak', "", "", "", "2")
```
Example with since and until date:

```
loklak.search('loklak', "2015-01-01", "2017-01-01", "", "")
```

dates shound be in \<YYYY-MM-DD\> format

query field is mandatory, other fields are optional, empty string can be used in their place if they are not
required.

### suggest API

This returns a list of suggestion based on the query word and queries made to the server

Method signature:

```
public String suggest(String query, String count, String order, String orderBy, String since, String until)
	                                                    throws Exception {
```

Query Structure :

```
loklak.suggest(<query>, <count>, <order>, <orderBy>, <since>, <until>)
```
Example with only query:

```
loklak.suggest("fossasia", "", "", "", "", "");
```

Example with all parameters:

```
loklak.suggest("fossasia", "5", "desc", "retrieval_next", "2014-01-01", "now");
```

### aggregations API

Returns Aggregation of collected data in json format

Method signature:

```
public String aggregations(String query, String since, String until, String fields, String limit, String count)
 	                                        throws Exception {
```
Query structure:

```
loklak.aggregations(<query>, <since_date>, <until_date>, <fields>, <limit>, <count>);

```

Example query:

```
loklak.aggregations("fossasia", "", "", "hashtags,mentions", "10", "0");
```

### susi API

Returns a string containig response from susi in json format

Method Signature:

```
public String susi(String query) throws Exception {
```

Query structure:

```
loklak.susi(<query_term>);
```

Example:

```
loklak.susi("Hi")
```

### Parsing the response

The various methods in this library returns string containing response from the Loklak API sevice in json format.
Java does not have any inbuilt json parsing library.However there are several external java libraries to parse json.
One such library is json-simple-1.1.1.jar also present in this repo.
This library (and many others) can be used to parse the response returned by the library methods.
An example of parsing the json string response is provided in Sample.java

### Using Sample.java

Sample.java contains sample usage of all the library functions. It also contains an example showing how to parse the json
strings using json-simple-1.1.1.jar.

To compile Sample.java use:

```
bash build_sample.sh
```

To run Sample.class use:

```
bash run_sample.sh
```

### Contributing

Maven is used to package this project.In order to build this library, maven is required.
In ubuntu maven can be installed by the following:

```
sudo apt-get install maven
```
Once maven is installed the project can be built in the following way:

Go to the root of this directory and execute:

```
maven package
```

In order to see error trace use :

```
maven -X package
```

All the API method definitions are present in Loklak.java.
New methods can be added here.

The jar file is generated in the target directory.
