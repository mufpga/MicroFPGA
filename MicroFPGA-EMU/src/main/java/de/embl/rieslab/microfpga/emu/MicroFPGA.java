package de.embl.rieslab.microfpga.emu;

import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.plugin.UIPlugin;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;

public class MicroFPGA  implements UIPlugin{

	@Override
	public String getName() {
		return "MicroFPGA";
	}

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller, TreeMap<String, String> pluginSettings) {
		return new MainFrame("MicroFPGA", controller, pluginSettings);
	}

}
