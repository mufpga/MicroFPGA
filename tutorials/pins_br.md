# Default layout Au+Br

The following table represent the output pins of the Au FPGA used with the [Br breaking board](https://alchitry.com/collections/all/products/alchitry-br). You can find the reference sheet of the Br board [here](https://cdn.alchitry.com/docs/Br%20Element%20Reference.pdf).

The pin numbers are indicated in between parentheses:

| Bank A |             |              |              |        |        |        |        |               |
| :----: | :---------: | :----------: | :----------: | :----: | :----: | :----: | :----: | :-----------: |
|   R    | **S3** (48) | **S4** (45)  | **P0** (42)  | - (39) | - (36) | - (33) | - (30) | **TTL0** (27) |
|  GND   | **S2** (49) | **S5** (46)  | **P1** (43)  | - (40) | - (37) | - (34) | - (31) | **TTL1** (28) |
|   +V   | **S1** (3)  |  **S6** (6)  | **TTL2** (9) | - (12) | - (15) | - (18) | - (21) |    - (24)     |
|  GND   | **S0** (2)  | **TTL4** (5) | **TTL3** (8) | - (11) | - (14) | - (17) | - (20) |    - (23)     |

| Bank B |               |               |        |        |        |               |               |               |
| :----: | :-----------: | :-----------: | :----: | :----: | :----: | :-----------: | :-----------: | :-----------: |
|   +V   | **AI3P** (48) | **AI2P** (45) | - (42) | - (39) | - (36) |    - (33)     |    - (30)     | **AI5P** (27) |
|  GND   | **AI3N** (49) | **AI2N** (46) | - (43) | - (40) | - (37) |    - (34)     |    - (31)     | **AI5N** (28) |
|   +V   | **AI7P** (3)  | **AI6P** (6)  | - (9)  | - (12) | - (15) | **AI1P** (18) | **AI4P** (21) | **AI0P** (24) |
|  GND   | **AI7N** (2)  | **AI6N** (5)  | - (8)  | - (11) | - (14) | **AI1N** (17) | **AI4N** (20) | **AI0N** (23) |

| Bank C |             |             |             |        |        |        |        |        |
| :----: | :---------: | :---------: | :---------: | :----: | :----: | :----: | :----: | :----: |
|  GND   | **L3** (49) | **L4** (46) | **P2** (43) | + (40) | + (37) | + (34) | + (31) | + (28) |
|   +V   | **L2** (48) | **L5** (45) | **P3** (42) | + (39) | + (36) | + (33) | + (30) | + (27) |
|  GND   | **L1** (2)  | **L6** (5)  | **P4** (8)  | + (11) | + (14) | + (17) | + (20) | + (23) |
|  Raw   | **L0** (3)  | **L7** (6)  | **Cam** (9) | + (12) | + (15) | + (18) | + (21) | + (24) |

| Bank D |      |      |        |        |      |      |         |      |
| :----: | :--: | :--: | :----: | :----: | :--: | :--: | :-----: | :--: |
|  GND   |  x   |  x   | + (43) |   x    |  x   |  x   | VP (31) |  x   |
|   +V   |  x   |  x   | + (42) |   x    |  x   |  x   | VN (30) |  x   |
|  GND   |  x   |  x   | + (8)  | + (11) |  x   |  x   |    x    |  x   |
|   +V   |  x   |  x   | + (9)  | + (12) |  x   |  x   |    x    |  x   |

**S**: servomotor outputs

**TTL**: ttl outputs

**AIXP**: positive pole of the analog input X

**AIXN**: negative pole of the analog input X

**P**: PWM outputs

**L**: laser trigger outputs

**Cam**: camera trigger input

**GND**: ground

**R**: raw +5 V 

**+V**: 3.3 V

**-**: vacant 1.8 V I/O pins 

**+**: vacant 3.3 V I/O pins

**x**: unavailable or unused pins

Note that because the analog input pins on the Au run at 1.8 V (I/O standard LVCMOS18), all pins connected to bank 35 (see [Au design](https://alchitry.com/products/alchitry-au-fpga-development-board)) have to be configured with the same I/O standard. They are indicated here with "-" and are therefore will not work with hardware expecting a 3.3 V or 5 V digital logic.

Pins are defined in the [user constraint file](https://github.com/jdeschamps/MicroFPGA/blob/master/Au_firmware/constraint/user.acf).
