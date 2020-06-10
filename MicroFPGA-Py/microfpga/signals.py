from abc import ABC, abstractmethod
from microfpga import regint

ADDR_MODE = 0
ADDR_DUR = 10
ADDR_SEQ = 20
ADDR_TTL = 30
ADDR_SERVO = 40
ADDR_PWM = 50 
ADDR_AI = 60 

ADDR_VER = 100 
ADDR_ID = 101

MAX_MODE = 4
MAX_DUR = 65535
MAX_SEQ = 65535
MAX_TTL = 1
MAX_SERVO = 65535
MAX_PWM = 255

def format_sequence(string):
    b = True
    for ch in string:
        if ch != '0' and ch != '1':
            b =  False
    
    if b and len(string) == 16:
        return int(string,2)
    else :
        return -1

class Signal(ABC):
    
    def __init__(self, channel_id: int, serial_com: regint.RegisterInterface, output: bool = True):
        self.channel_id = channel_id
        self.output = output
        self._serial_com = serial_com
    
    @abstractmethod
    def get_address(self):
        pass
       
    @abstractmethod 
    def is_allowed(self, value):
        pass
    
    def set_state(self, value):
        if self.output and self.is_allowed(value):
            self._serial_com.write(self.get_address()+self.channel_id, value)
            return True
        else:
            return False
        
    def get_state(self):
        return self._serial_com.read(self.get_address()+self.channel_id)
    
    
class Ttl(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com)
        
    def get_address(self):
        return ADDR_TTL
    
    def is_allowed(self, value):
        return (value >= 0) and (value <= MAX_TTL)
    
class Pwm(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com)
        
    def get_address(self):
        return ADDR_PWM
    
    def is_allowed(self, value):
        return (value >= 0) and (value <= MAX_PWM)
    
class Servo(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com)
        
    def get_address(self):
        return ADDR_SERVO
    
    def is_allowed(self, value):
        return (value >= 0) and (value <= MAX_SERVO)
    
class Analog(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com, False)
        
    def get_address(self):
        return ADDR_AI
    
    def is_allowed(self, value):
        return False
    
class _Mode(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com)
        
    def get_address(self):
        return ADDR_MODE
    
    def is_allowed(self, value):
        return (value >= 0) and (value <= MAX_MODE)

class _Duration(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com)
        
    def get_address(self):
        return ADDR_DUR
    
    def is_allowed(self, value):
        return (value >= 0) and (value <= MAX_DUR)
    
class _Sequence(Signal):
    
    def __init__(self, channel_id:int, serial_com:regint.RegisterInterface):
        Signal.__init__(self, channel_id, serial_com)
        
    def get_address(self):
        return ADDR_SEQ
    
    def is_allowed(self, value):
        return (value >= 0) and (value <= MAX_SEQ)
    
    
class LaserTrigger:
    MODE_OFF = 0
    MODE_ON = 1
    MODE_RISING = 2
    MODE_FALLING = 3
    MODE_CAMERA = 4
        
    def __init__(self, channel_id: int, serial_com:regint.RegisterInterface):
        self.channel_id = channel_id
        
        self.mode = _Mode(channel_id, serial_com)
        self.duration = _Duration(channel_id, serial_com)
        self.seq = _Sequence(channel_id, serial_com)
        
    def set_mode(self, value):
        return self.mode.set_state(value)
        
    def get_mode(self):
        return self.mode.get_state()
    
    def set_duration(self, value):
        return self.duration.set_state(value)
        
    def get_duration(self):
        return self.duration.get_state()
    
    def set_sequence(self, value):
        return self.seq.set_state(value)
        
    def get_sequence(self):
        return self.seq.get_state()
    
    def set_state(self, mode, duration, sequence):
        b = self.set_mode(mode)
        if not b:
            return b
        
        b = self.set_duration(duration)
        if not b:
            return b
        
        b = self.set_sequence(sequence)
        return b
    
    def get_state(self):
        return [self.get_mode(), self.get_duration(), self.get_sequence()]
    