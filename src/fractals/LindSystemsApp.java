package fractals;

import java.util.ArrayList;
import java.util.List;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import g4p_controls.GTextField;
import g4p_controls.GTextIconBase;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class LindSystemsApp implements IProcessingApp {
	private double[] window  = {-15, 15, -15, 15};
	private float[] viewport = {0, 0, 1, 1};
	private SubPlot plt;
	private List<Tree> fractals;
	private GButton updateBttn;
	private GTextField axiomField;
	private GLabel label1, label2, label3, label4;
	private GTextField FField, GField;
	private GTextField angleField;
	private GTextField FFieldS;
	private GTextField GFieldS;
	
	String axiom;
	float angle;
	int niter;

	Rule[] rules;
	private GLabel label5;
	private GTextField niterField;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		
		axiom = "XF";
		angle = PApplet.radians(60);
		niter = 5;
		
		rules = new Rule[2];
		rules[0] = new Rule('X', "YF+XF+Y");
		rules[1] = new Rule('Y', "XF-YF-X");
		
		fractals = new ArrayList<Tree>();
		setUpG4P(p);
	}

	
	public void setUpG4P(PApplet p) {
		label1 = new GLabel(p, p.width - 125, 10, 100, 20, "Axiom: ");
		label2 = new GLabel(p, p.width - 105, 55, 100, 20, "~> ");
		label3 = new GLabel(p, p.width - 105, 77, 100, 20, "~> ");
		label4 = new GLabel(p, p.width - 125, 32, 100, 20, "Angle: ");
		label5 = new GLabel(p, p.width - 125, 102, 100, 20, "N. Iter: ");


		label1.setLocalColorScheme(p.color(200));
		label2.setLocalColorScheme(p.color(200));
		label3.setLocalColorScheme(p.color(200));
		label4.setLocalColorScheme(p.color(200));
		label5.setLocalColorScheme(p.color(200));

		
		updateBttn = new GButton(p, p.width - 60, 126, 50, 20, "Set!");
		updateBttn.setLocalColorScheme(180, true);
		updateBttn.addEventHandler(this, "handleUpdate");
		axiomField = new GTextField(p, p.width - 82, 9f, 70, 20);
		axiomField.setText("XF");
		
		FField = new GTextField(p, p.width - 82, 55f, 70, 20);
		FField.setText("YF+XF+Y");
		FFieldS = new GTextField(p, p.width - 122, 55f, 12, 20);
		FFieldS.setText("X");

		GFieldS = new GTextField(p, p.width - 122, 78f, 12, 20);
		GFieldS.setText("Y");
		
		GField = new GTextField(p, p.width - 82, 78f, 70, 20);
		GField.setText("XF-YF-X");
		angleField = new GTextField(p, p.width - 82, 32f, 70, 20);
		angleField.setNumeric(0f, 360f, 60f);
		angleField.setText("60");
		
		niterField = new GTextField(p, p.width - 82, 102f, 70, 20);
		niterField.setNumeric(0, 1000, 5);
		niterField.setText("5");
	}
	
	public void handleUpdate(GButton button, GEvent event) {
		if (button == updateBttn && event == GEvent.CLICKED) {
			if (FFieldS.getText() == "" || FField.getText() == "") {System.out.println("At least one rule must be set!"); return;}
			if (FFieldS.getText().length() > 1) {System.out.println("Rule symbol too big"); return;}

			if (GFieldS.getText() == "" && FField.getText() == "") {
				rules = new Rule[1]; 
				rules[0] = new Rule(FFieldS.getText().charAt(0), FField.getText());
			} else {
				if (GFieldS.getText().length() > 1) {System.out.println("Rule symbol too big"); return;}
				rules = new Rule[2];
				rules[0] = new Rule(FFieldS.getText().charAt(0), FField.getText());
				rules[1] = new Rule(GFieldS.getText().charAt(0), GField.getText());
			}

			this.axiom = axiomField.getText();
			this.angle = PApplet.radians(angleField.getValueF());
			this.niter = niterField.getValueI();
		}
	}
	
	@Override
	public void draw(PApplet p, float dt) {
		p.background(63, 38, 34);
		for (Tree fractal : fractals) {
			fractal.grow(dt);
			fractal.display(p, plt);
		}
	}

	@Override
	public void mousePressed(PApplet p) {
		System.out.println(p.mouseY);
		if (p.mouseX > 650 && p.mouseY < 170) return;
		double[] w   = plt.getWorldCoord(p.mouseX, p.mouseY);
		PVector pos  = new PVector((float) w[0], (float) w[1]);
		Tree fractal;
		fractal = new Tree(axiom, rules, pos, 1f, angle, niter, 0.5f, 1f, p);			

		fractals.add(fractal);
	}

	@Override
	public void keyPressed(PApplet p) {
		if (p.key == 'R' || p.key == 'r') {
			fractals.clear();
		}
		
	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(PApplet p) {
		// TODO Auto-generated method stub
		
	}

}
