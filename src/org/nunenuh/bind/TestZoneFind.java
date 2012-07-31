/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind;

import java.util.Iterator;
import java.util.Set;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.config.ZoneConfig;

/**
 *
 * @author nunenuh
 */
public class TestZoneFind {

    public static void main(String[] args) {
        String file = "D:/document/NetBeansProjects/BINDLibrary/named/named.conf.local";
        ZoneConfig zc = new ZoneConfig(file);
        ZoneBean zone = zc.getByDomain("fandi.com");
        
        Set<ZoneRecordBean> set = zone.getZoneRecords();
        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            System.out.print(zoneRecordBean.getName());
            System.out.print("\t"+zoneRecordBean.getType());
            System.out.print("\t"+zoneRecordBean.getPriority());
            System.out.print("\t"+zoneRecordBean.getRecordData());
            System.out.println("");
        }
    }
}
