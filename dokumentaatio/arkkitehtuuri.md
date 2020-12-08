![arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/rakenne.png?raw=true)

# Käyttöliittymä

Käyttöliittymä sisältää kolme erilaista näkymää

- kirjautuminen
- uuden käyttäjän luominen
- kuvien katsominen ja viestien lähettäminen.

Jokaiseen liittyy oma FXML tiedosto sekä Controller. Eri controllerit keskustelevat ainoastaan Applications Logic luokan kanssa, lukuunottamatta muutamaa staattisen metodin kursia.

# Sovelluslogiikka

Sovellus on toteuttu MVC tapaa noudattaen. 
