package de.embl.rieslab.microfpga.devices;

import de.embl.rieslab.microfpga.regint.RegisterInterface;

public class DeviceFactory {

	public final static int MAX_TTL = 6;
	public final static int MAX_PWM = 6;
	public final static int MAX_LASER = 6;
	public final static int MAX_SERVO = 6;
	public final static int MAX_AI = 8;
	
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
		return MAX_TTL-counterTTLs_;
	}

	public int getNumPWMLeft() {
		return MAX_PWM-counterPWMs_;
	}

	public int getNumLaserLeft() {
		return MAX_LASER-counterLasers_;
	}

	public int getNumServoLeft() {
		return MAX_SERVO-counterServos_;
	}

	public int getNumAILeft() {
		return MAX_AI-counterAIs_;
	}
}
