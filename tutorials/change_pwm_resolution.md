## Change PWM bit depth

The PWM level is currently encoded by a 8 bits value. To increase the number of steps between the minimum and maximum value, the encoding can be changed to 16 bits for instance.

1. In au_top.luc (line 115), change the bit depth to 16:

   ```verilog
   pwm pulsewm[NUM_PWM](#TOP(254),#DIV(9),#WIDTH(16));
   dff dutycycle[NUM_PWM][16];
   ```

2. In au_top.luc (line 166), change the bit depth to 16:

   ```verilog
   dutycycle.d[reg.regOut.address-ADDR_PWM] = reg.regOut.data[15:0];
   ```

Now, the code on the user side should be changed to accept higher values, for instance:

- [Micro-Manager](https://github.com/jdeschamps/MicroFPGA/blob/master/Device_Adapter/MicroFPGA.cpp): line 1077, replace 255 by 65535.
- [Java](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Java/src/main/java/de/embl/rieslab/microfpga/devices/PWM.java): line 8, idem.
- [Python](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Py/microfpga/signals.py): line 30, idem.

