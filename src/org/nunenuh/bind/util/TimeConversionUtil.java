/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.util;

/**
 *
 * @author nunenuh
 */
public class TimeConversionUtil {
    
    public static long getSecondByYear(int year){
        return getSecondByDay(year*365);
    }
    
    public static long getSecondByMonth(int month){
        return getSecondByDay(month*30);
    }
    
    public static long getSecondByDay(int day){
        return getSecondByHour(day*24);
    }
    
    public static long getSecondByHour(int hour){
        return getSecondByMinute(hour*60);
    }
    
    public static long getSecondByMinute(int minute){
        return (minute*60);
    }
}

