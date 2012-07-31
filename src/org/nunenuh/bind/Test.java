/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.nunenuh.bind.util.ValidatorUtils;

/**
 *
 * @author nunenuh
 */
public class Test {

    public static void main(String[] args) {
        String DATE_FORMAT = "yyyyMMddss";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddss");
        System.out.println("Today is " + sdf.format(new Date()));
    }
}
