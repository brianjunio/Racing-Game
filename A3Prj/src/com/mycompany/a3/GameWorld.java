package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.ui.Dialog;


public class GameWorld extends Observable{
	private GameObjectCollection gameCollection = new GameObjectCollection();
	private Random r = new Random();
	private Car c;
	private int time =0;
	private String sound = "Off";
	private IIterator initialIterate = gameCollection.getIterator();
	private int curPylon = 0;
	private boolean changePosition;
	private Game g;
	private CarCollisionSound collisionSound;
	private FuelPickupSound fuelSound;
	private LifeEndSound lifeSound;
	
	//passes instances of game, car collision sound, and fuel pickup sound
	public GameWorld(Game gm, CarCollisionSound ccs, FuelPickupSound fps, LifeEndSound les){
		g = gm;
		collisionSound = ccs;
		fuelSound = fps;
		lifeSound = les;
	}
	
	//gameworld is initialized
	public void init(){
		gameCollection.add(new Car(this));
		gameCollection.add(new Pylon(250, 490, 1, this));
		gameCollection.add(new Pylon(341, 420, 2, this));
		gameCollection.add(new Pylon(425, 111, 3, this));
		gameCollection.add(new Pylon(738, 94, 4, this));
		gameCollection.add(new Birds(5, r.nextInt(360), 321, 123, r.nextInt(10) + 5));
		gameCollection.add(new Birds(5, r.nextInt(360), 532, 432, r.nextInt(10) + 5));
		gameCollection.add(new FuelCan(r.nextInt(10) + 10, 500, 400, this));
		gameCollection.add(new FuelCan(r.nextInt(10) + 10, 300, 300, this));
		gameCollection.add(new NonPlayerCar(182, 352, 1));
		gameCollection.add(new NonPlayerCar(329, 260, 2));
		gameCollection.add(new NonPlayerCar(150, 400, 3));
		while(initialIterate.hasNext()){		//Iterates through collection, points c to the car in the collection
			Object o = initialIterate.getNext();
			if(o instanceof Car && !(o instanceof NonPlayerCar))
				c = (Car) o;
			if(o instanceof NonPlayerCar && ((NonPlayerCar) o).getId() == 1)
				((NonPlayerCar) o).setStrategy(new NpctoPylon(gameCollection.getIterator(), ((NonPlayerCar) o)));
		}
		
	}
//additional methods here to manipulate game objects and related game state data

	//setters and getters
	public int getTime(){
		return time;
	}
	
	public CarCollisionSound getCrashSound(){
		return collisionSound;
	}
	
	public FuelPickupSound getFuelSound(){
		return fuelSound;
	}
	
	public void setTime(){
		time += 1;
	}
	
	public void setChangePosition(boolean yesNo){
		changePosition = yesNo;
	}
	
	public boolean getChangePosition(){
		return changePosition;
	}
	
	public int getCarFuel(){
		return c.getFuel();
	}
	
	public String getLastPylon(){
		return String.valueOf(c.getPylon());
	}
	
	public int getCarDamage(){
		return c.getDmgLevel();
	}
	
	public int getCurrentLives(){
		return c.getLives();
	}
	
	public String getSound(){
		return sound;
	}
	
	public Object getCollection(){
		return gameCollection;
	}
	//methods that notify the observers and updates the views
	public void setSound(String soundVal){
		setChanged();
		sound = soundVal;
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	
	public void accel(){
		setChanged();
		c.accelerate();	
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	public void left(){
		setChanged();
		c.turnLeft();
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	public void right(){
		setChanged();
		c.turnRight();
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	public void brake(){
		setChanged();
		c.brake();
		notifyObservers(gameCollection.getIterator());
		clearChanged();	
	}
	
	//handles car colliding with npc
	public void collide(){
		setChanged();
		c.setDmgLevel(2);
		IIterator i = gameCollection.getIterator();
		while(i.hasNext()){
			Object o = i.getNext();
			if(o instanceof NonPlayerCar && ((NonPlayerCar) o).getId() == 1){
				((NonPlayerCar) o).setDmgLevel(5);
			}
		}
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	//method to add fuel picked up from fuel can to total player car fuel
	public void fuel(FuelCan collideCan){
		setChanged();
		c.setFuel(collideCan.getCap() + c.getFuel());
		collideCan.setCap(0);
		System.out.println("Car filled by " + ((FuelCan) collideCan).getCap());
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	//What to do at each tick
	//Checks for collisions in instance of Car and instances of NPC
	//Moves movable objects
	public void tick(){
		setChanged();
		setTime();
		IIterator i = gameCollection.getIterator();
		IIterator i2 = gameCollection.getIterator();
		while(i.hasNext()){
			Object o = i.getNext();
			if(o instanceof Birds)
				((Birds) o).move();
			if(o instanceof Car && !(o instanceof NonPlayerCar)){
				while(i2.hasNext()){
					Object o2 = i2.getNext();
					if(o != o2){
						if(((Car) o).collidesWith((GameObject) o2)){
							((Car) o).handleCollision(((GameObject) o2));
							if(!(((Car) o).inCollideVector(((GameObject) o2))))
								((Car) o).addToCollideVector(((GameObject) o2));
						}
						else{
							if(((Car) o).inCollideVector(((GameObject) o2))){
								((Car) o).removeFromCollideVector(((GameObject) o2));
							}
						}
					}
				}
				if(c.getFuel() == 0)
					System.out.println("Out of fuel cant move");
				else if(c.getDmgLevel() == 10){
					g.timer.cancel();
					lifeSound.play();
					c.setColor(255, 0, 0);
					c.setFuel(1000);
					c.setX(100);
					c.setY(540);
					c.setDmgLevel(-10);
					c.setLives(c.getLives() - 1);
					Dialog.show("You lost a life!", "Press play to start new life", "Ok", null);
				}
				else if(c.getLives() == 0){
					g.timer.cancel();
					Dialog.show("Game Over", "Please exit game", "Ok", null);

				}
				else{
					c.move();	
					c.decFuel();
				}
			}
			if(o instanceof NonPlayerCar && ((NonPlayerCar)o).getId() == 1){
				while(i2.hasNext()){
					Object o2 = i2.getNext();
					if(o != o2){
						if(((NonPlayerCar) o).collidesWith((GameObject) o2)){
							((NonPlayerCar) o).handleCollision(((GameObject) o2));
							if(!(((NonPlayerCar) o).inCollideVector(((GameObject) o2))))
								((NonPlayerCar) o).addToCollideVector(((GameObject) o2));
						}
						else{
							if(((NonPlayerCar) o).inCollideVector(((GameObject) o2))){
								((NonPlayerCar) o).removeFromCollideVector(((GameObject) o2));
							}
						}
					}
				}
				((NonPlayerCar) o).invokeStrategy();
				((NonPlayerCar) o).move();
			}
			if(o instanceof NonPlayerCar && ((NonPlayerCar)o).getId() != 1){
				while(i2.hasNext()){
					Object o2 = i2.getNext();
					if(o != o2){
						if(((NonPlayerCar) o).collidesWith((GameObject) o2)){
							((NonPlayerCar) o).handleCollision(((GameObject) o2));
							if(!(((NonPlayerCar) o).inCollideVector(((GameObject) o2))))
								((NonPlayerCar) o).addToCollideVector(((GameObject) o2));
						}
						else{
							if(((NonPlayerCar) o).inCollideVector(((GameObject) o2))){
								((NonPlayerCar) o).removeFromCollideVector(((GameObject) o2));
							}
						}
					}
				}
				((NonPlayerCar) o).move();
			}
		}
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
	//Handles player car collision with pylon
	public void setLastPylon(int pylonHit){
		setChanged();
		if(pylonHit == curPylon + 1){
			curPylon += 1;
			c.setPylon(pylonHit);
			if(pylonHit == 4){
				Dialog.show("You win!", "Congratualtions, you beat the game!", "Ok", null);
				System.out.println("Congratulations you beat the game!");
				g.timer.cancel();
			}
		}
		notifyObservers(gameCollection.getIterator());
		clearChanged();
	}
	
}
