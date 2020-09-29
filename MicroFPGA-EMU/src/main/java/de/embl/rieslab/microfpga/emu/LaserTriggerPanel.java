package de.embl.rieslab.microfpga.emu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.ColorUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.UIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.utils.ColorRepository;
import de.embl.rieslab.emu.utils.EmuUtils;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;
import de.embl.rieslab.microfpga.emu.utils.BinaryConverter;


public class LaserTriggerPanel extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	//////// Components
	private JLabel labelbehaviour_;
	private JLabel labelpulselength_;
	private JLabel labelsequence_;
	private JComboBox<String> comboMode;	
	//private JCheckBox usesequence_;
	private JTextField textfieldpulselength_;
	private JTextField textfieldsequence_;
	private JSlider sliderpulse_;
	private TitledBorder border_;

	//////// Properties
	private static final String TRIGGER_MODE = "mode";
	private static final String TRIGGER_SEQUENCE = "sequence";
	private static final String PULSE_LENGTH = "pulse duration";
	
	//////// Parameters
	private static final String PARAM_TITLE = "Name";
	private static final String PARAM_COLOR = "Color";
	private String title_;
	private Color color_;
	
	// Mojo FPGA
	private final static int FPGA_MAX_PULSE = 65535;
	private final static int FPGA_MAX_SEQUENCE = 65535;
	private final static String[] FPGA_BEHAVIOURS = {"Off","On","Rising","Falling","Camera"};
	
	public LaserTriggerPanel(String label) {
		super(label);
		
		setupPanel();
	}

	private void setupPanel() {
		this.setLayout(new GridBagLayout());
		
		border_ = BorderFactory.createTitledBorder(null, title_, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,  null, color_);
		this.setBorder(border_);
		border_.setTitleFont(border_.getTitleFont().deriveFont(Font.BOLD, 12));
	
		
		///////////////////////////////////////////////////////////////////////// Instantiate components
		
		/////////////////////////////////////////////////////// labels
		labelbehaviour_ = new JLabel("Trigger mode:");
		labelpulselength_ = new JLabel("Pulse length (us):");
		labelsequence_ = new JLabel("Trigger sequence:");

		/////////////////////////////////////////////////////// mode combobox
		comboMode = new JComboBox<String>(FPGA_BEHAVIOURS);
		comboMode.setToolTipText("Set the trigger behaviour of the laser.");
		//combobehaviour_.setMinimumSize(new Dimension(40,10));

		/////////////////////////////////////////////////////// pulse length
		textfieldpulselength_ = new JTextField("0");
		textfieldpulselength_.setToolTipText("Set the pulse length of the laser.");
		//textfieldpulselength_.setPreferredSize(new Dimension(60,20));
		
		sliderpulse_ = new JSlider(JSlider.HORIZONTAL, 0, FPGA_MAX_PULSE, 0);
		sliderpulse_.setToolTipText("Set the pulse length of the laser.");
		

		/////////////////////////////////////////////////////// sequence
		textfieldsequence_ = new JTextField();
		textfieldsequence_.setToolTipText("Set the pulse sequence of the laser.");
		textfieldsequence_.setMaximumSize(new Dimension(105,20));

		/////////////////////////////////////////////////////// place components
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(4,0,4,0);
		c.fill = GridBagConstraints.NONE;		
		//c.insets = new Insets(2,5,2,5);
		
		// 0,0
		c.gridwidth = 3;
		this.add(labelbehaviour_,c);

		c.gridy = 1;
		this.add(labelpulselength_,c);
		
		c.gridy = 2;
		c.gridwidth = 6;		
		c.fill = GridBagConstraints.HORIZONTAL;		
		this.add(sliderpulse_,c);
		
		c.gridy = 3;
		c.gridwidth = 3;	
		this.add(labelsequence_,c);

		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		this.add(comboMode,c);

		c.gridy = 1;
		this.add(textfieldpulselength_,c);

		c.gridy = 3;
		this.add(textfieldsequence_,c);

	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new MultiStateUIProperty(this, getPropertyLabel(TRIGGER_MODE),
				"Laser mode: property dictating the behaviour of the laser trigger, from camera to pulsing.",
				new NoFlag(), FPGA_BEHAVIOURS.length));
		
		try {
			((MultiStateUIProperty) getUIProperty(getPropertyLabel(TRIGGER_MODE))).setStateNames(FPGA_BEHAVIOURS);
		} catch (UnknownUIPropertyException e) {
			e.printStackTrace();
		}
		
		addUIProperty(new UIProperty(this, getPropertyLabel(TRIGGER_SEQUENCE),
				"Laser sequence: trigger sequence property, following a 16 bits pattern of 0 (not triggered) and 1 (triggered).",
				new NoFlag()));
		addUIProperty(new UIProperty(this, getPropertyLabel(PULSE_LENGTH),
				"Laser pulse length: duration of the laser pulses.", new NoFlag()));
	}

	@Override
	protected void initializeParameters() {
		title_ = "Laser";
		color_ = Color.black;		
		
		String modes = FPGA_BEHAVIOURS[0];
		for(int i=1;i<FPGA_BEHAVIOURS.length;i++) {
			modes = modes+","+FPGA_BEHAVIOURS[i];
		}
		
		addUIParameter(new StringUIParameter(this, PARAM_TITLE,"Name of the laser, as displayed in the border title.",title_));
		addUIParameter(new ColorUIParameter(this, PARAM_COLOR,"Color of the laser name.",color_)); 
	}

	@Override
	public void propertyhasChanged(String name, String newvalue) {
		if(getPropertyLabel(TRIGGER_MODE).equals(name)){
			try {
				MultiStateUIProperty msprop = ((MultiStateUIProperty) getUIProperty(getPropertyLabel(TRIGGER_MODE)));
				comboMode.setSelectedIndex(msprop.getStateIndex(newvalue));
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		} else if(getPropertyLabel(TRIGGER_SEQUENCE).equals(name)){
			if(EmuUtils.isInteger(newvalue)){
				textfieldsequence_.setText(BinaryConverter.getBinary16bits(Integer.parseInt(newvalue)));
			} else {
				textfieldsequence_.setText(BinaryConverter.getBinary16bits(FPGA_MAX_SEQUENCE));
			}
		} else if(getPropertyLabel(PULSE_LENGTH).equals(name)){
			textfieldpulselength_.setText(newvalue);
			if(EmuUtils.isInteger(newvalue)){
				sliderpulse_.setValue(Integer.parseInt(newvalue));
			}
		}
	}

	@Override
	public void parameterhasChanged(String label) {
		if(PARAM_TITLE.equals(label)){
			try {
				title_ = getStringUIParameterValue(PARAM_TITLE);
				border_.setTitle(title_);
				this.repaint();
				getUIProperty(getPropertyLabel(TRIGGER_MODE)).setFriendlyName(title_+" "+TRIGGER_MODE);
				getUIProperty(getPropertyLabel(TRIGGER_SEQUENCE)).setFriendlyName(title_+" "+TRIGGER_SEQUENCE);
				getUIProperty(getPropertyLabel(PULSE_LENGTH)).setFriendlyName(title_+" "+PULSE_LENGTH);
			} catch (UnknownUIParameterException | UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		} else if(PARAM_COLOR.equals(label)){
			try {
				color_ = getColorUIParameterValue(PARAM_COLOR);
				border_.setTitleColor(color_);
				this.repaint();			
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {
		// do nothing
	}

	@Override
	public String getDescription() {
		return "The "+getPanelLabel()+" panel controls the triggering of laser thanks to the MicroMojo FPGA system. The triggering behaviour are either on/off, "
				+ ", pulsing on rising/falling edge or simply following the camera trigger. The pulse length can be set through a text area or a slider. "
				+ "Finally, the laser can be triggered following a sequence of 0 (off) and 1 (triggered). The sequence is 16 bits long. If the sequence set "
				+ "in the text area is made of 0 and 1, albeit with the wrong size, the text is colored in blue. When wrong characters are entered the text "
				+ "turns red.";
	}

	@Override
	protected void initializeInternalProperties() {
		// Do nothing
	}

	@Override
	public void internalpropertyhasChanged(String label) {
		// Do nothing
	}
	
	private String getPropertyLabel(String propName) {
		return getPanelLabel()+" "+propName;
	}

	@Override
	protected void addComponentListeners() {
		
		// updates TRIGGER_BEHAVIOUR based on the index of the JComboBox
		SwingUIListeners.addActionListenerOnSelectedIndex(this, getPropertyLabel(TRIGGER_MODE), comboMode);
		
		// Updates JSlider when updating PULSE_LENGTH from the JTextField
		textfieldpulselength_.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {	
				String s = textfieldpulselength_.getText();
				if (EmuUtils.isInteger(s)) {
					if(Integer.parseInt(s)<=FPGA_MAX_PULSE){
						sliderpulse_.setValue(Integer.parseInt(s));
						setUIPropertyValue(getPropertyLabel(PULSE_LENGTH),s);
					} else {
						sliderpulse_.setValue(FPGA_MAX_PULSE);
						textfieldpulselength_.setText(String.valueOf(FPGA_MAX_PULSE));
						setUIPropertyValue(getPropertyLabel(PULSE_LENGTH),String.valueOf(FPGA_MAX_PULSE));
					}
				}
	         }
	    });
		textfieldpulselength_.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent ex) {}

			@Override
			public void focusLost(FocusEvent ex) {
				String s = textfieldpulselength_.getText();
				if (EmuUtils.isInteger(s)) {
					if (Integer.parseInt(s) <= FPGA_MAX_PULSE) {
						sliderpulse_.setValue(Integer.parseInt(s));
						setUIPropertyValue(getPropertyLabel(PULSE_LENGTH), s);
					} else {
						sliderpulse_.setValue(FPGA_MAX_PULSE);
						textfieldpulselength_.setText(String.valueOf(FPGA_MAX_PULSE));
						setUIPropertyValue(getPropertyLabel(PULSE_LENGTH),String.valueOf(FPGA_MAX_PULSE));
					}
				}
			}
		});
		
		// Same from the JSlider point of view
		SwingUIListeners.addActionListenerOnIntegerValue(this, getPropertyLabel(PULSE_LENGTH), sliderpulse_, textfieldpulselength_);

		// JTextField sequence with 16 x {0} or {1}, check the user input
		textfieldsequence_.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {	
				String s = textfieldsequence_.getText();
				if (BinaryConverter.is16bits(s)) {
					textfieldsequence_.setForeground(ColorRepository.getColor("black"));
					String str = String.valueOf(BinaryConverter.getDecimal16bits(s));
					setUIPropertyValue(getPropertyLabel(TRIGGER_SEQUENCE),str);
				} else if(BinaryConverter.isBits(s)) {
					textfieldsequence_.setForeground(ColorRepository.getColor("blue"));
				} else {
					textfieldsequence_.setForeground(ColorRepository.getColor("red"));
				}
	         }
	    });
		textfieldsequence_.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent ex) {}

			@Override
			public void focusLost(FocusEvent ex) {
				String s = textfieldsequence_.getText();
				if (BinaryConverter.is16bits(s)) {
					textfieldsequence_.setForeground(ColorRepository.getColor("black"));
					String str = String.valueOf(BinaryConverter.getDecimal16bits(s));
					setUIPropertyValue(getPropertyLabel(TRIGGER_SEQUENCE),str);
				
				} else if(BinaryConverter.isBits(s)) {
					textfieldsequence_.setForeground(ColorRepository.getColor("blue"));
				} else {
					textfieldsequence_.setForeground(ColorRepository.getColor("red"));
				}
	         }
		});
	}
}
