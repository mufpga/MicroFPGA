package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.MicroFPGAController;
import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class TTL extends Signal {
	
	public final static int ON = 1;
	public final static int OFF = 0; 
	
	protected TTL(int id, RegisterInterface regint) {
		super(id, Direction.OUTPUT, regint);
	}

	@Override
	public boolean isValueAllowed(int i) {
		return (i == 0 || i == 1);
	}

	@Override
	public int getBaseAddress() {
		return MicroFPGAController.ADDR_TTL;
	}

}
