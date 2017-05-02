package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

//Must register as Observer to GameWorld
public class ScoreView extends Container implements Observer {
	private Label time, lives, pylon, fuel, damage, sound;
	private GameWorld gw;
	public ScoreView(GameWorld g){
		gw=g;
		g.addObserver(this);
		time = new Label("Time: " + g.getTime());
		lives = new Label("Lives Left: " + g.getCurrentLives());
		pylon = new Label("Highest Player Pylon: " + g.getLastPylon());
		fuel = new Label("Player Fuel Remaining: " + g.getCarFuel());
		damage = new Label("Player Damage Level: " + g.getCarDamage());
		sound = new Label("Sound: " + g.getSound());
		
        time.getAllStyles().setMargin(Component.LEFT, 150);
        sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
        time.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
        lives.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
        pylon.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
        fuel.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
        damage.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
        
        add(time);
        add(lives);
        add(pylon);
        add(fuel);
        add(damage);
        add(sound);

		
	}
	
    public void update (Observable o, Object arg) {
		time.setText("Time: " + gw.getTime());
		lives.setText("Lives Left: " + gw.getCurrentLives());
		pylon.setText("Highest Player Pylon: " + gw.getLastPylon());
		fuel.setText("Player Fuel Remaining: " + gw.getCarFuel());
		damage.setText("Player Damage Level: " + gw.getCarDamage());
		sound.setText("Sound: " + gw.getSound());
		
    } 
}
