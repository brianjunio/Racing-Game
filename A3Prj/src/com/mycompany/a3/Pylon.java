package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Pylon extends GameObject implements ISelectable{
	private int sequenceNumber;
	private boolean selected;
	public Pylon(float x, float y, int seq, GameWorld g){
		super.setX(x);
		super.setY(y);
		setSize(20);
		super.setColor(130, 130, 130);
		sequenceNumber = seq;
	}

	public int getSeqNum(){
		return this.sequenceNumber;
	}
	
	public void setSelected(boolean yesNo){
		selected = yesNo;
	}
	
	public boolean isSelected(){
		return selected;
		
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt){
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = Math.round(getX());// shape location relative 
		int yLoc = Math.round(getY());// to parent’s origin
		if (px >= xLoc - getSize() && px <= xLoc + getSize() && py <= yLoc + getSize() && py >= yLoc - getSize() && px <= xLoc + getSize())
		    return true; 
		else 
			return false;
	}		
	


	public void draw(Graphics g, Point pPtrRelPrnt) {
		if(isSelected()){
			int[] xPoints = {Math.round(getX()) - getSize(), Math.round(getX()) + getSize(), Math.round(getX())};
			int[] yPoints = {Math.round(getY()) - getSize(), Math.round(getY()) - getSize(), Math.round(getY()) + getSize()}; 
			g.setColor(getColor());
			g.drawPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(String.valueOf(getSeqNum()), Math.round(getX()), Math.round(getY()));
		}
		else{
			int[] xPoints = {Math.round(getX()) - getSize(), Math.round(getX()) + getSize(), Math.round(getX())};
			int[] yPoints = {Math.round(getY()) - getSize(), Math.round(getY()) - getSize(), Math.round(getY()) + getSize()}; 
			g.setColor(getColor());
			g.drawPolygon(xPoints, yPoints, 3);
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(String.valueOf(getSeqNum()), Math.round(getX()), Math.round(getY()));
		}		
	}
}
