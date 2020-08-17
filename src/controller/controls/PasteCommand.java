/**
 * 
 */
package controller.controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import controller.Command;
import controller.CommandHistory;
import controller.iShape;
import model.Point;
import model.applicationStateContext;
import view.interfaces.PaintCanvasBase;

public class PasteCommand implements Command, IUndoable {
    applicationStateContext appState = applicationStateContext.getInstance();
    SelectedShapesList copyShapesList;
    iShape newShape;
    Graphics2D graphics2D;
      
    @Override
    public void undo() {
	graphics2D = appState.graphics2D;
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0,0, appState.getPaintCanvas().getWidth(), appState.getPaintCanvas().getHeight());
	
	this.appState.getShapeList().remove(newShape);
	for (iShape shape : appState.getShapeList().returnSelectedShapesList()) { 
	    //appState.getUnSelectedShapesList().selectedShapesList) {
	    shape.draw();
	}

    }

    @Override
    public void redo() {
	graphics2D = appState.graphics2D;
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, appState.getPaintCanvas().getWidth(), appState.getPaintCanvas().getHeight());
	
	// redo is adding the shape into the shapelist that's in the redo stack
	this.appState.getShapeList().add(newShape);
	System.out.println("redone? "); 
	for (iShape shape : appState.getShapeList().returnSelectedShapesList()) { 
	     shape.draw();
	}
    }

    @Override
    public void run() throws IOException {
	copyShapesList = appState.getSSL();
	PaintCanvasBase paintCanvas = appState.getPaintCanvas();
	    for (iShape shape : copyShapesList.returnSelectedShapesList()) {
		Point selectedStartPoint = new Point(5,5);
		Point selectedEndPoint = new Point(selectedStartPoint.getX() + shape.width(), selectedStartPoint.getY() + shape.height());
		    iShape newShape = model.shapeFactory.shapeMaker(paintCanvas, shape.shapeType(), selectedStartPoint, selectedEndPoint, shape.primaryColor(), shape.secondaryColor(), shape.shadeShadingType());
		    this.newShape = newShape;
		    newShape.draw();
	    }
	    CommandHistory.add(this);

    }

}
