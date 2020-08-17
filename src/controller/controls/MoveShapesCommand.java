package controller.controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import controller.*;
import model.*;
import view.interfaces.PaintCanvasBase;

public class MoveShapesCommand implements Command, IUndoable {
    public Graphics2D graphics2D;
    Point startPoint;
    Point endPoint;
    PaintCanvasBase paintCanvas;
    iShape iShape;
    iShape noteMovedShape;
    iShape noteStationaryShape;
    applicationStateContext appState;
    shapeFactory shapeFactory = new shapeFactory(iShape);
    SelectedShapesList shapesList = new SelectedShapesList();
    StartAndEndPointMode seMode;
    int height;
    int width;
    Point sPoint;
    Point ePoint;
    Point newPoint;
    SelectedShapesList moveShapesList;
    int dx;
    int dy;
    public SelectedShapesList unSelectedShapesList = new SelectedShapesList();

    public MoveShapesCommand(PaintCanvasBase paintCanvas, applicationStateContext appState, Point startPoint,
	    Point endPoint) {
	this.paintCanvas = paintCanvas;
	this.appState = appState;
	this.startPoint = startPoint;
	this.endPoint = endPoint;
	this.moveShapesList = appState.getSSL();
	this.shapesList = appState.getShapeList();
	this.seMode = appState.getActiveStartAndEndPointMode();
	this.graphics2D = paintCanvas.getGraphics2D();

    }

    @Override
    public void run() throws IOException {
	System.out.println("Running shapeSelector");
	// clearing the canvas
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

	// repainting the shapes that weren't selected
	for (iShape shape : appState.getUnSelectedShapesList().selectedShapesList) {
	    noteStationaryShape = shape;
	    shape.draw();
	}

	// drawing the shapes in the new position
	for (iShape moveShape : moveShapesList.returnSelectedShapesList()) {
	    if (startPoint.getX() <= moveShape.startPoint().getX()) {
		int x = (moveShape.startPoint().getX() - startPoint.getX());
		dx = (moveShape.startPoint().getX() - x);
	    }
	    else if (startPoint.getX() > moveShape.startPoint().getX()) {
		int x = (startPoint.getX() - moveShape.startPoint().getX());
		dx = (moveShape.startPoint().getX() + x);
	    }

	    if (startPoint.getY() <= moveShape.startPoint().getY()) {
		int y = (moveShape.startPoint().getY() - startPoint.getY());
		dy = (moveShape.startPoint().getY() - y);
	    }
	    else if (startPoint.getY() > moveShape.startPoint().getY()) {
		int y = (startPoint.getY() - moveShape.startPoint().getY());
		dy = (moveShape.startPoint().getY() + y);
	    }
	    sPoint = new Point(dx, dy);
	    ePoint = new Point(dx + moveShape.width(), dy + moveShape.height());

	    iShape newShape = model.shapeFactory.shapeMaker(this.paintCanvas, moveShape.shapeType(), sPoint, ePoint,
		    moveShape.primaryColor(), moveShape.secondaryColor(), moveShape.shadeShadingType());
	    System.out.println("shape moved: " + newShape.startPoint().getX());
	    noteMovedShape=newShape;
	    noteMovedShape.draw();
	    
	}CommandHistory.add(this);
    }

    @Override
    public void undo() {
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
	// repainting the shapes that weren't selected and that were...just repainting everything...sloppy
	for (iShape shape : appState.getUnSelectedShapesList().selectedShapesList) {
	    shape.draw();
	}
	for (iShape moveShape : moveShapesList.returnSelectedShapesList()) {
	    moveShape.draw();
	}
	}

    @Override
    public void redo() {
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
	noteMovedShape.draw();
	for (iShape shape : appState.getUnSelectedShapesList().selectedShapesList) {
	    shape.draw();
	}
    }
}
