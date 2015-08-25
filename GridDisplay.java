import javax.swing.*;
import java.awt.*;

class GridDisplay extends JPanel{
	static int defaultLengthFactor = 10;
	int lengthFactor;
	GridDisplay(){
		calculateLengthFactor();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		displayField(g2);
		drawAnts(g2);
	}

	// Draws out the rectangular grid
	public void displayField(Graphics2D g2){
		
		for(int i = 0; i < Simulation.field.length; i++){
			for(int j = 0; j < Simulation.field[0].length; j++){
				g2.setColor(Simulation.colorMap[Simulation.field[i][j]]);
				Rectangle rectangle = new Rectangle(j * lengthFactor, i * lengthFactor, lengthFactor, lengthFactor);
				g2.fill(rectangle);

				// Draws black outline of rectangles
				g2.setColor(Color.BLACK);
				g2.draw(rectangle);

			}
		}
	}

	// Draws the ants
	public void drawAnts(Graphics2D g2){
		for(int i = 0; i < Simulation.ants.size(); i++){
			Ant ant = Simulation.ants.get(i);
			int gridX = ant.col * lengthFactor;
			int gridY = ant.row * lengthFactor;

			Rectangle rectangle = new Rectangle(gridX, gridY, lengthFactor, lengthFactor);

			g2.setColor(Color.RED);
			g2.draw(rectangle);
			g2.fill(rectangle);
		}
	}

	// Calculates the length factor based on the field
	public void calculateLengthFactor(){
		int max = 1;
		if(Simulation.field.length > Simulation.field[0].length){
			max = Simulation.field.length;
		}else{
			max = Simulation.field[0].length;
		}

		lengthFactor = (int) Math.floor((double) SimulationDisplay.length / max);

		if(lengthFactor > 10){
			lengthFactor = GridDisplay.defaultLengthFactor;
		}


	}	
}

