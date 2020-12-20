# Testing

This app has been tested with unit and itegrations tests using JUnit and mockito libraries. End-to-end testing has been performed manually.

## Unit and integration testing

### Application logic

All of the tests for application logic are in the logictests-package. The package contains test classes for UserLogic, ImageLogic and ChatLogic. UniversalLogic-class is not tested because it only contains setters and getters. 

Dependencies are simulated with Mock-objects. 

### DAO -classes

Each Dao has its test own class in daotests-package. Before each of the tests a new database is initialized and after each the database file is deleted. The testing does not effect the database otherwise used by the app.

### Test coverage


