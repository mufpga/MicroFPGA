<a href="https://mufpga.github.io/"><img src="https://raw.githubusercontent.com/mufpga/mufpga.github.io/main/img/logo_title.png" alt="Overview"/>

</a>

![version](https://img.shields.io/badge/version-3.1.0-blue)[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)



# Overview

MicroFPGA is an FPGA-based platform for the electronic control of microscopes. It aims at using affordable FPGA to generate or read signals from a variety of devices, including cameras, lasers, servomotors, filter-wheels, etc. It can be controlled via [Micro-Manager](https://micro-manager.org/MicroFPGA), or its [Java](https://github.com/mufpga/MicroFPGA-java), [Python](https://github.com/mufpga/MicroFPGA-py) and [LabView](https://github.com/mufpga/MicroFPGA-labview) communication libraries, and comes with optional complementary [electronics](https://github.com/mufpga/MicroFPGA-electronics).

Documentation and tutorials are available on [https://mufpga.github.io/](https://mufpga.github.io/).



<img src="https://raw.githubusercontent.com/mufpga/mufpga.github.io/main/img/figs/G_overview.png" alt="Overview"/>

## Content

This repository contains the FPGA configuration for the [Au](https://www.sparkfun.com/products/16527), [Au+](https://www.sparkfun.com/products/17514) and [Cu](https://www.sparkfun.com/products/16526) FPGAs (Alchitry). In order to use MicroFPGA the configuration must be uploaded to the FPGA using [AlchitryLabs](https://alchitry.com/alchitry-labs) or AlchitryLoader.

- Pre-compiled files can be downloaded [here](https://github.com/mufpga/MicroFPGA/releases).
- Instructions on how to build and upload the source code can be found on the [project's website](https://mufpga.github.io/2_installing_microfpga.html).


## Cite us
Joran Deschamps, Christian Kieser, Philipp Hoess, Takahiro Deguchi, Jonas Ries, "MicroFPGA: An affordable FPGA platform for microscope control",
HardwareX 2023 (13): e00407, doi:[10.1016/j.ohx.2023.e00407](https://doi.org/10.1016/j.ohx.2023.e00407).


MicroFPGA was written by Joran Deschamps, EMBL (2020).
