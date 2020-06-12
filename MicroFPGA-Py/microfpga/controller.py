from microfpga import signals
from microfpga import regint

class MicroFPGA:
    
    def __init__(self, n_lasers, n_ttls, n_servos, n_pwms, n_ais):
        self._serial = regint.RegisterInterface()
        self.device = self._serial.get_device()
        
        self._lasers = []
        self._ttls = []
        self._servos = []
        self._pwms = []
        self._ais = []
        if self._serial.is_connected():
            
            self.version = self._serial.read(signals.ADDR_VER)
            self.id = self._serial.read(signals.ADDR_ID)
                
            if (self.version == signals.CURR_VER) and (self.id == signals.ID_AU or self.id == signals.ID_CU):
                # instantiates lasers
                for i in range(n_lasers):
                    self._lasers.append(signals.LaserTrigger(i, self._serial))
                    
                # instantiates TTLs
                for i in range(n_ttls):
                    self._ttls.append(signals.Ttl(i, self._serial))
                    
                # instantiates lasers
                for i in range(n_servos):
                    self._servos.append(signals.Servo(i, self._serial))
                    
                # instantiates lasers
                for i in range(n_pwms):
                    self._pwms.append(signals.Pwm(i, self._serial))
                    
                # instantiates lasers
                if self.id == signals.ID_AU:
                    for i in range(n_ais):
                        self._ais.append(signals.Analog(i, self._serial))
            else:
                self.disconnect()
                if self.version != signals.CURR_VER:
                    raise Warning('Wrong version: expected '+str(signals.CURR_VER)+\
                                  ', got '+str(self.version)+'. The port has been disconnected')
                
                
                if self.id != signals.ID_AU and self.id != signals.ID_CU:
                    raise Warning('Wrong board id: expected '+str(signals.ID_AU)+\
                                  ' (Au) or '+str(signals.ID_CU)+' (Cu), got '+str(self.id)+'. The port has been disconnected')

    def disconnect(self):
        self._serial.disconnect()
        
    def is_connected(self):
        return self.__serial.is_connected()
    
    def get_number_lasers(self):
        return len(self._lasers)
    
    def get_number_ttls(self):
        return len(self._ttls)
    
    def get_number_servos(self):
        return len(self._servos)
    
    def get_number_pwms(self):
        return len(self._pwms)
    
    def get_number_analogs(self):
        return len(self._ais)
    
    def set_ttl_state(self, channel, value):
        if channel >= 0 and channel < self.get_number_ttls():
            return self._ttls[channel].set_state(value)
        else:
            return False

    def get_ttl_state(self, channel):
        if channel >= 0 and channel < self.get_number_ttls():
            return self._ttls[channel].get_state()
        else:
            return -1
    
    def set_servo_state(self, channel, value):
        if channel >= 0 and channel < self.get_number_servos():
            return self._servos[channel].set_state(value)
        else:
            return False

    def get_servo_state(self, channel):
        if channel >= 0 and channel < self.get_number_servos():
            return self._servos[channel].get_state()
        else:
            return -1
    
    def set_pwm_state(self, channel, value):
        if channel >= 0 and channel < self.get_number_pwms():
            return self._pwms[channel].set_state(value)
        else:
            return False

    def get_pwm_state(self, channel):
        if channel >= 0 and channel < self.get_number_pwms():
            return self._pwms[channel].get_state()
        else:
            return -1

    def get_analog_state(self, channel):
        if channel >= 0 and channel < self.get_number_analogs():
            return self._ais[channel].get_state()
        else:
            return -1
     
    def set_mode_state(self, channel, value):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].set_mode(value)
        else:
            return False

    def get_mode_state(self, channel):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].get_mode()
        else:
            return -1
     
    def set_duration_state(self, channel, value):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].set_duration(value)
        else:
            return False

    def get_duration_state(self, channel):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].get_duration()
        else:
            return -1
   
    def set_sequence_state(self, channel, value):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].set_sequence(value)
        else:
            return False

    def get_sequence_state(self, channel):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].get_sequence()
        else:
            return -1
        
    def set_laser_state(self, channel, mode, duration, sequence):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].set_state(mode, duration, sequence)
        else:
            return False
        
    def get_laser_state(self, channel):
        if channel >= 0 and channel < self.get_number_lasers():
            return self._lasers[channel].get_state()
        else:
            return [-1,-1,-1]
        
    def get_id(self):
        if self.id == signals.ID_AU:
            return 'Au'
        elif self.id == signals.ID_CU:
            return 'Cu'
        else:
            return 'Unknown' 
        
   



