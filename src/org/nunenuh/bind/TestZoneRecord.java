/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind;

import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.beans.interfaces.ZoneRecordType;
import org.nunenuh.bind.config.ZoneRecordConfig;

/**
 *
 * @author nunenuh
 */
public class TestZoneRecord {
    public static void main(String[] args) {
        
        ZoneBean zone           = new ZoneBean();
        zone.setFile("D:/document/NetBeansProjects/BINDLibrary/named/coba.com.db");
        zone.setTtl("14400");
        
        
        ZoneRecordBean zrbns1 = new ZoneRecordBean(zone, "coba.com", ZoneRecordType.A, "172.30.1.110");
        ZoneRecordBean zrbns2 = new ZoneRecordBean(zone, "coba.com", ZoneRecordType.A, "172.30.1.111");
        
        
        ZoneRecordConfig zrc = new ZoneRecordConfig();
        zrc.save(zrbns1);
        zrc.save(zrbns2);
        
//        zrc.deleteByType(zone, ZoneRecordType.MX);
       
    }
}
