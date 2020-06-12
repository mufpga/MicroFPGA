### This repository is work in progress. 

Soon:

- Fully tested release with compiled device adapter for MM 1.4 and 2-gamma
- Python / Java / LabView code
- Documentation


# MicroFPGA

MicroFPGA is intended as an electronic platform controlling a custom microscope. It is based on hobby [FPGA](https://en.wikipedia.org/wiki/Field-programmable_gate_array "Wikipedia")s (Field-Programmable Gate Array) from [Alchitry](https://alchitry.com/collections/all). It is compatible with [Micro-manager](https://micro-manager.org/ "Micro-manager website")  thanks to a custom device adapter. 

# Why using a FPGA?

As opposed to most microcontrollers (such as the Arduino), FPGAs can carry on multiple tasks in parallel due to their architecture. While the Arduino is a great solution for simple tasks, it is rapidly overwhelmed when required to process signals rapidly. A good example for such a task - and the original motivation for MicroFPGA - is to pulse in the microsecond range several lasers in a synchronous way. This can be achieved easily for one laser by an Arduino DUE, but becomes impossible with several lasers or requires multiple Arduino boards and synchronization signals. A FPGA can achive this task for many lasers without effort. Finally, Alchitry FPGAs are only slightly more expensive than Arduinos, making it an excellent choice for affordable integrated electronics in experimental set-ups. 

# What can MicroFPGA do?

By design, MicroFPGA  offers out of the box several control signals useful in microscopy:

|          Signal          |                           Details                            |              Use examples               |
| :----------------------: | :----------------------------------------------------------: | :-------------------------------------: |
| Laser triggering (Au/Cu) | Using a camera read-out signal, multiple lasers can be triggered by a TTL (On/Off, pulsing with us resolution, follow the camera) |            Laser triggering             |
|       TTL (Au/Cu)        |                        On/Off signal                         |              Flip-mirrors               |
|      Servos (Au/Cu)      |                   1 ms - 2 ms servo signal                   |     Filter-wheel, moveable elements     |
|       PWM (Au/Cu)        |                            0-100%                            | With a low-pass circuit: AOM %, laser % |
|   Analog read-out (Au)   |                  8 analog read-out channels                  |            Sensor read-outs             |

# Micro-manager

[Micro-manager](https://micro-manager.org/ "Micro-manager website") is an open-source microscope control software, with a large set of compatible commercial devices. The communication with each device is done through a so-called device adapter. MicroFPGA device adapter offers the possibility to load the desired number of each signal controller (LaserTrigger, TTL, Servo, PWM, AnalogReadOut) in the software. 

The device adapter needs to be compiled for a specific version of Micro-manager.

:warning: Make sure to input the correct voltage ranges to the FPGAs (e.g.: max 3.3 V for the camera signal and 1.8 V for the analog pins). :warning:

MicroFPGA was written by Joran Deschamps, EMBL (2020), and was developped on [Alchitry Labs](https://alchitry.com/pages/alchitry-labs).
