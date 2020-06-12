package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.MicroFPGAController;
import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class AnalogInput extends Signal{
	
	protected AnalogInput(int id, RegisterInterface regint) {
		super(id, Direction.INPUT, regint);
	}

	@Override
	public boolean isValueAllowed(int i) {
		return false;
	}

	@Override
	public int getBaseAddress() {
		return MicroFPGAController.ADDR_ANALOG_INPUT;
	}

}
