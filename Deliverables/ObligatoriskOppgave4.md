# The Agile Tunas

### DELOPPGAVE 1 - Team og Prosjekt



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

