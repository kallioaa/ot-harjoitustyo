# Architecture

## Structure

The app is implementing a layered architecture with three layers meaning no dependencies to layers above.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)


## User interface

The user interface follows the Model-View-Controller model. It has a total of three views:

- log in
- creating a new user
- searching for images and senging messages (main view).

Views are imoplemented by FXML-files avulla which can be found in *src/main/resources/gui*. All of the views have their own Controller which can be found in *imagechatter.gui*.

The controllers are connected to different logic classes. Controllers for Login and creating a new user use the UserLogic class. Controller for the main view uses the userLogic, imageLogic and chatLogic classes.



# Sovelluslogiikka

Sovellus on toteuttu MVC tapaa noudattaen. Applications logic keskustelee eri dao:jen kanssa pitäen UI puolen mahdollisimman tyhmänä. Kaikki Dao:t sekä sovelluslogiikan luokkia on toteutettu Singletonien avulla.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)

# Tiedostot

Sovellus käyttää tietojen tallentamiseen SQLite tietokantaa, jossa on neljä eri taulua.



