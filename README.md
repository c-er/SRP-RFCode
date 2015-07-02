# Zone Manager Java API
This is an API written in Java that accesses a server running ZoneManager and fills objects with
information about Tags, Readers, Channels, and TagLinks. It is easy to determine any useful property
of any of these objects using this API.

## Requirements
Should work for any version of Java, but tested on Java 1.7 and 1.8.

## Installation
The API, its source, and all its dependencies are contained in the jarfile [SRP_RFCode_lib_src.jar](https://github.com/c-er/SRP-RFCode/blob/master/SRP_RFCode_lib_src.jar). If using an IDE,
simply link the jarfile as an external library. Otherwise, just add the jar to the CLASSPATH.

## Documentation
The documentation is located at [c-er.github.io/SRP-RFCode](c-er.github.io/SRP-RFCode). It is also contained in the jarfile [SRP_RFCode_doc.jar](https://github.com/c-er/SRP-RFCode/blob/master/SRP_RFCode_doc.jar) on the master branch.

## Credits
This API depends on the [org.json](www.json.org/java/index.html) library to parse the JSON output of the raw HTTP commands sent to the Zone Manager server.
The [org.json library](www.json.org/java/index.html) is packaged along with our API in the [SRP_RFCode_lib_src.jar](https://github.com/c-er/SRP-RFCode/blob/master/SRP_RFCode_lib_src.jar) file, so org.json does
not have to be installed seperately. Documentation and source for org.json is available at
[www.json.org/java/index.html](http://www.json.org/java/index.html)
