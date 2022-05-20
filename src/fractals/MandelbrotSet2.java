package fractals;

import processing.core.PApplet;
import tools.SubPlot;

public class MandelbrotSet2 {
	private int niter;
	private int x0, y0;
	private int dimx, dimy;
	
	public MandelbrotSet2(int niter, SubPlot plt) {
		this.niter = niter;
		float[] bb = plt.getBoundingBox();
		x0 = (int)bb[0];
		x0 = (int)bb[1];
		x0 = (int)bb[2];
		x0 = (int)bb[3];
	}
	
	public void display(PApplet p, SubPlot plt) {
		for(int xx=x0; xx<x0+dimx;xx++) {
			for (int yy=y0; yy<y0+dimy;yy++) {
				double[] cc = plt.getWorldCoord(xx, yy);		
				Complex c = new Complex(cc);
				Complex x = new Complex();
				int i;
				for(i=0;i<niter;i++) {
					x.mult(x).add(c);
					System.out.println("aaa");
					if (x.norm() >2) 
						break;
				}
				int color = ( i == niter) ? p.color(0) : p.color(255);
				p.stroke(color);
				p.point(xx,  yy);
			}
		}
	}
}
