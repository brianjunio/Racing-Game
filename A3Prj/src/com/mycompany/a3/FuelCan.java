/* Creates a fuelcan object and provides methods
 * to set and retrieve values from initialized fuelcans.
 */

package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class FuelCan extends GameObject implements ISelectable{
	private int capacity;
	private boolean selected;
	private GameWorld gw;
	public FuelCan(int cap, float x, float y, GameWorld g){
		setCap(cap);
		setSize(cap);
		setColor(0, 0, 255);
		setX(x);
		setY(y);
		gw=g;
	}
	
	public int getCap(){
		return capacity;
	}
	
	public void setCap(int s){
		capacity = s;
	}
	
	public void setSelected(boolean yesNo) {
		selected = yesNo;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = Math.round(getX());// shape location relative 
		int yLoc = Math.round(getY());// to parent’s origin
		if (px >= xLoc - getSize() && px <= xLoc + getSize() && py <= yLoc + getSize() && py >= yLoc - getSize() && px <= xLoc + getSize())
		    return true; 
		else 
			return false;
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		if(isSelected()){
			g.setColor(getColor());
			g.drawRect(Math.round(getX()), Math.round(getY()), Math.round(getSize()), Math.round(getSize()));
			g.setColor(ColorUtil.YELLOW);
			g.drawString(String.valueOf(getCap()), Math.round(getX()), Math.round(getY()));
		}
		else{
			g.setColor(getColor());
			g.drawRect(Math.round(getX()), Math.round(getY()), Math.round(getSize()), Math.round(getSize()));
			g.fillRect(Math.round(getX()), Math.round(getY()), getSize(), getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(String.valueOf(getCap()), Math.round(getX()), Math.round(getY()));
		}		
	}
}
