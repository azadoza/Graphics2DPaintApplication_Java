package controller;

import model.Point;
import model.ShapeColor;
import model.ShapeCommand;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;
import model.applicationStateContext;
import view.interfaces.PaintCanvasBase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import controller.controls.MoveShapesCommand;
import controller.controls.SelectShapesCommand;

public class MouseListener extends MouseAdapter {
    // MouseListener reads input from user to determine start and end points
    private Point startPoint;
    private Point sPoint;
    private Point endPoint;
    private Point ePoint;
    private PaintCanvasBase paintCanvas;
    ShapeType shapeType ;
    ShapeColor primaryColor;
    ShapeColor secondaryColor;
    ShapeShadingType shapeShadingType;
    applicationStateContext appState;
    iShape iShape;
    StartAndEndPointMode startEndMode;

    public MouseListener(PaintCanvasBase paintCanvas, applicationStateContext appState) {
	this.paintCanvas = paintCanvas;
	this.appState = appState;
	// this.iShape = iShape;

    }

    @Override
    public void mousePressed(MouseEvent e) {
	// mousePressed purpose: noting starting coords
	System.out.printf("Location chosen for beginning of click...");
	sPoint = new Point(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
	// mouseReleased purpose: Noting ending coords
	Command command;
	// Declare the ICommand and run upon release of mouse

	ePoint = new Point(e.getX(), e.getY());
	
	// Making the shapes actually show up where clicked
	int xStart = Math.min(sPoint.getX(), ePoint.getX());
	int yStart = Math.min(sPoint.getY(), ePoint.getY());
	startPoint = new Point(xStart, yStart);
	System.out.println("Starting coords: " + startPoint.getX() + ", " + startPoint.getY());

	int xEnd = Math.max(sPoint.getX(), ePoint.getX());
	int yEnd = Math.max(sPoint.getY(), ePoint.getY());
	endPoint = new Point(xEnd, yEnd);
	// Constructor to IShapeCommand
	

	System.out.printf("Mouse unclicked. Ending coords: " + endPoint.getX() + ", " + endPoint.getY()
		+ "\n_______ \n");

	// (shapeShadingType.equals(ShapeShadingType.OUTLINE))
	
	StartAndEndPointMode startEndMode = appState.getActiveStartAndEndPointMode();
	if (startEndMode == StartAndEndPointMode.DRAW) {
	    command = new ShapeCommand(paintCanvas, startPoint, endPoint, appState);
	    try {
		System.out.print("***DRAW SHAPE MODE*** \n");
		command.run();
	    }
	    catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
	else if(startEndMode ==StartAndEndPointMode.SELECT) {
	    appState.selectedShapesList.clearSelection();
	    command = new SelectShapesCommand(paintCanvas, appState, startPoint, endPoint);
	    try {
		System.out.print("***SELECT SHAPE MODE*** \n");
		command.run();
	    }
	    catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}
	
	else if(startEndMode ==StartAndEndPointMode.MOVE) {
	    //iShape = new rectangle(paintCanvas, startPoint, endPoint, primaryColor, secondaryColor, shapeShadingType);
	 
	    command= new MoveShapesCommand(paintCanvas, appState, startPoint, endPoint);
	    try {
		System.out.print("***MOVE SHAPE MODE*** \n");
		command.run();
	    }
	    catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}
	
	else {
	    System.out.print("u broke it");
	}
    }
}
