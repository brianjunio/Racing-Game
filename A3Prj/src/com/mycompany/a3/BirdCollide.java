package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BirdCollide extends Command{
	private GameWorld gw;
	public BirdCollide(GameWorld g) {
		super("Collide with bird");
		gw = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		gw.collide();
	}
}
