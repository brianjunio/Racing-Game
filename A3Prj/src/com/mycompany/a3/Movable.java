package com.mycompany.a3;
public abstract class Movable extends GameObject {	
	private int speed;
	private int heading;


	public void setSpeed(int s){
		speed += s;
	}
	
	public void setHeading(int s) {	
		heading = s;
	}
	
	public void setHeading(double s){
		heading = (int)s;
	}
	
	public int getHeading() {	
		return heading;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void move() {
		if(getX() > 1024 || getX() < 0 || getY() > 560 || getY() < 0) {
			setHeading(getHeading() + 180);
		}
		setX(getX() + Math.round((speed * Math.cos(Math.toRadians(90 - getHeading())))));
		setY(getY() + Math.round((speed * Math.sin(Math.toRadians(90 - getHeading())))));
	
		System.out.println("The " + getName() + " has moved " + Math.round((getSpeed() * Math.cos(Math.toRadians(90 - getHeading())))) 
		+ " pixels in the x direction to " + " and " + Math.round((getSpeed() * Math.sin(Math.toRadians(90-getHeading())))) + 
		" pixels in the y direction");
		
	}
}