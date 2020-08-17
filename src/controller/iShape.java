package controller;

import controller.controls.SelectedShapesList;
import model.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface iShape {
    void draw();
    void draw(SelectedShapesList selectedShapesList);
    StartAndEndPointMode seMode();
    Point startPoint();
     Point endPoint();
     ShapeType shapeType();
     int height();
     int width();
     void xShift(int xAxis);
     void yShift(int yAxis);
     ShapeColor primaryColor();
     ShapeColor secondaryColor();
     ShapeShadingType shadeShadingType();

}
