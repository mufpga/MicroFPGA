# Electronics



- [Box](Box) is a 3D printed case used in the Ries lab to pack the Au FPGA and the various electronic boards.
- [The low-pass and divider board](LowPass_divider) is a bi-directional voltage divider between 3.3 V and 5 V with an optional low-pass filter in one direction. This board is used to convert 3.3 V PWM from the Au/Cu FPGA to a 3.3,  4 or 5 V analog signal. Additionally, it can be used to simply voltage divide a signal from 5 V to 3.3 V.
- [The analog divider board]() is a board used to scale down 3.3 and 5 V analog signals to 1 V, while preventing voltages higher than 1.6 V to reach the Au FPGA.



Each electronic board contains a PDF with the board plan and the circuits, as well as the Gerber files and the bill of materials necessary to reproduce the board.

The electronic boards were designed, produced and tested by Christian Kieser (EMBL, Electronic Workshop).

