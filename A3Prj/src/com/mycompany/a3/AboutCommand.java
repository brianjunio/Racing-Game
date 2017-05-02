package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	
	public AboutCommand(){
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		Dialog.show("About",
				"Hit the pylons in correct order to win. Avoid birds and other cars. Hit fuel cans to restock on fuel",
				"Lets DO THIS", null);

	}

}
