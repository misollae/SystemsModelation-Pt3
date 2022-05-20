package fractals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import g4p_controls.GTextField;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class ChaosGameApp implements IProcessingApp {
	private double[] window  = {0, 15, 0, 15};
	private float[] viewport = {0, 0, 1, 1};
	private SubPlot plt;
	private float baseDist;
	
	private List<PVector> vertices;
	private List<PVector> chaosVertices;
	private List<PVector> lastSelectedVertices;

	private PVector selectedVertice;

	private List<Integer> colors;
	private List<Integer> constraints;

	private PVector positionX;
	private int numVertices;
	private float jump;
	private boolean pause;
	private GButton updateBttn;
	private GTextField jumpField;
	
	@Override
	public void setup(PApplet p) {
		plt      = new SubPlot(window, viewport, p.width, p.height);
		vertices = new ArrayList<PVector>();
		chaosVertices        = new ArrayList<PVector>();
		lastSelectedVertices = new ArrayList<PVector>();


		colors        = new ArrayList<Integer>();
		constraints   = new ArrayList<Integer>();
		
		PVector vertice1 = new PVector(p.width / 2, (float) 0.27 * p.height);
		PVector vertice2 = new PVector((float) 0.27 * p.width,(float) 0.8 * p.height);
		PVector vertice3 = new PVector((float) 0.27 * p.width + PVector.dist(vertice1, vertice2), (float) 0.8 * p.height);

		vertices.add(vertice1);
		vertices.add(vertice2);
		vertices.add(vertice3);
		
		baseDist = PVector.dist(vertice1, vertice2);
		numVertices = 3;
		
		positionX = new PVector(p.width/2, p.height/2);
		
		colors.add(p.color(252, 199, 174));
		colors.add(p.color(138, 202, 181));
		colors.add(p.color(222, 206, 186));
		colors.add(p.color(209, 209, 192));
		colors.add(p.color(197, 213, 196));
		
		jump = 0.5f;
		
		setUpG4(p);
	}
	
	public void setUpG4(PApplet p) {
		GLabel label = new GLabel(p, p.width - 190, 10, 100, 20, "Jump Value: ");
		label.setLocalColorScheme(p.color(200));
		updateBttn = new GButton(p, p.width - 60, 10, 50, 20, "Set!");
		updateBttn.setLocalColorScheme(180, true);
		updateBttn.addEventHandler(this, "handleUpdate");
		jumpField = new GTextField(p, p.width - 115, 9.5f, 50, 20);
		jumpField.setNumeric(0f, 1f, 0.5f);
		jumpField.setText("0.5");
	}
	
	public void handleUpdate(GButton button, GEvent event) {
		if (button == updateBttn && event == GEvent.CLICKED) {
			this.jump = jumpField.getValueF();
			System.out.println("Jump ~> " + jump);
		}
	}
	

	@Override
	public void draw(PApplet p, float dt) {
		p.background(63, 38, 34);
		
		p.textSize(13.5f);
		p.text("Tap corresponding numeric keys to toggle (availability may depend on number of sides):", 20,  p.height - 94 + 5);
		
		if (constraints.contains(1)) p.fill(255, 255); else p.fill(255, 150);
		p.text("1. The current vertex cannot be the previous vertex", 20,  p.height - 79 + 5);
		if (constraints.contains(2)) p.fill(255, 255); else p.fill(255, 150);
		p.text("2. The current vertex cannot be any of the 3 previous vertexes", 20, p.height -  64 + 5);
		if (constraints.contains(3)) p.fill(255, 255); else p.fill(255, 150);
		p.text("3. The current vertex cannot be one place away clockwise from the previous vertex", 20, p.height - 49 + 5);
		if (constraints.contains(4)) p.fill(255, 255); else p.fill(255, 150);
		p.text("4. The current vertex cannot be one place away from the previous vertex", 20, p.height - 34 + 5);
		if (constraints.contains(5)) p.fill(255, 255); else p.fill(255, 150);
		p.text("5. If the two last vertexes are the same, it cannot pick a neighboring vertex", 20, p.height - 19 + 5);
		
		//  MOVIMENTO PONTO X
		if (!pause) {
			for (int i = 0; i < 5 ; i++) {
			
			// "Sorteie um dos 3 pontos (A, B ou C), aleatoriamente, e designe esse ponto por T"
			boolean valido = true;
			PVector pontoT = vertices.get((int) p.random(0, this.numVertices));	
			do {
				valido = true;
				pontoT = vertices.get((int) p.random(0, this.numVertices));	
				if (constraints.contains(1)) 
					if (pontoT.equals(this.lastSelectedVertices.get(this.lastSelectedVertices.size()-1))) {valido = false;}
				if (constraints.contains(2)) 
					if (this.lastSelectedVertices.contains(pontoT)) {valido = false;}
				if (constraints.contains(3)) {
					PVector lastSelected = lastSelectedVertices.get(this.lastSelectedVertices.size()-1);
					PVector nextClockWise = (vertices.indexOf(lastSelected) == vertices.size()-1) ? vertices.get(0) : vertices.get(vertices.indexOf(lastSelected) + 1);
					if (pontoT.equals(nextClockWise)) {valido = false;}
				}
				if (constraints.contains(4)) {
					PVector lastSelected = lastSelectedVertices.get(this.lastSelectedVertices.size()-1);
					PVector next     = (vertices.indexOf(lastSelected) == vertices.size()-1) ? vertices.get(0) : vertices.get(vertices.indexOf(lastSelected) + 1);
					PVector previous = (vertices.indexOf(lastSelected) == 0) ? vertices.get(vertices.size()-1) : vertices.get(vertices.indexOf(lastSelected) - 1);
					if (pontoT.equals(next) || pontoT.equals(previous)) {valido = false;}
				}
				if (constraints.contains(5)) {
					PVector lastSelected = lastSelectedVertices.get(this.lastSelectedVertices.size()-1);
					PVector lastSelected2 = lastSelectedVertices.get(this.lastSelectedVertices.size()-2);

					PVector next     = (vertices.indexOf(lastSelected) == vertices.size()-1) ? vertices.get(0) : vertices.get(vertices.indexOf(lastSelected) + 1);
					PVector previous = (vertices.indexOf(lastSelected) == 0) ? vertices.get(vertices.size()-1) : vertices.get(vertices.indexOf(lastSelected) - 1);
					if (lastSelected.equals(lastSelected2) && (pontoT.equals(next) || pontoT.equals(previous))) {valido = false;}
				}
			} while (!valido);
			
						
			// "Calcule o ponto intermédio entre X e T passando esse ponto a ser o novo valor de X, ou seja, X = X + 0.5(T - X)"
			PVector subtraction = PVector.sub(pontoT, positionX);			
			PVector mult        = PVector.mult(subtraction, 1-jump);
			this.positionX.add(mult);	
			
			if (this.lastSelectedVertices.size()-1 < 2) this.lastSelectedVertices.add(pontoT);
			else { this.lastSelectedVertices.remove(0); this.lastSelectedVertices.add(pontoT);}
						
			p.fill(189, 198, 227);
			p.circle(positionX.x, positionX.y, 7);
			
			this.chaosVertices.add(new PVector(positionX.x, positionX.y));
		}
		}
		
		for (PVector chaosVertice : chaosVertices) {	
			float shortestDistance = PVector.dist(chaosVertice, vertices.get(0));
			PVector closestVertice = vertices.get(0);
			
			for (PVector vertice : vertices) {
				if (PVector.dist(chaosVertice, vertice) < shortestDistance) {
					shortestDistance = PVector.dist(chaosVertice, vertice);
					closestVertice   = vertice;
					}
				}
			p.stroke(colors.get(vertices.indexOf(closestVertice)));
			p.fill(colors.get(vertices.indexOf(closestVertice)));
			p.circle(chaosVertice.x, chaosVertice.y, 3f);
		}
		
		for (PVector vertice : vertices) {
			int index = vertices.indexOf(vertice);
			p.stroke(189, 198, 227);
			PVector nextVertice = (index != vertices.size()-1) ? vertices.get(index+1) : vertices.get(0);
			p.line(vertice.x, vertice.y, nextVertice.x, nextVertice.y);
		}
		
		for (PVector vertice : vertices) {			
			p.stroke(colors.get(vertices.indexOf(vertice)));
			p.fill(colors.get(vertices.indexOf(vertice)));
			p.circle(vertice.x, vertice.y, 7);
		}
	}

	@Override
	public void mousePressed(PApplet p) {		
		for (PVector vertice : vertices) {			
			if (PVector.dist(new PVector((float) p.mouseX, (float) p.mouseY), vertice) <= 6f) {
				this.selectedVertice = vertice;
			}
		}
		
	}

	@Override
	public void mouseReleased(PApplet p) {
		this.selectedVertice = null;
	}

	@Override
	public void keyPressed(PApplet p) {
		
		if (p.key == 'r' || p.key == 'R') {
			chaosVertices.clear();
			positionX = new PVector(p.width/2, p.height/2);
			return;
		}
		
		if (p.key == ' ') {
			this.pause = !pause;
			return;
		}
		
		if (p.key == '1' || p.key == '2' || p.key == '3' || p.key == '4' || p.key == '5') {
			int key = Integer.valueOf(p.key-48);
			if (this.numVertices == 3 && (key == 2 || key == 4 || key == 5)) return;
			if (this.numVertices == 4 && (key == 2)) return;

			boolean has = constraints.contains(key);
			if (has) constraints.remove(constraints.indexOf(key)); else constraints.add(key);
			return;
		}

		vertices.clear();
		constraints.clear();
		if (numVertices < 5 && (p.key == 'm' || p.key == 'M')) numVertices++;
		if (numVertices > 3 && (p.key == 'n' || p.key == 'N')) numVertices--;

		switch (numVertices) {
			case 3: 
				PVector vertice1 = new PVector(p.width / 2, (float) 0.27 * p.height);
				PVector vertice2 = new PVector((float) 0.27 * p.width,(float) 0.8 * p.height);
				PVector vertice3 = new PVector((float) 0.27 * p.width + PVector.dist(vertice1, vertice2), (float) 0.8 * p.height);
				vertices.add(vertice1);
				vertices.add(vertice2);
				vertices.add(vertice3);
				break;
			case 4:
				vertice1 = new PVector((float) 0.27 * p.width, (float) 0.8 * p.height - baseDist);
				vertice2 = new PVector((float) 0.27 * p.width,(float) 0.8 * p.height);
				vertice3 = new PVector((float) 0.27 * p.width + baseDist, (float) 0.8 * p.height);
				PVector vertice4 = new PVector((float) 0.27 * p.width + baseDist, (float) 0.8 * p.height - baseDist);
				
				vertices.add(vertice1);
				vertices.add(vertice2);
				vertices.add(vertice3);
				vertices.add(vertice4);
				break;
			case 5: 
				float b = p.height/2 - 0.47f*((float) 0.27 * p.height);
				vertice1 = new PVector(p.width / 2, 0.47f*((float) 0.27 * p.height));
				vertice2 = new PVector(p.width/2 - b * PApplet.cos(PApplet.radians(18)), (p.height/2) - b * PApplet.sin(PApplet.radians(18)));
				vertice3 = new PVector(p.width/2 - b * PApplet.cos(PApplet.radians(-54)), (p.height/2) - b * PApplet.sin(PApplet.radians(-54)));
				vertice4 = new PVector(p.width/2 - b * PApplet.cos(PApplet.radians(-54)) + PVector.dist(vertice1, vertice2), (p.height/2) - b * PApplet.sin(PApplet.radians(-54)));
				PVector vertice5 = new PVector(p.width/2 + b * PApplet.cos(PApplet.radians(18)), (p.height/2) - b * PApplet.sin(PApplet.radians(18)));				
				vertices.add(vertice1);
				vertices.add(vertice2);
				vertices.add(vertice3);
				vertices.add(vertice4);
				vertices.add(vertice5);
				break;
		}
	}

	@Override
	public void mouseDragged(PApplet p) {
		if (selectedVertice != null)
			this.selectedVertice.set(p.mouseX, p.mouseY);
	}

}
