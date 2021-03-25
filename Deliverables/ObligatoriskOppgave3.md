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


