# BedrockCombact

Ein einfaches Minecraft-Plugin für Spigot/Paper, das den 1.9-Angriffscooldown praktisch deaktiviert, indem die Angriffsgeschwindigkeit von Spielern auf `1024` gesetzt wird.

## Funktionen

- keine Commands
- keine Permissions
- setzt für Spieler die Attack Speed auf `1024`
- wendet die Änderung an bei:
  - Serverstart (für bereits online Spieler)
  - Join
  - Respawn
  - Weltwechsel

## Build

Voraussetzungen:

- Java 21
- Maven

Build im Projektordner:

```powershell
mvn clean package
```

Falls `mvn` auf deinem System nicht im `PATH` ist, kannst du Maven entweder installieren oder über deine IDE bauen.

## Installation

1. Die erzeugte JAR aus `target/` in den `plugins/`-Ordner deines Servers kopieren.
2. Server neu starten.

## Hinweis

Das Plugin setzt nur den Angriffscooldown per Attack-Speed-Attribut auf Bedrock-ähnliches Verhalten (`1024`). Es fügt bewusst keine Commands oder weitere Combat-Systeme hinzu.

