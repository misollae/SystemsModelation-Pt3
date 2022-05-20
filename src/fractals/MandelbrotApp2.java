package fractals;

import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class MandelbrotApp2 implements IProcessingApp{

	private double[] window = {-2, 2, -2, 2};
	private float[] viewport = {0, 0, 1, 1};
	private SubPlot plt;
	private MandelbrotSet2 ms;
	
	@Override
	public void setup(PApplet p) {
		
		plt = new SubPlot(window, viewport, p.width, p.height);
		
		ms = new MandelbrotSet2(300, plt);
		
		ms.display(p, plt);
		System.out.println("aaa");
	}

	@Override
	public void draw(PApplet p, float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub
		
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