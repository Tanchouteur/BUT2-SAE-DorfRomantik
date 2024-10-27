# Variables
PROJECT_NAME = Dorfjavatik
SRC_DIR = src
OUT_DIR = out/production/SAE31_2024
LIB_DIR = ressources/_LIB
RESOURCES_DIR = ressources
PACKAGE = fr/iutfbleau/SAE31_2024_LTA
MAIN_CLASS = fr.iutfbleau.SAE31_2024_LTA.ControlerMain


compile:
	javac -d $(OUT_DIR) -cp "$(LIB_DIR)/mariadb-java-client-2.5.3.jar" \
		$(SRC_DIR)/$(PACKAGE)/*/*/*.java \
		$(SRC_DIR)/$(PACKAGE)/*/*.java \
		$(SRC_DIR)/$(PACKAGE)/*.java

# copie les ressources
cp_resources: compile
	mkdir -p $(OUT_DIR)/Audio/MusicInGame $(OUT_DIR)/Audio/TuileSound $(OUT_DIR)/Audio \
	          $(OUT_DIR)/Images/Sujet $(OUT_DIR)/Images $(OUT_DIR)/META-INF \
	          $(OUT_DIR)/_LIB
	cp $(LIB_DIR)/* $(OUT_DIR)/_LIB
	cp -r $(RESOURCES_DIR)/Audio/MusicInGame/* $(OUT_DIR)/Audio/MusicInGame/
	cp -r $(RESOURCES_DIR)/Audio/TuileSound/* $(OUT_DIR)/Audio/TuileSound/
	cp -r $(RESOURCES_DIR)/Audio/* $(OUT_DIR)/Audio/
	cp -r $(RESOURCES_DIR)/Images/Sujet/* $(OUT_DIR)/Images/Sujet/
	cp -r $(RESOURCES_DIR)/Images/* $(OUT_DIR)/Images/
	cp -r $(RESOURCES_DIR)/META-INF/* $(OUT_DIR)/META-INF/

jar: cp_resources
	jar cfm  $(PROJECT_NAME).jar $(RESOURCES_DIR)/META-INF/MANIFEST.MF -C $(OUT_DIR) .


run: jar
	java -cp "$(PROJECT_NAME).jar:$(LIB_DIR)/mariadb-java-client-2.5.3.jar" $(MAIN_CLASS)

clean:
	rm -rf out/*
	rm -f $(PROJECT_NAME).jar
