import serial.tools.list_ports

class RegisterInterface:
    
    def __init__(self):
        self._device = self.__find_port()
        
        if self._device is not None:
            self._serial = serial.Serial(self._device, 1000000, timeout=1)
            self._connected = True
        else:
            self._serial = None
            self._connected = False
    
    def __find_port(self):
        AU_CU_VID = '0403:6010'
        VID_PID = 'VID:PID'[::-1]
        SER = ' SER'
        
        # list ports
        plist = list(serial.tools.list_ports.comports())
        
        # checks vendor and product IDs
        for s in plist:
            start = len(s.hwid)-s.hwid[::-1].find(VID_PID)+1
            end = s.hwid.find(SER)
            
            vid_pid = s.hwid[start:end]
            if vid_pid == AU_CU_VID:
                return s.device
    
    def is_connected(self):
        return self._connected
    
    def disconnect(self):
        self._serial.close()
        
    def get_device(self):
        return self._device
    
    def __format_read_request(self, address):
        buff = bytearray(5)
        
        buff[0] = 0 << 7
        buff[1] = address & 0xff
        buff[2] = (address >> 8) & 0xff
        buff[3] = (address >> 16) & 0xff
        buff[4] = (address >> 24) & 0xff
        
        return buff
    
    def __format_write_request(self, address, data):
        buff = bytearray(9)
        
        buff[0] = 1 << 7
        buff[1] = address & 0xff
        buff[2] = (address >> 8) & 0xff
        buff[3] = (address >> 16) & 0xff
        buff[4] = (address >> 24) & 0xff
        buff[5] = data & 0xff
        buff[6] = (data >> 8) & 0xff
        buff[7] = (data >> 16) & 0xff
        buff[8] = (data >> 24) & 0xff
        
        return buff
    
    def __format_to_int(self, data):
        val = (data[0] & 0xff) | (data[1] & 0xff) << 8 | (data[2] & 0xff) << 16 | (data[3] & 0xff) << 24
        return val
    
    def write(self, address, value):
        if self._connected:
            self._serial.write(self.__format_write_request(address, value))
        
    def read(self, address):
        if self._connected:
            self._serial.write(self.__format_read_request(address))
            return self.__format_to_int(self._serial.read(4))

