# Variables
PROJECT_NAME = jeux
SRC_DIR = src
OUT_DIR = out/production/SAE31_2024
LIB_DIR = ressources/_LIB
RESOURCES_DIR = ressources
MAIN_CLASS = fr.iutfbleau.SAE31_2024_LTA.Vue.VueMenu
compile:
	javac -d $(OUT_DIR) -cp $(SRC_DIR)/fr/iutfbleau/SAE31_2024_LTA/**/*.java

jar: compile
	jar cfm $(PROJECT_NAME).jar $(RESOURCES_DIR)/META-INF/MANIFEST.MF -C $(OUT_DIR) . \
	    -C $(RESOURCES_DIR)/Audio . \
	    -C $(RESOURCES_DIR)/Images . \
	    -C $(RESOURCES_DIR)/Video . \
	    -C $(LIB_DIR) .

copy-dlls: jar
	mkdir -p $(OUT_DIR)/bin
	cp $(OUT_DIR)/bin/

run: copy-dlls
	java -cp "$(OUT_DIR):$(OUT_DIR)/bin/*" -jar $(PROJECT_NAME).jar

clean:
	rm -rf $(OUT_DIR)/*
	rm -f $(PROJECT_NAME).jar

.PHONY: compile jar copy-dlls run clean
