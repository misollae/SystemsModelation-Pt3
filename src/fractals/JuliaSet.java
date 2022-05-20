package fractals;

import processing.core.PApplet;
import tools.SubPlot;

public class JuliaSet {
	private int niter;
	private int x0, y0;
	private int dimx, dimy;
	
	public JuliaSet(int niter, SubPlot plt) {
		this.niter = niter;
		float[] bb = plt.getBoundingBox();
		x0 = (int) bb[0];
		y0 = (int) bb[1];
		dimx = (int) bb[2];
		dimy = (int) bb[3];
	}
	
	public void display(PApplet p, SubPlot plt, Complex c) {
		int tt = p.millis();
		p.loadPixels();
		for (int xx = x0 ; xx < x0 + dimx ; xx++) {
			for (int yy = y0 ; yy < y0 + dimy ; yy++) {
				double[] cc = plt.getWorldCoord(xx, yy);
				Complex z = new Complex(cc);
				Complex x = new Complex();
				int i;
				for(i = 0 ; i < niter ; i++) {
					z.mult(z).add(c);
					if (z.norm() > 2) 
						break;
				}
				
		        p.pixels[yy*p.width + xx] = (i == niter) ? p.color(0) : p.color(39+(251-39)*(i%16)*16/200, 1 + (140-1)*(i%16)*16/200, 0+(171-0)*(i%16)*16/200);

			}
		}
		p.updatePixels();
		System.out.println(p.millis() - tt);
	}
}
