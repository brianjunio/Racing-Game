package com.mycompany.a3;

import java.util.Vector;

import com.codename1.ui.Graphics;
import com.codename1.util.MathUtil;

public class NonPlayerCar extends Car {
	private int id;
	private Vector<GameObject> collideVector = new Vector<GameObject>();
	private IStrategy curStrategy;
	
	public NonPlayerCar(float x, float y, int id) {
		setX(x);
		setY(y);
		setColor(255, 0, 0);
		setSize(20);
		setMaxDmg(100);
		setFuel(50);
		setSpeed(3);
		setFuelRate(1);
		setInitDmgLevel(0);
		setHeading(180);
		maxSpeed(100);
		setPylon(0);
		setLives(3);
		setDmgColor(0);	
		setId(id);
		setName("NPC");
		}

	public void setStrategy(IStrategy s){
		curStrategy = s;
	}
	
	public void invokeStrategy(){
		curStrategy.apply();
	}
	
	public void setId(int s){
		id = s;
	}
	
	public int getId(){
		return id;
	}
	
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawArc(Math.round(getX()), Math.round(getY()), 2*getSize(), 2*getSize(), 0, 360);
	}
}
