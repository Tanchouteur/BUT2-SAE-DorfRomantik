# Information BDD
## SLQ pour recrée la bdd : 
### ListeTuiles
```sql 
CREATE TABLE `ListeTuiles` (
       `id` int(11) NOT NULL AUTO_INCREMENT,
       `seed` int(11) NOT NULL,
       `BestScore` int(11) NOT NULL DEFAULT 0,
       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci


```

### PartieJouer
```sql 
CREATE TABLE `PartieJouer` (
       `id` int(11) NOT NULL AUTO_INCREMENT,
       `PlayerName` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nom du joueur',
       `Score` int(11) NOT NULL COMMENT 'Score fait par le joueur',
       `ListeTuile` int(11) NOT NULL,
       PRIMARY KEY (`id`),
       KEY `PartieListeTuiles___fk` (`ListeTuile`),
       CONSTRAINT `PartieListeTuiles___fk` FOREIGN KEY (`ListeTuile`) REFERENCES `ListeTuiles` (`id`)   
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Chaque partie jouer jusquau bout se enregstrer ici afin d''avoir une scoreboard et un bestscore'
```

### Trigger pour la table PartieJouer qui permet de mettre a jour le bestscore a chaque nouvelle partie enregistrer
```sql
CREATE TRIGGER UpdateBestScoreAfterInsert
AFTER INSERT ON PartieJouer
FOR EACH ROW
BEGIN
    DECLARE currentBestScore INT;
    
    SELECT BestScore INTO currentBestScore
    FROM ListeTuiles
    WHERE id = NEW.ListeTuile;

    IF NEW.Score > currentBestScore THEN
        UPDATE ListeTuiles
        SET BestScore = NEW.Score
        WHERE id = NEW.ListeTuile;
    END IF;
END;
```

## SQL Pour remplir les tables : 

### ListeTuiles : 
```sql

```

### PartieJouer :
```sql

```