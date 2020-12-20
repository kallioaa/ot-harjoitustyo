# Architecture

## Structure

The app is implementing a layered architecture with three layers meaning no dependencies to layers above.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)


## User interface

The user interface follows the Model-View-Controller model. It has a total of three views:

- log in
- creating a new user
- searching for images and sending messages (main view).

Views are implemented by FXML-files which can be found in *src/main/resources/ui*. All of the views have their own Controller which can be found in *imagechatter.gui*.

The controllers are connected to different logic classes. Controllers for Login and creating a new user use the UserLogic class. Controller for the main view uses the userLogic, imageLogic and chatLogic classes.


## Application logic

The application logic consists of the three above mentioned logic classes to increase the cohesiveness. Principal goal of the split is to diffrenetiate the logic of three core functionalities -- user, image and chat.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/Sovelluslogiikka1.png?raw=true)

**Picture above is a simplification of the logic layer.** In addition to the daos the logic classes are connected to an *UniversalLogic*-object which stores attributes public to all logics. Below is a more in-depth visualization of the logic layer and description of the classes.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/logicKuvaus.png?raw=true)

**UserLogic**

UserLogic provides three simple methods and takes care of interaction with the DaoUsers. This class provides the User of the current session for the Universal logic after communicating with DaoUsers. The User of current session is needed by both ChatLogic and ImageLogic.

**ChatLogic**

ChatLogic uses the information about the current image and user to offer methods for UI to add and fetch messages from the DaoChat

**ImageLogic**

Imagelogic class provides the imageID of a current image for UniversalLogic. 

## Database
 
The ImageChatter is using a SQLite database for storing all the information. Dao class initializes the database (tables) and holds information about the session -- database password etc. Dao will initialize a new database only if it does not exist already. The commands for creating the tables are being read from *[src/main/resources/database/tables.sql](https://github.com/kallioaa/ot-harjoitustyo/blob/master/ImageChatter/src/main/resources/imagechatter/database/tables.sql)*. After this, all communcation with the database is done by the FileDaoUsers, FileDaoImages and FileDaoChats classes.

Methods communicating with the database all initialize and close the connection to the database, so there is no connection left open. Try-with-resources structure is used in every connection and statement, which is recommended by Oracle.

### Files

When you run the program, a new file called *imagechatter.db* will be added to your current repository. Running the tests will create a file called *testDatabase.db*.

## Core operations

Here are some the core functionalities visualized with sequence forms.

### Creating a new user

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/newUserSequence.png?raw=true)

User clicks the new user button after typing username, password and password confirmation on the UI. The UI calls UserLogic's createUser method. If there is something wrong with the credentials (password too short or confirmation incorrect) userLogic returns a string about what went wrong to the UI.

If the credentials are okay UserLogic proceeds to call addAccount method from DaoUsers. Add account method returns true if the new account is created and false if the username is already taken. UserLogic returns a string about what happened to the UI. The string is null if the login is successfull, now UI changes to the Log in -view for the user.

### Logging in 

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/LoggingInv2.png)

1. When user clicks the Log in -button after typing username and password. 
2. The ControllerUser calls the logIn method
2. UserLogic which calls the logIn method from DaoUsers. 
4. If the log in is successful, meaning user is not already logged in and the credentials match, a new User Object is created and passed to the UserLogic class. If the log in is not successful null is returned. 
5. UserLogic sets the new User to UniversalLogic class.
6. UserLogic returns true if user is not null and false otherwise. 
7. ControllerUser changes to mainView if the return value is true. Otherwise displayed an error message about the log in not being successful.

### Searching for an image

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/Searchingforapictureh.png)


### Adding a message

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/AddMessage.png)

### Things to improve and closing thoughts

I am overall pretty happy about my the archictecture of the app. In the future, I will probably move checking the new user credentials to DaoUsers. 

Because of the lack of time, I did not finnish everything I wanted for the *Loppupalautus* release. This is why there exists some methods and attributes in the User class, which are not used.

