######################################
# Author: 	Joost Vandorp, S. Smith	 #
# Revised: 	Thursday, Feb 24, 2017	 #
# Description:	"MAKEFILE"		 #
######################################

# Assumes JUnit is installed
# Assumes CLASSPATH has been set for Junit

JFLAGS = -g
#JCLASS = -cp ./src:.:$(CLASSPATH):/mnt/c/Users/anand/Desktop/MacStuff/Software_Eng/junit-4-5.jar
JCLASS = -cp ./src:.:$(CLASSPATH):/usr/share/java/junit4-4.5.jar # on mills
#AllTests
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $*.java

CLASSES = \
	src/LuT.java \
	src/PointT.java \
	src/Seq2D.java \
        src/LanduseMapT.java \
        src/DemT.java \
        src/TestPointT.java \
        src/TestLanduseMapT.java \
        src/TestDemT.java \
        src/TestPointT.java \
	src/AllTests.java \

MAIN = AllTests

default: classes

classes: $(CLASSES:.java=.class)

doc:
	doxygen doxConfig
	cd latex && $(MAKE)

test: src/$(MAIN).class
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore $(MAIN)

expt: src/A3Example.java
	$(JC) $(JCLASS) $(JFLAGS) src/A3Example.java
	$(JVM) $(JCLASS) A3Example

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class
