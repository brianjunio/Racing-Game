package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class AccelCommand extends Command{
	private GameWorld gw;
	
	public AccelCommand(GameWorld g) {
		super("Accelerate");
		gw = g;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		gw.accel();
	}
}
