# Message service
U okviru message servisa se nalazi jedan entitet:
* Message

Razmjena poruka ce se izvrsavati iskljucivo u sklopu odobrenog zahtjeva za iznajmljivanje.

U paketu service i njegovom potpaketu intf nalaze se definicije metoda, onoga sta bi trebao ovaj servis da realizuje.
### MessageService
Realizuje:
* dobavljanje svih poruka u odnosu na request 
* slanje poruke
* cuvanje poruka u bazi podataka 