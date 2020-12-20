# Architecture

## Structure

The app is implementing a layered architecture with three layers meaning no dependencies to layers above.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)


## User interface

The user interface follows the Model-View-Controller model. It has a total of three views:

- log in
- creating a new user
- searching for images and senging messages (main view).

Views are implemented by FXML-files which can be found in *src/main/resources/gui*. All of the views have their own Controller which can be found in *imagechatter.gui*.

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

Sovellus käyttää tietojen tallentamiseen SQLite tietokantaa, jossa on neljä eri taulua.

## Core operations
