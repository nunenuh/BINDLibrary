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
public class TestZone {

    public static void main(String[] args) {

        String ns1    = "ns1.mediahostindo.web.id";
        String ns2    = "ns2.mediahostindo.web.id";
        
        String domain       = "coba.com";
        String path         = "D:/document/NetBeansProjects/BINDLibrary/named/";

        ZoneBean zb = new ZoneBean();
        zb.setDomain(domain);
        zb.setType("master");
        zb.setFile(path+domain+".db");
        zb.setTtl("14400");
        
        //add SOA
        ZoneAuthorityBean zab = new ZoneAuthorityBean();
        zab.setZone(zb);
        zab.setName(domain);
        zab.setNameServer(ns1);
        zab.setEmailAddress("hostmaster."+domain);
        zab.setSerialNumber(ZoneAuthorityUtils.getValidSerial());
        zab.setRefresh(TimeConversionUtil.getSecondByHour(1));
        zab.setRetry(TimeConversionUtil.getSecondByHour(2));
        zab.setExpire(TimeConversionUtil.getSecondByDay(1));
        zab.setMinimumCacheTTL(TimeConversionUtil.getSecondByDay(3));
        zb.setZoneAuthority(zab);
        
        //add NS
        Set<ZoneRecordBean> set = new HashSet<ZoneRecordBean>();
        ZoneRecordBean zrbns1 = new ZoneRecordBean(zb, "nunenuh.com", ZoneRecordType.NS, ns1);
        ZoneRecordBean zrbns2 = new ZoneRecordBean(zb, "nunenuh.com", ZoneRecordType.NS, ns2);
        
        
        set.add(zrbns1);
        set.add(zrbns2);
        zb.setZoneRecords(set);
        
        
        
        ZoneConfig zc = new ZoneConfig("D:/document/NetBeansProjects/BINDLibrary/named/"+"named.conf.local");
        zc.save(zb);
        
        
        
//        ZoneConfig zc = new ZoneConfig();
//        ZoneBean zone = zc.getById(1);
//        
//        
//
//        ZoneRecordConfig zrc = new ZoneRecordConfig();
//        Set<ZoneRecordBean> set = zone.getZoneRecords();
//
//
//        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
//            ZoneRecordBean zoneRecordBean = it.next();
//            System.out.print("line [" + zoneRecordBean.getId() + "]:\t");
//            System.out.print(zoneRecordBean.getName());
//            System.out.print("\t" + zoneRecordBean.getZone().getTtl());
//            System.out.print("\t" + zoneRecordBean.getClazz());
//            System.out.print("\t" + zoneRecordBean.getType());
//            System.out.print("\t" + zoneRecordBean.getPriority());
//            System.out.print("\t" + zoneRecordBean.getRecordData());
//            System.out.println();
//
//        }

    }
}
