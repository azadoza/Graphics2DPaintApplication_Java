package model;

import view.interfaces.PaintCanvasBase;

import java.awt.Color;
import java.awt.Graphics2D;

import controller.Command;
import controller.CommandHistory;
import controller.iShape;
import controller.controls.IUndoable;

public class IShapeCommand implements Command, IUndoable {
    public Graphics2D graphics2D;
    Point startPoint;
    Point endPoint;
    PaintCanvasBase paintCanvas;
    ShapeType shapeType;
    ShapeColor primaryColor;
    ShapeColor secondaryColor;
    ShapeShadingType shapeShadingType;
    iShape iShape;
    iShape lastShape;
    applicationStateContext applicationState;
    shapeFactory shapeFactory = new shapeFactory(iShape);
    int height;
    int width;

    public IShapeCommand(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint,
	    applicationStateContext applicationState) {
	this.applicationState = applicationState;
	this.paintCanvas = paintCanvas;
	this.shapeType = applicationState.getActiveShapeType();
	System.out.println("ShapeType IShapeCommand : " + shapeType);
	this.primaryColor = applicationState.getActivePrimaryColor();
	this.secondaryColor = applicationState.getActiveSecondaryColor();
	this.shapeShadingType = applicationState.getActiveShapeShadingType();
	this.startPoint = startPoint;
	this.endPoint = endPoint;
	this.graphics2D = paintCanvas.getGraphics2D();
    }

    public void run() {

	System.out.println("Running shapeMaker: " + shapeType);
	iShape = model.shapeFactory.shapeMaker(paintCanvas, shapeType, startPoint, endPoint, primaryColor,
		secondaryColor, shapeShadingType);
	iShape.draw();
	lastShape = iShape;
	this.applicationState.getShapeList().add(iShape);
	CommandHistory.add(this);
	
	
    }
    

    @Override
    public void undo() {
	this.applicationState.getShapeList().remove(lastShape);
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, applicationState.getPaintCanvas().getWidth(), applicationState.getPaintCanvas().getHeight());
	
	for (iShape shape : applicationState.getShapeList().returnSelectedShapesList()) { 
	    shape.draw();
	}
    }

    @Override
    public void redo() {
	// redo is adding the shape into the shapelist that's in the redo stack
	this.applicationState.getShapeList().add(lastShape);
	System.out.println("redone? "); 
	for (iShape shape : applicationState.getShapeList().returnSelectedShapesList()) { 
	     shape.draw();
	}

    }
}
