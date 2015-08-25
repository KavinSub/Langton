import javax.swing.*;
import java.awt.*;

class SimulationDisplay extends JPanel{

	GridDisplay gridDisplay;
	static int length = 800;

	SimulationDisplay(){
		setLayout(null);

		gridDisplay = new GridDisplay();

		add(gridDisplay);
		centerDisplay();
	}

	// Centers the grid display using absolute positioning
	public void centerDisplay(){
		int x = 0, y = 0;
		if(Simulation.field[0].length < SimulationDisplay.length / gridDisplay.lengthFactor){
			x = (length / 2) - (gridDisplay.lengthFactor * Simulation.field[0].length) / 2;
		}
		if(Simulation.field.length < SimulationDisplay.length / gridDisplay.lengthFactor){
			y = (length / 2) - (gridDisplay.lengthFactor * Simulation.field.length) / 2;
		}
		gridDisplay.setBounds(x, y, gridDisplay.lengthFactor * Simulation.field[0].length + 1, gridDisplay.lengthFactor * Simulation.field.length + 1);
	}

	public void updateGrid(){
		gridDisplay.repaint();
	}


}