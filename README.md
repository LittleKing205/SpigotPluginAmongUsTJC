# SpigotPluginAmongUsTJC

Ein kleines Plugin, die Among Us in der Mincraft Version von TheJoCraft Blizzor und RGB Pixel erweitert

Falls ihr das Project nicht kennt. Schaut unbedinnt bei TheJoCraft vorbei. -> [Youtube](https://www.youtube.com/user/thejominecraft) / [Among Us Playlist](https://www.youtube.com/watch?v=kUU34zO52uc&list=PL8yutqH6-uWoloIFyaAI1YRKk8DL8ITCQ)

Das Chatsystem wird folgendermaßen verändert.

  - Während der Runde ist der Chat gesperrt.
  - Im Meeting, können alle überlebenen miteinander schreiben.
  - Geister können die ganze zeit untereinander schreiben.
  
Zudem können die Spielerfarben abgeändert werden.
  
# Ingame Befehle

| Befehl | Permission | Beschreibung |
| ------ | ------ | ------ |
| color {Farbe} | amongus.set-color | Ändert die eigene Spielerfarbe |
| reset | amongus.reset | Startet den Reset der Welt (Rotes Glas in der Lobby) |

# Installation

Die neuste Version des Plugins [hier](https://github.com/LittleKing205/SpigotPluginAmongUsTJC/releases/) herunterladen.

Und einfach in den Plugins Ordner eures Spigot/Bukkit Servers kopieren.

# Konfiguration

Alle Nachrichten, die zu einem Spieler gesendet werden, können in verändert werden.

```yaml
messages:
  chat-only-in-meeting: Chatting and speaking is only permitted in a meeting during a round.
  color:
    already-assigned: You already have the color.
    no-params: No parameter was given.
    wrong-params: A wrong Parameter was given.
    color-changed: Your color was successful changed.
    color-not-changed: Your color is already taken.
    not-in-hub: To change your color, you must be in the Hub.
```

Um die Farben zu ändern, die eingegeben werden ist Folgender Teil der config.yml wichtig.
Die Reihenfolge darf nicht verändert werden, da sonst eine falsche Farbe gesetzt wird.

```yaml
player-colors:
  - white
  - orange
  - magentha
  - light_blue
  - yellow
  - green
  - pink
  - black
  - red
  - blue
```

# Bekannte Fehler.

  - Sobald man sich bereit setzt kann man nicht Chatten.
  - Die Farbe lässt sich auch während einer Runde verändern. (Was zu Fehlern kommen kann)
 
# TODO:
  - ~~Command für das resetten der Map (Rotes Glas Am anfang der Map) hinzufügen.~~
  - Chunkloader hinzufügen.
  - Commands für die Einstellungen hinzufügen.
  - ~~Command für das setzen einer Farbe.~~
  - _Eure Ideen_
  
Falls Fehler auftauchen sollten, bitte ein Ticket öffnen oder gern mithelfen ;) Pull requests sind gern erwünscht.
