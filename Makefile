# Variables
SRC_DIR = src/fr/iutfbleau/SAE31_2024_LTA/

BDD_DIR = $(SRC_DIR)Bdd/
CONFIG_DIR = $(SRC_DIR)config/
ENDGAME_DIR = $(SRC_DIR)endGame/
JEUX_DIR = $(SRC_DIR)jeux/
MEDIA_DIR = $(SRC_DIR)media/
MENU_DIR = $(SRC_DIR)menu/
PARTIE_DIR = $(SRC_DIR)partieJouer/
POPUP_DIR = $(SRC_DIR)popup/

OUT_DIR = out
LIB_DIR = ressources/_LIB
CLASSPATH = $(LIB_DIR)/mariadb-java-client-2.5.3.jar
MAIN_CLASS = fr/iutfbleau/SAE31_2024_LTA/ControllerMain  # Corrected name

# Liste des fichiers sources
Bdd_FILES =

Config_FILES = $(SRC_DIR)config/ConfigFileHandler.java \
               $(SRC_DIR)config/ConfigManager.java \
               $(SRC_DIR)config/Configuration.java

EndGame_FILES = $(SRC_DIR)endGame/ControllerSaveGame.java \
                $(SRC_DIR)endGame/ModelEndGame.java \
                $(SRC_DIR)endGame/VueScoreScreen.java

Jeux_FILES = $(SRC_DIR)jeux/Controller2D.java \
             $(SRC_DIR)jeux/ControllerPoseTuile.java \
             $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LAT/jeux/ModelTuile \
             $(SRC_DIR)jeux/ModelMatrice.java \
             $(SRC_DIR)jeux/VueInfoPanel.java \
             $(SRC_DIR)jeux/ControllerMouseWheelDecalage.java \
             $(SRC_DIR)jeux/ModelJeux.java \
             $(SRC_DIR)jeux/ModelPoche.java \
             $(SRC_DIR)jeux/VueJeux.java \
             $(SRC_DIR)jeux/VueTuile.java

Media_FILES = $(SRC_DIR)media/MediaPlayerManager.java \
              $(SRC_DIR)media/ModelMediaLoader.java \
              $(SRC_DIR)media/MusiqueTrack.java

Menu_FILES = $(SRC_DIR)menu/ControllerFocus.java \
             $(SRC_DIR)menu/ControllerMenuCard.java \
             $(SRC_DIR)menu/ModelMenu.java \
             $(SRC_DIR)menu/VueMenu.java

PartieJouer_FILES = $(SRC_DIR)partieJouer/ControllerPartieJouerBTN.java \
                    $(SRC_DIR)partieJouer/ControllerSearchPartieJouer.java \
                    $(SRC_DIR)partieJouer/ModelPartieJouer.java \
                    $(SRC_DIR)partieJouer/VuePartieJouer.java

Popup_FILES = $(SRC_DIR)popup/ControllerPopup.java \
              $(SRC_DIR)popup/ControllerVolumeChange.java \
              $(SRC_DIR)popup/VueSettingsPopup.java \
              $(SRC_DIR)popup/VueTuto.java

Main_FILES = $(SRC_DIR)ControllerMain.java \
             $(SRC_DIR)ModelPrincipale.java \
             $(SRC_DIR)VuePrincipale.java


BddListeTuiles.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(BDD_DIR)BddListeTuiles.java
BddPartieJouer.class : BddListeTuiles.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(BDD_DIR)BddPartieJouer.java
ModelBDD.class : BddListeTuiles.class BddPartieJouer.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(BDD_DIR)ModelBDD.java

Bdd: BddListeTuiles.class BddPartieJouer.class ModelBDD.class

Configuration.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(CONFIG_DIR)Configuration.java
ConfigFileHandler.class: Configuration.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(CONFIG_DIR)ConfigFileHandler.java

#ModelPrincipale <-> VuePrincipale
ModelPrincipale.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(SRC_DIR)ModelPrincipale.java
#interdépendances entre ConfigManager -> ControllerPopup -> Vuetuto -> ConfigManager + ControllerPopup <-> VuePrincipale
ConfigManager.class&ControllerPopup.class&VueTuto.class&VuePrincipale.class: #Configuration.class VueTuto.class ControllerPopup.class ConfigManager.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(CONFIG_DIR)ConfigManager.java $(POPUP_DIR)ControllerPopup.java \
	$(POPUP_DIR)ControllerPopup.java $(POPUP_DIR)VueTuto.java $(SRC_DIR)VuePrincipale.java \

config : Configuration.class ConfigFileHandler.class #ConfigManager.class

ControllerPopup.class : VueTuto.class ConfigManager.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(POPUP_DIR)ControllerPopup.java
ControllerVolumeChange.class : ConfigManager.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(POPUP_DIR)ControllerVolumeChange.java
#pas possible pour le moment
VueSettingsPopup.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(POPUP_DIR)VueSettingsPopup.java
VueTuto.class :
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(POPUP_DIR)VueTuto.java
popup : ControllerPopup.class ControllerVolumeChange.class VueSettingsPopup.class VueTuto.class


# TOUT compiler
compile: Bdd config #endGame media partieJouer jeux menu popup main

run: compile
	java -cp $(OUT_DIR):$(CLASSPATH) $(MAIN_CLASS)

# Cible pour nettoyer les fichiers .class
clean:
	rm -rf $(OUT_DIR)/*
