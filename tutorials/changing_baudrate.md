# Changing the baudrate

### 

In au_top.luc (line 106)

      uart_rx rx (#BAUD(1000000), #CLK_FREQ(100000000)); // serial receiver
      uart_tx tx (#BAUD(1000000), #CLK_FREQ(100000000)); // serial transmitter