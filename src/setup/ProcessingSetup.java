package setup;
import fractals.ChaosGameApp;
import fractals.Forest;
import fractals.LSystemApp;
import fractals.MandelbrotApp;
import fractals.MandelbrotApp2;
import processing.core.PApplet;

public class ProcessingSetup extends PApplet {
	public static IProcessingApp app;
	private int lastUpdate;
	
	@Override
	public void settings() {
		size(800, 600);
	}
	
	@Override
	public void setup() {
		app.setup(this);
		lastUpdate = millis();
	}
	
	@Override
	public void draw() {
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		app.draw(this, dt);
	}
	
	@Override
	public void mousePressed() {
		app.mousePressed(this);
	}
	
	@Override
	public void mouseDragged() {
		app.mouseDragged(this);
	}
	
	@Override
	public void mouseReleased() {
		app.mouseReleased(this);
	}
	
	@Override
	public void keyPressed() {
		app.keyPressed(this);
	}
	
	public void setApp(IProcessingApp sentApp) {
		app = sentApp;
	}
	
	public static void main(String[] args) {
		//app = new LSystemApp();
		//app = new Forest();
		app = new MandelbrotApp2();
		//app = new ChaosGameApp();
		PApplet.main(ProcessingSetup.class);
	}
}