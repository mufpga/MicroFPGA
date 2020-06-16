package de.embl.rieslab.microfpga.emu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.RescaledUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.utils.EmuUtils;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class PWMPanel extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	//////// Properties
	private JTextField textfield_;
	private JSlider slider_;	
	private TitledBorder border_;
	
	//////// Properties
	private static final String POSITION = "channel value";
	
	//////// Parameters
	private static final String NAME = "name";
	
	private static final int MAX = 100;
	
	public PWMPanel(String label) {
		super(label);
		
		this.setLayout(new GridBagLayout());
		
		border_ = BorderFactory.createTitledBorder(null, getPanelLabel(), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black);
		this.setBorder(border_);
		border_.setTitleFont(border_.getTitleFont().deriveFont(Font.BOLD, 12));
		
		textfield_ = new JTextField(String.valueOf(MAX)+".0");
		slider_ = new JSlider(JSlider.HORIZONTAL, 0, MAX, MAX);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2,15,2,15);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		this.add(new JLabel("Value:"),c);

		c.gridx = 1;
		this.add(textfield_,c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		this.add(slider_,c);
	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new RescaledUIProperty(this, getPropertyLabel(POSITION), "PWM channel value as a percentage. Use the slope and offset values to change the range.", new NoFlag()));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(getPropertyLabel(POSITION).equals(newvalue)){
			if(EmuUtils.isNumeric(newvalue)){
				double val = Double.parseDouble(newvalue);
				if(val>=0 && val<=MAX){
					textfield_.setText(String.valueOf(val));
					slider_.setValue((int) val);	
				}
			}
		} 
	}

	@Override
	protected void addComponentListeners() {		
		SwingUIListeners.addActionListenerOnIntegerValue(this, getPropertyLabel(POSITION), textfield_, 0, MAX);
		SwingUIListeners.addActionListenerOnIntegerValue(this, getPropertyLabel(POSITION), slider_, textfield_);
	}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	public void internalpropertyhasChanged(String propertyName) {}

	@Override
	protected void initializeParameters() {
		addUIParameter(new StringUIParameter(this, getPropertyLabel(NAME),"Title of the panel.", getPanelLabel()));
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		if (getPropertyLabel(NAME).equals(parameterName)) {
			try {
				String s = getStringUIParameterValue(NAME);
				border_.setTitle(s);
				this.repaint();
				getUIProperty(getPropertyLabel(POSITION)).setFriendlyName(s+" "+POSITION);
			} catch (UnknownUIParameterException | UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getDescription() {
		return "The "+getPanelLabel()+" panel includes a text field and a slider sharing the same value.";
	}

	@Override
	public void shutDown() {}
	
	private String getPropertyLabel(String propName) {
		return getPanelLabel()+" "+propName;
	}
}
