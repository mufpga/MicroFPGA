# Understanding analog pin mapping

### ADC

page 38: https://www.xilinx.com/support/documentation/user_guides/ug480_7Series_XADC.pdf

page 1, 2 and 3: https://cdn.shopify.com/s/files/1/2702/8766/files/alchitry_au_sch.pdf?1474573937723901827

xadc.xdc

| **Channel** | **address <br />(hex)** | **address** | MicroFPGA channel | Pin (physical) | Pin (Br)    |
| ----------- | :---------------------: | :---------: | :---------------: | -------------- | :---------- |
| **Vaux 15** |           1fh           |     31      |     channel 7     | E2/D1          | BankB 3/2   |
| **Vaux 14** |           1eh           |     30      |     channel 6     | B2/A2          | BankB 6/5   |
| **Vaux 13** |           1dh           |     29      |     channel 5     | C7/C6          | BankB 27/28 |
| **Vaux 12** |           1ch           |     28      |     channel 4     | B6/B5          | BankB 21/20 |
| Vaux 11     |           1bh           |      -      |         -         | -              | -           |
| Vaux 10     |           1ah           |      -      |         -         | -              | -           |
| Vaux 9      |           19h           |      -      |         -         | -              | -           |
| Vaux 8      |           18h           |      -      |         -         | -              | -           |
| **Vaux 7**  |           17h           |     23      |     channel 3     | C1/B1          | BankB 48/49 |
| **Vaux 6**  |           16h           |     22      |     channel 2     | C3/C2          | BankB 45/46 |
| **Vaux 5**  |           15h           |     21      |     channel 1     | A5/A4          | BankB 18/17 |
| **Vaux 4**  |           14h           |     20      |     channel 0     | B7/A7          | BankB 24/23 |
| Vaux 3      |           13h           |      -      |         -         | -              | -           |
| Vaux 2      |           12h           |      -      |         -         | -              | -           |
| Vaux 1      |           11h           |      -      |         -         | -              | -           |
| Vaux 0      |           10h           |      -      |         -         | -              | -           |
| VP/VN       |           03h           |      3      |         -         | H8/J7          | BankD 31/30 |

