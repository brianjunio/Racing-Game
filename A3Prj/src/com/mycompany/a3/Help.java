package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class Help extends Command {
	public Help(){
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		Dialog.show("Commands", "Accelerate: 'a' key \n" + 
								"Brake: 'b' key \n" + 
								"Turn left: 'l' key \n" + 
								"Turn right: 'r' key \n" +
								"Pick up fuel: 'f' key\n" +
								"Tick: 't' key\n" +
								"Exit: 'x' key", "Ok", null);
	}
}
