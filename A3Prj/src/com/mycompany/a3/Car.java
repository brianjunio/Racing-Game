package com.mycompany.a3;

import java.util.Vector;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Car extends Movable implements ISteerable, ICollider{
	private int steeringDirection;
	private int maximumSpeed;
	private int fuelLevel;
	private int fuelConsumptionRate;
	private int damageLevel;
	private int lastPylonReached;
	private int maxDamageLevel;
	private int lives;
	private int dmgColor;
	private GameWorld gw;
	private Vector<GameObject> collideVector = new Vector<GameObject>();

	/*car object created with specifications detailed by assignment sheet*/
	public Car(GameWorld g){
		gw=g;
		setX(100);
		setY(540);
		setColor(255, 0, 0);
		setSize(20);
		setMaxDmg(10);
		setFuel(1000);
		setSpeed(7);
		setFuelRate(1);
		setInitDmgLevel(0);
		maxSpeed(100);
		setHeading(130);
		setPylon(0);
		setLives(3);
		setDmgColor(0);
		setName("Car");
	}

	public Car(){

	}

	/* Mutators */

	public void setMaxDmg(int s){
		maxDamageLevel = s;
	}

	public void setLives(int s){
		lives = s;
	}

	public void setPylon(int s){
		lastPylonReached = s;
	}


	public void maxSpeed(int s){
		maximumSpeed = s;
	}
	
	public void setDmgColor(int s){
		dmgColor += s;
	}

	public void setInitDmgLevel(int s){
		damageLevel = s;
	}

	public void setDmgLevel(int s){
		damageLevel += s;
		setDmgColor(s*10);
		setColor(255, 0, getDmgColor());
	}


	public void setFuelRate(int r){
		fuelConsumptionRate = r;
	}

	public void setFuel(int newFuel){
		fuelLevel = newFuel;
	}


	/*Getters*/

	//returns max damage the car can take
	public int getMaxDmg(){
		return maxDamageLevel;
	}


	//returns the last pylon it touched
	public int getPylon(){
		return lastPylonReached;
	}

	//returns damage of car
	public int getDmgLevel(){
		return damageLevel;
	}


	//returns current fuel level of car
	public int getFuel(){
		return fuelLevel;
	}

	//returns current steering direction
	public int getSteering(){
		return steeringDirection;
	}

	public int getLives(){
		return lives;
	}

	public int getDmgColor(){
		return dmgColor;
	}

	public Vector<GameObject> getCollideVector(){
		return collideVector;
	}

	// decrements fuel level of car by predetermined amount
	public void decFuel(){
		if(fuelLevel > 0)
			setFuel(getFuel()-fuelConsumptionRate);

	}


	//adjusts the steering direction of car
	public void turnLeft(){
		if(steeringDirection >= -40){
			steeringDirection -= 5;
			setHeading(getHeading() - 5);
			System.out.println("Car wheels turned 5 degrees to the left");
		}
		else
			System.out.println("Steering wheel at max turn radius.");
	}

	//adjusts the steering direction of car
	public void turnRight(){
		if(steeringDirection <= 40){
			steeringDirection += 5;
			setHeading(getHeading() + 5);
			System.out.println("Car wheels turned 5 degrees to the right.");
		}
		else
			System.out.println("Steering wheel at max turn radius.");
	}


	//Reduces speed of car by predetermined amount
	public void brake(){
		if(getSpeed() > 0){
			setSpeed(-1);
			System.out.println("Car has slowed down by 5");
		}
	}

	//Increases speed of car by predetermined amount.
	//If maximum speed is reached, warning is printed to the user instead.
	public void accelerate(){
		if(getSpeed() == maximumSpeed)
			System.out.println("You've reached your maximum speed!");
		else{
			System.out.println("Speed increased by 1");
			setSpeed(1);
		}
	}

	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillArc(Math.round(getX()), Math.round(getY()), 2*getSize(), 2*getSize(), 0, 360);
	}

	public boolean collidesWith(GameObject otherObj) {
		boolean result = false;
		int thisCenterX = Math.round(getX() + (getSize()));
		int thisCenterY = Math.round(getY() + (getSize()));
		int otherCenterX = Math.round(otherObj.getX() + (otherObj.getSize()/2));
		int otherCenterY = Math.round(otherObj.getY() + (otherObj.getSize()/2));

		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentSqr = (dx*dx + dy*dy);

		int thisRadius = getSize();
		int otherRadius = otherObj.getSize();
		int radiiSquared = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);

		if(distBetweenCentSqr <= radiiSquared)
			result = true;
		return result;
	}

	public void addToCollideVector(GameObject otherObject){
		collideVector.add(otherObject);
	}

	public boolean inCollideVector(GameObject otherObject){
		boolean result;
		if(collideVector.isEmpty()){
			result = false;
		}
		else{
			result = collideVector.contains(otherObject);
		}
		return result;
	}

	public void removeFromCollideVector(GameObject otherObject){
		collideVector.remove(otherObject);
	}

	public void handleCollision(GameObject otherObject) {
		if(!(inCollideVector(otherObject))){
			if(otherObject instanceof NonPlayerCar){
				gw.collide();
				if(gw.getSound() == "On")
					gw.getCrashSound().play();
				setColor(-30,0,0);
			}
			else if(otherObject instanceof FuelCan){
				gw.fuel(((FuelCan) otherObject));
				if(gw.getSound() == "On")
					gw.getFuelSound().play();
			}
			else if(otherObject instanceof Birds){
				gw.collide();
				setColor(-30,0,0);
				brake();
			}
			else{
				gw.setLastPylon(((Pylon) otherObject).getSeqNum());
			}
		}
	}
}
