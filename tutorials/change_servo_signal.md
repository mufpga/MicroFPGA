## Change servo signal

### Change signal

Most servomotors obey to the standard PWM signal of period 20 ms and pulse length between 1 and 2 ms. Should you need a different signal, it can be modified in the servo_standard.luc file (line 14):

```verilog
const MIN_T = 1; // min pulse (ms)  
const MAX_T = 2; // max pulse (ms)  
const PERIOD = 20; // period (ms)  
```

Note that this will affect the signal for all servos. In order to have servos with the standard and with the modified parameters, you will need to duplicate the servo_standard.luc file in the MicroAu or MicroCu project (e.g. servo_standard_2.luc), create instance of the duplicated module in au_top.luc or cu_top.luc and allocate the inputs and outputs. Example for two servos with the modified parameters:

au_top.luc (line 109):

```verilog
// declaration
servo_standard servo_controller[NUM_SERVOS-2];
servo_standard_2 servo_controller_2[2]; // servo signals with new parameters
```

au_top.luc (line 229):

```verilog
// input/output assignments
servo_controller.position = position.q[NUM_SERVOS-1:2];
servo_controller_2.position = position.q[1:0];
servo_sig.update = servo_sig_update.q;
servo_sig.signal_in[NUM_SERVOS-1:2] = servo_controller.servo;
servo_sig.signal_in[1:0] = servo_controller_2.servo;
```

### Change switch-off delay

In servo_stop.luc (line 16):

```verilog
dff count_sig[30]; // count until ~ 10 sec @ 100MHz
```

Since the clock frequency is 100 MHz, then the switch-off delay of the servos is:

<img src="https://render.githubusercontent.com/render/math?math=(2^{30}-1)*10^{-8} \approx 10.7 s">

You can change the bit depth of *count_sig* to adjust the switch-off delay, for instance:

|      line 16       | delay (s) |
| :----------------: | :-------: |
| dff count_sig[28]; |   2.68    |
| dff count_sig[29]; |   5.37    |
| dff count_sig[31]; |   21.47   |
| dff count_sig[32]; |   42.99   |

