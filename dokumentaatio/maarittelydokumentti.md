# Keskustelupalstat kuvista

Ohjelman ideana on tarjota anonyymi paikka vuorovaikutukselle johonkin kuvaan liittyen. Esimerkiksi voit syöttää ohjelmaan internetistä kuvan, joka avaa siihen liittyvän keskustelupalstan. Tämä on erityisen hyödyllistä aikana, jolloin sosiaalisen median palveluissa kuten Instagramissa useat käyttäjät 
jakavat samoja kuvia, joille ei ole kuitenkaan olemassa yhteistä kommenttikenttää. Useat näistä "trendaavista" jaetuista kuvista on disinformatiivisia, joten ihmisten laumaälylle näiden kuvein faktojen korjaamiselle olisi tarvetta.


## Käyttäjät

* Normaalikäyttäjä (käyttäjä)
* Admin käyttäjä, jolla on oikeus poistaa kuva ja siihen liittyvät kommentit sovelluksesta.

## Suunnitellut toiminnallisuudet (normaalikäyttäjä)

* Käyttäjä voi kopioda ohjelmaan kuvan, jonka jälkeen käyttälle näytetään kuvasta kirjoitetut kommentit.
* käyttäjä voi myös etsiä kuvan sen ID arvon avulla.
* Käyttäjä voi kirjoittaa omia kommentteja kuvan keskutelupalstaan.

## Teknisempiä yksityiskohtia

* Kuvat muutetaan RGB kuvista greyscaleen taltiontia varten.
* Kun uudella kuvalla tehdään haku, sitä verrataan vain samankokoisiin kuviin, mikä ei ole suuri ongelma, koska palveluissa kuten instagramissa kuvien koot ovat vakioita.
* Kommenteille, kuville ja estetyille kuville (Kuvan id)  on tietokannassa omat taulut.

