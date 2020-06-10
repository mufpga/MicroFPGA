package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class PWM extends Signal{

	public static final int ADDR_PWM = 50;
	
	public static final int MAX = 255;
	public static final int MIN = 0;

	protected PWM(int id, RegisterInterface regint) {
		super(id, Direction.OUTPUT, regint);
	}

	@Override
	public boolean isValueAllowed(int i) {
		return (i>= MIN && i<=MAX);
	}

	@Override
	public int getBaseAddress() {
		return ADDR_PWM;
	}
}
