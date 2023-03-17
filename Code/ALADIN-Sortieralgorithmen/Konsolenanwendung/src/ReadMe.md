# Programmablauf vom Generator für Sortieralgorithmen

Es gibt eine Klasse _Start_, welche den _AppContext_ instanziiert und 
_AppContext.startKonfiguration()_ aufruft.

Im _AppContext_ steckt die Programmlogik, welche wie folgt aussieht: 

- -- 

### Funktion: startKonfiguration()
* Konfiguration des Feldes und der Sortierung mittels _Konfigurator_Sortierung_
* Aufruf: _feld_konfigurieren()_, _sortierung_konfigurieren()_
  * Nutzereingaben 
  * Output: Länge des Feldes (Anzahl), Zeichenart (numerisch / alphanumerisch), Reihenfolge
    (aufsteigend / absteigend), Sortieralgorithmus (Bubblesort / Insertsort / Quicksort / Mergesort)
* Aufruf: _feld_generieren()
  * Generierung des Feldes unter Berücksichtigung der Nutzereingaben Anzahl und Zeichenart

### Verhalten der Klasse setzen (Strategy pattern)
* das Strategy pattern sorgt dafür, dass dem _AppContext_ ein gewisses Verhalten zugewiesen werden kann 
* dies macht den Code übersichtlicher und kürzer, denn die Funktion _sortieren()_ muss nur 
  einmal aufgerufen werden und im eigentlichen Sortieralgorithmus kann auf weitere if-Abfragen 
  verzichtet werden
* setSortierverhalten: setzt das Sortierverhalten (numerisch aufsteigend / numerisch absteigend / 
  alphanumerisch aufsteigend / alphanumerisch absteigend)
* setSortieralgorithmus: setzt den Sortieralgorithmus mittels angegebenen Algorithmus

### Funktion: sortieren()
* jeder Sortieralgorithmus hat seine eigene _sortieren()_ Funktion
* durch das vorherige Setzen des Sortieralgorithmus wird die _sortieren()_ Funktion des gewählten Algorithmus
  aufgerufen
* die Funktion sortiert das generierte Feld schrittweise und speichert jeden Sortierschritt 
  in einer ArrayList _liste_system_ vom Typ Sortierschritt 
* jeder Sortierschritt enthält das aktuelle Feld, die beiden getauschten Elemente, sowie die Indexe 
  der jeweiligen Elemente
* um zu entscheiden, ob der Algorithmus das Feld aufsteigend oder absteigend sortieren soll und
  ob es sich um ein numerisches oder alphanumerisches Feld handelt, wird dem Sortieralgorithmen 
  das vorher festgelegte Sortierverhalten mitgegeben 
* jede Entscheidung, welche sich auf die Sortierung auswirkt ist ausgelagert und wird
  je nach Verhalten aufgerufen
* der Algorithmus bricht automatisch ab, sobald das Feld fertig sortiert wurde

### Funktion: startAuswertung()
* Konfiguration der Auswertung mittels Konfigurator_Auswertung
* Aufruf: _auswertung_konfigurieren()_
  * Nutzereingaben
  * Output: Auswertungsverfahren
* setAuswertungsverhalten: setzt das Auswertungsverfahren
* mögliche Auswertungsverfahren:
  * Gesamtes Feld ausgeben 
  * Feld schrittweise ausgeben
  * Übungsmodus (Feld mit Hilfe lösen)
  * Prüfungsmodus (Feld ohne Hilfe lösen)
* Aufruf der Funktion _auswerten()_

### Funktion auswerten()
* jedes Auswertungsverfahren hat eine eigene _auswerten()_ Funktion 
* dank des Strategy pattern muss auch diese im _AppContext_ nur einmal aufgerufen werden

#### 1. Gesamtes Feld ausgeben
* in diesem Auswertungsverfahren werden alle Sortierschritte, sowie die getauschten 
  Elemente zur besseren Übersich ausgegeben 

#### 2. Feld schrittweise ausgeben
* in diesem Auswertungsverfahren hat der Nutzer die Möglichkeit, sich die Sortierschritte
  schrittweise (mittels ENTER) anzuzeigen und bei Bedarf zum letzten Schritt zurückzuspringen

#### 3. Übungsmodus
* in diesem Auswertungsverfahren sortiert der Nutzer das generierte Feld selbst 
* hierfür werden die Indexe der zu tauschenden Elemente eingegeben 
* ist der Schritt richtig, wird er vom System akzeptiert und der Nutzer wird
  zur Eingabe der nächsten Indexe aufgefordert 
* ist der Schritt falsch, so wird der Nutzer darüber benachrichtigt und muss diesen 
  Schritt neu sortieren 
* nach drei falschen Eingaben bietet das System hilfe an 
* akzeptiert der Nutzer, so wird der aktuelle Sortierschritt vom System angezeigt
* am Ende gibt es eine kleine Auswertung, welche die Fehler des Nutzers zeigt

#### 4.1 Prüfungsmodus: Sortierung
* in diesem Auswertungsverfahren sortiert der Nutzer das generierte Feld selbst 
* hierfür werden die Indexe der zu tauschenden Elemente eingegeben 
* jeder Sortierschritt wird wie im Sortieralgorithmus in einer Arraylist _liste_nutzer_ vom 
  Typ Sortierschritt gespeichert 
* die Sortierung kann beendet werden, in dem der Nutzer für beide Indexe 'F' eingibt 
* sollte der Nutzer im ersten Index versehentlich eine falsche Zahl eingegeben haben,  
  besteht die Möglichkeit, im zweiten Index die selbe Zahl einzugeben, um den Schritt
  aus der Auswertung zu nehmen

#### 4.2 Prüfungsmodus: Vergleich
* nachdem der Nutzer mit der Sortierung fertig ist, beginnt die eigentliche Auswertung 
  der Lösung
* zuerst werden die beiden ArrayLists _liste_system_ und _liste_nutzer_ vollständig ausgegeben
* anschließend werden die einzelnen Sortierschritte in den beiden Listen vergleichen 
* hierfür dient die Funktion _Arrays.equals()_
* im Vergleich der jeweiligen Sortierschritte sind folgende Szenarien möglich:
  * Schritt ist richtig
  * Schritt ist falsch
  * Schritt ist zusätzlich - der Nutzer hat zu viele Schritte für die Sortierung benötigt
  * Schritt fehlt - der Nutzer hat Schritte übersprungen oder vergessen
* je nach eingetretenem Szenario beim Vergleich, wird diesem Szenario ein Punkt gutgeschrieben

##### 4.3 Prüfungsmodus: Punktevergabe
* am Ende des Vergleichs kommt es zur Berechnung der Gesamtpunktzahl 
* grundsätzlich bringt jede Aufgabe insgesamt 10 Punkte
* dies dient zur einheitlichen Punktevergabe, unabhängig der benötigten Sortierschritte
* damit bei richtiger Sortierung 10 Punkte erreicht werden können, muss ein Punkt eine Werigkeit bekommen 
* diese Wertigkeit berechnet sich aus: 10 / (Anzahl der Sortierschritte, die das System benötigt hat)
* angenommen das System hat 5 Schritte gebraucht, dann ist die Wertigkeit für einen Punkt 2
* hat der Nutzer nun alle 5 Schritte richtig sortiert, so errechnet sich seine Punkzzahl aus 2 * 5, 
  was eine Gesamtpunktzahl von 10 Punkten bringt
* Punktabzüge gibt es für folgende Szenarios: falsche Schritte und zusätzliche Schritte
* fehlende Sortierschritte führen zu keinem Punktabzug, werden bei der Auswertung jedoch mit aufgeführt
* Generell wird die Gesamtpunktzahl somit folgendermaßen berechnet: 
* (richtige Schritte) * Wertigkeit - (falsche Schritte) * Wertigkeit - (zusätzliche Schritte) * Wertigkeit

### Programmabschluss
* nach abgeschlossener Auswertung bleiben dem Nutzer 3 Möglichkeiten: 
  * Das Feld und die Sortierung können neu konfiguriert werden
  * Es kann ein neues Auswertungsverfahren gewählt werden 
  * das Programm kann beendet werden 
* je nach Auswahl des Nutzers springt das Programm zum gegebenen Punkt 