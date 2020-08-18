package controller;

import model.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface iShape {
    void draw();
    StartAndEndPointMode seMode();
    Point startPoint();
     Point endPoint();
     ShapeType shapeType();
     int height();
     int width();
     ShapeColor primaryColor();
     ShapeColor secondaryColor();
     ShapeShadingType shadeShadingType();

}
