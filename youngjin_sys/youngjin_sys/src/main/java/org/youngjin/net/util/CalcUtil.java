package org.youngjin.net.util;

public class CalcUtil {
	public static Integer fromKgToLbs(Integer kg){
		Integer lbs = (int) (kg * 2.204623);
		
		return lbs;
	}
	
	public static String checkCommaString(Object param){
		String number = "";
		String returnNumber = "";
		if(param instanceof String){
			number = (String)param;
		}else if(param instanceof Integer){
			number = param.toString();
		}
		
		if(number.length() > 3){
			for (int i = 0 ; i < number.length() ; i ++ ){
				if( i % 3 == 1){
					returnNumber.concat("," + number.charAt(i));
				} else {
					returnNumber.concat(String.valueOf(number.charAt(i)));
				}
			} 
			
			return returnNumber;
		} else {
			return number;
		}
	}
}
