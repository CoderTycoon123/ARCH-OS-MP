package Downloads;

import java.util.Scanner;

public class Float2BinaryHexConverter 
{
    private double decimal;
    private int exp;
    private String strBinary;
    private double binary;
    private int E;
    private double mantissa;
    private int signBit;
    private int Eprime;
    private String strMantissa;
    
    public Float2BinaryHexConverter() {}

    public int getSignBit()
    {
        return signBit;
    }
    
    public String getstrBinary()
    {
        return strBinary;
    }

    public double getBinary()
    {
        return binary;
    }

    public int getE()
    {
        return E;
    }

    public double getMantissa()
    {
        return mantissa;
    }
    
    public int getEprime()
    {
        return Eprime;
    }
    
    public String getstrMantissa()
    {
        return strMantissa;
    }
    
    public void setDecimal(double deci)
    {
        decimal = deci;
    }
    
    public void setExp(int exp)
    {
        this.exp = exp;
    }
    
    public void main()
    {
        //############################################### INPUT ########################################################
        if (decimal < 0)
                signBit = 1;

        decimal = decimal * (Math.pow(10, exp));	// ex. 4.5 x 10 ^ 2 becomes 450.0
        String strDecimal = decimal + "";

        String strWhole = wholeToBinary(strDecimal);

        String strFraction= fractionToBinary(strDecimal.substring(strDecimal.indexOf('.'), strDecimal.length()));
        strBinary = strWhole + strFraction;

        binary = Double.parseDouble(strBinary);
        E = getNormalizedExp(binary);

        strBinary = binary + "";
        strMantissa = strBinary.substring(strBinary.indexOf('.'));
        mantissa = Double.parseDouble(strMantissa);

        Eprime = E + 127;
        // SPECIAL CASES
        if (Eprime == 0 && binary == 0)
                System.out.println(binary);
        else if (Eprime == 0 && mantissa !=0)
                System.out.println("Denormalized");
        //else if 

        else
        {
            strMantissa = mantissa + "";
            int periodIndex = strMantissa.indexOf('.');

            strMantissa = strMantissa.substring(periodIndex+1);
            //System.out.println("strMantissa: "+ strMantissa);
            while (strMantissa.length() < 23)
            {
                    strMantissa+="0";
            }
        }
    }
    
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
}