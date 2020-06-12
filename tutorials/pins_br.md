## Default layout Au+Br

The following table represent the output pins of the Au FPGA used with the [Br breaking board](https://alchitry.com/collections/all/products/alchitry-br). You can find the reference sheet of the Br board on Alchitry website.

S: servomotor outputs
TTL: ttl outputs
AIXP: positive pole of the analog input X
AIXN: negative pole of the analog input X
P: pwm outputs
L: laser trigger outputs
Cam: camera trigger input
GND: ground
R: raw +5 V 
+V: 3.3 V
-: vacant 1.8 V I/O pins 
x: vacant 3.3 V I/O pins

The pin numbers are indicated in between parentheses:

| Bank A |         |         |         |        |        |        |        |           |
| :----: | :-----: | :-----: | :-----: | :----: | :----: | :----: | :----: | :-------: |
|   R    | S3 (48) | S4 (45) | S8 (42) | - (39) | - (36) | - (33) | - (30) | TTL0 (27) |
|  GND   | S2 (49) | S5 (46) | S9 (43) | - (40) | - (37) | - (34) | - (31) | TTL1 (28) |
|   +V   | S1 (3)  | S6 (6)  |  x (9)  | - (12) | - (15) | - (18) | - (21) |  - (24)   |
|  GND   | S0 (2)  | S7 (5)  |  x (8)  | - (11) | - (14) | - (17) | - (20) |  - (23)   |

| Bank B |           |           |        |        |        |           |           |           |
| :----: | :-------: | :-------: | :----: | :----: | :----: | :-------: | :-------: | :-------: |
|   +V   | AI6P (48) | AI4P (45) | - (42) | - (39) | - (36) |  - (33)   |  - (30)   | AI3P (27) |
|  GND   | AI6N (49) | AI4N (46) | - (43) | - (40) | - (37) |  - (34)   |  - (31)   | AI3N (28) |
|   +V   | AI7P (3)  | AI5P (6)  | - (9)  | - (12) | - (15) | AI2P (18) | AI1P (21) | AI0P (24) |
|  GND   | AI7N (2)  | AI5N (5)  | - (8)  | - (11) | - (14) | AI2N (17) | AI1N (20) | AI0N (23) |

| Bank C |         |         |         |           |           |         |         |         |
| :----: | :-----: | :-----: | :-----: | :-------: | :-------: | :-----: | :-----: | :-----: |
|  GND   | L3 (49) | L4 (46) | L8 (43) | TTL2 (40) | TTL6 (37) | P0 (34) | P4 (31) | P8 (28) |
|   +V   | L2 (48) | L5 (45) | L9 (42) | TTL3 (39) | TTL7 (36) | P1 (33) | P5 (30) | P9 (27) |
|  GND   | L1 (2)  | L6 (5)  |  x (8)  | TTL4 (11) | TTL8 (14) | P2 (17) | P6 (20) | x (23)  |
|  Raw   | L0 (3)  | L7 (6)  | Cam (9) | TTL5 (12) | TTL9 (15) | P3 (18) | P7 (21) | x (24)  |

| Bank D |      |      |      |      |      |      |         |      |
| :----: | :--: | :--: | :--: | :--: | :--: | :--: | :-----: | :--: |
|  GND   |  x   |  x   | (43) |  x   |  x   |  x   | VP (31) |  x   |
|   +V   |  x   |  x   | (42) |  x   |  x   |  x   | VN (30) |  x   |
|  GND   |  x   |  x   | (8)  | (11) |  x   |  x   |    x    |  x   |
|   +V   |  x   |  x   | (9)  | (12) |  x   |  x   |    x    |  x   |

Note that because the analog input pins run at 1.8 V (I/O standard LVCMOS18), all pins connected to bank 35 (see [Au design](https://alchitry.com/products/alchitry-au-fpga-development-board)) have to be configured with the same I/O standard. They are indicated here with "-" and are therefore not available to interact with hardware expecting a 3.3 V or 5 V digital logic. If you are not using the analog inputs, they could in theory be removed from the firmware and other signals attributed to the pins with 3.3 V standard.

