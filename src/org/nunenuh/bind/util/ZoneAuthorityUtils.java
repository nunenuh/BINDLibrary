/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nunenuh
 */
public class ZoneAuthorityUtils {
    
      public static long getValidSerial() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddss");
        return Long.valueOf(sdf.format(new Date()));
    }
}
