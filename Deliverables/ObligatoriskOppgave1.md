# The Agile Tunas

## DELOPPGAVE 1

### Prosess- og prosjektplan:

Jacob
God  erfaring med praktisk og teoretisk ledelse og teamarbeid etter å ha gått Befalsskole i Forsvaret.
Har gått 3 år på Norges Handelshøyskole der psykologi og ledelse er  godt integrert i fagplanen. Har
tilbrakt	vesentlig tid i studentforeningen NHHS. Jobbet gjentatte ganger som  gruppemedlem eller gruppeleder.
Personlighetstype INTJ eller ENTJ.

Robin
Har bachelor i petroleumslogistikk, har drevet med idrett i norgestoppen. Liker å ha det kjekt. 
Personlighetstype ENTJ-A.

Olesya
Bred erfaring innenfor kreativt arbeid. Vært i norgestoppen innenfor mitt fagområde, der vi ble bedømt av anerkjente personligheter i bransjen.  
Personlighetstype ESFJ.

Lisa
Kompetanse/Erfaring: Lang erfaring med lagkommunikasjon i pressede/stressende situasjoner der        
konkurranse og teamarbeid er essensielt for gode resultater.
Personlighetstype: INFJ

Rolleinndeling:

Teamlead: 


## DELOPPGAVE 2 - Prosjekt- og prosessplan

Av prosjektmetodikk har vi landet på en en kombinasjon av Scrum og Kanban. Da prosjektet vi skal utføre har gitte tidsfrister virker Scrum som et hensiktsmessig verktøy for å sikre at gitte arbeidsoppgaver blir utført til korrekt tid. På denne måten ser vi for oss å opprettholde en god oversikt over hvilke arbeidsoppgaver som gjenstår slik at vi kan unngå unødvendig stress og skippertaksarbeid kvelden før fristen. For å kunne holde god oversikt over hvilke oppgaver som skal og må gjøres benytter vi Kanban. Vi har opprettet et Project Board med Trello slik at vi kan visualisere arbeidsprosessen og øke effektiviteten ved at alle er på samme side når det kommer til oversikt over arbeidsoppgavene til den enkelte.
Måten vi kombinerer Scrum og Kanban er ved hjelp av tidsfrister på arbeidsoppgavene som er hengt opp på vårt Project Board. Vi tror dette vil skape en robust plattform som vil være både effektiv og oversiktlig.

Når det kommer til det rent praktiske rundt oppgaven vil vi tilstrebe å gjennomføre en samarbeidsrettet arbeidsmetode.
Vårt andre møte sammen gikk i all hovedsak til å utarbeide en kontrakt for hvordan vi ønsker å ha det i teamet. Brorparten av det 2 timer lange møtet gikk til å diskutere de ulike elementene som kontrakten tar for seg. Det å være proaktiv viser seg ofte å være det beste, så ved å gå gjennom ulike tenkte scenarier som kan komme til å oppstå er vi alle trygge på hvordan vi skal gå fram.

Måten vi ønsker å organisere prosjektet på den første tiden er å aktivt benytte oss av Trello og det project-boardet vi har der. Vi sikter på å ha minst 2 møter i uken á 2 timer hver. Med mulighet og åpenhet om mer enn dette om det skal være nødvendig. Ved å starte og avslutte hvert møte med å oppdatere det i henhold til den progresjonen vi har hatt, tror vi at vi vil kunne holde en god oversikt over hva alle i teamet jobber med, samt kunne ha en totaloversikt over hvor i prosessen vi er. Vi oppdaterer de aller fleste oppgaver på en måte der man kan se hvem som skal utføre en oppgave og når oppgaven skal være gjort til. Siden vi har en tidsfrist for innlevering av prosjektet virker dette som en tilfredstillende måte å organisere oss på.
Vi har valgt å benytte oss av Discord som kommunikasjonsplattform. Der deler vi lenker eller bilder med hverandre. Vi har og opprettet en egen discord-chat der vi kan ha videomøter om det skulle være nødvendig. Med dokumenter der alle skal ha mulighet til å skrive og redigere i sanntid benytter vi oss av google docs. Her har vi skrevet blant annet team-kontrakten da alle skulle skrive inn sine sterke og svake sider.
For detaljer omkring møtedeltakelse, konfliktløsning, uforutsette hendelser, forventningsavklaring osv. legger vi ved kontrakten i sin helhet.

## DELOPPGAVE 3 - 'overskrift her'

###Brukerhistorier

#### Brukerhistorie MVP 1 - Vise et spillebrett

Som en spiller 
ønsker jeg å se spillebrettet
slik at jeg kan få en oversikt over objektene og planlegge spillet mitt.

Løsningsbeskrivelse
Lage spillebrettet i Tiled og lage kode i IntelliJ for å vise det.

Akseptansekriterier
Gitt at spillebrettet vises på skjermen så skal 
spilleren ha en formening om dimensjonene til brettet,
hvor de ulike objektene er samt startposisjonene.

#### Brukerhistorie MVP 2 - Vise brikke på spillebrett


#### Brukerhistorie MVP 3:
Som en spiller
Ønsker jeg at det er mulig å flytte brikken som representerer en robot i spillet,
slik at brikken flytter seg slik som jeg har planlagt.

Løsningsbeskrivelse MVP 3:
Lage en metode som tar inn keycodes og differensierer mellom de fire ulike tastetrykkene: pil opp, pil ned, venstre pil og høyre pil.
Metoden flytter brikken i henhold til piltastenes keycode.

Akseptansekriterier MVP 3:
Gitt at brikken er flyttbar, så skal jeg kunne se at brikken flyttes riktig i henhold til tastetrykk.


#### Brukerhistorie MVP 4 - Robot besøker flagg

Som en spiller ønsker jeg å kunne besøke flagg slik at jeg kan vinne spillet.

Løsningsbeskrivelse
Roboten må kunne bevege seg iht. de instruksjonene den har fått, samt at spillet må registrere at en robot står på flagget.
En Robot-klasse vil da trenge en teller eller en boolean verdi for at man skulle bekrefte dette.


Akseptansekriterier
Gitt at roboten kan besøke et flagg:
1. Må flagget være synlig på brettet.
   a. På denne måten kan en spiller bevege seg mot det.
2. Må brikken kunne bevege på seg.
   a. I tillegg må vi alltid vite hvor roboten er.
3. Må vi kunne vite når en brikke og et flagg står i samme rute på brettet. 
   