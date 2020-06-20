# Car request service
Ovaj servis ukljucuje sljedece entitete: 

* Ad
* Report
* Request

U paketu service i njegovom potpaketu intf nalaze se definicije metoda, onoga sta bi trebao ovaj servis da realizuje.

## ReportService
Izvrsava operacije koje su vezane za izvjestaje:
* preuzimanje svih izvjestaja
* dodavanje novog izvjestaja
* cuvanje izvjestaja u bazi podataka
* placanje ukoliko dodje do prekoracenja kilometraze (na osnovu id-ja request-a)

## RequestService
Izvrsava operacije koje su vezane za zahtjeve:
* kreiranje zahtjeva za iznajmljivanje automobila
* dobavljanje zahtjeva
* odobravanje zahtjeva
* otkazivanje zahtjeva od strane korisnika
* automatsko odbijanje zahtjeva koji nisu prihvaceni (automobil je rentiran)
* automatska funkcija za odbijanje zahtjeva koji nisu obradjeni

