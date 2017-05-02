package com.mycompany.a3;

import java.util.Vector;


public class GameObjectCollection {
	private Vector<Object> gameCollection;

	public GameObjectCollection(){
		gameCollection = new Vector<Object>();
	}
	
	public void add(Object newObject){
		gameCollection.add(newObject);
	}
	
	public IIterator getIterator(){
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator{
		private int currElementIndex;
		
		public GameObjectIterator(){
			currElementIndex = -1;
		}
		
		public boolean hasNext(){
			if(gameCollection.size() <= 0)
				return false;
			if(currElementIndex == gameCollection.size() -1)
				return false;
			return true;
		}
		
		public Object getNext(){
			currElementIndex++;
			return(gameCollection.elementAt(currElementIndex));
		}
	}
}
