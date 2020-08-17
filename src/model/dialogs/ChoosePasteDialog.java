package model.dialogs;

import model.Paste;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChoosePasteDialog implements IDialogChoice<Paste> {
    private final IApplicationState applicationState;

    public ChoosePasteDialog(IApplicationState applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Paste";
    }

    @Override
    public String getDialogText() {
        return "Paste or DONT";
    }

    @Override
    public Paste[] getDialogOptions() {
        return Paste.values();
    }

    @Override
    public Paste getCurrentSelection() {
        return applicationState.getActivePaste();
    }
}
