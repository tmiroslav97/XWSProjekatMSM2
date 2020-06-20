# Rent-A-Car - XWS projekat
Fakultetski projekat iz XML i WS.

Ovde je dat pregled svih mikroservisa koji su definisani i link ka njihovom detaljnom opisu.

Autori:
* [Svetlana Antesevic](https://github.com/SvetlanaAnt)
* [Magdalena Lakic](https://github.com/magdalenalakic)
* [Miroslav Tomic](https://github.com/tmiroslav97)

### MonolitApp
Monolitna agentska aplikacija sa backendom i frontendom.

[Detaljno](MonolitApp/README.md)
(Port: 8083)

### Spisak mikroservisa

Naziv mikroservisa | Kratak opis | Port | Detljano
------------ | ------------- | ------------- | -------------
Eureka service discovery | Za konfiguraciju service discovery je koriscena eureka. |  8761 |  [Detaljno](eureka/README.md)
Zuul gateway service | Za gateway service je koriscen zuul. |  8082 |  [Detaljno](zuul/README.md)
Authentication service | / |  8084 |  [Detaljno](Services/authentication-service/README.md)
Ad search service | / |  8085 |  [Detaljno](Services/ad-search-service/README.md)
Ad service | / |  8086 |  [Detaljno](Services/ad-service/README.md)
Codebook service | / |  8087 |  [Detaljno](Services/codebook-service/README.md)
Price list and discount service | / |  8088 |  [Detaljno](Services/price-list-and-discount-service/README.md)
Car request service | / | 8090 |  [Detaljno](Services/car-request-service/README.md)
Message service | / |  8091 |  [Detaljno](Services/message-service/README.md)
Email service | Servis ce biti podignut u cloud- u. |  / |  [Detaljno](Services/email-service/README.md)
Front service | Frontend za glavni backend aplikacije. |  80 |  [Detaljno](Services/front/README.md)