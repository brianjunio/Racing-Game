package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class LifeEndSound {
	private Media m;
	
	public LifeEndSound(String fileName){
		try{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/aif");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		m.setTime(0);
		m.play();
	}
}
