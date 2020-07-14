# Changing the number of signals

The number of devices available for each signal is defined in the [top file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/source/au_top.luc) of the Alchitry Labs project. Note that the number of analog signals cannot be increased (technically only to 9 if incorporating the dedicated VP/VN signal).

1. First, change the constants at the beginning of the [top file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/source/au_top.luc) (line 81):

   ```verilog
   // number of signals
   const NUM_LASERS = 10;
   const NUM_TTL = 11; // we added one TTL signal here
   const NUM_PWM = 10;
   const NUM_SERVOS = 10;
   const NUM_ANALOG = 8;
   ```

2. Then, add the corresponding signals to the definition of the top file module (lines 9 to 72). Here is an example of the new TTL signal:

   ```verilog
   output ttl8,
   output ttl9,
   output ttl10, // new ttl signal
   output servo0,
   output servo1,
   ```

   Ignore the error for now.

3. Finally, the new signal should be linked with a module output. In our example (line 247):

   ```verilog
   ttl8 = ttl.q[8];
   ttl9 = ttl.q[9];
   ttl10 = ttl.q[10] // new ttl signal receives the output of the ttl module
   ```

   Here are examples of the lines one needs to add for all other signals:

   ```verilog
   laser10 = l.lasersignal[10]; // new laser
   servo10 = servo_sig.signal_out[10]; // new servo
   pwm10 = pulsewm.pulse[10]; // new PWM
   ```

4. Finally, the new signal of our example (ttl10) needs to be added to the [user constraint file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/constraint/user.acf):

   ```verilog
   pin ttl9 C15;
   pin ttl10 A9; // new ttl
   ```

   Here, we chose a free 3.3 V pin (see [pins mapping](pins_br.md)).

5. Then compile and update the firmware.



Note that if you are using [Micro-Manager](using_micro-manager.md), the [Java](using_java.md) or the [Python](using_python.md) libraries, you will have to modify the number of signals in the user code as well:

- [Micro-Manager device adapter](https://github.com/jdeschamps/MicroFPGA/blob/master/Device_Adapter/MicroFPGA.cpp)
- [Python library](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Py/microfpga/signals.py)
- [Java library](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Java/src/main/java/de/embl/rieslab/microfpga/MicroFPGAController.java)

