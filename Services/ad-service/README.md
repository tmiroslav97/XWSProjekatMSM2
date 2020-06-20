# Ad service
Ovaj servis ukljucuje sljedece entitete: 

* Ad
* Car
* Car Calendar Term
* Comment

U paketu service i njegovom potpaketu intf nalaze se definicije metoda, onoga sta bi trebao ovaj servis da realizuje.

## AdService
Izvrsava operacije koje su vezane za oglase:
* preuzimanje svih oglasa
* preuzimanje paginiranih oglasa
* preuzimanje jednog oglasa
* brisanje 
* izmjena 
* dodavanje novog oglasa
* cuvanje u bazu podataka
* vracanje oglasa sa najvecim rejtingom
* pregled ocjena
* postavljanje ocjene
* sinhronizacija podataka

## CarService 
Izvrsava operacije vezane za auta:
* brisanje
* izmjena
* cuvanje u bazi 
* dodavanje
* vracanje automobila sa najvecom kilometrazom

## CommentService
Izvrsava operacije za:
* postavljanje komentara
* brisanje komentara 
* cuvanje komentara
* vracanje automobila sa najvecim brojem komentara
* odobravanje komentara (tj. odbijanje)

## CarCalendarTermService
Izvrsava operacije za:
* vracanje slobodnih termina za automobil
* definisanje novog slobodnog termina (zauzece automobila)
* izmjena termina
* brisanje termina
