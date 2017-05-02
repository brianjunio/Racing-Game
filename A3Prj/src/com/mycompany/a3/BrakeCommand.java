package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command {
	private GameWorld gw;
	public BrakeCommand(GameWorld g) {
		super("Apply Brakes");
		gw = g;
	}

	@Override
	public void actionPerformed(ActionEvent ev){
		gw.brake();
	}
}
