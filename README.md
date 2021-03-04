# INF112 Obligatorisk oppgave 1: Prosjekt RoboRally
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
- Robot kan ikke falle av kartet.


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
Naviger til src/main/java/inf112.skeleton.app/Main og kjør Main filen for å starte spillet.
