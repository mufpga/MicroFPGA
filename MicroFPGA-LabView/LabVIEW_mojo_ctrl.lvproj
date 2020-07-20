<?xml version='1.0' encoding='UTF-8'?>
<Project Type="Project" LVVersion="18008000">
	<Item Name="My Computer" Type="My Computer">
		<Property Name="server.app.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="server.control.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="server.tcp.enabled" Type="Bool">false</Property>
		<Property Name="server.tcp.port" Type="Int">0</Property>
		<Property Name="server.tcp.serviceName" Type="Str">My Computer/VI Server</Property>
		<Property Name="server.tcp.serviceName.default" Type="Str">My Computer/VI Server</Property>
		<Property Name="server.vi.callsEnabled" Type="Bool">true</Property>
		<Property Name="server.vi.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="specify.custom.address" Type="Bool">false</Property>
		<Item Name="mojo" Type="Folder">
			<Property Name="NI.SortType" Type="Int">3</Property>
			<Item Name="private" Type="Folder">
				<Item Name="U32_to_hex.vi" Type="VI" URL="../U32_to_hex.vi"/>
				<Item Name="address_plus_laserNumber_U32_to_hex.vi" Type="VI" URL="../address_plus_laserNumber_U32_to_hex.vi"/>
				<Item Name="mojo_laser_control.vi" Type="VI" URL="../mojo_laser_control.vi"/>
				<Item Name="hex_to_U32.vi" Type="VI" URL="../hex_to_U32.vi"/>
				<Item Name="read_string.vi" Type="VI" URL="../read_string.vi"/>
			</Item>
			<Item Name="easy_test" Type="Folder">
				<Item Name="test microFPGA VIs.vi" Type="VI" URL="../test microFPGA VIs.vi"/>
				<Item Name="single_analog_read_test.vi" Type="VI" URL="../../../../SVN/projects/AA_INKU_POWERBOARD/LabVIEW/Software LV2017_COM port auswahl/test/single_analog_read_test.vi"/>
			</Item>
			<Item Name="microFPGA_init.vi" Type="VI" URL="../microFPGA_init.vi"/>
			<Item Name="microFPGA_close.vi" Type="VI" URL="../microFPGA_close.vi"/>
			<Item Name="microFPGA_laser_mode_addr_0_ctrl.vi" Type="VI" URL="../microFPGA_laser_mode_addr_0_ctrl.vi"/>
			<Item Name="microFPGA_laser_duration_addr_10_ctrl.vi" Type="VI" URL="../microFPGA_laser_duration_addr_10_ctrl.vi"/>
			<Item Name="microFPGA_laser_sequence_addr_20_ctrl.vi" Type="VI" URL="../microFPGA_laser_sequence_addr_20_ctrl.vi"/>
			<Item Name="microFPGA_TTL_addr_30_ctrl.vi" Type="VI" URL="../microFPGA_TTL_addr_30_ctrl.vi"/>
			<Item Name="microFPGA_servo_position_addr_40_ctrl.vi" Type="VI" URL="../microFPGA_servo_position_addr_40_ctrl.vi"/>
			<Item Name="microFPGA_PWM_addr_50_ctrl.vi" Type="VI" URL="../microFPGA_PWM_addr_50_ctrl.vi"/>
			<Item Name="microFPGA_analog_input_addr_60_ctrl.vi" Type="VI" URL="../microFPGA_analog_input_addr_60_ctrl.vi"/>
			<Item Name="microFPGA_version_read_addr_100_ctrl.vi" Type="VI" URL="../microFPGA_version_read_addr_100_ctrl.vi"/>
		</Item>
		<Item Name="single_ttl_test.vi" Type="VI" URL="../../../../SVN/projects/AA_INKU_POWERBOARD/LabVIEW/Software LV2017_COM port auswahl/test/single_ttl_test.vi"/>
		<Item Name="Dependencies" Type="Dependencies">
			<Item Name="vi.lib" Type="Folder">
				<Item Name="VISA Configure Serial Port" Type="VI" URL="/&lt;vilib&gt;/Instr/_visa.llb/VISA Configure Serial Port"/>
				<Item Name="VISA Configure Serial Port (Instr).vi" Type="VI" URL="/&lt;vilib&gt;/Instr/_visa.llb/VISA Configure Serial Port (Instr).vi"/>
				<Item Name="VISA Configure Serial Port (Serial Instr).vi" Type="VI" URL="/&lt;vilib&gt;/Instr/_visa.llb/VISA Configure Serial Port (Serial Instr).vi"/>
				<Item Name="VISA Open Access Mode.ctl" Type="VI" URL="/&lt;vilib&gt;/Instr/_visa.llb/VISA Open Access Mode.ctl"/>
			</Item>
		</Item>
		<Item Name="Build Specifications" Type="Build"/>
	</Item>
</Project>
