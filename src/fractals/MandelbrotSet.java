package fractals;

import processing.core.PApplet;
import tools.SubPlot;

public class MandelbrotSet {
	private int niter;
	private int x0, y0;
	private int dimx, dimy;
	
	public MandelbrotSet(int niter, SubPlot plt) {
		this.niter = niter;
		float[] bb = plt.getBoundingBox();
		x0 = (int) bb[0];
		y0 = (int) bb[1];
		dimx = (int) bb[2];
		dimy = (int) bb[3];
	}
	
	public void display(PApplet p, SubPlot plt) {
		int tt = p.millis();
		p.loadPixels();
		for (int xx = x0 ; xx < x0 + dimx ; xx++) {
			for (int yy = y0 ; yy < y0 + dimy ; yy++) {
				double[] cc = plt.getWorldCoord(xx, yy);
				Complex c = new Complex(cc);
				Complex x = new Complex();
				int i;
				for(i = 0 ; i < niter ; i++) {
					x.mult(x).add(c);
					if (x.norm() > 2) 
						break;
				}
				
		//		p.pixels[yy*p.width + xx] = (i == niter) ? p.color(0) : p.color((i%16)*16);
		        p.pixels[yy*p.width + xx] = (i == niter) ? p.color(0) : p.color(39+(251-39)*(i%16)*16/200, 1 + (140-1)*(i%16)*16/200, 0+(171-0)*(i%16)*16/200);
//		        p.pixels[yy*p.width + xx] = (i == niter) ? p.color(0) : p.color(14+(49-14)*(i%16)*16/200, 21 + (118-21)*(i%16)*16/200, 101+(204-101)*(i%16)*16/200);
//		        p.pixels[yy*p.width + xx] = (i == niter) ? p.color(0) : p.color(59+(225-59)*(i%16)*16/200, 28 + (126-28)*(i%16)*16/200, 113+(120-113)*(i%16)*16/200);
//		        p.pixels[yy*p.width + xx] = (i == niter) ? p.color(0) : p.color(2+(78-2)*(i%16)*16/200, 38 + (135-38)*(i%16)*16/200, 26+(120-26)*(i%16)*16/200);

			}
		}
		p.updatePixels();
		System.out.println(p.millis() - tt);
	}
}
