package model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import controller.iColorfulShape;
import controller.iShape;
import controller.controls.SelectedShapesList;
import view.interfaces.PaintCanvasBase;

public class rectangle implements iShape {
    public Point startPoint;
    public Point endPoint;
    public PaintCanvasBase paintCanvas;
    public ShapeType shapeType;
    public int height;
    public int width;
    public ShapeColor primaryColor;
    public ShapeColor secondaryColor;
    ShapeShadingType shapeShadingType;
    public iColorfulShape primaryColorChose;
    public iColorfulShape secondaryColorChose;
    SelectedShapesList selectedShapesList;
    Stroke basicStroke = new BasicStroke(10);

    

    public rectangle(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, ShapeColor primaryColor, 
	    ShapeColor secondaryColor, ShapeShadingType shapeShadingType) {
        this.paintCanvas = paintCanvas;
	this.startPoint = startPoint;
	this.endPoint = endPoint;
	this.primaryColor = primaryColor;
	this.secondaryColor = secondaryColor;
        this.shapeShadingType = shapeShadingType;
	primaryColorChose = new iColorfulShape(primaryColor);
	secondaryColorChose = new iColorfulShape(secondaryColor);
    }
  

    public Point startPoint() {
	return this.startPoint;
    }

    public Point endPoint() {
	return this.endPoint;
    }

    public ShapeType shapeType() {
	return ShapeType.RECTANGLE;
    }

    public int height() {
	height = Math.abs(endPoint.getY() - startPoint.getY());
	return height;
    }

    public int width() {
	width = Math.abs(endPoint.getX() - startPoint.getX());
	return width;
    }

    public ShapeColor primaryColor() {
	return primaryColor;
    }

    public ShapeColor secondaryColor() {
	return secondaryColor;
    }

    public ShapeShadingType shadeShadingType() {
	return shapeShadingType;
    }
@Override
    public void draw() {
	Graphics2D graphics2d = paintCanvas.getGraphics2D();
	if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
//      graphics2d.setColor(Color.GREEN);
//      graphics2d.fillRect(12, 13, 200, 400);
	    //Outline Only –Only shape outline will be drawn. Use Primary Color to draw this.
	    graphics2d.setColor(primaryColorChose.getColor(primaryColor));
	    graphics2d.fillRect(startPoint.getX(), startPoint.getY(), width(), height());
	}
	else if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
	    // graphics2d.setStroke(new BasicStroke(5));
	    // graphics2d.setColor(Color.BLUE);
	    // graphics2d.drawRect(12, 13, 200, 400);
	    //Filled-In –Only the inside of the shape will be drawn –there will be no visible outline. Use Primary Color to draw this.
	    graphics2d.setColor(primaryColorChose.getColor(primaryColor));
	    graphics2d.setStroke(basicStroke);
	    graphics2d.drawRect(startPoint.getX(), startPoint.getY(), width(), height());
	}
	else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
	    //Both the inside and the outline will be drawn. Use Primary Color for the inside and Secondary Color for the outline.
	    graphics2d.setColor(secondaryColorChose.getColor(secondaryColor));
	    graphics2d.setStroke(basicStroke);
	    graphics2d.drawRect((startPoint.getX()), (startPoint.getY()), width(), height());
	    graphics2d.setColor(primaryColorChose.getColor(primaryColor));
	    graphics2d.fillRect(startPoint.getX(), (startPoint.getY()), width(), height());

	}

    }

//@Override
//public void draw(SelectedShapesList selectedShapesList) {
//    Graphics2D graphics2d = paintCanvas.getGraphics2D();
//    Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//    graphics2d.setStroke(stroke);
//    graphics2d.setColor(primaryColorChose.getColor(primaryColor));
//    width = width() + 5;
//    height = height() + 5;
//    graphics2d.drawRect(startPoint.getX(), startPoint.getY(), width(), height());
//    
//}


@Override
public StartAndEndPointMode seMode() {
    // TODO Auto-generated method stub
    return null;
}
}
