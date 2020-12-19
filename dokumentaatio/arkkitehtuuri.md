# Architecture

## Structure

The app is implementing a layered architecture with three layers meaning no dependencies to layers above.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)


## User interface

The user interface has three differnet views.

- log in
- creating a new user
- searching for images and senging messages

Views are imoplemented by FXML-files avulla which can be found in *src/main/resources/gui*. All of the views have their own Controller which can be found in *imagechatter.gui*.

This 

# Sovelluslogiikka

Sovellus on toteuttu MVC tapaa noudattaen. Applications logic keskustelee eri dao:jen kanssa pitäen UI puolen mahdollisimman tyhmänä. Kaikki Dao:t sekä sovelluslogiikan luokkia on toteutettu Singletonien avulla.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne1.png?raw=true)

# Tiedostot

Sovellus käyttää tietojen tallentamiseen SQLite tietokantaa, jossa on neljä eri taulua.



