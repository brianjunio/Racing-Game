package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayCommand extends Command{
	private Game g;
	private int turn;
	public PlayCommand(Game gm, Button play){
		super("Play");
		
		g = gm;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev){
		turn += 1;
		g.changePlayName(turn%2);
	}
}