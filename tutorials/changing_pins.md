# Changing pins

### Lasers, TTLs, Servos and PWMs

To change the pin associated with a specific signal, modify the configuration file **user.acf** in the source folder of Au/Cu firmware folder. Note that the pins of the analog signals are defined differently.

For instance, in the case of the Au board, pin A27 is associated with TTL0:

```c
pin ttl0 A27;
```

A look at the [pins distribution](pins_br.md) on the Br board tells us that the pin D43 is free, and we can therefore map it to TTL0:

```c
pin ttl0 D43;
```

You can alternatively swap it with a pin you may not be using (example: PWM9).

Once you modified the configuration file, you need to [build and update the FPGA firmware](installing_microfpga.md) (instruction 6 and 7).

### Analog inputs

Analog input pins are fixed. On the other hand, their MicroFPGA labels can be swapped. They are a consequence of the addresses order in the [analog module](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/source/analog.luc) (line 39):

```verilog
// pins (Br): B3/B2, B6/B5, B27/B28, B21/B20, B48/B49, B45/B46, B18/B17, B24/B23
const ADDR = {31,30,29,28,23,22,21,20};
```

There, the channel 0 has the address 20, which means that it is on pin B24/23. If you want the channel 0 in place of the channel 5, then swap the addresses 20 and 29:

```verilog
// pins (Br): B3/B2, B6/B5, B27/B28, B21/B20, B48/B49, B45/B46, B18/B17, B24/B23
const ADDR = {31,30,20,28,23,22,21,29};
```

Finally, compile and update the firmware on the FPGA (see [Installing and building MicroFPGA](installing_microfpga.md) for a walkthrough).

For more details on the analog channels addresses, consult the [Analog input mapping](ai_mapping.md) note.