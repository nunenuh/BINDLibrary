/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind;

import org.nunenuh.bind.beans.ZoneAuthorityBean;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.config.ZoneAuthorityConfig;
import org.nunenuh.bind.helper.ZoneAuthorityHelper;
import org.nunenuh.bind.util.ZoneAuthorityUtils;

/**
 *
 * @author nunenuh
 */
public class TestZoneAuthority {
    
    public static void main(String[] args) {
        ZoneBean zone = new ZoneBean();
        ZoneAuthorityBean zoneAuthority = new ZoneAuthorityBean();
        
        zone.setDomain("test1.com");
        zone.setType("master");
        zone.setFile("D:/document/NetBeansProjects/BINDLibrary/named/test.com.db");
        zone.setTtl("14400");
        
        zoneAuthority.setZone(zone);
        zoneAuthority.setName("nunenuh.com");
        zoneAuthority.setNameServer("ns2.mediahostindo.web.id");
        zoneAuthority.setEmailAddress("hostmaster.nunenuh.com");
        zoneAuthority.setSerialNumber(ZoneAuthorityUtils.getValidSerial());
        zoneAuthority.setRefresh(3600);
        zoneAuthority.setRetry(8600);
        zoneAuthority.setExpire(3600);
        zoneAuthority.setMinimumCacheTTL(8600);
        
        ZoneAuthorityConfig zac = new ZoneAuthorityConfig();
        zac.set(zoneAuthority);
        zoneAuthority = zac.get(zone);
        
    }
}
