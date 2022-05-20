package fractals;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class LSystemApp implements IProcessingApp {

	private LSystem lsys;
	private double[] window  = {-15, 15, -15, 15};
	private float[] viewport = {0f, 0f, 1f, 1f};
	
	private PVector startingPos = new PVector();
	private SubPlot plt;
	private Turtle turtle;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		
		Rule[] rules = new Rule[1];
//		rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
//		rules[1] = new Rule('F', "FF");
//		rules[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
		rules[0] = new Rule('F', "FF+F-F+F+FF");
		//rules[1] = new Rule('G', "GG");
		
		lsys   = new LSystem("F+F+F+F", rules);
		turtle = new Turtle(5, PApplet.radians(90));
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(63, 38, 34);
		float[] bb = plt.getBoundingBox();
		turtle.setPose(startingPos, PApplet.radians(90), p, plt);
	    turtle.render(lsys, p, plt);
	}

	@Override
	public void mousePressed(PApplet p) {
		System.out.println(lsys.getSequence());
		lsys.nextGeneration();
		turtle.scaling(0.5f);
	}

	@Override
	public void keyPressed(PApplet p) {
		
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
