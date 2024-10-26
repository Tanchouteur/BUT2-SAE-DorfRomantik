# Variables
SRC_DIR = src/fr/iutfbleau/SAE31_2024_LTA/

ANI_DIR = $(SRC_DIR)animator/
BDD_DIR = $(SRC_DIR)Bdd/
CONFIG_DIR = $(SRC_DIR)config/
ENDGAME_DIR = $(SRC_DIR)endGame/
JEUX_DIR = $(SRC_DIR)jeux/
LAYERS_DIR = $(SRC_DIR)layers/
LOADING_DIR = $(SRC_DIR)loading/
MEDIA_DIR = $(SRC_DIR)media/
MENU_DIR = $(SRC_DIR)menu/
MEF_DIR = $(SRC_DIR)miseEnForme/
PARTIE_DIR = $(SRC_DIR)partieJouer/
POPUP_DIR = $(SRC_DIR)popup/

OUT_DIR = out/production/

LIB_DIR = ressources/_LIB
CLASSPATH = $(LIB_DIR)/mariadb-java-client-2.5.3.jar
MAIN_CLASS = fr/iutfbleau/SAE31_2024_LTA/ControllerMain
#MEF
BrighterHoverJComponent.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(MEF_DIR)BrighterHoverJComponent.java
DarkerHoverJcomponnent.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(MEF_DIR)DarkerHoverJcomponnent.java
StyleComponent.class : BrighterHoverJComponent.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(MEF_DIR)StyleComponent.java

#BDD
BddListeTuiles.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(BDD_DIR)BddListeTuiles.java
BddPartieJouer.class : BddListeTuiles.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(BDD_DIR)BddPartieJouer.java

#
#PopupBd.class :
#	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(BDD_DIR)ModelBDD.java $(POPUP_DIR)PopupBd.java
#ModelBDD.class&BddConnectionTask.class: BddListeTuiles.class BddPartieJouer.class PopupBd.class
#	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(BDD_DIR)ModelBDD.java $(BDD_DIR)BddConnectionTask.java
#Media
MediaPlayerManager.class : ModelPrincipale.class ClipStopListener.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(MEDIA_DIR)MediaPlayerManager.java
ClipStopListener.class : MediaPlayerManager.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(MEDIA_DIR)ClipStopListener.java
#CONFIG
Configuration.class :
	javac -cp $(CLASSPATH) -d $(OUT_DIR) $(CONFIG_DIR)Configuration.java
ConfigFileHandler.class: Configuration.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(CONFIG_DIR)ConfigFileHandler.java

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

WindowStateHandler.class : ControlerMain.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(LOADING_DIR)WindowStateHandler.java
ControlerMain.class : WindowStateHandler.class
	javac -cp $(CLASSPATH):$(OUT_DIR) -d $(OUT_DIR) $(SRC_DIR)ControlerMain.java
#Tout en une ligne
Bdd: BddListeTuiles.class BddPartieJouer.class #ModelBDD.class&BddConnectionTask.class
config : Configuration.class ConfigFileHandler.class #ConfigManager.class
miseEnForme : BrighterHoverJComponent.class DarkerHoverJcomponnent.class StyleComponent.class


popup : ControllerPopup.class ControllerVolumeChange.class VueSettingsPopup.class VueTuto.class


# TOUT compiler
compile: Bdd config miseEnForme


run: compile
	java -cp $(OUT_DIR):$(CLASSPATH) $(MAIN_CLASS)

# Cible pour nettoyer les fichiers .class
clean:
	rm -rf $(OUT_DIR)/*
