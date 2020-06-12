## Changing pins

To change the pin associated with a specific signal, modify the configuration file **user.acf** in the source folder of Au/Cu firmware folder.

For instance, in the case of the Au board, pin A27 is associated with ttl0:

```c
pin ttl0 A27;
```

A look at the [pins distribution](pins_br.md) on the Br board tells us that the pin D43 is free, and we can therefore map it to ttl0:

```c
pin ttl0 D43;
```

You can alternatively swap it with a pin you may not be using (example: PWM9).

Once you modified the configuration file, you need to [build and update the FPGA firmware](build_and_update.md).