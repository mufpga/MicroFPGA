// (c) Copyright 1995-2020 Xilinx, Inc. All rights reserved.
// 
// This file contains confidential and proprietary information
// of Xilinx, Inc. and is protected under U.S. and
// international copyright and other intellectual property
// laws.
// 
// DISCLAIMER
// This disclaimer is not a license and does not grant any
// rights to the materials distributed herewith. Except as
// otherwise provided in a valid license issued to you by
// Xilinx, and to the maximum extent permitted by applicable
// law: (1) THESE MATERIALS ARE MADE AVAILABLE "AS IS" AND
// WITH ALL FAULTS, AND XILINX HEREBY DISCLAIMS ALL WARRANTIES
// AND CONDITIONS, EXPRESS, IMPLIED, OR STATUTORY, INCLUDING
// BUT NOT LIMITED TO WARRANTIES OF MERCHANTABILITY, NON-
// INFRINGEMENT, OR FITNESS FOR ANY PARTICULAR PURPOSE; and
// (2) Xilinx shall not be liable (whether in contract or tort,
// including negligence, or under any other theory of
// liability) for any loss or damage of any kind or nature
// related to, arising under or in connection with these
// materials, including for any direct, or any indirect,
// special, incidental, or consequential loss or damage
// (including loss of data, profits, goodwill, or any type of
// loss or damage suffered as a result of any action brought
// by a third party) even if such damage or loss was
// reasonably foreseeable or Xilinx had been advised of the
// possibility of the same.
// 
// CRITICAL APPLICATIONS
// Xilinx products are not designed or intended to be fail-
// safe, or for use in any application requiring fail-safe
// performance, such as life-support or safety devices or
// systems, Class III medical devices, nuclear facilities,
// applications related to the deployment of airbags, or any
// other applications that could lead to death, personal
// injury, or severe property or environmental damage
// (individually and collectively, "Critical
// Applications"). Customer assumes the sole risk and
// liability of any use of Xilinx products in Critical
// Applications, subject only to applicable laws and
// regulations governing limitations on product liability.
// 
// THIS COPYRIGHT NOTICE AND DISCLAIMER MUST BE RETAINED AS
// PART OF THIS FILE AT ALL TIMES.
// 
// DO NOT MODIFY THIS FILE.

// IP VLNV: xilinx.com:ip:xadc_wiz:3.3
// IP Revision: 6

// The following must be inserted into your Verilog file for this
// core to be instantiated. Change the instance name and port connections
// (in parentheses) to your own signal names.

//----------- Begin Cut here for INSTANTIATION Template ---// INST_TAG
xadc_wiz_0 your_instance_name (
  .di_in(di_in),                              // input wire [15 : 0] di_in
  .daddr_in(daddr_in),                        // input wire [6 : 0] daddr_in
  .den_in(den_in),                            // input wire den_in
  .dwe_in(dwe_in),                            // input wire dwe_in
  .drdy_out(drdy_out),                        // output wire drdy_out
  .do_out(do_out),                            // output wire [15 : 0] do_out
  .dclk_in(dclk_in),                          // input wire dclk_in
  .vp_in(vp_in),                              // input wire vp_in
  .vn_in(vn_in),                              // input wire vn_in
  .vauxp4(vauxp4),                            // input wire vauxp4
  .vauxn4(vauxn4),                            // input wire vauxn4
  .vauxp5(vauxp5),                            // input wire vauxp5
  .vauxn5(vauxn5),                            // input wire vauxn5
  .vauxp6(vauxp6),                            // input wire vauxp6
  .vauxn6(vauxn6),                            // input wire vauxn6
  .vauxp7(vauxp7),                            // input wire vauxp7
  .vauxn7(vauxn7),                            // input wire vauxn7
  .vauxp12(vauxp12),                          // input wire vauxp12
  .vauxn12(vauxn12),                          // input wire vauxn12
  .vauxp13(vauxp13),                          // input wire vauxp13
  .vauxn13(vauxn13),                          // input wire vauxn13
  .vauxp14(vauxp14),                          // input wire vauxp14
  .vauxn14(vauxn14),                          // input wire vauxn14
  .vauxp15(vauxp15),                          // input wire vauxp15
  .vauxn15(vauxn15),                          // input wire vauxn15
  .user_temp_alarm_out(user_temp_alarm_out),  // output wire user_temp_alarm_out
  .vccint_alarm_out(vccint_alarm_out),        // output wire vccint_alarm_out
  .vccaux_alarm_out(vccaux_alarm_out),        // output wire vccaux_alarm_out
  .ot_out(ot_out),                            // output wire ot_out
  .channel_out(channel_out),                  // output wire [4 : 0] channel_out
  .eoc_out(eoc_out),                          // output wire eoc_out
  .alarm_out(alarm_out),                      // output wire alarm_out
  .eos_out(eos_out),                          // output wire eos_out
  .busy_out(busy_out)                        // output wire busy_out
);
// INST_TAG_END ------ End INSTANTIATION Template ---------

// You must compile the wrapper file xadc_wiz_0.v when simulating
// the core, xadc_wiz_0. When compiling the wrapper file, be sure to
// reference the Verilog simulation library.

