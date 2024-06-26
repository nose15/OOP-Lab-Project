# Architektura #

## 1. Architektura aplikacji ##
### 1.1. Frontend (paczka app) ###
Obejmuje interfejs użytkownika oraz całą logikę związaną z wyświetlaniem a także przyjmowaniem danych od użytkownika.
![Diagram frontendu](./front-diagram.png)
#### View ####
Klasa odpowiedzialna za zarządzanie interfejsem użytkownika. Znajduje się tam logika odpowiedzialna za obsługę interfejsu, a także wyświetlanie grafu, będącego mapą, na której odbywa się symulacja.
#### Input manager ####
Zawarty poprzez kompozycje w instancji View. Jest odpowiedzialny za przetwarzanie inputu użytkownika i wysyłanie go do backendu.

#### Obsługa eventów ####
Jest realizowana poprzez Event Listenery, które dziedziczą po BaseEventListenerze. Każdy z nich implementuje jakiś rodzaj Event Listenerów z biblioteki Swing odpowiedzialnej za interfejs użytkownika.
Za jej pomocą odbierany jest input użytkownika.

### 1.2. Backend (paczka simulation) ###
Obejmuje logikę związaną z obsługą symulacji oraz odpowiedzialną za obsługę osobnego wątku symulacji.
![Diagram Backendu](./back-diagram.png)
#### Simulation Manager ####
Odpowiedzialnością tej klasy jest zarządzanie całym procesem symulacji. Zawiera ona SimInputHandler oraz SimStateUpdater odpowiedzialne za komunikację z interfejsem, a także SimulationThread, która jest odpowiedzialna za zarządzanie wątkiem, na którym działa symulacja.
#### SimStateUpdater ####
Jest odpowiedzialna za wysyłanie danych dotyczących obecnego stanu symulacji do GUI. Serializuje dane do postaci DTO i umieszcza je w kolejce podanej poprzez dependency injection.

#### SimInputHandler ####
Odpowiedzialny za interpretację inputu użytkownika podanego w postaci DTO. Wywołuje metody SImulationManagera i przez to umożliwia kontrolowanie go z poziomu GUI.
#### Simulation Thread ####
Odpowiedzialny za runtime symulacji. Dziedziczy po wbudowanej klasie Thread, co umożliwia uruchomienie go z poziomu SimulationManagera jako osobny wątek i kontrolowanie go poprzez kolejkę wstrzykniętą do niego poprzez dependency injection. 

## 2. Architektura Symulacji ##
![Diagram Symulacji](./simulation-diagram.png)

#### Simulation ####
Klasa, która zawiera logikę odpowiedzialną za wykonywanie kolejnych kroków symulacji. Każdy krok jest wykonywany co ustalony odstęp czasu, dzięki czemu symulacja działa z tą samą prędkością na wszystkich komputerach.
Zawiera ona obiekt klasy graf, jako mapę, na której odbywa się symulacja. Zawiera również agentów, u których w każdym kroku wywołuje metodę act(), która wykonuje ich zachowanie.

#### Agent ####
Bazowa, wirtualna klasa agenta. Udostępnia podstawowe metody pozwalające na poruszanie się po mapie oraz wykonywanie akcji na węzłach w sieci (Nodes)
Dzieci klasy agent (Hacker i ITSpec) zawierają logikę odpowiedzialną za podejmowanie decyzji o wykonywaniu akcji zdefiniowanych w klasie Agent.

#### Node ####
Pasywny element symulacji. Jest to element grafu. W obecnej wersji nie wykonuje żadnych akcji. Zawiera swój stan i jest elementem, na którym hakerzy i informatycy mogą wykonywać hakowanie lub naprawę.