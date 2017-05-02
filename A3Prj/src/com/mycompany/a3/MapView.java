package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


//Must register as observer to GameWorld
public class MapView extends Container implements Observer {
	private GameObjectCollection gc;
	private Point pCmpRelPrnt;
	private Point pPtrRelPrnt;
	private GameWorld gw;
	public MapView(Object goc, GameWorld g){
		gc = (GameObjectCollection) goc;
		gw=g;
	}
	
	public void pointerPressed(int x, int y) {
		//make pointer location relative to parent’s origin
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		pPtrRelPrnt = new Point(x, y);
		pCmpRelPrnt = new Point(getX(), getY()); 
		IIterator it = gc.getIterator();
		while (it.hasNext()) {
			Object o = it.getNext();
			if(o instanceof ISelectable){
				ISelectable s = (ISelectable) o;
				if(s.contains(pPtrRelPrnt, pCmpRelPrnt)) 
					s.setSelected(true); 
				else if(s.isSelected() && gw.getChangePosition()){
					GameObject go = (GameObject) s;
					go.setX(pPtrRelPrnt.getX());
					go.setY(pPtrRelPrnt.getY());
				}
				else
					s.setSelected(false);
				
			}

		  }
		repaint(); 
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		IIterator it = gc.getIterator();
		while(it.hasNext()){
			Object x = it.getNext();
			if(x instanceof Pylon){
				((Pylon)x).draw(g, pPtrRelPrnt);
			}
			
			if(x instanceof Birds){
				((Birds)x).draw(g);
			}
			
			if(x instanceof Car && !(x instanceof NonPlayerCar)){
				((Car)x).draw(g);
			}
			
			if(x instanceof FuelCan){
				((FuelCan)x).draw(g, pCmpRelPrnt);
			}
			
			if(x instanceof NonPlayerCar){
				((NonPlayerCar)x).draw(g);
			}
		}
	}
    public void update (Observable o, Object arg) {
    	System.out.println();
    	IIterator i = (IIterator) arg;
    	while(i.hasNext()){
    		Object x = i.getNext();
    		if(x instanceof Pylon)
    			System.out.println("Pylon XY Location: " + ((Pylon) x).getX() + ','+ ((Pylon) x).getY() + " Color: " + ((Pylon) x).getColorString() + " size: " + ((Pylon) x).getSize() + " Pylon Number: " + ((Pylon) x).getSeqNum());		
    		if(x instanceof Car && !(x instanceof NonPlayerCar))
    			System.out.println("Car XY Location: " + ((Car) x).getX() + ','+ ((Car) x).getY() + " Color: " + ((Car) x).getColorString() + " size: " + ((Car) x).getSize() + " Heading: " + ((Car) x).getHeading());
    		if(x instanceof FuelCan)
    			System.out.println("Fuel Can XY Location: " + ((FuelCan) x).getX() + ','+ ((FuelCan) x).getY() + " Color: " + ((FuelCan) x).getColorString() + " size: " + ((FuelCan) x).getSize());
    		if(x instanceof Birds)
    			System.out.println("Bird XY Location: " + ((Birds) x).getX() + ','+ ((Birds) x).getY() + " Color: " + ((Birds) x).getColorString() + " size: " + ((Birds) x).getSize() + " Heading: " + ((Birds) x).getHeading());
    		if(x instanceof NonPlayerCar)
    			System.out.println("NPC ID Number: " + ((NonPlayerCar) x).getId() + " XY Location: " + ((NonPlayerCar) x).getX() + ',' + ((NonPlayerCar) x).getY() + " Color: " + ((NonPlayerCar) x).getColorString() + " size: " + ((NonPlayerCar) x).getSize() + " Heading: " + ((NonPlayerCar) x).getHeading() + " Damage: " + ((NonPlayerCar) x).getDmgLevel());

    	}
    	System.out.println();
    	repaint();

    }

}