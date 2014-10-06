DIST_DIR := build/distributions
OUTPUT_FILE := c3185790_assignment2.zip
FILES := src/main/webapp 

.PHONY: dist prep clean

prep: TEMP = $(DIST_DIR)/temp
prep:
	@- mkdir -p $(TEMP)
	@- cp -R src/main/webapp/* $(TEMP)
	@- mkdir -p $(TEMP)/WEB-INF/classes/game
	@- cp -R src/main/java/* $(TEMP)/WEB-INF/classes/game

dist: prep
	@- cd $(DIST_DIR)/temp && zip ../$(OUTPUT_FILE) * -r

clean:
	@- rm -rf $(DIST_DIR)
