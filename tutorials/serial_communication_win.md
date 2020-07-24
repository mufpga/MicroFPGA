## Enable serial port communication (Windows)

In order to update the firmware with Alchitry Labs, the FPGA USB interface 0 must be configured with the WinUSB driver (see [installing MicroFPGA](installing_microfpga.md)).

The [Micro-Manager device adapter](https://github.com/jdeschamps/MicroFPGA/tree/master/Device_Adapter), as well as the [Java](https://github.com/jdeschamps/MicroFPGA/tree/master/MicroFPGA-Java) and [Python](https://github.com/jdeschamps/MicroFPGA/tree/master/MicroFPGA-Py) libraries,  are meant to use serial communication with the FPGA. Therefore, the USB interface 1 must show up as a COM port (Windows):

1. Download the legacy drivers (see "Legacy Drivers (COM port)" in the [Alchitry Labs installation page](https://alchitry.com/pages/alchitry-labs)). These are FTDI drivers v2.12.28.
2. In the Windows device manager, under Universal Serial Bus devices, locate the "Alchitry FPGA (Interface 1)".
3. Right-click on it, then select "Properties". In the window that appears, select "Driver", then "Update Driver".
4. Browse your computer for driver software, and choose to pick from a list of device drivers. Uncheck "Show compatible hardware".
5. Select the "FTDI" manufacturer and then the "FT4222" driver with the correct version (v2.12.28).
6. Install the driver.
7. The FPGA interface 0 should still appear as a Universal Serial Bus device, while the interface 1 should now appear as a COM port (example: COMport21).

Once serial port communication is enabled, the FPGA can be [loaded in Micro-Manager](using_micro-manager.md), [in the Java](using_java.md) or [in the Python](using_python.md) libraries. Note that the baud rate is 1000000.

