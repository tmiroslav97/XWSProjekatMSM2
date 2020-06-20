# Authentication service
U okviru authentication servisa se nalaze sledeci entiteti trazeni u specifikaciji (izuzeti su "pomocni" entiteti):
* User
* Agent
* EndUser
* Firm

U paketu service i njegovom potpaketu intf nalaze se definicije metoda, onoga sta bi trebao ovaj servis da realizuje.

Autentifikacija je Token Based pomocu jwt tokena, autorizacija je Role Based, a security je implementiran pomocu spring security- a.
### AuthenticationService
Realizuje:
* registraciju
* prijavljivanje
* refresh tokena 
* verifikaciju korisnika

### UserService
Izvrsava operacije vezane za generalizovane stvari oko korisnika:
* cuvanje korisnika u bazi
* brisanje
* provjera postojanj putem email- a
* izmjena podataka o korisniku

### AgentService
Izvrsava operacije vezane za agenta:
* cuvanje agenta u bazi
* brisanje
* dobavljanje agenta
* dobavljanje liste agenata
* izmjena podataka o agentu

### EndUserService
Izvrsava operacije vezane za krajnjeg korisnika:
* cuvanje krajnjeg korisnika u bazi
* brisanje
* dobavljanje krajnjeg korisnika
* dobavljanje liste krajnjih korisnika
* izmjena podataka o krajnjem korisniku (blokiranje, aktiviranje, uklanjanje iz sistema)
* definisanje permisija za korisnike

### FirmService
Izvrsava operacije vezane za firmu:
* registracija firme
* dobavljanje podataka o firmi
* dobavljanje podataka o firmama
* brisanje firme
* izmjena podataka o firmi
* sinhronizovanje podataka firme
