# INF112 Prosjekt RoboRally
[![Build Status](https://travis-ci.com/inf112-v21/The-Agile-Tunas.svg?branch=master)](https://travis-ci.com/inf112-v21/The-Agile-Tunas)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/598d6a507dc74d34989e2c999450792d)](https://www.codacy.com/gh/inf112-v21/The-Agile-Tunas/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v21/The-Agile-Tunas&amp;utm_campaign=Badge_Grade)

## Beskrivelse av prosjektet
Prosjektet er å implementere brettspillet RoboRally i Java med spillmotoren libgdx.

Teamnavn: The Agile Tunas

Teammedlemmer: Jacob Hagan, Robin Lee Lange, Olesya Pasichnyk, Lisa Maria Eliassen

## Kjente bugs:
- Om man er veldig flink og klarer å velge 2 kort som ligger ovenfor hverandre
samtidig ved å trykke midt mellom dem, så legger de seg oppå hverandre i neste plass
for programkort.
- Robot kan ikke falle av kartet. (Ikke egentlig en bug, men noe vi har valgt at ikke skal kunne skje).


## Krav - MVP:
- Vise et spillebrett
- Vise brikke på spillebrett
- Flytte brikke (vha taster e.l. for testing)
- Robot besøker flagg
- Robot vinner ved å besøke flagg
- Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
- Dele ut kort
- Velge 5 kort
- Bevege robot ut fra valgte kort

## Hvordan kjøre spillet:

Finn den nyeste releasen og last ned Source Code. Du må unzippe zip filen
og åpne den i en java IDE (for eksempel IntelliJ). Dette prosjektet er bygget på Maven. 

Naviger til src/main/java/game/Main. Her inne velger du selv om du vil bare kjøre spillet 
eller å se skjermene vi har laget. Om du vil kjøre spillet kommenter ut "new Lwjgl3Application(new MultiplayerGameHandler(), cfg);"
og kjør Main filen for å starte spillet. Man får da opp en skjerm som viser brettet samt hvilke kort du har fått utdelt.
Trykk på de kortene man vil ha i den rekkefølgen man vil ha de. Man vinner ved å besøke flagget.
Om du vil se skjermene til spillet kommenter ut "new Lwjgl3Application(new ScreenOrchestrator(), cfg);" og kjør Main filen.
Man får da opp en meny skjerm der man kan velge mellom singleplayer, multiplayer, preferences, rules og exit. Singleplayer 
og rules er ikke implementert. Om man velger multiplayer så får man opp en skjerm der en får valget om å vere host, eller
vanlig spiller. Velger man å være en vanlig spiller vil man kunne skrive inn IP'en man vil koble seg til og nevnet man ønsker å benytte. 
For å kunne koble seg opp over internett må man sette opp Port-Forwarding på ruteren sin. 
Da setter man porten til 20000 og da for både UDP og TCP.


