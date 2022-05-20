package setup;
import processing.core.PApplet;

public interface IProcessingApp
{
	public void setup(PApplet p);
	public void draw(PApplet p, float dt);
	public void mousePressed(PApplet p);
	public void mouseReleased(PApplet p);
	public void keyPressed(PApplet p);
	public void mouseDragged(PApplet p);
}
