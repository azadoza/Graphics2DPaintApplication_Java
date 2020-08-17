package controller.controls;
import java.util.ArrayList;

import controller.*;

public class SelectedShapesList{
    // Adding shapes to the selectedShapesList
    // This will add to, remove from, and clear the list
    ArrayList<iShape> selectedShapesList = new ArrayList<iShape>();
   
    public SelectedShapesList() {
    }
    
    public void add(iShape shape) {
	selectedShapesList.add(shape);
    }
    
    public void remove(iShape shape) {
	selectedShapesList.remove(shape);
    }
    
    public void clearSelection() {
	selectedShapesList = new ArrayList<iShape>();
    }
    
    public ArrayList<iShape> returnSelectedShapesList(){
	return this.selectedShapesList;
    } 
    
    public int returnShapeIndex(iShape shape) {
	return selectedShapesList.indexOf(shape); //returns the first?
    }
}
