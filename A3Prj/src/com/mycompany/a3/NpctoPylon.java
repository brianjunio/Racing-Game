package com.mycompany.a3;
import com.codename1.util.MathUtil;

public class NpctoPylon implements IStrategy{
	private IIterator iterate;
	private NonPlayerCar npc;
	private double pylonX, pylonY, npcX, npcY, A, B, angleChange;
	public NpctoPylon(IIterator i, NonPlayerCar npc){
		iterate = i;
		this.npc = npc;
	}
	public void apply(){
		while(iterate.hasNext()){
			Object o = iterate.getNext();
			if(o instanceof Pylon && ((Pylon) o).getSeqNum() == 1){
				pylonX = ((Pylon) o).getX();
				pylonY = ((Pylon) o).getY();
				break;
			}
		}
		npcX = npc.getX();
		npcY = npc.getY();
		
		A = pylonX - npcX;
		B = pylonY - npcY;
		angleChange = MathUtil.atan2(B, A); //answer in radians
		npc.setHeading((Math.toDegrees(angleChange)));
		
	}

}
