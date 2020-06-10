package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class AnalogInput extends Signal{

	private  static final int ADDR_ANALOG_INPUT = 60;
	
	protected AnalogInput(int id, RegisterInterface regint) {
		super(id, Direction.INPUT, regint);
	}

	@Override
	public boolean isValueAllowed(int i) {
		return false;
	}

	@Override
	public int getBaseAddress() {
		return ADDR_ANALOG_INPUT;
	}

}
