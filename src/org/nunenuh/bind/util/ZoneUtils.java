/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.util;

import org.nunenuh.bind.beans.ZoneBean;

/**
 *
 * @author nunenuh
 */
public class ZoneUtils {
    
    private ZoneBean zone;

    public ZoneUtils(ZoneBean zone) {
        this.zone = zone;
    }
    
    public String getZoneLine(){
        StringBuilder sb = new StringBuilder();
        sb.append("zone \"").append(zone.getDomain()).append("\" { ");
        sb.append("type ").append(zone.getType()).append("; ");
        sb.append("file \"").append(zone.getFile()).append("\"; ");
        if (zone.getType().equals("slave") && zone.getMasters() != null) {
            sb.append("masters { ").append(zone.getMasters()).append("; }; ");
        }
        sb.append("};");
        
        return sb.toString();
    }
}
