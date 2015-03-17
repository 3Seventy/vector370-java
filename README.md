*******************
1. Introduction

Thank you for downloading the 3seventy.client.java.zip file from the 3Seventy website.

The zip file contains all the files and examples needed to build your own applications using Java 7.

This doc explains how to run examples under the "examples" folder.

*******************
2. Requirements

The primary 3Seventy client resides in the export folder as 3seventy.java.client-[VERSION].jar, you will
need to reference this file in your Java project.

In addition to the 3Seventy client jar, there are several additional 3rd party jar files you will need.
You can find these files in the contrib folder:

hk2-api-2.3.0-b10
hk2-locator-2.3.0-b10
hk2-utils-2.3.0-b10
javax.annotation-api-1.2
javax.inject-2.3.0-b10
jackson-all-1.9.0
javax.ws.rs-api-2.0.1
jersey-client-2.12
jersey-common
jersey-guava-2.12
log4j-api-2.0.2
log4j-core-2.0.2

***************
3. Building

1) Create a Java project and add all specified jar files and the 3seventy.java.client-[VERSION].jar file to the
   project build path.

2) Create a folder named resource under the project root and create config.properties file in it and supply the
   log in details and base URL.  Note that these examples will NOT run as is. 

Note that these examples will NOT run as is.  There are various parameters that you will need to fill in, so make
sure you read the source code for those spots you will need to adjust.

For more information please see: https://api.3seventy.com/docs/
