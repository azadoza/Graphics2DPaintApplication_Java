package controller.controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import controller.Command;
import controller.CommandHistory;
import controller.iShape;
import model.applicationStateContext;
import model.shapeFactory;
import view.interfaces.PaintCanvasBase;

public class DeleteCommand implements Command, IUndoable {
    public Graphics2D graphics2D;
    public SelectedShapesList unSelectedShapesList;
    PaintCanvasBase paintCanvas;
    iShape iShape;
    iShape lastShape;
    shapeFactory shapeFactory = new shapeFactory(iShape);
    SelectedShapesList shapesList = new SelectedShapesList();
    private applicationStateContext appState = applicationStateContext.getInstance();

    public DeleteCommand() {

    }

    @Override
    public void run() throws IOException {
	unSelectedShapesList = appState.getUnSelectedShapesList();
	graphics2D = appState.graphics2D;
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, appState.getPaintCanvas().getWidth(), appState.getPaintCanvas().getHeight());
	for (iShape shape : unSelectedShapesList.returnSelectedShapesList()) {
	    shape.draw();
	    lastShape=shape;
	}
	CommandHistory.add(this);

    }

    @Override
    public void undo() {
	this.appState.getShapeList().add(lastShape);
	graphics2D.fillRect(0, 0, appState.getPaintCanvas().getWidth(), appState.getPaintCanvas().getHeight());
	for (iShape shape : appState.getShapeList().returnSelectedShapesList()) {
	    shape.draw();
	}

    }

    @Override
    public void redo() {
	this.appState.getShapeList().remove(lastShape);
	System.out.println("deleted again bub"); 
	unSelectedShapesList = appState.getUnSelectedShapesList();
	graphics2D = appState.graphics2D;
	graphics2D.setColor(Color.white);
	graphics2D.fillRect(0, 0, appState.getPaintCanvas().getWidth(), appState.getPaintCanvas().getHeight());
	for (iShape shape : unSelectedShapesList.returnSelectedShapesList()) {
	    shape.draw();
	}
    }

}
