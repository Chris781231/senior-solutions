Ebben a feladatban filmeket kell tárolni adatbázisban, Spring Data JPA segítségével.

Legyen egy `Movie` entitás. Minden filmnek legyen egy egyedi azonosítója (`id`, ehhez legyen megadva
a generálási stratégia), egy címe (`title`) és értékelései (`ratings`, amely egy `ElementCollection`).
Legyen a `Movie` osztálynak egy `addRating()` metódusa, amely adjon hozzá egy új értékelést az értékelések
listájához. Ha még nem érkezett értékelés a filmre, akkor példányosítsa is le először a listát.

A controller osztály alapértelmezetten a `/api/movies` URL-en legyen elérhető. Lehessen benne lekérni a
filmeket (ez egyszerű listázást jelent), valamint lehessen létrehozni is új filmet, ehhez csupán a címét
kelljen megadni a request body-ban. Az `/{id}/rating` URL-en keresztül lehessen értékelést adni a megadott
id-jú filmre. A második és a harmadik metódus DTO-t adjon vissza.

Az adatbázisban természetesen két külön táblában kell, hogy megjelenjenek a filmek (`movies` tábla) és a
kapcsolódó értékelések (`movie_ratings` tábla, ebben az egyik oszlop külső kulccsal hivatkozik a
filmek id-jára).

Adatbázisnak Docker konténerben  elindított MariaDB-t használj!