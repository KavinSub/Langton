import java.io.*;

class MainSetup{

	static int[][] field;
	public static void main(String[] args) throws IOException{
		if(args.length != 0){
			readField(args[0]);
		}else{
			readField("test.txt");
		}
		Simulation.setField(field);
		Simulation.setColorMap();

		// Checks that the appropriate files are present
		if(args.length >= 2 && args[1].equals("c")){
			if(args.length == 4){
				File tileMap = new File(args[2]);
				File dirMap = new File(args[3]);
				if(tileMap.exists() && dirMap.exists()){
					readTileMap(args[2]);
					readDirectionMap(args[3]);
				}
			}else{
				readTileMap("tile.txt");
				readDirectionMap("dir.txt");
			}
		}else{
			Simulation.setTileMapDefault();
			Simulation.setDirectionMapDefault();
		}
		
		MainDisplay display = new MainDisplay();
	}

	// Reads in the field from a text file
	// Check README for file specification
	private static void readField(String filename) throws IOException{

		BufferedReader reader = null;

		// Create the file reader
		try{	
			reader = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException ex){
			printError(String.format("File %s not found", filename));
		}

		int row = 0, col = 0;
		// If the file reader was successfully created
		if(reader != null){

			String line;
			String tokens[];

			// First line is always the number of rows and columns
			line = reader.readLine();
			tokens = line.split(" ");
			try{
				row = Integer.parseInt(tokens[0]);
				col = Integer.parseInt(tokens[1]);
			}catch(NumberFormatException ex){
				printError("Line 1 must specify row and col using integers");
			}

			field = new int[row][col];

			// If the empty option is set, an empty field of 1's and 0's is set
			if(tokens.length == 3 && tokens[2].toUpperCase().equals("EMPTY")){
				for(int i = 0; i < row; i++){
					for(int j = 0; j < col; j++){
						field[i][j] = 0;
					}
				}
			}else{
				// Next part of file should specify field
				int i = 0, j = 0;
				try{
					for(i = 0; i < row; i++){
						line = reader.readLine();
						for(j = 0; j < col; j++){
							char temp = line.charAt(j);

							if(!MainSetup.isValidChar(temp)){
								printError(String.format("Invalid char '%c' found at row: %d col: %d", temp, i + 1, j + 1));
							}else{
								field[i][j] = temp - 48;
							}
						}
					}
				// In the event that the field is formatted incorrectly

				// If there are not enough cols
				}catch(StringIndexOutOfBoundsException ex){  
					printError(String.format("Row %d has %d cols, should contain %d cols", i + 1, j, col));
				// If there are not enough rows
				}catch(NullPointerException ex){
					printError(String.format("Row %d is missing", i + 1));
				}
			}

			// Loop reads commands
			line = reader.readLine();
			while(line != null){
				tokens = line.split(" ");
				if(tokens[0].toUpperCase().equals("ANT")){
					try{
						Simulation.createAnt(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

					}catch(NumberFormatException ex){
						printError("Coordinate values must be integers");
					}
				}

				line = reader.readLine();
			}

			reader.close();
		}else{
			printError("Reader not created");
		}

		if(Simulation.ants.size() == 0){
			Simulation.createAnt(0, 0);
		}


	}

	// Prints error message, and exits program
	public static void printError(String message){
		System.out.printf("Error: %s\n", message);
		System.exit(0);
	}

	// Test function that prints field
	private static void printField(){
		// Prints the size values of the array
		System.out.printf("row: %d, col %d\n", field.length, field[0].length);

		// Prints the field itself
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[0].length; j++){
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

	// Maps the users custom color map
	private static void readTileMap(String filename) throws IOException{
		if(filename == null) filename = "tile.txt";
		//Sets all values in tile map to default of -1
		for(int i = 0; i < Simulation.tileMap.length; i++){
			Simulation.tileMap[i] = -1;
		}
		BufferedReader reader = null;

		// Create the file reader
		try{	
			reader = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException ex){
			printError(String.format("File %s not found\n", filename));
		}

		for(int i = 0; i < Simulation.tileMap.length; i++){
			String line = reader.readLine();
			if(line != null){
				String[] tokens = line.split(" ");
				String color1 = tokens[0].toUpperCase();
				String color2 = tokens[1].toUpperCase();
				int index = Simulation.searchColorMap(Simulation.stringToColor(color1));
				Simulation.tileMap[index] = Simulation.searchColorMap(Simulation.stringToColor(color2)); 
			}else{
				break;
			}
		}

		reader.close();

	}

	// Creates the direction map
	private static void readDirectionMap(String filename) throws IOException{
		if(filename == null) filename = "dir.txt";
		// Defaults map value to -1
		for(int i = 0; i < Simulation.directionMap.length; i++){
			Simulation.directionMap[i] = -1;
		}

		BufferedReader reader = null;

		// Create the file reader
		try{	
			reader = new BufferedReader(new FileReader(filename));
		}catch(FileNotFoundException ex){
			printError(String.format("File %s not found for direction map\n", filename));
		}

		// Maps Color to direction based on dir.txt file specification
		for(int i = 0; i < Simulation.directionMap.length; i++){
			String line = reader.readLine();
			if(line != null){
				String[] tokens = line.split(" ");
				String color = tokens[0];
				String direction = tokens[1];
				int dir = -1;
				if(direction.toUpperCase().equals("RIGHT")){
					dir = 0;
				}else if(direction.toUpperCase().equals("LEFT")){
					dir = 1;
				}
				Simulation.directionMap[Simulation.searchColorMap(Simulation.stringToColor(color))] = dir;
			}else{
				break;
			}
		}

		reader.close();		
	}

	private static boolean isValidChar(char c){
		if(Character.isDigit(c) || c == ':' || c == ';' || c == '<'){
			return true;
		}else{
			return false;
		}
	}

}
