package model;

import controller.iShape;
import controller.controls.SelectedShapesList;
import view.interfaces.PaintCanvasBase;

public class shapeFactory {
    static // context
    iShape iShape;
    SelectedShapesList selectedShapesList;

    public shapeFactory(iShape iShape) {
	shapeFactory.iShape = iShape;
    }

    public static controller.iShape shapeMaker(PaintCanvasBase paintCanvas, ShapeType shapeType, Point startPoint,
	    Point endPoint, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shapeShadingType) {

	if (shapeType == null) {
	    iShape = new rectangle(paintCanvas, startPoint, endPoint, ShapeColor.BLACK, ShapeColor.BLUE,
		    ShapeShadingType.OUTLINE_AND_FILLED_IN);
	    return iShape;
	}

	else if (shapeType.equals(ShapeType.RECTANGLE)) { // .equals compares value of obj
	    iShape = new rectangle(paintCanvas, startPoint, endPoint, primaryColor, secondaryColor, shapeShadingType);
	}
	else if (shapeType.equals(ShapeType.ELLIPSE)) {
	    iShape = new ellipse(paintCanvas, startPoint, endPoint, primaryColor, secondaryColor, shapeShadingType);
	}
	else if (shapeType.equals(ShapeType.TRIANGLE)) {
	    iShape = new triangle(paintCanvas, startPoint, endPoint, primaryColor, secondaryColor, shapeShadingType);
	}
	return iShape;
    }
}
