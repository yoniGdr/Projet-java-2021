###################################################
#                    VARIABLES                    #
###################################################

EXEC_TEST=java -jar test4poo.jar
CLASSES_DIR=cd classes
SRC_DIR=cd src
TESTS_DIR=cd test
REMOVE=rm -rf
JC_TEST= javac -classpath test4poo.jar
JAR= jar cvfm
ALL_TESTS= tests/game/action/*.java \
		   tests/game/board/*.java \
		   tests/game/farmGame/*.java \
		   tests/game/resource/*.java \
		   tests/game/tile/*.java \
		   tests/game/warGame/*.java 

###################################################



###################################################
#             COMPILATION DES CLASSES             #
###################################################

cls: clean
	@$(SRC_DIR) && javac game/*.java -d ../classes

FarmGame: cls
	@$(SRC_DIR) && javac farmGameMain/*.java -d ../classes

WarGame: cls
	@$(SRC_DIR) && javac warGameMain/*.java -d ../classes

###################################################



###################################################
#                EXECUTION DES JEU                # 
###################################################

playFarmGame: FarmGame
	$(CLASSES_DIR) && java farmGameMain.FarmGameMain Zac Yoni Isidore Emilie 
	
playWarGame: WarGame
	$(CLASSES_DIR) && java warGameMain.WarGameMain Zac Yoni Isidore Emilie 

playFarmGameI: FarmGame
	$(CLASSES_DIR) && java farmGameMain.FarmGameMainI Zac true Yoni false Isidore true
	
playWarGameI: WarGame
	$(CLASSES_DIR) && java warGameMain.WarGameMainI Zac true Yoni false Isidore true

###################################################




###################################################
#             COMPILATION DES TESTS               #
###################################################

tests: WarGame FarmGame
	@echo "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
	@cat assets/asciiArt/loading
	@$(JC_TEST) $(ALL_TESTS) -d classes
	@cat assets/asciiArt/tests
	@cat assets/asciiArt/created
###################################################


###################################################
#               EXECUTION DES TESTS               #
###################################################


%:
	$(EXEC_TEST) $@
#exemple: make game.farmGame.FarmerTest

###################################################	



###################################################
#               INTERFACE GRAPHIQUE               #
###################################################

FarmGameGUI:
	@$(SRC_DIR) && javac gameGUI/FarmGameGUI.java -d ../classes && cd ..
	@$(CLASSES_DIR) && java gameGUI.FarmGameGUI

WarGameGUI:
	@$(SRC_DIR) && javac gameGUI/WarGameGUI.java -d ../classes && cd ..
	@$(CLASSES_DIR) && java gameGUI.WarGameGUI

###################################################



###################################################
#                  DOCUMENTATION                  #
###################################################
	
doc:
	@cat assets/asciiArt/loading
	@$(SRC_DIR) && javadoc  game/*.java -d ../docs 
	@cat assets/asciiArt/doc
	@cat assets/asciiArt/created

###################################################


###################################################
#                CREATION DES JAR                 #
###################################################
	
agricole.jar: FarmGame 
	@cat assets/asciiArt/loading
	@$(CLASSES_DIR) && $(JAR) ../jar/$@ ../jar/manifest-MainAgricole game farmGameMain
	@cat assets/asciiArt/agricole
	@cat assets/asciiArt/created

guerre.jar: WarGame
	@cat assets/asciiArt/loading
	@$(CLASSES_DIR) && $(JAR) ../jar/$@ ../jar/manifest-MainGuerre game warGameMain
	@cat assets/asciiArt/guerre
	@cat assets/asciiArt/created

agricoleI.jar: FarmGame 
	@cat assets/asciiArt/loading
	@$(CLASSES_DIR) && $(JAR) ../jar/$@ ../jar/manifest-MainAgricoleI game farmGameMain
	@cat assets/asciiArt/agricoleI
	@cat assets/asciiArt/created

guerreI.jar: WarGame
	@cat assets/asciiArt/loading
	@$(CLASSES_DIR) && $(JAR) ../jar/$@ ../jar/manifest-MainGuerreI game warGameMain
	@cat assets/asciiArt/guerreI
	@cat assets/asciiArt/created

###################################################





###################################################
#                     AUTRES                      #
###################################################

all: agricole.jar guerre.jar agricoleI.jar guerreI.jar tests
	@echo "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
	@cat assets/asciiArt/intro

clean:
	@echo "cleaning..."
	@$(CLASSES_DIR)
	@$(REMOVE) game/*.class
	@cat assets/asciiArt/cleaned

.PHONY:	doc clean tests all

###################################################



#AUTHORS : Zakaria El Khayari, Yoni Gaudiere, Yao-Isidore Amevigbe
