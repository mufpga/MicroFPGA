package de.embl.rieslab.microfpga.emu;

import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.TwoStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIPropertyTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class TTLPanel extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	//////// Properties
	private JToggleButton toggleOnOff_;	
	
	//////// Properties
	private static final String STATE = "channel state";
	
	//////// Parameters
	private static final String NAME = "name";
	
	public TTLPanel(String label) {
		super(label);
		
		toggleOnOff_ = new JToggleButton();
		this.add(toggleOnOff_);
	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new TwoStateUIProperty(this, getPropertyLabel(STATE), "Channel linked to an on/off button of the TTL tab.", new NoFlag()));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(getPropertyLabel(STATE).equals(newvalue)){
			try {
				toggleOnOff_.setSelected(((TwoStateUIProperty) getUIProperty(getPropertyLabel(STATE))).isOnState(newvalue));
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
	protected void addComponentListeners() {		
		try {
			SwingUIListeners.addActionListenerToTwoState(this, getPropertyLabel(STATE), toggleOnOff_);
		} catch (IncorrectUIPropertyTypeException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	public void internalpropertyhasChanged(String propertyName) {}

	@Override
	protected void initializeParameters() {
		addUIParameter(new StringUIParameter(this, getPropertyLabel(NAME),"Text displayed on the on/off button in the TTL tab.",getPanelLabel()));
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		if (getPropertyLabel(NAME).equals(parameterName)) {
			try {
				String s = getStringUIParameterValue(parameterName);
				toggleOnOff_.setText(s);
				getUIProperty(getPropertyLabel(STATE)).setFriendlyName(s);
			} catch (UnknownUIParameterException | UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getDescription() {
		return "The "+getPanelLabel()+" includes an toggle button switching the device between two states.";
	}

	@Override
	public void shutDown() {}
	
	private String getPropertyLabel(String propName) {
		return getPanelLabel()+" "+propName;
	}
}
