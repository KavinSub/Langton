public class Ant{
	static int Ant_ID = 0;

	int id;
	State state;

	// Positions of ants
	int row;
	int col;

	// Creates an ant, keeps track of its ID
	public Ant(int row, int col){
		state = State.LEFT;
		id = Ant_ID;
		Ant_ID++;

		this.row = row;
		this.col = col;
	}

	public void printAnt(){
		System.out.printf("Ant %d is located at (row: %d, col: %d) and is moving %s.\n", id, row, col, getState());
	}

	// Sets the row of the ant
	public void setRow(int row){
		this.row = row;
	}

	// Sets the col of the cant
	public void setCol(int col){
		this.col = col;
	}

	// Rotates the ant right
	public void rotateRight(){
		state = State.values()[(state.ordinal() + 1) % 4];
	}

	// Rotates the ant left
	public void rotateLeft(){
		int modval = (state.ordinal() - 1) % 4;
		if(modval < 0){
			modval += 4;
		}
		state = State.values()[modval];
	}

	// Prints movement state of ant
	public String getState(){
		String direction;
		switch(state){
			case LEFT:
				direction = "left";
				break;
			case UP:
				direction = "up";
				break;
			case RIGHT:
				direction = "right";
				break;
			case DOWN:
				direction = "down";
				break;

			default:
				direction = "invalid";	
				break;
		}

		return direction;
		//System.out.printf("Ant %d is moving %s\n", id, direction);
	}
}

// The movement state of the ant
enum State{
	LEFT, UP, RIGHT, DOWN
}