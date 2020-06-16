package de.embl.rieslab.microfpga.emu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class ServoComboPanel extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	//////// Properties
	private JComboBox<String> combo_;	
	private TitledBorder border_;
	
	//////// Properties
	private static final String POSITION = "channel position";
	
	//////// Parameters
	private static final String NAME = "name";
	private static final String NAME_POS = "positions name";
	private String[] defaults;
	private static final int MAX_POS = 6;
	
	public ServoComboPanel(String label) {
		super(label);
		
		border_ = BorderFactory.createTitledBorder(null, getPanelLabel(), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,  null, Color.black);
		this.setBorder(border_);
		border_.setTitleFont(border_.getTitleFont().deriveFont(Font.BOLD, 12));
		
		combo_ = new JComboBox<String>(defaults);
		this.add(combo_);
	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new MultiStateUIProperty(this, getPropertyLabel(POSITION), "Channel linked to a drop-down list with "+MAX_POS+" positions.", new NoFlag(), MAX_POS));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(getPropertyLabel(POSITION).equals(newvalue)){
			try {
				combo_.setSelectedIndex(((MultiStateUIProperty) getUIProperty(getPropertyLabel(POSITION))).getStateIndex(newvalue));
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
	protected void addComponentListeners() {	
		SwingUIListeners.addActionListenerOnSelectedIndex(this, getPropertyLabel(POSITION), combo_);
	}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	public void internalpropertyhasChanged(String propertyName) {}

	@Override
	protected void initializeParameters() {
		addUIParameter(new StringUIParameter(this, getPropertyLabel(NAME),"Text displayed in above the drop-down list.",getPanelLabel()));
		
		StringBuilder sb = new StringBuilder(MAX_POS);
		defaults = new String[MAX_POS];
		for(int i=0;i<MAX_POS;i++) {
			defaults[i] = "Pos"+i;
			sb.append(defaults[i]);
			sb.append(",");
		}

		sb.deleteCharAt(sb.length()-1);
		addUIParameter(new StringUIParameter(this, getPropertyLabel(NAME_POS),"Comma separated names of the positions, there must be "+MAX_POS+" values.",sb.toString()));
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
		if (getPropertyLabel(NAME_POS).equals(parameterName)) {
			try {
				String s = getStringUIParameterValue(getPropertyLabel(NAME_POS));
				setNames(s);
			} catch (UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void setNames(String s){
		String[] astr = s.split(",");
		int maxind = MAX_POS > astr.length ? astr.length : MAX_POS;
		String[] vals = new String[MAX_POS];
		for(int i=0;i<maxind;i++){
			vals[i] = astr[i];
		}
		for(int i=maxind;i<MAX_POS;i++) {
			vals[i] = defaults[i];
		}
		
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) combo_.getModel();
        model.removeAllElements();

        for (String item : vals) {
            model.addElement(item);
        }
        combo_.setModel(model);		
	}

	@Override
	public String getDescription() {
		return "The "+getPanelLabel()+" contains a drop-down list with "+MAX_POS+" positions.";
	}

	@Override
	public void shutDown() {}
	
	private String getPropertyLabel(String propName) {
		return getPanelLabel()+" "+propName;
	}
}
