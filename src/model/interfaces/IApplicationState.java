package model.interfaces;

import controller.controls.SelectedShapesList;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();
    
    SelectedShapesList getSSL();
    
    SelectedShapesList getShapeList();

    void deleteShapes();

    void copyShapes();

    void pasteShapes();

    void undoShapes();

    void redoShapes();
}
