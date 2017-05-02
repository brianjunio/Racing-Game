package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command{
	private GameWorld gw;
	private int turn;
	public PositionCommand(GameWorld g){
		super("Change Position");
		gw = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		turn += 1;
		if(turn % 2 == 1)
			gw.setChangePosition(true);
		else
			gw.setChangePosition(false);
			
	}
}
