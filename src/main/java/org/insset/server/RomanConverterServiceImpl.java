/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.insset.client.service.RomanConverterService;

/**
 *
 * @author VILAIN THEO, KIT ANTHONY ET COUSIN FABIEN.
 */
@SuppressWarnings("serial")
public class RomanConverterServiceImpl extends RemoteServiceServlet implements
        RomanConverterService {
            private static final String[] RCODE = {"M", "CM", "D", "CD", "C", "XC", "L",
                                           "XL", "X", "IX", "V", "IV", "I"};
            private static final int[]    BVAL  = {1000, 900, 500, 400,  100,   90,  50,
                                           40,   10,    9,   5,   4,    1};
    
    private static final int[] decimalValues = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] romanNumerals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
            
    @Override
    public String convertDateYears(String nbr) throws IllegalArgumentException {
        
        return "XV/III/MX";
    }

    @Override
    public Integer convertRomanToArabe(String nbr) throws IllegalArgumentException {
      
    final Matcher matcher = Pattern.compile("M|CM|D|CD|C|XC|L|XL|X|IX|V|IV|I").matcher(nbr);
    
    int result = 0; 
    

    while (matcher.find())
        for (int i = 0; i < romanNumerals.length; i++)
            if (romanNumerals[i].equals(matcher.group(0)))
                result += decimalValues[i];
                
    return result;

     
    
    }
    

    @Override
    public String convertArabeToRoman(Integer nbr) throws IllegalArgumentException {
        
       
        String roman = "";         // Roman notation will be accumualated here.
        
        // Loop from biggest value to smallest, successively subtracting,
        // from the binary value while adding to the roman representation.
        for (int i = 0; i < RCODE.length; i++) {
            while (nbr >= BVAL[i]) {
                nbr -= BVAL[i];
                roman  += RCODE[i];
            }
        }
        return roman;
        
    }

}
