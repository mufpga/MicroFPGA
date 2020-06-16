package de.embl.rieslab.microfpga.emu.utils;

public class BinaryConverter {

	/**
	 * Returns the decimal value of a 16bits string, 0 otherwise.
	 * 
	 * @param s
	 * @return Decimal value of s
	 */
	public static int getDecimal16bits(String s){
		if(!isBits(s) || s.length() != 16){
			return 0;
		}
		
		int i = Integer.parseInt(s, 2);
		if(i>65535){
			i=65535;
		}
		return i;
	}
	
	/**
	 * Tests if a string represents 16bits.
	 * 
	 * @param s
	 * @return True if the string represents 16 bits, false otherwise.
	 */
	public static boolean is16bits(String s){
		if(s.length() != 16){
			return false;
		}
	
		for(int i=0;i<16;i++){
			if(!s.substring(i, i+1).equals("0") && !s.substring(i, i+1).equals("1") ){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isBits(String s){	
		for(int i=0;i<s.length();i++){
			if(!s.substring(i, i+1).equals("0") && !s.substring(i, i+1).equals("1") ){
				return false;
			}
		}
		return true;
	}
	
	public static String getBinary16bits(int val){
		if(val>65535){
			return "1111111111111111";
		} else if(val<=0){
			return "0000000000000000";
		}
		
		String s = Integer.toBinaryString(val);
		
		if(s.length()<16){
			int diff = 16-s.length();
			for(int i=0;i<diff;i++){
				s = "0"+s;
			}
		}
		
		return s;
	}
}