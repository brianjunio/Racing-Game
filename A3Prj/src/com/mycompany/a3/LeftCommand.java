package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftCommand extends Command{
	private GameWorld gw;
	public LeftCommand(GameWorld g) {
		super("Turn Left");
		gw = g;
	}

	@Override
	public void actionPerformed(ActionEvent ev){
		gw.left();
	}
}
