package controller.controls;

import java.io.IOException;
import controller.*;
import model.*;
import view.interfaces.PaintCanvasBase;

public class SelectShapesCommand implements Command{
    Point startPoint;
    Point endPoint;
    PaintCanvasBase paintCanvas;
    StartAndEndPointMode seMode;
    iShape iShape;
    applicationStateContext appState;
    shapeFactory shapeFactory = new shapeFactory(iShape);
    SelectedShapesList shapesList = new SelectedShapesList();
    

   public SelectShapesCommand(PaintCanvasBase paintCanvas, applicationStateContext appState, Point startPoint,
	    Point endPoint) {
	this.appState = appState ;
	this.paintCanvas = paintCanvas;
	this.startPoint = startPoint;
	this.endPoint = endPoint;
	this.shapesList = appState.getShapeList();
	this.seMode = appState.getActiveStartAndEndPointMode();
	
    }
    @Override
    public void run() throws IOException {
	System.out.println("Running shapeSelector");
	iShape = model.shapeFactory.shapeMaker(paintCanvas, ShapeType.RECTANGLE, startPoint, endPoint, ShapeColor.BLACK, ShapeColor.BLACK, ShapeShadingType.OUTLINE);
	iShape.draw(shapesList);
	System.out.println(shapesList.returnSelectedShapesList().size());
	for (controller.iShape shape : shapesList.returnSelectedShapesList()) {
	    System.out.println("Shape: start: " + shape.startPoint().getX() + " " + shape.startPoint().getY() + " end: " + shape.endPoint().getX() + "" + shape.endPoint().getY());
	    System.out.println("Select: start: " + startPoint.getX() + " " + startPoint.getY() + " end: " + endPoint.getX() + " " + endPoint.getY());
	    
	    if (shape.startPoint().getX() >= startPoint.getX() && 
		    shape.startPoint().getY() >= startPoint.getY() && 
		    shape.endPoint().getX() <= endPoint.getX() && 
		    shape.endPoint().getY() <= endPoint.getY()) {
		appState.getSSL().add(shape);
		iShape.draw(shapesList);
		System.out.println("Selected Shape " + shape);
	    }
	    
	    else {
	    	appState.getUnSelectedShapesList().add(shape);
	    }
	}
	//shapesList.clearSelection();

    }



    }
