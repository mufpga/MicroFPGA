package de.embl.rieslab.microfpga.emu;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;
import de.embl.rieslab.emu.utils.settings.BoolSetting;
import de.embl.rieslab.emu.utils.settings.IntSetting;
import de.embl.rieslab.emu.utils.settings.Setting;
import de.embl.rieslab.emu.utils.settings.StringSetting;
import de.embl.rieslab.microfpga.emu.LaserTriggerPanel;

public class MainFrame  extends ConfigurableMainFrame{

	private static final long serialVersionUID = 1L;

	private static final int MAX_LASERS = 10;
	private static final int MAX_SERVOS = 10;
	private static final int MAX_TTL = 10;
	private static final int MAX_PWM = 10;
	private static final int MAX_AI = 8;
	
	// settings
	private static final String SETTING_USE_TRIGGER = "Trigger tab";
	private static final String SETTING_USE_SERVO = "Servo tab";
	private static final String SETTING_USE_SERVO_COMBO = "Servo tab with drop-down list";
	private static final String SETTING_USE_TTL = "TTL tab";
	private static final String SETTING_USE_PWM = "PWM tab";
	private static final String SETTING_USE_AI = "Analog input tab";
	private static final String SETTING_USE_AI_MONITOR = "Monitor analog tab";

	private static final String SETTING_NAME_TRIGGER = "Laser tab title";
	private static final String SETTING_NAME_SERVO = "Servos tab title";
	private static final String SETTING_NAME_TTL = "TTL tab title";
	private static final String SETTING_NAME_PWM = "PWM tab title";
	private static final String SETTING_NAME_AI = "Analog tab title";
	private static final String SETTING_NAME_MONITOR = "Monitor analog title";
	
	private static final String SETTING_NUM_LASERS = "Number of lasers";
	private static final String SETTING_NUM_SERVO = "Number of servos";
	private static final String SETTING_NUM_SERVO_COMBO = "Number of servos with drop-down list";
	private static final String SETTING_NUM_TTL = "Number of TTL";
	private static final String SETTING_NUM_PWM = "Number of PWM";
	private static final String SETTING_NUM_AI = "Number of AI";
	
	public MainFrame(String title, SystemController controller, TreeMap<String, String> pluginSettings) {
		super(title, controller, pluginSettings);
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		HashMap<String, Setting> defaultSettings = new HashMap<String, Setting>();
		defaultSettings.put(SETTING_USE_TRIGGER, new BoolSetting(SETTING_USE_TRIGGER, "Check to enable the laser trigger tab.", true));
		defaultSettings.put(SETTING_USE_SERVO, new BoolSetting(SETTING_USE_SERVO, "Check to enable the servo tab.", true));
		defaultSettings.put(SETTING_USE_SERVO_COMBO, new BoolSetting(SETTING_USE_SERVO_COMBO, "Check to enable the servo tab with drop-down lists.", true));
		defaultSettings.put(SETTING_USE_TTL, new BoolSetting(SETTING_USE_TTL, "Check to enable the TTL tab.", true));
		defaultSettings.put(SETTING_USE_PWM, new BoolSetting(SETTING_USE_PWM, "Check to enable the PWM tab.", true));
		defaultSettings.put(SETTING_USE_AI, new BoolSetting(SETTING_USE_AI, "Check to enable the analog input tab.", true));
		defaultSettings.put(SETTING_USE_AI_MONITOR, new BoolSetting(SETTING_USE_AI_MONITOR, "Check to enable the analog monitoring tab.", true));
		
		
		defaultSettings.put(SETTING_NAME_TRIGGER, new StringSetting(SETTING_NAME_TRIGGER, "Title of the laser trigger tab.", "Trigger"));
		defaultSettings.put(SETTING_NAME_SERVO, new StringSetting(SETTING_NAME_SERVO, "Title of the servo tab.", "Servos"));
		defaultSettings.put(SETTING_NAME_TTL, new StringSetting(SETTING_NAME_TTL, "Title of the TTL tab.", "TTL"));
		defaultSettings.put(SETTING_NAME_PWM, new StringSetting(SETTING_NAME_PWM, "Title of the PWM tab.", "PWM"));
		defaultSettings.put(SETTING_NAME_AI, new StringSetting(SETTING_NAME_AI, "Title of the analog input tab.", "AI"));
		defaultSettings.put(SETTING_NAME_MONITOR, new StringSetting(SETTING_NAME_MONITOR, "Title of the analog monitoring tab.", "Monitor"));

		defaultSettings.put(SETTING_NUM_LASERS, new IntSetting(SETTING_NUM_LASERS, "Number of lasers (max = "+MAX_LASERS+").", 4));
		defaultSettings.put(SETTING_NUM_SERVO, new IntSetting(SETTING_NUM_SERVO, "Number of servos (max = "+MAX_SERVOS+")."
				+ "Note that the number of servos is fixed, ans shared between panels with and without drop-down list.", 4));
		defaultSettings.put(SETTING_NUM_SERVO_COMBO, new IntSetting(SETTING_NUM_SERVO_COMBO, "Number of servos with drop-down list (max = "+MAX_SERVOS+")."
				+ "Note that the number of servos is fixed, ans shared between panels with and without drop-down list.", 4));
		defaultSettings.put(SETTING_NUM_TTL, new IntSetting(SETTING_NUM_TTL, "Number of TTL channels (max = "+MAX_TTL+").", 4));
		defaultSettings.put(SETTING_NUM_PWM, new IntSetting(SETTING_NUM_PWM, "Number of PWM channels (max = "+MAX_PWM+").", 4));
		defaultSettings.put(SETTING_NUM_AI, new IntSetting(SETTING_NUM_AI, "Number of analog input channels (max = "+MAX_AI+").", 4));
		
		return defaultSettings;
	}

	@Override
	protected void initComponents() {
    	HashMap<String, Setting> settings = this.getCurrentPluginSettings();
    	
    	JTabbedPane tabs = new JTabbedPane();

    	////////////////////////// Laser trigger
    	if(((BoolSetting) settings.get(SETTING_USE_TRIGGER)).getValue()) {

    		JPanel lasertrigg = new JPanel();
            JScrollPane scroll = new JScrollPane(lasertrigg);
			
            int horizontalPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
            int vericalPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
     
            scroll.setHorizontalScrollBarPolicy(horizontalPolicy);
            scroll.setVerticalScrollBarPolicy(vericalPolicy);
            
			int n_lasers = ((IntSetting) settings.get(SETTING_NUM_LASERS)).getValue();
			
			if(n_lasers > 0) {
				if(n_lasers > MAX_LASERS) {
					n_lasers = MAX_LASERS;
				}
				int n_rows = n_lasers % 2 == 0 ? n_lasers/2 : (n_lasers+1)/2;
				
				lasertrigg.setLayout(new GridLayout(n_rows,2));
				for(int i=0;i<n_lasers;i++){
					LaserTriggerPanel pane = new LaserTriggerPanel("Laser "+i);
					lasertrigg.add(pane);
				}
				
				String title = settings.get(SETTING_NAME_TRIGGER).getStringValue();
				tabs.add(title, scroll);
			}
    	}

    	////////////////////////// Servos
    	if(((BoolSetting) settings.get(SETTING_USE_SERVO)).getValue() || 
    			((BoolSetting) settings.get(SETTING_USE_SERVO_COMBO)).getValue()) {
			JPanel servos = new JPanel();

			int n_servos = ((IntSetting) settings.get(SETTING_NUM_SERVO)).getValue();
			int n_servos_combo = ((IntSetting) settings.get(SETTING_NUM_SERVO_COMBO)).getValue();
					
			if(n_servos+n_servos_combo > 0) {
				if(n_servos+n_servos_combo > MAX_SERVOS) {
					n_servos = MAX_SERVOS % 2 == 0 ? MAX_SERVOS/2 : (MAX_SERVOS-1)/2;
					n_servos_combo = MAX_SERVOS % 2 == 0 ? n_servos : n_servos+1;
				}
				
				int n_rows = (n_servos+n_servos_combo) % 2 == 0 ? 
						(n_servos+n_servos_combo)/2 : (n_servos+n_servos_combo+1)/2;
				
				servos.setLayout(new GridLayout(n_rows,2));
				for(int i=0;i<n_servos_combo;i++){
					ServoComboPanel pane = new ServoComboPanel("Servo "+i);
					servos.add(pane);
				}
				for(int i=n_servos_combo;i<n_servos_combo+n_servos;i++){
					ServoPanel pane = new ServoPanel("Servo "+i);
					servos.add(pane);
				}
				
				String title = settings.get(SETTING_NAME_SERVO).getStringValue();
				tabs.add(title, servos);
			}
    	}

    	////////////////////////// TTL
    	if(((BoolSetting) settings.get(SETTING_USE_TTL)).getValue()) {
			JPanel ttls = new JPanel();
			
			int n_ttls = ((IntSetting) settings.get(SETTING_NUM_TTL)).getValue();
			
			if(n_ttls > 0) {

				if(n_ttls > MAX_TTL) {
					n_ttls = MAX_TTL;
				}
				
				ttls.setLayout(new GridLayout(n_ttls,1));
				for(int i=0;i<n_ttls;i++){
					TTLPanel pane = new TTLPanel("TTL "+i);
					ttls.add(pane);
				}
				
				JPanel pane = new JPanel();
				pane.add(ttls);
				String title = settings.get(SETTING_NAME_TTL).getStringValue();
				tabs.add(title, pane);
			}
    	}

    	////////////////////////// PWM
    	if(((BoolSetting) settings.get(SETTING_USE_PWM)).getValue()) {
			JPanel pwms = new JPanel();
			
			int n_pwms = ((IntSetting) settings.get(SETTING_NUM_PWM)).getValue();
			
			if(n_pwms > 0) {
				if(n_pwms > MAX_PWM) {
					n_pwms = MAX_PWM;
				}

				int n_rows = n_pwms % 2 == 0 ? n_pwms/2 : (n_pwms+1)/2;
				
				pwms.setLayout(new GridLayout(n_rows,2));
				for(int i=0;i<n_pwms;i++){
					PWMPanel pane = new PWMPanel("PWM "+i);
					pwms.add(pane);
				}
				
				String title = settings.get(SETTING_NAME_PWM).getStringValue();
				tabs.add(title, pwms);
			}
    	}

    	////////////////////////// Analog input
    	if(((BoolSetting) settings.get(SETTING_USE_AI)).getValue()) {
			JPanel ais = new JPanel();
			
			int n_analogs = ((IntSetting) settings.get(SETTING_NUM_AI)).getValue();
			
			if(n_analogs > 0) {
				if(n_analogs > MAX_AI) {
					n_analogs = MAX_AI;
				}

				int n_rows = n_analogs % 2 == 0 ? n_analogs/2 : (n_analogs+1)/2;
				
				ais.setLayout(new GridLayout(n_rows,2));
				for(int i=0;i<n_analogs;i++){
					AnalogPanel pane = new AnalogPanel("Analog channel "+i);
					ais.add(pane);
				}
				
				String title = settings.get(SETTING_NAME_AI).getStringValue();
				tabs.add(title, ais);
			}
    	}

    	////////////////////////// Analog monitoring
    	if(((BoolSetting) settings.get(SETTING_USE_AI_MONITOR)).getValue()) {
			String title = settings.get(SETTING_NAME_MONITOR).getStringValue();
    		tabs.add(new GraphPanel(title));
    	}
    	
    	this.add(tabs);
		
        this.pack(); 
	}

	@Override
	protected String getPluginInfo() {
		return "MicroFPGA was developped by Joran Deschamps, EMBL (2020). It is intended as the control interface for a FPGA-based electronics"
				+ "for microscopy."
				+ " For more details, visit the github repository: https://github.com/jdeschamps/MicroFPGA. \n";
	}

}
