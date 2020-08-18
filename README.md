# Tower-Defence

A Tower Defence game made with JavaFX as a school project.

![](https://i.imgur.com/rivLUfi.jpeg)

## Getting started

**Installation**

```
git clone https://github.com/Groupe-Projet-S2/HAL-9000-Defender.git
```

**Dependencies**
* [JUnit4](https://junit.org/junit4/)
* [Java 1.8](https://www.java.com/fr/download/)
* [JavaFX](https://docs.oracle.com/javase/8/javafx/api/toc.htm)

## Structure

```
├── src/
    ├── Main.java
    ├── Models/
    │   ├── Entities/
    │   │   ├── Bonus/
    │   │   │   ├── AdBlock.java
    │   │   │   ├── Bonus.java
    │   │   │   ├── CKleaner.java
    │   │   │   └── SudVPN.java
    │   │   ├── Virus/
    │   │   │   ├── Listeners/
    |   │   │   │   ├── AdwareListener
    |   │   │   │   └── RansomwareListener
    │   │   │   ├── Adware.java
    │   │   │   ├── Ransomware.java
    │   │   │   ├── Trojan.java
    │   │   │   ├── Virus.java
    │   │   │   ├── Worm.java
    │   │   │   └── Zombie.java
    │   │   ├── Tower/
    │   │   │   ├── Afast.java
    │   │   │   ├── CPU.java
    │   │   │   ├── Damageable.java
    │   │   │   ├── Firewall.java
    │   │   │   ├── GoodwareBytes.java
    │   │   │   ├── IVG.java
    │   │   │   ├── KilobitDefender
    │   │   │   └── Tower.java
    │   │   ├── Projectile/
    │   │   │   ├── Dynamic.java
    │   │   │   ├── Motionless.java
    │   │   │   ├── Projectile.java
    │   │   │   └── Static.java
    │   │   └── Entity.java
    │   ├── Environment/
    │   │   ├── Graphs.java
    │   │   ├── Location.java
    │   │   ├── SpriteSheet.java
    │   │   ├── Tile.java
    │   │   ├── TileMap.java
    │   │   ├── Vectore.java
    │   │   └── World.java
    │   └── Game
    ├── Controllers/
    │   ├── Listeners/
    │   │   ├── HostileBoxesListener.java
    │   │   ├── ProjectileListener.java
    │   │   ├── TowerListener.java
    │   │   └── VirusListener.java
    │   └── MapController.java
    ├── Views/
    │   ├── AlertBox.java
    │   ├── AudioMenu.java
    │   ├── MapView.java
    │   ├── ProjectileView.java
    │   ├── TooltipView.java
    │   ├── TowerView.java
    │   ├── VirusView.java
    │   └── sample.fxml ← to be renamed in 'MainView.fxml'
    └── Utils/ ← to be renamed in 'resources'
```

## License

None.

## Credits

**For the parodic use of their name**
* Avast Antivirus
* BitDefender
* AVG Antivirus
* MalwareBytes
* NordVPN
* CCleaner

**Developpers**
* [Cedric-F](https://github.com/Cedric-F)
* [Laefuu](https://github.com/Laefuu)
* [WFRousseau](https://github.com/WFRousseau)


## Compatibility

Developped on IntelliJ IDEA (201.6668.121 +)
Java 8
