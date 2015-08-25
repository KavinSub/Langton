import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	public class MainDisplay extends JFrame implements ActionListener{
		
	Timer timer;
	SimulationDisplay simulationDisplay;
	ControlPanel controlPanel;
	int runsLeft = 0;
	public MainDisplay(){

		setTitle("Langton's Ant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add Panels here
		controlPanel = new ControlPanel();
		controlPanel.setPreferredSize(new Dimension(300, 800));
		add(controlPanel, BorderLayout.LINE_START);

		simulationDisplay = new SimulationDisplay();
		simulationDisplay.setPreferredSize(new Dimension(SimulationDisplay.length, SimulationDisplay.length));
		add(simulationDisplay, BorderLayout.CENTER);
		
		controlPanel.addActionListeners(this);
		// Call pack
		pack();
		setResizable(false);
		setVisible(true);

		timer = new Timer(10, this);
		timer.setActionCommand("timer");
	}

	public void actionPerformed(ActionEvent e){
		/*Simulation.step();
		simulationDisplay.updateGrid();*/
		String command = e.getActionCommand();
		
		if(command.equals("step")){
			step();
		}else if(command.equals("timer")){
			timer();
		}else if(command.equals("pause")){
			pause();
		}else if(command.equals("play")){
			play();
		}else if(command.equals("playstep")){
			playStep();
		}

		try{
			timer.setDelay(Math.abs(Integer.parseInt(controlPanel.setDelay.getText())));
		}catch(NumberFormatException ex){}

	}
	
	// Updates simulation, and updates drawing
	public void step(){
		Simulation.step();
		simulationDisplay.updateGrid();
		controlPanel.updateStepsLabel();
	}

	// Pauses simulation
	public void pause(){
		timer.stop();
		controlPanel.step.setEnabled(true);
		controlPanel.play.setEnabled(true);
		controlPanel.playStep.setEnabled(true);
	}

	// Plays simulation
	public void play(){
		timer.start();
		controlPanel.step.setEnabled(false);
		controlPanel.playStep.setEnabled(false);
	}

	public void timer(){
		if(runsLeft != 0){
			runsLeft--;
			controlPanel.setSteps.setText(Integer.toString(runsLeft));
			if(runsLeft == 0){
				timer.stop();
				controlPanel.step.setEnabled(true);
				controlPanel.play.setEnabled(true);
				controlPanel.playStep.setEnabled(true);
				controlPanel.pause.setEnabled(true);
			}
		}
		step();
	}

	// Runs n steps of the simulation
	public void playStep(){
		try{
			runsLeft = Math.abs(Integer.parseInt(controlPanel.setSteps.getText()));
			timer.start();
			controlPanel.step.setEnabled(false);
			controlPanel.play.setEnabled(false);
			controlPanel.pause.setEnabled(false);
			controlPanel.playStep.setEnabled(false);
		}catch(NumberFormatException ex){}
	}

}
