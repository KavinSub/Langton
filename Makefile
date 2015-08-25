# Makefile for Langton's Ant project

MainSetup: MainSetup.class Ant.class Simulation.class MainDisplay.class ControlPanel.class SimulationDisplay.class GridDisplay.class
	jar cvfm LangtonAnt.jar Manifest *.class
	rm *.class

MainSetup.class: MainSetup.java
	javac MainSetup.java

Ant.class: Ant.java
	javac Ant.java

Simulation.class: Simulation.java
	javac Simulation.java	

MainDisplay.class: MainDisplay.java
	javac MainDisplay.java

ControlPanel.class: ControlPanel.java
	javac ControlPanel.java

SimulationDisplay.class: SimulationDisplay.java
	javac SimulationDisplay.java

GridDisplay.class: GridDisplay.java
	javac GridDisplay.java