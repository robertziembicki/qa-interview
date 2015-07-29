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
### Download selenium standalone server jar file
### Install maven libraries
All required maven libraries are defined in pom.xml file as dependencies.
Please run the following command to install maven libraries:
mvn install

### Run shell scripts
chromedriver-installer.sh
selenium-server-hub-start.sh
selenium-server-node-chrome-start.sh
selenium-server-node-firefox-start.sh

The above scripts are included in the folder named /scripts.
Please run them from the command line.

