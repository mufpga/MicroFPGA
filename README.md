
# MicroFPGA configuration

MicroFPGA is an FPGA-based platform for the electronic control of microscopes. It aims at using affordable FPGA to generate or read signals from a variety of devices, including cameras, lasers, servomotors, filter-wheels, etc. It can be controlled via [Micro-Manager](https://micro-manager.org/), or its [Java](https://github.com/mufpga/MicroFPGA-java), [Python](https://github.com/mufpga/MicroFPGA-py) and [LabView](https://github.com/mufpga/MicroFPGA-labview) communication libraries, and comes with optional complementary [electronics](https://github.com/mufpga/MicroFPGA-electronics).

## Content

This repository contains the FPGA configuration for the [Au](https://www.sparkfun.com/products/16527), [Au+](https://www.sparkfun.com/products/17514) and [Cu](https://www.sparkfun.com/products/16526) FPGAs ([Alchitry](https://alchitry.com/)). The source code can be compiled and uploaded to the FPGA using [AlchitryLabs](https://alchitry.com/alchitry-labs). Alternatively, you can download the [latest released](https://github.com/mufpga/MicroFPGA/releases) and upload it directly to the FPGA using AlchitryLoader (available when AlchitryLabs is installed). All steps are described in the [documentation](https://mufpga.github.io/2_installing_microfpga.html).

#### Check out the project homepage for the documentation, resources and tutorials: [mufpga.github.io](https://mufpga.github.io).

MicroFPGA was written by Joran Deschamps, EMBL (2020). It was tested with AlchitryLabs 1.2.7.
