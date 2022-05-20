package fractals;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class Forest implements IProcessingApp {

	private double[] window  = {-15, 15, 0, 15};
	private float[] viewport = {0, 0, 1, 1};
	private SubPlot plt;
	private List<Tree> forest;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		forest = new ArrayList<Tree>();
		
		Rule[] rules = new Rule[1];
//		rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
//		rules[1] = new Rule('F', "FF");
		rules[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
	}

	@Override
	public void draw(PApplet p, float dt) {
		float[] bb = plt.getBoundingBox();
		p.rect(bb[0], bb[1], bb[2], bb[3]);
		
		for (Tree tree : forest) {
			tree.grow(dt);
			tree.display(p, plt);
		}
	}

	@Override
	public void mousePressed(PApplet p) {
		double[] w   = plt.getWorldCoord(p.mouseX, p.mouseY);
		PVector pos  = new PVector((float) w[0], (float) w[1]);
		Tree tree;
		if (p.random(100) < 50) {
			Rule[] rules = new Rule[1];
			rules[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
			tree = new Tree("F", rules, pos, 0.4f, PApplet.radians(22.5f), 3, 0.5f, 1f, p);			
		} else {
			Rule[] rules = new Rule[2];
			rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
			rules[1] = new Rule('F', "FF");
			tree = new Tree("X", rules, pos, 0.4f, PApplet.radians(22.5f), 3, 0.5f, 1f, p);			
		}
		
		forest.add(tree);
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
