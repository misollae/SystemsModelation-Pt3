package fractals;

import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class MandelbrotApp implements IProcessingApp {
	private double[] window  = {-2, 2, -2, 2};
	private float[] viewport = {0, 0, 1, 1};
	private float[] viewport2 = {0.65f, 0, 0.35f,0.35f};

	private SubPlot plt, plt2;
	private MandelbrotSet ms;
	private int mx0, my0, mx1, my1;
	private JuliaSet js;
	private boolean showJulia = true;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		plt2 = new SubPlot(window, viewport2, p.width, p.height);

		ms = new MandelbrotSet(300, plt);
		js = new JuliaSet(300, plt2);

		ms.display(p, plt);
	}

	@Override
	public void draw(PApplet p, float dt) {
		ms.display(p, plt);
		
		double[] cc = plt.getWorldCoord(p.mouseX, p.mouseY);
		displayNewWindow(p, dt);
		if (showJulia) {
			js.display(p, plt2, new Complex(cc));
			p.noFill();
			p.strokeWeight(2);
			p.stroke(123, 56, 68);
			p.rect(0.65f * p.width, p.height - 0.35f*p.height, 0.35f * p.width - 2f, 0.35f*p.height - 2);
		}
	}

	public void displayNewWindow(PApplet p, float dt) {
		p.pushStyle();
		p.noFill();
		p.strokeWeight(3);
		p.stroke(255);
//		float width  = Math.abs(mx1 - mx0);
//		float height = (width * p.height) / p.width;
		p.rect(mx0, my0, mx1-mx0, my1-my0);
		p.popStyle();
	}
	
	@Override
	public void mousePressed(PApplet p) {
		mx0 = mx1 = p.mouseX;
		my0 = my1 = p.mouseY;
	}
	
	@Override
	public void mouseDragged(PApplet p) {
		mx1 = p.mouseX;
		my1 = p.mouseY;
	}

	@Override
	public void mouseReleased(PApplet p) {
		double[] xy0 = plt.getWorldCoord(mx0, my0);
		double[] xy1 = plt.getWorldCoord(p.mouseX, p.mouseY);
		double xmin = Math.min(xy0[0], xy1[0]);
		double xmax = Math.max(xy0[0], xy1[0]);
		double ymin = Math.min(xy0[1], xy1[1]);
		double ymax = Math.max(xy0[1], xy1[1]);
		double[] window = {xmin, xmax, ymin, ymax};
		plt = new SubPlot(window, viewport, p.width, p.height);
		plt2 = new SubPlot(window, viewport2, p.width, p.height);

		mx0 = my0 = my1 = mx1;
	}

	@Override
	public void keyPressed(PApplet p) {
		if (p.key == 'R' || p.key == 'r') {
			double[] window  = {-2, 2, -2, 2};
			plt = new SubPlot(window, viewport, p.width, p.height);
			plt2 = new SubPlot(window, viewport2, p.width, p.height);

		}
		if (p.key == 'J' || p.key == 'j') {
			this.showJulia = !showJulia;
		}
		
		
	}

}
