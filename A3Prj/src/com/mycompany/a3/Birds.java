package com.mycompany.a3;

import com.codename1.ui.Graphics;

public class Birds extends Movable{	
	
	//bird object is created as specified by assignment documentation
	public Birds(int sp, int head, float x, float y, int sz){
		setSpeed(sp);
		setColor(212,212,25);
		setHeading(head);
		setX(x);
		setY(y);
		setSize(sz);
		setName("Bird");
	}
		
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawArc(Math.round(getX()), Math.round(getY()), 2*getSize(), 2*getSize(), 0, 360);
	}
}
