package models;

import java.util.ArrayList;

import DB.DBaccess;

public class CollectionList extends Model {

	private ArrayList<Collection> collection;
	
	public CollectionList(){
		collection = new ArrayList<Collection>();
		createDummyData();
	}
	
	public CollectionList(ArrayList<Collection> collection){
		this.collection = collection;
	}
	
	public Collection getUnit(Collection collection){
		if(this.collection.contains(collection))
			return this.collection.get(this.collection.indexOf(collection));
		
		return null;
	}
	
	public Collection getUnit(int unitNo){
		for(Collection collection: collection){
			if(collection.getUnitNo() == unitNo)
				return collection;
		}
		
		return null;
	}
	
	public ArrayList<Collection> getAllOverdueUnits(){
		ArrayList<Collection> temp = new ArrayList<>();
		for(Collection collection: collection){
			if(collection.isOverdue())
				temp.add(collection);
		}
		return temp;
	}
	
	public ArrayList<Collection> getCollection(){
		return collection;
	}
	
	private void createDummyData(){
		
	}
	
}
