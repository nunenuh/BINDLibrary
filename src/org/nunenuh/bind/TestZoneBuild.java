/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind;

import java.util.HashSet;
import java.util.Set;
import org.nunenuh.bind.beans.ZoneAuthorityBean;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.beans.interfaces.ZoneRecordType;
import org.nunenuh.bind.config.ZoneConfig;
import org.nunenuh.bind.util.TimeConversionUtil;
import org.nunenuh.bind.util.ZoneAuthorityUtils;

/**
 *
 * @author nunenuh
 */
public class TestZoneBuild {

    public static void main(String[] args) {
        String file     = "D:/document/NetBeansProjects/BINDLibrary/named/named.conf.local";
        String path     = "D:/document/NetBeansProjects/BINDLibrary/named/";
        String domain   = "lima.com";
        String ns1      = "ns1.mediohostindo.web.id";
        String ns2      = "ns2.mediohostindo.web.id";
        String prip     = "172.30.1.110";
        String secip    = "172.30.1.111";
        String ttl     = "14400";
        
        
        ZoneBean zone = new ZoneBean();
        zone.setDomain(domain);
        zone.setFile(path + domain + ".db");
        zone.setTtl(ttl);
        zone.setType("master");

        ZoneAuthorityBean zab = new ZoneAuthorityBean();
        zab.setName(domain);
        zab.setNameServer(ns1);
        zab.setEmailAddress("hostmaster." + domain);
        zab.setSerialNumber(ZoneAuthorityUtils.getValidSerial());
        zab.setRefresh(TimeConversionUtil.getSecondByHour(1));
        zab.setRetry(TimeConversionUtil.getSecondByHour(3));
        zab.setExpire(TimeConversionUtil.getSecondByDay(1));
        zab.setMinimumCacheTTL(TimeConversionUtil.getSecondByDay(3));
        zab.setZone(zone);

        Set<ZoneRecordBean> setZRB = new HashSet<ZoneRecordBean>();
        setZRB.add(new ZoneRecordBean(zone, domain, ZoneRecordType.NS, ns1));
        setZRB.add(new ZoneRecordBean(zone, domain, ZoneRecordType.NS, ns2));
        setZRB.add(new ZoneRecordBean(zone, domain, ZoneRecordType.A, prip));
        setZRB.add(new ZoneRecordBean(zone, domain, ZoneRecordType.A, secip));
        setZRB.add(new ZoneRecordBean(zone, "www", ZoneRecordType.CNAME, domain));
        setZRB.add(new ZoneRecordBean(zone, "ftp", ZoneRecordType.CNAME, domain));
        setZRB.add(new ZoneRecordBean(zone, "mail", ZoneRecordType.CNAME, domain));
        
        zone.setZoneAuthority(zab);
        zone.setZoneRecords(setZRB);
        ZoneConfig zc = new ZoneConfig(file);
        zc.save(zone);

    }
    
    
}
