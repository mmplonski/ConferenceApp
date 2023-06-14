# ConferenceApp
## Krótki opis
Jest to aplikacja do zarządzania konferencjami, napisana w Javie 17 przy pomocy SpringBoota.
Należy upewnić się, że komputer z którego korzystamy ma zainstalowaną tą wersje Javy. 
Aplikacja korzysta z bazy danych H2. W repozytorium znajduje się plik ConferenceApp.postman_collection.json w którym znajduje się 
przygotowana kolekcja endpointów
## Uruchomienie
Przed uruchomieniem należy upewnić się, że na porcie 8080 nie działa inna aplikacja Javowa.
Aplikację możną uruchomić na dwa sposoby:
1. Za pomocą środowiska programistycznego, preferowany InteliJ IDEA
Należy sklonować projekt https://github.com/mmplonski/ConferenceApp
2. Dla systemów Linux/MacOS
  Za pomocą skryptu start.sh.
  Należy pobrać projekt a następnie przejść w terminalu do lokalizacji projektu, i wpisać polecenie sh start.sh.
  Dla systemów Windows
  Należy pobrać projekt a następnie uruchomić plik start.bat
  
## Założenia
1. Klient jest rejestrowany w momencie wykonania rezerwacji na prelekcje. Jeśli w bazie nie znajduje się klient o podanym loginie i emailu
to zostanie uworzony i dodany do bazy.
2. Login i email muszą być unikalne. Nie można dodać dwóch użytkowników o takim samym emailu. I nie można dodać użytkownika jeśli jego login jest już zajęty.
3. Aby można było zapisać użytkownika na prelekcje, prelekcje musi posiadać wolne miejsce oraz użytkownik nie może brać udziału w innej prelekcji o tej samej godzinie. 
4. Po wykonanej rezerwacji, w pliku powiadomienia.txt zostanie dodane potwiedzenie.

## Komunikacja z aplikacją 
### Wyświetlenie planu konferencji 
 GET http://localhost:8080/conference
 
 ### Zestawienie wykładów wg zainteresowania (procentowy udział uczestników w danym wykładzie)
 GET http://localhost:8080/conference
 
  ### Zestawienie ścieżek tematycznych wg zainteresowania (procentowy udział)
 GET http://localhost:8080/conference/registerUsers/statements/paths

### Wyświetlenie listy zarejestrowanych użytkowników wraz z ich adresami e-mail
 GET http://localhost:8080/conference/registerUsers
 
 ### Dodanie rezerwacji
 POST http://localhost:8080/reservations/{prelectionId}
 
 prelectionId - id prelekcji na który chcemy zarejestrować użytkownika
 
 Przykładowe body:
```
{
    "login": "mplonski",
    "email": "mplonski@wp.pl"
}
```
  ###  Usunięcie rezerwacji
 DELETE http://localhost:8080/reservations/{id}
 
 id - id rezerwacji 
 
 ###  Pobranie rezerwacji dla konkretnego użytkownika
  GET http://localhost:8080/reservations/user/{userId}
  
  userId - id użytkownika
  
  ###  Zmiana emaila użytkownika
  PATCH http://localhost:8080/users/{id}
  
  id - id użytkownika
  ```
{
    "email": "newEmail@wp.pl"
}
```
  


