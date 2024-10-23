# Variables
SRC_DIR = src
OUT_DIR = out
LIB_DIR = ressources/_LIB
CLASSPATH = $(LIB_DIR)/mariadb-java-client-2.5.3.jar
MAIN_CLASS = fr/iutfbleau/SAE31_2024_LTA/ControllerMain  # Corrected name

# Liste des fichiers sources
Bdd_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/Bdd/BddListeTuiles.java \
            $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/Bdd/BddPartieJouer.java \
            $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/Bdd/ModelBDD.java

Config_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/config/ConfigFileHandler.java \
               $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/config/ConfigManager.java \
               $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/config/Configuration.java

EndGame_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/endGame/ControllerSaveGame.java \
                $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/endGame/ModelEndGame.java \
                $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/endGame/VueScoreScreen.java

Jeux_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/Controller2D.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/ControllerPoseTuile.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/ModelMatrice.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/VueInfoPanel.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/ControllerMouseWheelDecalage.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/ModelJeux.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/ModelPoche.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/VueJeux.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/jeux/VueTuile.java

Media_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/media/MediaPlayerManager.java \
              $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/media/ModelMediaLoader.java \
              $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/media/MusiqueTrack.java

Menu_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/menu/ControllerFocus.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/menu/ControllerMenuCard.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/menu/ModelMenu.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/menu/VueMenu.java

PartieJouer_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/partieJouer/ControllerPartieJouerBTN.java \
                    $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/partieJouer/ControllerSearchPartieJouer.java \
                    $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/partieJouer/ModelPartieJouer.java \
                    $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/partieJouer/VuePartieJouer.java

Popup_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/popup/ControllerPopup.java \
              $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/popup/ControllerVolumeChange.java \
              $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/popup/VueSettingsPopup.java \
              $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/popup/VueTuto.java

Main_FILES = $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/ControllerMain.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/ModelPrincipale.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/VuePrincipale.java

# Compilation des différents sous-dossiers dans OUT_DIR
Bdd: $(Bdd_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

config: popup $(Config_FILES)  # Added dependency on popup
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

endGame: $(EndGame_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

jeux: $(Jeux_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

media: $(Media_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

menu: $(Menu_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

partieJouer: $(PartieJouer_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

popup: $(Popup_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

main: $(Main_FILES)
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $^

# Cible pour tout compiler
compile: Bdd config endGame media partieJouer jeux menu popup main

# Exécution du programme principal après compilation
run: compile
	java -cp $(OUT_DIR):$(CLASSPATH) $(MAIN_CLASS)

# Cible pour nettoyer les fichiers .class
clean:
	rm -rf $(OUT_DIR)/*

# Cible par défaut
default: run
