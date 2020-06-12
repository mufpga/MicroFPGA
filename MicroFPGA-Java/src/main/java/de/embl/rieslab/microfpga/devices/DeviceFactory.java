package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.MicroFPGAController;
import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class DeviceFactory {
	
	private final RegisterInterface regint_;
	
	private int counterTTLs_, counterPWMs_, counterLasers_, 
		counterServos_, counterAIs_;
	
	public DeviceFactory(RegisterInterface regint) {
		regint_ = regint;
		
		counterTTLs_ = 0;
		counterPWMs_ = 0;
		counterLasers_ = 0;
		counterServos_ = 0;
		counterAIs_ = 0;
	}

	public TTL getTTL() {
		if(getNumTTLLeft() > 0) {
			return new TTL(counterTTLs_++,regint_);
		}
		return null;
	}
	
	public PWM getPWM() {
		if(getNumTTLLeft() > 0) {
			return new PWM(counterPWMs_++,regint_);
		}
		return null;
	}
	
	public LaserTrigger getLaser() {
		if(getNumTTLLeft() > 0) {
			return new LaserTrigger(counterLasers_++,regint_);
		}
		return null;
	}
	
	public Servo getServo() {
		if(getNumTTLLeft() > 0) {
			return new Servo(counterServos_++,regint_);
		}
		return null;
	}
	
	public AnalogInput getAI() {
		if(getNumTTLLeft() > 0) {
			return new AnalogInput(counterAIs_++,regint_);
		}
		return null;
	}

	public int getNumTTLLeft() {
		return MicroFPGAController.MAX_TTL-counterTTLs_;
	}

	public int getNumPWMLeft() {
		return MicroFPGAController.MAX_PWM-counterPWMs_;
	}

	public int getNumLaserLeft() {
		return MicroFPGAController.MAX_LASER-counterLasers_;
	}

	public int getNumServoLeft() {
		return MicroFPGAController.MAX_SERVO-counterServos_;
	}

	public int getNumAILeft() {
		return MicroFPGAController.MAX_AI-counterAIs_;
	}
}
