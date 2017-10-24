package models;

import java.util.ArrayList;

import views.View;

public abstract class Model {
	
	private ArrayList<View> observers;
	
	public Model(){
		observers = new ArrayList<>();
	}
	
	
	/** Notifies observers that there are changes **/
	public void notifyObservers(){
		for(int i = 0; i < observers.size(); i++){
			observers.get(i).resetLayout();
		}
	}

	/** Attaches observers to this model **/
	public void attach(View v){
		observers.add(v);
	}
	
	/** Detaches observers from this  **/
	public void detach(View v){
		observers.remove(v);
	}
	
}
