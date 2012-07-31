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
public class TestZoneDelete {

    public static void main(String[] args) {
        String path = "C:/Users/nunenuh/Dropbox/skripsi/software/epanel/BINDLibrary/named";
        String file = "C:/Users/nunenuh/Dropbox/skripsi/software/epanel/BINDLibrary/named/named.conf.local";
        String domain = "fandi.com";
        
        ZoneConfig zc = new ZoneConfig(file);
        ZoneBean zone = zc.getByDomain(domain);
        
        ZoneRecordConfig zrc = new ZoneRecordConfig();
        
        ZoneRecordBean zrb = new ZoneRecordBean();
        zrb.setName(domain);
        zrb.setType("NS");
        zrb.setRecordData("ns2.mediahostindo.web.id");
        
        zrc.deleteByProperty(zone, zrb);
        zrc.resetIndexPosition(zone);
        
    }
}
