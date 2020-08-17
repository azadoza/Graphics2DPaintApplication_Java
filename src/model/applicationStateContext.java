
package model;

import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.controls.SelectedShapesList;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class applicationStateContext implements IApplicationState {
    public Point selectedStartPoint = new Point(0,0);
    public Point selectedEndPoint = new Point(100,100);
    public PaintCanvasBase paintCanvas;
    public int height;
    public int width;
    public Graphics2D graphics2D;
    public Graphics graphics;
    private IUiModule uiModule;
    private IDialogProvider iDialogProvider;
    private ShapeType shapeType = ShapeType.ELLIPSE;
    private ShapeColor primaryColor = ShapeColor.LIGHT_GRAY;
    private ShapeColor secondaryColor = ShapeColor.MAGENTA;
    private ShapeShadingType shapeShadingType = ShapeShadingType.OUTLINE_AND_FILLED_IN;
    private StartAndEndPointMode startEndMode = StartAndEndPointMode.DRAW;
    public SelectedShapesList shapeList = new SelectedShapesList();
    public SelectedShapesList selectedShapesList = new SelectedShapesList();
    public SelectedShapesList unSelectedShapesList = new SelectedShapesList();
    public SelectedShapesList copyShapesList = new SelectedShapesList();

    public static applicationStateContext instance;

    private applicationStateContext() {
    }

    public static applicationStateContext getInstance() {
	if (instance == null) {
	    instance = new applicationStateContext();
	}
	return instance; // appStateContext
    }

    public void setIUiModule(IUiModule uiModule) {
	this.uiModule = uiModule;
	this.iDialogProvider = new DialogProvider(this);
    }

    @Override
    public void setActiveShape() {
	// IDialogChoice choice = new IDialogChoice();
	this.shapeType = uiModule.getDialogResponse(iDialogProvider.getChooseShapeDialog());

    }

    @Override
    public void setActivePrimaryColor() {
	this.primaryColor = uiModule.getDialogResponse(iDialogProvider.getChoosePrimaryColorDialog());
	// System.out.print(primaryColor);
    }

    @Override
    public void setActiveSecondaryColor() {
	this.secondaryColor = uiModule.getDialogResponse(iDialogProvider.getChooseSecondaryColorDialog());

    }

    @Override
    public void setActiveShadingType() {
	this.shapeShadingType = uiModule.getDialogResponse(iDialogProvider.getChooseShadingTypeDialog());

    }

    @Override
    public void setActiveStartAndEndPointMode() {
	this.startEndMode = uiModule.getDialogResponse(iDialogProvider.getChooseStartAndEndPointModeDialog());

    }
    
    public void setSelectedShapesList(SelectedShapesList shapesList) {
	this.shapeList = shapesList;
    }

    @Override
    public void undoShapes() {
	// TODO Auto-generated method stub

    }

    @Override
    public void redoShapes() {
	// TODO Auto-generated method stub

    }

    @Override
    public ShapeType getActiveShapeType() {
	return shapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
	return primaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
	// TODO Auto-generated method stub
	return secondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
	// TODO Auto-generated method stub
	return shapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
	// TODO Auto-generated method stub
	return startEndMode;
    }

    @Override
    public SelectedShapesList getSSL() {
	// TODO Auto-generated method stub
	return selectedShapesList;
    }

    @Override
    public SelectedShapesList getShapeList() {
	// TODO Auto-generated method stub
	return shapeList;
    }

    public SelectedShapesList getUnSelectedShapesList() {
	return unSelectedShapesList;
    }

    public void setPaintCanvas(PaintCanvasBase paintCanvas) {
	this.paintCanvas = paintCanvas;
	this.graphics2D = paintCanvas.getGraphics2D();

    }
    
    public PaintCanvasBase getPaintCanvas() {
	return this.paintCanvas;
    }

    @Override
    public void deleteShapes() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void copyShapes() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void pasteShapes() {
	// TODO Auto-generated method stub
	
    }

}