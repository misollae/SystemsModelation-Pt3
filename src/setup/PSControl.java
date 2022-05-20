package setup;

import processing.core.PVector;

public class PSControl {
	private float averageAngle;
	private float dispersionAngle;
	private float minVelocity;
	private float maxVelocity;
	private float minLifetime;
	private float maxLifetime;
	private float minRadius;
	private float maxRadius;
	public float flow;
	private int color;

	
	public PSControl(float[] velControl, float[] lifetime, float[] radius, float flow, int color) {
		setVelParams(velControl);
		setLifetimeParams(lifetime);
		setRadiusParams(radius);
		setFlow(flow);
		setColor(color);
	}
	
	public void setFlow(float flow) {
		this.flow = flow;
	}
	
	public float getFlow() {
		return flow;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setRadiusParams(float[] radius) {
		minRadius = radius[0];
		maxRadius = radius[1];
	}
	
	public void setLifetimeParams(float[] lifetime) {
		minLifetime = lifetime[0];
		maxLifetime = lifetime[1];
	}
	
	public float getRndRadius() {
		return getRnd(minRadius, maxRadius);
	}
	
	public float getRndLifetime() {
		return getRnd(minLifetime, maxLifetime);
	}
	
	public void setVelParams(float[] velControl) {
		averageAngle    = velControl[0];
		dispersionAngle = velControl[1];
		minVelocity     = velControl[2];
		maxVelocity     = velControl[3];
	}
	
	public PVector getRndVel() {
		float angle = getRnd(averageAngle - dispersionAngle/2, averageAngle + dispersionAngle/2);
		PVector v = PVector.fromAngle(angle);
		return v.mult(getRnd(minVelocity, maxVelocity));
	}
	
	public static float getRnd(float min, float max) {
		return (float) (min + Math.random() * (max - min));
	}
}
