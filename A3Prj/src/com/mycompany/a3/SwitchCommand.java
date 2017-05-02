package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SwitchCommand extends Command{

	public SwitchCommand() {
		super("Switch");
	}
	@Override
	
	public void actionPerformed(ActionEvent ev){
		System.out.println("Strategy is supposed to switch");
	}
}
