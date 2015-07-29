# qa-interview
This proejct repository was created to implement a solution for QA Interview task.

## Development environment
* Java 1.7;
* Selenium WebDriver (Java implementation);
* Selenium Grid;
* TestNG;
* Maven2.

## Platform and web browsers
* Platform: MacOS;
* Browsers: Firefox (ver. 39.0) and Google Chrome (ver. 43.0.2357.134).

## Before running selenium tests
### Install maven libraries
All required maven libraries are defined in pom.xml file as dependencies.
Please run the following command to install maven libraries:

mvn install

### Download selenium standalone server jar file
Download selenium-server-standalone-2.46.0.jar from http://www.seleniumhq.org/download/ web page. 
Create /jars folder inside project folder and upload previously downladed .jar file into this folder.

### Run shell scripts
* chromedriver-installer.sh
* selenium-server-hub-start.sh
* selenium-server-node-chrome-start.sh
* selenium-server-node-firefox-start.sh

The above scripts are included in the folder named /scripts.
Please run them from the command line. Ensure that the scripts are run in the correct order (as listed).

