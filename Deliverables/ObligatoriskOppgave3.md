# The Agile Tunas

### DELOPPGAVE 1 - Prosjekt og prosjektstruktur


#### Roller 
Under forrige gjennomgang av prosjektet og teamet satte vi en person som skulle fungere som kodeansvarlig.
Dette har vist seg å være en god beslutning når prosjektet har vokst. Å ha en fast person som sørger for at alt er 
velldokumentert og at vi får luket bort kodebiter vi ikke benytter oss av. 

#### Teamet 
Den største utfordingen denne gangen oppso når et av teammedlemmene ble syk. 
Når vi skrev teamkontrakten i starten av semesteret diskuterte vi hva vi skulle gjøre om et av teammedlemmene ble syk i
en kritisk fase av oppgaven. Da ble vi enige om å dekke hullet som oppsto etter beste evne. Måten vi løste dette på
denne gangen var at de resterende medlemmene bare måtte ta i et ekstra tak for å komme i land med de oppgavene vi ønsket
å gjennomføre. Måten vi jobbet med prosjektet var å delegere et ansvarsområde til hver av oss.
Så denne siste uken skulle vi jobbe for å sy sammen de ulike delene slik at vi kunne oppnå ønsket resultat.
Dette ble en utfordring når et av medlemmene ikke kunne delta i dette. Men eneste løsningen var å sette av tid til å
sette seg inn i koden til Robin. Dette gikk greit, men tok selvagt lengre tid. 
Utover dette fungerer teamet godt. Vi føler det er lite å utsette på kommunikasjonen og vi ser ingen grunn til å endre
prosjektmetodikk. 

#### Retrospekt
Ved forrige retrospekt ble vi enge om å bruke lengre tid på planleggingen mht andre gjøremål som kunne kollidere. 
Dette lyktes vi greit med og vi fikk satt av tilstrekkelig tid til prosjektet. 
Den nye rollen vi bestemte oss for å innføre kan og betegnes som en god beslutning. 

##### Hva gikk bra? 
Det som gikk bra denne gangen var at vi tok de erfaringene vi gjorde oss ved forrige retrospekt, gjorde tiltak og så
resultater av dette. Det var også betryggende at teamet stilte opp når et av medlemmene ble syk. Vi vidreførte praksisen 
med å dele opp ansvarsområder og oppgaver og vi føler dette fungerer godt når vi kommuniserer godt. Vi bruker fremdeles
Discord til alt av kommunikasjon og vi har klart å skape en kultur der vi kan spørre om alt og terskelen for å stille
spørsmål eller ytre noe er veldig lav. 

##### Hva kunne gått bedre? 
Den største utfordingen vi møtte denne gangen lå i en delvis misforståelse om hva vi ønsket å prioritere i koden.
Når vi har delt opp arbeidsoppgaver og ansvarsområder er et åpenbart at enkelte områder kommer til å ha en delvis overlapp.
Denne overlappen kan skape en avhengighet mellom hva man trenger at andre har gjort før man kan iverksette en implementajson. 
Her gikk det nok noe tid tapt i venting, som har delvis rot i at man ikke har kommunisert tydelig nok utover hvilke
funksjonaliteter man er avhengig av.

#### Forbedringspunkter
1. Kommunisere tydelig hvilke avhengingheter det eksisterer i prosjektet slik at alle vet hva de burde fokusere på. 
2. Jobbe tettere på områder der flere arbeidsoppgaver overlapper.

#### Prioritering 
Ved denne innleveringen hadde vi størst fokus på å få en fungerende GUI og få til multiplayer. I tillegg til hadde vi et
ønske om å få implementert mockito slik at vi få bort de feilene som oppstå ved å kjøre testene sist gang. 

#### Gruppedynamikk og kommunikasjon
Gruppedynamikken er det lite å utsette på. Vi er blitt trygge på hverandre og opparbeidet en god oversikt over den
enkeltets egenskaper. Vi kommuniserer godt og som nevnt over har vi en veldig lav terskel for å stille spørsmål. 


### Deloppgave 2 - Krav
Vi har valgt å fremdeles prioritere multiplayerdelen. I tillegg til dette har vi vektlagt å få en fungerende GUI. 
GUI'en er så godt som ferdig. Multiplayer delen trenger fremdeles litt arbeid for å kunne støtte et fullverdig spill. 
All kommuninkasjon mellom server og klient er der, men det gjenstår enda å implemntere en del forretningslogikk til spillet. 
Siden sist gang har vi jobbet mye med refaktorering for å kunne implemtere en multiplayer på en god måte. 
Det ble laget en egen game handler for multiplayer for å kunne vise flere spillere på samme brett. Hele GUI'en ble laget
med tilhørende screens for å kunne ha en intuitiv måte å starte spillet på. Man kan nå sette opp en server gjennom GUI'en 
og hva vi skal arbeide fram mot framover er ganske klart. 

Da vi ikke har en fullt fungerende multiplayer kan vi ikke si at vi er forbi MVP, men vi er på god vei.

#### Brukerhistorier MVP 6 - Kontakt mellom en Server og flere klienter

Som host ønsker jeg:

Å kunne starte en server via GUI 

Løsningskriterier:

Bruke Screens og buttons sette opp en ny server. 

Akseptansekriterier:

Serveren initieres ved å trykke på Host knappen i gui. 
Andre brukere kan koble seg til serveren.

#### Brukerhistorier Screen - MenuScreen

Som spiller ønsker jeg:

Å kunne se en meny skjerm når programmet starter.
Meny skjermen skal inneholde knapper til single- og multiplayer, preferences, rules og exit program.

Løsningskriterier:

Bruke TextButtons til å lage knappene og legge dem inn i en table.

Akseptansekrav:

Når programmet kjøres i main skal det første som dukker opp vere en meny skjerm 
med alle knappene som står spesifisert i brukerhistorien.

#### Brukerhistorier Screen - MenuScreen

Som spiller ønsker jeg:

Å trykke på de forskjellige knappene i meny skjermen. Avhengig av hva slags knapp jeg trykker på 
skal det komme opp en ny skjerm som tilhører knappen.

Løsningskriterier:

Legge til en listener til knappene. Lage changescreen metode og bruke den til å endre hva skjerm som blir 
vist etter at man har trykket på knappene i meny skjermen. 

Akseptansekriterier:

Når programmet kjøres og meny skjermen dukker opp, skal det vere mulig å trykke på forskjellige knapper
og få opp en ny skjerm som tilhører de forskjellige knappene.

#### Brukerhistorier Screen - EndScreen

Som spiller ønsker jeg:

At programmet skal bli lukket når jeg trykker på exit-knappen i meny skjermen.

Løsningskriterier:

Lage en EndScreen som kalles etter at man trykker på exit knappen og programmet lukkes.

Akseptansekriterier:

Når man trykker på exit-knappen i meny skjermen skal programmet lukke seg.

#### Brukerhistorier Screen - PreferencesScreen

Som spiller ønsker jeg:

Å få opp en preferences skjerm etter at jeg har trykket på preference knappen i meny skjermen. 
Der skal jeg kunne velge om jeg vil ha musikk og lyd på eller av i programmet og hvor høy de skal vere.

Løsningskriterier:

Lage PreferencesScreen som inneholder fire knapper på den høgre siden og beskrivelse av knappene på
venste siden. To av knappene skal vere trykkbare og gi brukeren valget om å ha lyd og musikk på eller av. 
De to andre knappene skal vere skyveknapper som gir brukeren valget om hvor høyt musikken og lyden i 
programmet skal vere.

Akseptansekriterier:

Når man trykker på preferences knappen i meny skjermen skal preferences skjermen dukke opp med fire knapper 
og beskrivelse av de.

#### Brukerhistorier Screen - PreferencesScreen

Som spiller ønsker jeg:

Å returnere tilbake til meny skjermen fra preference skjermen.

Løsningskriterier:

Bruke TextButtons til å lage return knappen. Legge til en listener til knappen og bruke
changescreen metoden til å endre skjermen tilbake til meny skjermen.

Akseptansekriterier:

Se return knappen og kunne trykke på den.
Når man trykker på return knappen i preferences skjermen skal meny skjermen vise seg.

#### Brukerhistorier Screen - RuleScreen

Som spiller ønsker jeg:

Å få opp en rules skjerm etter at jeg har trykket på regler knappen i meny skjermen.
Der skal jeg få oversikt over reglene i spillet RoboRally. 

Løsningskriterier:

Lage en RuleScreen. Lage en ny label og skrive inn de reglene som gjelder for vår implementasjon av spillet.

Akseptansekriterier:

Når man trykker på rules knappen i meny skjermen skal rules skjermen dukke opp med reglene for spillet.

#### Brukerhistorier Screen - RuleScreen

Som spiller ønsker jeg:

Å returnere tilbake til meny skjermen fra rules skjermen.

Løsningskriterier:

Bruke TextButtons til å lage return knappen. Legge til en listener til knappen og bruke
changescreen metoden til å endre skjermen tilbake til meny skjermen.

Akseptansekriterier:

Se return knappen og kunne trykke på den.
Når man trykker på return knappen i rules skjermen skal meny skjermen vise seg.

#### Brukerhistorier Screen - MainScreen

Som spiller ønsker jeg:

Å starte singleplayer spillet når jeg trykker på singleplayer knappen i meny skjermen.

Løsningskriterier:

Lage en MainScreen som skal vise singleplayer spillet. 

Akseptansekriterier:

Når man trykker på singleplayer knappen i meny skjermen skal man få opp singleplayer delen av spillet 
RoboRally.

### Deloppgave 3 - Produktleveranse og kodekvalitet
Det oppdaterte klassediagrammet vårt er lagt ved i Deliverables: KlassediagramOblig3.png.
Manuelle tester CardDeck og CardTest som ikke passerte i forrige oblig, passerer nå.
Vi har også hatt en litt skjev fordelig i commits, det er på grunn av at vi ofte bruker code with me 
og det er vanlig at den samme personen er host. 


