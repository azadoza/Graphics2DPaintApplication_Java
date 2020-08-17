package model;
import model.interfaces.*;
import view.interfaces.IDialogChoice;
import view.interfaces.IUiModule;

public class DialogProviderContext implements IDialogProvider{
    IUiModule iuModule;
    public DialogProviderContext(IUiModule iuModule) {
	this.iuModule = iuModule;
    }

    @Override
    public IDialogChoice<ShapeType> getChooseShapeDialog() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public IDialogChoice<ShapeColor> getChoosePrimaryColorDialog() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public IDialogChoice<ShapeColor> getChooseSecondaryColorDialog() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public IDialogChoice<ShapeShadingType> getChooseShadingTypeDialog() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public IDialogChoice<StartAndEndPointMode> getChooseStartAndEndPointModeDialog() {
	// TODO Auto-generated method stub
	return null;
    }

}
