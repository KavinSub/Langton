import java.util.*;
import java.awt.Color;
public class Simulation{

	static int[][] field;

	static int[] directionMap = new int[13];
	static int[] tileMap = new int[13];
	static Color[] colorMap = new Color[13];

	static ArrayList<Ant> ants = new ArrayList<Ant>();

	static int steps = 0;
	// Adds an ant to the list given the coordinates
	public static void createAnt(int row, int col){
		Ant ant = new Ant(row, col);
		ants.add(ant);
	}

	// Test function that prints states of the ants
	public static void printAnts(){
		for(int i = 0; i < ants.size(); i++){
			ants.get(i).printAnt();
		}
	}

	public static void setField(int[][] field){
		Simulation.field = field;
	}

	public static void step(){
		// rotates ants
		for(int i = 0; i < ants.size(); i++){
			Ant ant = ants.get(i);

			if(directionMap[field[ant.row][ant.col]] == 0){
				ant.rotateRight();
			}else{
				ant.rotateLeft();
			}
		}

		// Changes tile
		for(int i = 0; i < ants.size(); i++){
			Ant ant = ants.get(i);
			field[ant.row][ant.col] = tileMap[field[ant.row][ant.col]];
		}

		// Moves ants, wraps around world
		for(int i = 0; i < ants.size(); i++){
			Ant ant = ants.get(i);
			if(ant.getState().equals("left")){
				ant.col--;
				if(ant.col < 0) ant.col += field[0].length;
			}else if(ant.getState().equals("down")){
				ant.row = (ant.row + 1) % field.length;
			}else if(ant.getState().equals("right")){
				ant.col = (ant.col + 1) % field[0].length;
			}else{
				ant.row--;
				if(ant.row < 0) ant.row += field[0].length;
			}
		}

		Simulation.steps++;
	}

	// Default tile mapping. Reverses tiles upon contact with ant
	public static void setTileMapDefault(){
		tileMap[0] = 1;
		tileMap[1] = 0;
	}

	// Default direction mapping. 0 turns right, 1 turns left
	public static void setDirectionMapDefault(){
		directionMap[0] = 0;
		directionMap[1] = 1;
	}

	// A hardcoded map for the colors, included on README
	public static void setColorMap(){
		colorMap[0] = Color.WHITE;
		colorMap[1] = Color.BLACK;
		colorMap[2] = Color.BLUE;
		colorMap[3] = Color.CYAN;
		colorMap[4] = Color.DARK_GRAY;
		colorMap[5] = Color.GRAY;
		colorMap[6] = Color.GREEN;
		colorMap[7] = Color.LIGHT_GRAY;
		colorMap[8] = Color.MAGENTA;
		colorMap[9] = Color.ORANGE;
		colorMap[10] = Color.PINK;
		colorMap[11] = Color.RED;
		colorMap[12] = Color.YELLOW;
	}

	// Performs linear search on the color map to determine its index
	public static int searchColorMap(Color color){
		for(int i = 0; i < colorMap.length; i++){
			if(colorMap[i] == color){
				return i;
			}
		}
		return -1;
	}

	// Returns a Color object based on the string value
	public static Color stringToColor(String color){
		String upColor = color.toUpperCase();

		if(upColor.equals("WHITE")){
			return Color.WHITE;
		}else if(upColor.equals("BLACK")){
			return Color.BLACK;
		}else if(upColor.equals("BLUE")){
			return Color.BLUE;
		}else if(upColor.equals("CYAN")){
			return Color.CYAN;
		}else if(upColor.equals("DARK_GRAY")){
			return Color.DARK_GRAY;
		}else if(upColor.equals("GRAY")){
			return Color.GRAY;
		}else if(upColor.equals("GREEN")){
			return Color.GREEN;
		}else if(upColor.equals("LIGHT_GRAY")){
			return Color.LIGHT_GRAY;
		}else if(upColor.equals("MAGENTA")){
			return Color.MAGENTA;
		}else if(upColor.equals("ORANGE")){
			return Color.ORANGE;
		}else if(upColor.equals("PINK")){
			return Color.PINK;
		}else if(upColor.equals("RED")){
			return Color.RED;
		}else if(upColor.equals("YELLOW")){
			return Color.YELLOW;
		}else{
			MainSetup.printError(String.format("Color %s not found\n", color));
			return null;
		}
		
	}
}