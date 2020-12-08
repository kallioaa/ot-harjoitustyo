![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne.png?raw=true)

# Käyttöliittymä

Käyttöliittymä sisältää kolme erilaista näkymää

- kirjautuminen
- uuden käyttäjän luominen
- kuvien katsominen ja viestien lähettäminen.

Jokaiseen liittyy oma FXML tiedosto sekä Controller (MVC). Eri controllerit keskustelevat ainoastaan Applications Logic luokan kanssa, lukuunottamatta muutamaa staattisen metodin kursia.

# Sovelluslogiikka

Sovellus on toteuttu MVC tapaa noudattaen. Applications logic keskustelee eri dao:jen kanssa pitäen UI puolen mahdollisimman tyhmänä. Kaikki Dao:t sekä sovelluslogiikan luokkia on toteutettu Singletonien avulla.

![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne.png?raw=true)

# Tiedostot

Sovellus käyttää tietojen tallentamiseen SQLite tietokantaa, jossa on neljä eri taulua.



