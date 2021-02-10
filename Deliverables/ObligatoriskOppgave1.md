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
Personlighetstype: INFJ.

Rolleinndeling: Jacob Hagan (Teamlead, utvikler), Robin Lange (Utvikler), Olesya Pasichnyk (Kundekontakt, utvikler), Lisa Eliassen (Utvikler).

Teamlead: Jacob Hagan.

## DELOPPGAVE 2 - Prosjekt- og prosessplan

Av prosjektmetodikk har vi landet på en en kombinasjon av Scrum og Kanban. Da prosjektet vi skal utføre har gitte tidsfrister virker Scrum som et hensiktsmessig verktøy for å sikre at gitte arbeidsoppgaver blir utført til korrekt tid. På denne måten ser vi for oss å opprettholde en god oversikt over hvilke arbeidsoppgaver som gjenstår slik at vi kan unngå unødvendig stress og skippertaksarbeid kvelden før fristen. For å kunne holde god oversikt over hvilke oppgaver som skal og må gjøres benytter vi Kanban. Vi har opprettet et Project Board med Trello slik at vi kan visualisere arbeidsprosessen og øke effektiviteten ved at alle er på samme side når det kommer til oversikt over arbeidsoppgavene til den enkelte.
Måten vi kombinerer Scrum og Kanban er ved hjelp av tidsfrister på arbeidsoppgavene som er hengt opp på vårt Project Board. Vi tror dette vil skape en robust plattform som vil være både effektiv og oversiktlig.

Når det kommer til det rent praktiske rundt oppgaven vil vi tilstrebe å gjennomføre en samarbeidsrettet arbeidsmetode.
Vårt andre møte sammen gikk i all hovedsak til å utarbeide en kontrakt for hvordan vi ønsker å ha det i teamet. Brorparten av det 2 timer lange møtet gikk til å diskutere de ulike elementene som kontrakten tar for seg. Det å være proaktiv viser seg ofte å være det beste, så ved å gå gjennom ulike tenkte scenarier som kan komme til å oppstå er vi alle trygge på hvordan vi skal gå fram.

Måten vi ønsker å organisere prosjektet på den første tiden er å aktivt benytte oss av Trello og det project-boardet vi har der. Vi sikter på å ha minst 2 møter i uken á 2 timer hver. Med mulighet og åpenhet om mer enn dette om det skal være nødvendig. Ved å starte og avslutte hvert møte med å oppdatere det i henhold til den progresjonen vi har hatt, tror vi at vi vil kunne holde en god oversikt over hva alle i teamet jobber med, samt kunne ha en totaloversikt over hvor i prosessen vi er. Vi oppdaterer de aller fleste oppgaver på en måte der man kan se hvem som skal utføre en oppgave og når oppgaven skal være gjort til. Siden vi har en tidsfrist for innlevering av prosjektet virker dette som en tilfredstillende måte å organisere oss på.
Vi har valgt å benytte oss av Discord som kommunikasjonsplattform. Der deler vi lenker eller bilder med hverandre. Vi har og opprettet en egen discord-chat der vi kan ha videomøter om det skulle være nødvendig. Med dokumenter der alle skal ha mulighet til å skrive og redigere i sanntid benytter vi oss av google docs. Her har vi skrevet blant annet team-kontrakten da alle skulle skrive inn sine sterke og svake sider.
For detaljer omkring møtedeltakelse, konfliktløsning, uforutsette hendelser, forventningsavklaring osv. legger vi ved kontrakten i sin helhet.

## DELOPPGAVE 3 - Forventet produkt

Det overordnede målet for applikasjonen vil  i første iterasjon være å skape et solid fundament hvor videre utvikling kan foregå på en ryddig og effektiv måte. Til dette trenger vi å implementere de mest grunnleggende funksjonene til spillet. Herunder å vise brettet med tilhørende brikker, kunne flytte på brikkene samt utvikle en seiersbetingelse.
Vi har valgt å prioritere de enkleste funksjonene i spillet først. Dette er et valgt tatt på bakgrunn av at ingen av oss har særlig erfaring med spillutvikling tidligere, og vi tror det vil lønne seg å få satt seg godt inn i det grunnleggende før man bruker for mye tid på noe man ikke vet man får fullført til fristen. Dog er det en intern prioritet i gruppen å få sett på en multiplayer-løsning så fort de 5 første punktene er ferdig. Det er det punktet som virker mest krevende og behøver mest tid for å sette seg inn i. Men siden det ikke er et krav for å få spillet til å fungere har vi valgt å prioritere det noe lavere.

###Brukerhistorier

#### Brukerhistorie MVP 1 - Vise et spillebrett

Som en spiller 
ønsker jeg å se spillebrettet
slik at jeg kan få en oversikt over objektene og planlegge spillet mitt.

Løsningsbeskrivelse:
Lage spillebrettet i Tiled og lage kode i IntelliJ for å vise det.

Akseptansekriterier:
Gitt at spillebrettet vises på skjermen så skal 
spilleren ha en formening om dimensjonene til brettet,
hvor de ulike objektene er samt startposisjonene.

#### Brukerhistorie MVP 2 - Vise brikke på spillebrett

Som en spiller
ønsker jeg at brikken som jeg kontrollerer skal dukke opp på
brettet i den posisjonen som jeg har fått tildelt. 
I første grad skal brikken vises i startposisjon 1.

Løsningsbeskrivelse:
Skrive inn kode som gjør at bretter putter spillerbrikken
på brettet i den riktige startposisjonen sin.

Akseptansekriterier: 
Gitt at brettet er synlig så skal spilleren kunne se
brikken sin på brettet i den tildelte startposisjonen sin.

#### Brukerhistorie MVP 3 - Flytte brikke (vha taster e.l. for testing)

Som en spiller
ønsker jeg at det er mulig å flytte brikken som representerer en robot i spillet,
slik at brikken flytter seg slik som jeg har planlagt.

Løsningsbeskrivelse:
Lage en metode som tar inn keycodes og differensierer mellom de fire ulike tastetrykkene: pil opp, pil ned, venstre pil og høyre pil.
Metoden flytter brikken i henhold til piltastenes keycode.

Akseptansekriterier:
Gitt at brikken er flyttbar, så skal jeg kunne se at brikken flyttes riktig i henhold til tastetrykk.

#### Brukerhistorie MVP 4 - Robot besøker flagg

Som en spiller ønsker jeg å kunne besøke flagg slik at jeg kan gjøre fremskritt i spillet.

Løsningsbeskrivelse:
Roboten må kunne bevege seg iht. de instruksjonene den har fått, samt at spillet må registrere at en robot står på flagget.
En Robot-klasse vil da trenge en teller eller en boolean verdi for at man skulle bekrefte dette.

Akseptansekriterier:
Gitt at roboten kan besøke et flagg må flagget være synlig på brettet slik at en spiller kan planlegge ruten mot det. 
Gitt at roboten kan besøke et flagg må vi vite hvor roboten er til en hver tid slik at vi kan bekrefte om den står på et flagg eller ikke. 

#### Brukerhistorie MVP 5 - Robot vinner ved å besøke flagg
Kommentar: vi vil senere utvide denne brukerhistorien når spillet blir mer komplisert.

Som en spiller 
ønsker jeg å vinne når jeg besøker flagg
slik at jeg fullfører spillet.

Løsningsbeskrivelse:
Skrive kode i IntelliJ som endrer ikonet til spilleren når spilleren besøker flagg.

Akseptansekriterier:
Gitt at spilleren vinner ved å besøke flagg skal ikonet til spilleren endres
til vinner-ikonet slik at spilleren vet at hun/han har vunnet. 