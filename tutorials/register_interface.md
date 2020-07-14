# Register interface

The communication interface is based on the [Mojo Lucid tutorial](https://alchitry.com/blogs/tutorials/register-interface) from Alchitry. Each [MicroFPGA signal](microfpga.md) has an address used for reading/writing its value:

|           Parameter           |         Address          |        Values        |
| :---------------------------: | :----------------------: | :------------------: |
|          Laser mode           |   laser number (0-10)    |         0-4          |
|        Laser duration         | 10 + laser number (0-10) |       0-65535        |
|        Laser sequence         | 20 + laser number (0-10) |       0-65535        |
|           TTL state           |  30 + TTL number (0-10)  |         0-1          |
|        Servo position         | 40 + servo number (0-10) |       0-65535        |
|              PWM              |  50 + PWM number (0-10)  |        0-255         |
| Analog signal <br>(READ only) | 60 + Analog number (0-8) | 0-65535 (READ only)  |
|    Version <br>(READ only)    |           100            |    2 (READ only)     |
|      ID<br />(READ only)      |           101            | 49 or 79 (READ only) |

The addresses are defined in the [top file of the Alchitry Labs project](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/source/au_top.luc). A read request is answered by 4 bytes representing the queried value. Note that unknown addresses will cause the FPGA to answer **11206655** (error code).

Examples of the register interface implementations can be found here:

- **C++**: in the [Micro-Manager device adapter](https://github.com/jdeschamps/MicroFPGA/blob/master/Device_Adapter/MicroFPGA.cpp), line 308, 325 and 338.
- **Python**: in the [MicroFPGA-Py library](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Py/microfpga/regint.py), line 41, 52 and 67.
- **Java**: in the [MicroFPGA-Java library](https://github.com/jdeschamps/MicroFPGA/blob/master/MicroFPGA-Java/src/main/java/de/embl/rieslab/microfpga/regint/RegisterInterface.java), line 66 and 96.



