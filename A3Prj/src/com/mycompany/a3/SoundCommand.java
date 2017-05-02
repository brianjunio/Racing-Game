package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command{
	private GameWorld gw;
	private CheckBox s;
	private BGSound bgs;
	public SoundCommand(GameWorld g, CheckBox sb, BGSound b) {
		super("Sound");
		gw =g;
		s =sb;
		bgs =b;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		if(s.isSelected()){
			gw.setSound("On");
			bgs.play();
			
		}
		else{
			gw.setSound("Off");
			bgs.pause();
		}
	}

}
