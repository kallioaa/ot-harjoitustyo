# Architecture

## Structure

The app is implementing a layered architecture with three layers meaning no dependencies to layers above.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)


## User interface

The user interface follows the Model-View-Controller model. It has a total of three views:

- log in
- creating a new user
- searching for images and senging messages (main view).

Views are implemented by FXML-files which can be found in *src/main/resources/ui*. All of the views have their own Controller which can be found in *imagechatter.gui*.

The controllers are connected to different logic classes. Controllers for Login and creating a new user use the UserLogic class. Controller for the main view uses the userLogic, imageLogic and chatLogic classes.


## Application logic

The application logic consists of the three above mentioned logic classes to increase the cohesiveness. Principal goal of the split is to diffrenetiate the logic of three core functionalities -- user, image and chat.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/Sovelluslogiikka1.png?raw=true)

**Picture above is a simplification of the logic layer.** In addition to the daos the logic classes are connected to an *UniversalLogic*-object which stores atributes public to all logics. Below is a visual is a more in-depth visualization of the logic layer and description of the classes.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/logicKuvaus.png?raw=true)

**UserLogic**

UserLogic provides three simple methods and takes care of interaction with the DaoUsers. This class provides the User of the current session for the Universal logic after communicating with DaoUsers. The User of current session is needed by both ChatLogic and ImageLogic.

**ChatLogic**

ChatLogic uses the information about the current image and user to offer methods for UI to add and fetch messages from the DaoChat

**ImageLogic**

Imagelogic class provides the imageID of a current image for UniversalLogic. 

## Database
 
The ImageChatter is using a SQLite database for storing all the information. Dao class initializes the database (tables) and holds information about the session -- database password etc. Dao will initialize a new database only if it does not exist already. The commands for creating the tables are being read from *[src/main/resources/database/tables.sql](https://github.com/kallioaa/ot-harjoitustyo/blob/master/ImageChatter/src/main/resources/imagechatter/database/tables.sql)*. After this, all communcation with the database is done by the FileDaoUsers, FileDaoImages and FileDaoChats classes.

Methods communicating with the database all initialize and closes the connection to the database, so there is now connection left open. Try-with-resources structure is used in every connection and statement, which is recommended by Oracle.

### Files

When you run the program, a new file called *imagechatter.db* will be added to your current repository. Running the tests will create a file called *testDatabase.db*.

## Core operations

Here are some the core functionalities visualized with sequence forms.

### Creating a new user

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/newUserSequence.png?raw=true)




