package de.embl.rieslab.microfpga.regint;

import jssc.SerialPortList;

public class ListPorts {

	public static String[] listPorts() {
        return SerialPortList.getPortNames();
	}
	
	public static void printPorts() {
		System.out.println("--- COMports list ---");
		for(String s: SerialPortList.getPortNames())
			System.out.println(s);
	}
}
