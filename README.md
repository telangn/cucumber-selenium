
  # WebServices: Front-End GUI QA Automation 

This is a Cucumber - Selenium Framework

## Getting Started

```
git clone https://github.com/telangn/cucumber-selenium.git
```

### Prerequisites

Internet Connectivity

### Installing

From the directory of the POM.xml :

```
mvn clean install
```
Or..

```
mvn verify
```

## Running the tests

To run with a specific browser or on Selenium Grid, use the following arguments:

```
-Dbrowser="chrome"
```
```
-Dbrowser="firefox"
```
```
-Dbrowser="grid"
```

If nothing is chosen, chrome is default.

To run with Mobile Emulation, use the following arguments:

```
-Dmobile="android"
```
```
-Dmobile="iphone"
```

To run with Headless, use the following arguments:

```
-Dheadless="on"
```

### Parallel Tests

To run parallel tests, add additional runner classes and increase the fork count within the POM.xml file, as needed.


## Coding Style / Design Patterns

Dependency Injection - Handled with Cucumber-PicoContainer

CukeLogs - Implemented by Encapsulation of Java Logger class

Settings - Implemented with Enum Class

WebDriver - Implemented with Factory Pattern

Page Object Model

## Deployment

This project is ready to deploy on Jenkins. 

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Cucumber](https://cucumber.io/) - Framework
* [Selenium](https://www.seleniumhq.org/) - WebDriver


## Contributing

N/A

## Versioning

N/A

## Authors

* **Ninad Telang** - *Initial work* - [Ninad](https://github.com/telangn)


## License

N/A

## Acknowledgments

* God and our Lord Jesus Christ


  
