# ImageChatter

Voit luoda ohjelmaan käyttäjätunnukset ja kirjautua sisään. Kirjauduttuasi pystyt liittämään tesktikenttään minkä tahansa ei "data"-alkuisen kuvan osoitteen ja näät sen avautuvan käyttöliittymään. Tiedot siitä, että käyttäjäsi on lisännyt ensimmäiseksi kuvan (rgb-arvoista generoidun hashkoodin) tiettynä ajanhetkenä (saattaa näyttää gmt +0 aikaa). Nyt voit kirjoittaa kuvaan kommentteja, jotka muut näkevät syöttäessään saman kuvan järjestelmään.

Pystyt nollaamaan tietokannan poistamalla resourcesDb kansiosta tiedoston kkDatabase.mv.db. Tämä kannattaa suorittaa testien testaamisen jälkeen, jotta ensimmäinen lisäämäsi kuva saa id-arvokseen 1.

Suoritettavissa oleva jar-tiedoston saa komennolla.

```
mvn clean compile assembly:single
```


## Määrittelydokumentin toiminnallisuudesta toteutettu

- Käyttöliittymä [x] 
- Kuvien lisääminen tietokantaan [x]
- Viestien lisääminen tietokantaan [x] 
- admin käyttäjä, jota en todennäköisesti toteuta []
- Kuvan hakeminen id perusteella []

[Harjoitustyön määrittelydokumentti](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakirjanoito](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/työaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Release](https://github.com/kallioaa/ot-harjoitustyo/releases/tag/viikko5)

