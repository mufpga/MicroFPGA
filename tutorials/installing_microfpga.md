## Installing and building MicroFPGA



1. Download and install [Git](https://git-scm.com/downloads).

2. Clone this repository in the folder of your choice:

   ```bash
   $ cd /path/to/folder/
   $ git clone https://github.com/jdeschamps/MicroFPGA.git
   ```

3. Download and install [Alchitry Labs](https://alchitry.com/pages/alchitry-labs). Follow the indications on the page, in particular:

   1. Click on the link to download and install Alchitry Labs.
   2. Download [Zadig](https://zadig.akeo.ie/).
   3. Follow the instructions ("Installing WinUSB" section) to configure the FPGA interface 0 with the WinUSB driver using Zadig.
   4. Install Vivado or IceCube2 depending on your FPGA (Au or Cu), the links are at the [bottom of the page]((https://alchitry.com/pages/alchitry-labs)). You will need to register an account with Xilinx to install Vivado.

4. Start Alchitry Labs and in the "Settings" menu, set the location of the compiling tools (Vivado or IceCube2).

5. In "Project", open the MicroFPGA project. It is located in the folder where it was cloned in 1.

6. Click on the hammer icon to build the project. It can take few minutes.

7. Once the project has finished, upload it to your board with the plain arrow (Flash).

8. Test the firmware by using the "Tools/Register Interface". Note that the interface 1 of the board needs to have been configured in Zadig with libUSB to work. Type "101" as an address and click on "Read". The value should change to 49 (Cu) or 79 (Au).



Note that in order to use MicroFPGA with Micro-Manager and the Java/Python libraries from this repository, you will need to [change the board drivers](serial_communication_win.md).

