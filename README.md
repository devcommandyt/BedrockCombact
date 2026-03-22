# BedrockCombact

Ein einfaches Minecraft-Plugin für Spigot/Paper, das den 1.9-Angriffscooldown praktisch deaktiviert, indem die Angriffsgeschwindigkeit von Spielern auf `1024` gesetzt wird, und zusätzlich die Locator Bar per Konfiguration steuern kann.

## Funktionen

- keine Commands
- keine Permissions
- setzt für Spieler die Attack Speed auf `1024`
- kann die Locator Bar global pro Server-Konfiguration aktivieren oder deaktivieren
- wendet die Änderung an bei:
  - Serverstart (für bereits online Spieler)
  - Join
  - Respawn
  - Weltwechsel
  - Welt-Load für die Locator-Bar-Einstellung

## Build

Voraussetzungen:

- Java 21
- Maven

Build im Projektordner:

```powershell
mvn clean package
```

Oder mit deinem festen Maven-Pfad unter Windows:

```powershell
Set-Location "C:\Users\PC\IdeaProjects\BedrockCombact"
& "C:\temp\apache-maven-3.9.14\bin\mvn.cmd" clean package
```

Falls `mvn` auf deinem System nicht im `PATH` ist, kannst du Maven entweder installieren oder über deine IDE bauen.

## Installation

1. Die erzeugte JAR aus `target/` in den `plugins/`-Ordner deines Servers kopieren.
2. Server neu starten.

## Konfiguration

Datei: `src/main/resources/config.yml`

```yaml
locator-bar:
  enabled: false
```

- `true` = Locator Bar bleibt aktiv
- `false` = Locator Bar wird in allen Welten deaktiviert

## Hinweis

Das Plugin setzt den Angriffscooldown per Attack-Speed-Attribut auf Bedrock-ähnliches Verhalten (`1024`) und steuert optional die Bukkit-GameRule `LOCATOR_BAR`. Es fügt bewusst keine Commands oder weitere Combat-Systeme hinzu.

