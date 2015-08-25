import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel{

	JButton play, pause, playStep, step;
	JTextField setDelay, setHeight;
	JTextField setSteps;
	JLabel setDelayLabel;

	JLabel stepLabel, steps;
	
	// Creates a control panel, and lays out components
	ControlPanel(){

		initializeButtons();
		initializeTextFields();
		initializeLabels();

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.ipadx = 18;

		c.gridx = 0;
		c.gridy = 0;
		add(play, c);

		c.gridx = 1;
		c.gridy = 0;
		add(pause, c);

		c.gridx = 1;
		c.gridy = 1;
		add(step, c);

		c.gridx = 0;
		c.gridy = 2;
		add(playStep, c);

		c.gridx = 1;
		c.gridy = 2;
		add(setSteps, c);

		c.gridx = 0;
		c.gridy = 3;
		add(setDelayLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		add(setDelay, c);

		/*c.gridx = 0;
		c.gridy = 4;
		add(setHeightLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		add(setHeight, c);*/

		c.gridx = 1;
		c.gridy = 4;
		add(stepLabel, c);

		c.gridx = 2;
		c.gridy = 4;
		add(steps, c);
	}

	// These methods initalize the components
	private void initializeButtons(){
		play = new JButton("Play");
		pause = new JButton("Pause");
		step = new JButton("Step");
		playStep = new JButton("Play Steps");
	}

	private void initializeTextFields(){
		setDelay = new JTextField(6);
		setHeight = new JTextField(6);
		setSteps = new JTextField(6);
	}

	private void initializeLabels(){
		setDelayLabel = new JLabel("Set Delay");
		stepLabel = new JLabel("Steps: ");
		steps = new JLabel("0");
	}

	// Adds action listeners and sets action commands to listener
	public void addActionListeners(ActionListener listener){
		play.addActionListener(listener);
		pause.addActionListener(listener);
		step.addActionListener(listener);
		playStep.addActionListener(listener);

		play.setActionCommand("play");
		pause.setActionCommand("pause");
		step.setActionCommand("step");
		playStep.setActionCommand("playstep");
	}

	// Updates the step value displayed on the label
	public void updateStepsLabel(){
		steps.setText(Integer.toString(Simulation.steps));
	}

}