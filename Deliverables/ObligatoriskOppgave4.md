# The Agile Tunas

### DELOPPGAVE 1 - Team og Prosjekt


####Roller

Rollene i teamet fungerer utrolig bra, alle de som har tatt på seg 
rollene har virkelig utført det som krevdes av de. 
Vi trengte ikke å bytte de fra forrige obligatoriske oppgave til nå.
Teamleder er Jacob, kundekontakt er Olesya, kodemesteren er Lisa og 
Robin er tok mer hånd om det administrative.


####Prosjektmetodikk

Valget av prosjektmetodikk som vi valgte helt i starten har ikke endret seg
og vi syns at den blandingen av kanban og skrum vi tok har fungert utrolig bra.
Teamdynamikken har vært fantastisk, vi har hatt god stemning og har vært 
motiverte gjennom hele perioden for å levere et godt produkt. 
Når noen i teamet har hatt noe de ikke er fornøyd med så har teamet satt
seg ned for å løse dette sammen.


####Retrospekt

Siden det siste retrospektets endringer fungerte bra har vi prøvd å implementert
det denne gangen også. Det vi raskt fant ut var at når vi ble samlet så
jobbet vi mest i code with me denne gangen. Det ble for det meste feilsøking
i koden og finne ut av ting sammen.


Om vi hadde begynt prosjektet på nytt så skulle vi satt oss ned med spillet
og faktisk spilt spillet slik at alle på teamet hadde en god
forståelse av hvordan gangen i spillet går. 
Også hadde vi satt oss ned og laget et tankekart eller et diagram hvor 
vi da diskuterte hvordan vi vil at spillet skal fungere. 
Om vi hadde gjort prosjektet på nytt så hadde vi allerede visst hvem som 
var god til hva. Kunne kanskje lastet ned noen andre sine spill fra 
tidligere år og spilt gjennom noen ulike utgaver av de også sett hvordan 
de har løst det. Hvordan de skiller seg ut fra hva vi har tenkt osv. 
Kanskje lagt ned mer planlegging enn å gå rett på koden og kjørt cowboy stil. 
Skulle selvfølgelig vært flinkere til å jobbe jevnt og trutt så vi slipper 
sånne skippertak på slutten av hver frist. Men vi fikk noen kræsjer mellom fag i 
starten som vi i løpet av prosjektet lærte av.

#### Hva vi har vært flinke til
Hva vi har gjort bra er å sette ned rammer, hvilke dager vi skal møte og 
jobbe sammen som er fast. Alle på teamet har vært ganske flinke til å 
sette av tid og vært fleksible om noe uforutsett skulle dukke opp 
og alle personer måtte stille på dekk. 
Vi har vært flinke til å bli kjent med hverandre og kommuniserer bra, 
ingen er redd for å spørre om hjelp. 
Vi har vært flinke til å sette av litt tid til hver oppgave også finne ut om 
vi skal fortsette på den veien eller slutte og finne en annen løsning.

#### Gruppedynamikk

Gruppedynamikken er bra, Jacob har vært en klippe som teamleder, 
han har vist god oversikt og lederevne, han er åpen for innspill og 
ikke redd for å gi beskjed når det trengs. 
Olesya har vært flink til å videreformidle våre spørsmål til gruppeleder 
og faktisk få svar. Lisa har vært god på å forstå koden, hun ser gjennom 
alt som kommer inn, kommer med forslag, hun gjør endringer som hun mener 
er nødvendig for å gå en lett leselig kode som samtidig fungerer. 
Robin har vært flink til å gjøre det administrative, å skrive møtereferat,
og ha styr på brukerhistorier og alt det andre administrative. 
Gruppen har vært flink til å bruke trello til å sette opp oppgaver, 
frister og fordele oppgaver.

Gruppedynamikken har absolutt blitt bedre over tiden, i starten var 
Lisa og Olesya kanskje litt forsiktige, men har virkelig åpnet seg opp er 
nå ikke redde for å si sin mening. Jacob har blitt flinkere til å spørre 
om hjelp om det er noe han sliter med i stedet for å stange hodet i veggen alene.
Robin har blitt også flinkere til å gi beskjed når han gjør endringer og å 
kommentere kode slik at de andre på laget forstår hvorfor koden hans er der. 
Kommunikasjonsdelen har vært egentlig den samme fra starten til slutten.

#### Konklusjon

Alt i alt har gruppen fungert bra, alle har vært motiverte og åpen samtidig hatt et klart mål i sikte.



### DELOPPGAVE 2 - Krav


#### Brukerhistorie 1 - Conveyor Belts

Som spiller jeg at:

At roboten min blir flyttet på i riktig retning når den står på en conveyor belt.

Løsningsbeskrivelse:

Sjekke om roboten til spilleren står på en conveyor belt tile og flytte roboten et steg
i den retningen conveyor belt ligger. Ulike retninger av conveyor belts har spesielle
tile ID.

Akseptansekriterier:

Gitt at roboten står på en conveyor belt så skal
roboten bli synlig flyttet et steg i den rette retningen som
conveyor belt ligger.

#### Brukerhistorie 2 - Lasers

Som spiller ønsker jeg:

At roboten min skal bli skadet når den står på en plass der laseren skyter.

Løsningsbeskrivelse:

Sjekke at roboten står på en tile der laseren skyter, bruker tile ID til dette.
Implementere at roboten har 100 HP slik at den mister 10 HP om den står på en tile der laseren skyter.

Akseptansekriterier:

Gitt at roboten befinner seg på en tile der laseren skyter så skal roboten miste 10 HP.

#### Brukerhistorie 3 - Gear Clockwise

Som spiller ønsker jeg:

At roboten min skal bli rotert med klokken når den står på en plass med clockwise gear.

Løsningsbeskrivelse:

Sjekke at roboten står på en tile med clockwise gear, bruker tile ID til dette.
Rotere roboten med klokken ved å endre direction.

Akseptansekriterier:

Gitt at roboten befinner seg på en tile der clockwise gear er, skal roboten bli synlig rotert med klokken.

#### Brukerhistorie 4 - Gear Counter-Clockwise

Som spiller ønsker jeg:

At roboten min skal bli rotert mot klokken når den står på en plass med clockwise gear.

Løsningsbeskrivelse:

Sjekke at roboten står på en tile med counter-clockwise gear, bruker tile ID til dette.
Rotere roboten mot klokken ved å endre direction.

Akseptansekriterier:

Gitt at roboten befinner seg på en tile der counter-clockwise gear er, skal roboten bli synlig rotert mot klokken.

### DELOPPGAVE 3 - Produktleveranse og kodekvalitet

