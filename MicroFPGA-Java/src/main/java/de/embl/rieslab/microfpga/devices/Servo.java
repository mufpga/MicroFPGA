package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.MicroFPGAController;
import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class Servo extends Signal{

	public static final int MAX = 65535;
	public static final int MIN = 0;
	
	protected Servo(int id, RegisterInterface regint) {
		super(id, Direction.OUTPUT, regint);
	}

	@Override
	public boolean isValueAllowed(int i) {
		return (i>= MIN && i<=MAX);
	}

	@Override
	public int getBaseAddress() {
		return MicroFPGAController.ADDR_SERVO;
	}

}
