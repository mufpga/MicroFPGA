package de.embl.rieslab.microfpga.regint;

import java.util.Arrays;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;

/*
 * This class was written by EmbeddedMicro (now Alchitry):
 * https://alchitry.com/blogs/tutorials/register-interface
 */
public class RegisterInterface {
	
	SerialPort serialPort_;
	
	public boolean connect(String port) {
		if (port == null)
			return false;

		if (port.equals(""))
			return false;
		
		if (!Arrays.asList(SerialPortList.getPortNames()).contains(port))
			return false;

		serialPort_ = new SerialPort(port);
		try {
			serialPort_.openPort();
		} catch (SerialPortException e) {
			return false;
		}
		try {
			serialPort_.setParams(1000000, 8, 1, 0);
		} catch (SerialPortException e) {
			try {
				serialPort_.closePort();
			} catch (SerialPortException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	public boolean disconnect() throws SerialPortException {
		return serialPort_.closePort();
	}


	public boolean write(int address, int data) throws SerialPortException {
		byte[] buff = new byte[9];
		buff[0] = (byte) (1 << 7);
		buff[1] = (byte) (address & 0xff);
		buff[2] = (byte) ((address >> 8) & 0xff);
		buff[3] = (byte) ((address >> 16) & 0xff);
		buff[4] = (byte) ((address >> 24) & 0xff);
		buff[5] = (byte) (data & 0xff);
		buff[6] = (byte) ((data >> 8) & 0xff);
		buff[7] = (byte) ((data >> 16) & 0xff);
		buff[8] = (byte) ((data >> 24) & 0xff);
		return serialPort_.writeBytes(buff);
	}

	public boolean write(int address, boolean increment, int[] data) throws SerialPortException {
		for (int i = 0; i < data.length; i += 64) {
			int length = Math.min(data.length - i, 64);
			if (!write64(address, increment, data, i, length))
				return false;
			if (increment)
				address += length;
		}
		return true;
	}

	private boolean write64(int address, boolean increment, int[] data, int start, int length) throws SerialPortException {
		byte[] buff = new byte[5 + length * 4];
		buff[0] = (byte) ((1 << 7) | (length - 1));
		if (increment)
			buff[0] |= (1 << 6);
		buff[1] = (byte) (address & 0xff);
		buff[2] = (byte) ((address >> 8) & 0xff);
		buff[3] = (byte) ((address >> 16) & 0xff);
		buff[4] = (byte) ((address >> 24) & 0xff);
		for (int i = 0; i < length; i++) {
			buff[i * 4 + 5] = (byte) (data[i + start] & 0xff);
			buff[i * 4 + 6] = (byte) ((data[i + start] >> 8) & 0xff);
			buff[i * 4 + 7] = (byte) ((data[i + start] >> 16) & 0xff);
			buff[i * 4 + 8] = (byte) ((data[i + start] >> 24) & 0xff);
		}

		return serialPort_.writeBytes(buff);
	}

	public int read(int address) throws SerialPortException, SerialPortTimeoutException {
		byte[] buff = new byte[5];
		buff[0] = (byte) (0 << 7);
		buff[1] = (byte) (address & 0xff);
		buff[2] = (byte) ((address >> 8) & 0xff);
		buff[3] = (byte) ((address >> 16) & 0xff);
		buff[4] = (byte) ((address >> 24) & 0xff);
		if (!serialPort_.writeBytes(buff))
			throw new SerialPortException(serialPort_.getPortName(), "readReg", "Failed to write address");
		buff = serialPort_.readBytes(4, 1000);
		return (buff[0] & 0xff) | (buff[1] & 0xff) << 8 | (buff[2] & 0xff) << 16 | (buff[3] & 0xff) << 24;
	}

	public void read(int address, boolean increment, int[] data) throws SerialPortException, SerialPortTimeoutException {
		for (int i = 0; i < data.length; i += 64) {
			int length = Math.min(data.length - i, 64);
			read64(address, increment, data, i, length);
			if (increment)
				address += length;
		}
	}

	private void read64(int address, boolean increment, int[] data, int start, int length) throws SerialPortException, SerialPortTimeoutException {
		byte[] buff = new byte[5];
		buff[0] = (byte) ((0 << 7) | (length - 1));
		if (increment)
			buff[0] |= (1 << 6);
		buff[1] = (byte) (address & 0xff);
		buff[2] = (byte) ((address >> 8) & 0xff);
		buff[3] = (byte) ((address >> 16) & 0xff);
		buff[4] = (byte) ((address >> 24) & 0xff);

		if (!serialPort_.writeBytes(buff))
			throw new SerialPortException(serialPort_.getPortName(), "readReg", "Failed to write address");

		buff = serialPort_.readBytes(length * 4, 3000);

		for (int i = 0; i < buff.length; i += 4) {
			data[i / 4 + start] = (buff[i] & 0xff) | (buff[i + 1] & 0xff) << 8 | (buff[i + 2] & 0xff) << 16 | (buff[i + 3] & 0xff) << 24;
		}
	}
}
