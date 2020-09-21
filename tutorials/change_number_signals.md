# Changing the number of signals

The number of devices available for each signal is defined in the [top file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/source/au_top.luc) of the Alchitry Labs project. Note that the number of analog signals cannot be increased (technically only to 9 if incorporating the dedicated VP/VN signal).

1. First, change the constants at the beginning of the [top file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/source/au_top.luc) (line 62):

   ```verilog
   // number of signals
   const NUM_LASERS = 8;
   const NUM_TTL = 6; // we added one TTL signal here
   const NUM_PWM = 5;
   const NUM_SERVOS = 7;
   const NUM_ANALOG = 8;
   ```

2. Then, add the corresponding signals to the definition of the top file module (lines 8 to 57). Here is an example of the new TTL signal:

   ```verilog
   output ttl3,
   output ttl4,
   output ttl5, // new ttl signal
   output servo0,
   output servo1,
   ```

   Ignore the error for now.

3. Finally, the new signal should be linked with a module output. In our example (line 226):

   ```verilog
   ttl3 = ttl.q[3];
   ttl4 = ttl.q[4];
   ttl5 = ttl.q[5] // new ttl signal receives the output of the ttl module
   ```

4. Finally, the new signal of our example (ttl5) needs to be added to the [user constraint file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/constraint/user.acf):

   ```verilog
   pin ttl3 A8;
   pin ttl4 A5;
   pin ttl5 C40; // new ttl
   ```

   Here, we chose a free 3.3 V pin (see [pins mapping](pins_br.md)).

5. Then compile and update the firmware (see [Installing and building MicroFPGA](installing_microfpga.md) for a walkthrough). 

Note that if you are using [Micro-Manager](using_micro-manager.md), the [Java](using_java.md) or the [Python](using_python.md) libraries, you will have to modify the number of signals in the user code as well:

- [Micro-Manager device adapter](https://github.com/jdeschamps/MicroFPGA/blob/master/Device_Adapter/MicroFPGA.cpp)
- [Python library](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Py/microfpga/signals.py)
- [Java library](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Java/src/main/java/de/embl/rieslab/microfpga/MicroFPGAController.java)

