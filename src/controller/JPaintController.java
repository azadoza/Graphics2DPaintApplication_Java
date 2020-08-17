package controller;

import java.io.IOException;

import controller.controls.DeleteCommand;
import controller.controls.PasteCommand;
import controller.controls.RedoCommand;
import controller.controls.UndoCommand;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private UndoCommand undoCommand = new UndoCommand();
    private RedoCommand redoCommand = new RedoCommand();
    private PasteCommand pasteCommand = new PasteCommand();
    private DeleteCommand deleteCommand = new DeleteCommand();
    
    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.DELETE, () -> {
	    try {
		deleteCommand.run();
	    }
	    catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	});
        uiModule.addEvent(EventName.COPY, () -> applicationState.copyShapes());
        uiModule.addEvent(EventName.PASTE, () -> {
	    try {
		pasteCommand.run();
	    }
	    catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	});
        uiModule.addEvent(EventName.UNDO, () -> undoCommand.run());
        uiModule.addEvent(EventName.REDO, () -> redoCommand.run());
    }
}
