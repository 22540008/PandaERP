/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Ricardo
 */
public class CurrencyUtils {
    public static final NumberFormat VN_FORMAT;

    static {
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        formatSymbols.setCurrencySymbol("₫");
        VN_FORMAT = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        ((DecimalFormat) VN_FORMAT).setDecimalFormatSymbols(formatSymbols);
    }    

    // Format các kiểu dữ liệu về VN-currency
    public static String format(Long value) {
        return VN_FORMAT.format(value);
    }

    public static String format(Integer value) {
        return VN_FORMAT.format(value);
    }
    
    public static String format(Double value) {
        return VN_FORMAT.format(value);
    }

    public static String format(String value) {
        //return VN_FORMAT.format(Long.parseLong(value));
        return VN_FORMAT.format(Double.parseDouble(value));
    }

    public static String format(Object value) {
        if (value instanceof Long) {
            return format((Long) value);
        } 
        else if (value instanceof Integer) {
            return format((Integer) value);
        } 
        else if (value instanceof String) {
            return format((String) value);
        } 
        else {
            throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
        }
    }

    public static void VN_FORMAT(long l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Chuyển VN-currency format về number format
    public static Integer parseToInt(String value) {
        try {
            Number number = VN_FORMAT.parse(value);
            return number.intValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid currency format: " + value, e);
        }
    }
    
    public static Long parseToLong(String value) {
        try {
            Number number = VN_FORMAT.parse(value);
            return number.longValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid currency format: " + value, e);
        }
    }

    public static Double parseToDouble(String value) {
        try {
            Number number = VN_FORMAT.parse(value);
            return number.doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid currency format: " + value, e);
        }
    }

    public static String parseToString(String value) {
        try {
            Number number = VN_FORMAT.parse(value);
            return number.toString();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid currency format: " + value, e);
        }
    }



}

/* Cách sử dụng:
Long longValue = 1000000L;
Integer intValue = 2000000;
String stringValue = "3000000";
System.out.println("Long value: " + CurrencyUtils.format(longValue));
System.out.println("Integer value: " + CurrencyUtils.format(intValue));
System.out.println("String value: " + CurrencyUtils.format(stringValue));

public class Main {
    public static void main(String[] args) {
        String currencyValue = "₫1,000,000";

        Long longValue = CurrencyUtils.parseToLong(currencyValue);
        System.out.println("Parsed Long value: " + longValue);

        Integer intValue = CurrencyUtils.parseToInt(currencyValue);
        System.out.println("Parsed Integer value: " + intValue);

        String stringValue = CurrencyUtils.parseToString(currencyValue);
        System.out.println("Parsed String value: " + stringValue);
    }
}
*/