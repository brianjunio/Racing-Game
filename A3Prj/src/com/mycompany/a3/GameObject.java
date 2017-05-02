package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class GameObject implements IDrawable{

	private int size;
	private float xcoord;
	private float ycoord;
	private int r, g, b;
	private IStrategy curStrategy;
	private String name;

	public GameObject(){
		
	}
	
	public void setSize(int sz){
		size = sz;
	}
	
	public void setName(String s){
		name = s;
	}
	
	public String getName(){
		return name;
	}
	
	public int getSize(){
		return size;
	}
	
	public float getX(){
		return xcoord;
	}
	
	public void setX(float x){
		xcoord = x;
	}
	
	public float getY(){
		return ycoord;
	}
	
	public void setY(float y){
		ycoord = y;
	}
	
	public int getColor(){
		return ColorUtil.rgb(r, g, b);
	}
	
	public String getColorString(){
		return '[' + String.valueOf(r) + ',' + String.valueOf(g) + ',' + String.valueOf(b) + ']';

	}
	
	public void setColor(int x, int y, int z){
		r += x;
		g += y;
		b += z;
		ColorUtil.rgb(x, y, z);
	}
	
	
	public void setStrategy(IStrategy s){
		curStrategy = s;
	}
	
	public void invokeStrategy(){
		curStrategy.apply();
	}


	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}

