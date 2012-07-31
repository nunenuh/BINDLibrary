/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind;

import java.util.Iterator;
import java.util.List;
import org.nunenuh.bind.beans.ZoneAuthorityBean;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.beans.interfaces.ZoneRecordType;
import org.nunenuh.bind.config.ZoneConfig;
import org.nunenuh.bind.config.ZoneRecordConfig;

/**
 *
 * @author nunenuh
 */
public class TestZoneUpdate {

    public static void main(String[] args) {
        String file = "C:/Users/nunenuh/Dropbox/skripsi/software/epanel/BINDLibrary/named/named.conf.local";
        String domain = "fandi.com";
        String ns1 = "ns1.mediahostindo.web.id";
        String ns2 = "ns2.mediahostindo.web.id";
        String prip = "172.30.1.110";
        String secip = "172.30.1.111";
        String ttl = "14400";


        ZoneConfig zc = new ZoneConfig(file);
        List<ZoneBean> list = zc.list();

        for (Iterator<ZoneBean> it = list.iterator(); it.hasNext();) {
            ZoneBean zone = it.next();
            System.out.println("Looping");
            ZoneAuthorityBean zab = zone.getZoneAuthority();
            
            //delete by  NS and A record
            ZoneRecordConfig zrc = new ZoneRecordConfig();
            zrc.deleteByNameAndType(zone, zone.getDomain(), ZoneRecordType.NS);
            zrc.deleteByNameAndType(zone, zone.getDomain(), ZoneRecordType.A);


            //add NS and A record
            zrc.save(new ZoneRecordBean(zone, zone.getDomain(), ZoneRecordType.NS, ns1));
            zrc.save(new ZoneRecordBean(zone, zone.getDomain(), ZoneRecordType.NS, ns2));
            zrc.save(new ZoneRecordBean(zone, zone.getDomain(), ZoneRecordType.A, prip));
            zrc.save(new ZoneRecordBean(zone, zone.getDomain(), ZoneRecordType.A, secip));
            zrc.resetIndexPosition(zone);
        }
    }
}
