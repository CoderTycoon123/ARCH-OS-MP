package cpl;

import java.util.Scanner;

public class Float2BinaryHexConverter {
	
	public String fractionToBinary(String str)
	{
		double n = Double.parseDouble(str);
		String result = "";
		while (n > 0)
		{
			double ones = n * 2;
			if (ones > 0)
			{
				result+="1";
				n = ones -1;
			}
			else
			{
				result+="0";
				n = ones;
			}
		}
		return "."+result;
	}
	
	public String wholeToBinary(String n)
	{
		int whole = Integer.parseInt(n.substring(0, n.indexOf('.')));
		return Integer.toBinaryString(whole);
	}
	
	public int getNormalizedExp(double binary) // normalizes binary while counting exp
	{
		int E = 0;
		if(binary > 9)
		{
			while(binary > 9)
			{
				binary /= 10;
				E++;
			}
		}
		else if(binary < 1 && binary > 0)
		{
			while (binary < 1)
			{
				binary *= 10;
				E --;
			}
		}
		return E;
	}
	
	public static void main(String [] args)
	{
		Float2BinaryHexConverter app = new Float2BinaryHexConverter();
		System.out.println("============================ INPUT (will transfer to GUI if possible) ============================");
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter decimal mantissa: ");
		double decimal = sc.nextDouble();
		sc.nextLine();
		System.out.print("Please enter exponent (base-10): ");
		int exp = sc.nextInt();
		//############################################### INPUT ########################################################
		int signBit = 0;
		if (decimal < 0)
		{
			signBit = 1;
			decimal *= -1;
		}
		
		decimal = decimal*(Math.pow(10, exp));	// ex. 4.5 x 10 ^ 2 becomes 450.0
		String strDecimal = decimal + "";
		System.out.println("decimal: "+ decimal);
		String strWhole = app.wholeToBinary(strDecimal);
		
		String strFraction= app.fractionToBinary(strDecimal.substring(strDecimal.indexOf('.'), strDecimal.length()));
		String strBinary = strWhole + strFraction;
		System.out.println("binary whole & fraction combined: "+strBinary);
		
		
		double binary = Double.parseDouble(strBinary);
		//int E = app.getNormalizedExp(binary);
		
		int E = 0;
		if(binary > 9)
		{
			while(binary > 9)
			{
				binary /= 10;
				E++;
			}
		}
		else if(binary < 1 && binary > 0)
		{
			while (binary < 1)
			{
				binary *= 10;
				E --;
			}
		}
		System.out.println("normalized: "+binary);
		System.out.println("E = "+E);
		
		strBinary = binary + "";
		String strMantissa = strBinary.substring(strBinary.indexOf('.')+1);
		System.out.println("strMan "+strMantissa);
		int mantissa = Integer.parseInt(strMantissa);
		
		System.out.println("mantissa: "+mantissa);
		
		int Eprime = E + 127;
		// SPECIAL CASES
		if (Eprime == 0 && binary == 0)
			System.out.println(binary);
		else if (Eprime == 0 && mantissa !=0)
			System.out.println("Denormalized");
		else if (Eprime == 255 && mantissa == 0)
			System.out.println("infinity and beyond!");
		else if(Eprime == 255 && mantissa != 0)
			System.out.println("Nan");
		
		else
		{
			//strMantissa = mantissa + "";
			//int periodIndex = strMantissa.indexOf('.');
			
			//strMantissa = strMantissa.substring(periodIndex+1);
			//System.out.println("strMantissa: "+ strMantissa);
			while (strMantissa.length() < 23)
			{
				strMantissa+="0";
			}
			System.out.println(signBit + " "+ Integer.toBinaryString(Eprime)+" "+ strMantissa);
		}
		
		System.out.println("done");
	}
}
