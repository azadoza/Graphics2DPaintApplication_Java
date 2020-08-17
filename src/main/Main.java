package main;

import controller.IJPaintController;
import model.*;
import controller.JPaintController;
import controller.MouseListener;
import controller.iShape;
import model.applicationStateContext;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;
import model.IShapeCommand;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        applicationStateContext appContext = applicationStateContext.getInstance();
        appContext.setIUiModule(uiModule);
        appContext.setPaintCanvas(paintCanvas);
        IJPaintController controller = new JPaintController(uiModule, appContext);
        controller.setup();
        System.out.println("shapeType: " + appContext.getActiveShapeType());
	paintCanvas.addMouseListener(new MouseListener(paintCanvas, appContext));

        // For example purposes only; remove all lines below from your final project.

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
//        // Filled in rectangle
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);

        // Clears the Canvas
        //paintCanvas.repaint();
    }
}
