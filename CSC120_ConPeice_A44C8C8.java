
package csc120_conpeice_A44C8C8;
import java.math.BigInteger;
import java.util.Scanner;

/** Coder = A44C8C8
 *  program can convert between any two base numbers up to 62.
 *  has two options.
 * 
 */

public class CSC120_ConPeice_A44C8C8 {
    
    private static final String key = "0213456789ABCDEFGHIJKLMNOPQRSTUVWXYZabc"
            + "defghijklmnopqrstuvwxyz" ;
    
    public static void main(String[] args) {
        String option = "";
        Scanner scan = new Scanner(System.in);
        String number= "";
        int startBase, endBase;
        startBase = 0;
        endBase = 0;
        
        System.out.println("Bonjur and welcome to the Number Base Conversion");
        System.out.println("Program. \n\nThis program can convert from any");
        System.out.println("number base to another. Example Decimal, Octal,");
        System.out.println("Hexadecimal, ect.");
        System.out.print("Currently supports base 2 to 62.\n");
        
        do{
            System.out.println("\nThis Program has two options:"
                    + "\n1. Convert a number to another base."
                    + "\n2. Exit program.\n");
            option = scan.nextLine().trim();
            
            if(option.equals("1")){
                System.out.println("Option 1 selected.");
                
                System.out.println("\nEnter the number to convert:");
                number = scan.nextLine();
                
                
                System.out.println("\nEnter the current base (2 to 62): ");
                System.out.println("Base 2 = Binary, 8 = Octal, 16 = Hexadecimal.");
                startBase = scan.nextInt();
                scan.nextLine();
                
                System.out.println("\nEnter the target base to convert too (2 to 62).");
                endBase = scan.nextInt();
                scan.nextLine();
                
                System.out.println("\ncomputing...computing...computing...");
                try{
                    String result = convertBase(number, startBase, endBase);
                    System.out.println("\n" + number + ", base " + startBase +
                             ", converted to base " + endBase + ", results in: "+result);
                } catch (Exception e){
                    System.out.println("Error: Invalid input, '" +number+
                            "' is not a valid number for base " + startBase + ".");
                }
            }
        } while (!option.equals("2"));
        
        System.out.println("Option 2 selected.");
        System.out.println("\nProgarm shutting down. Thanks you!!");
        System.out.println("computing...computing...computing... \n=(");
    }
    
    public static String convertBase(String number, int startBase, int endBase) throws Exception{
        if(startBase < 2 || endBase <2){
            throw new IllegalArgumentException("Base must be 2 or greater.");
        }
        
        // Convert the input number to decimal.
        BigInteger decimalValue = toDecimal(number, startBase);
        
        // Convert from decimal to the target base.
        return endDecimal(decimalValue, endBase);
    }
    
    public static BigInteger toDecimal(String number, int base) throws Exception{
        if(base <=36){
            // Java supports up to base 36 natively.
            return new BigInteger(number, base);
        }
        
        BigInteger value = BigInteger.ZERO;
        BigInteger baseBig = BigInteger.valueOf(base);
        
        
        // Convert each digit from the custom base to decimal.
        for(char digit : number.toCharArray()){
            int digitValue = key.indexOf(digit);
            if(digitValue == -1 || digitValue >= base){
                throw new Exception("Invalid charcter '" + digit + "' for base"
                + base);
            }
            value = value.multiply(baseBig).add(BigInteger.valueOf(digitValue));
        }
        return value;
    }
    
    // Convert from decimal to the target base.
    public static String endDecimal(BigInteger number, int base){
        if(base <= 36){
            // Java supports base 2 to 36 natively
            return number.toString(base).toUpperCase();
        }
        
        StringBuilder result = new StringBuilder();
        BigInteger baseBig = BigInteger.valueOf(base);
        
        // Convert decimal to the target base using the division method.
        while(number.compareTo(BigInteger.ZERO) > 0){
            int remainder = number.mod(baseBig).intValue();
            result.insert(0, key.charAt(remainder));
            number = number.divide(baseBig);
        }
        
        return result.length() == 0 ? "0" : result.toString();
    }
}


