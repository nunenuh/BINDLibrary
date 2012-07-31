/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.util.ZoneOperation;
import org.nunenuh.bind.util.ZoneUtils;

/**
 *
 * @author nunenuh
 */
public class ZoneConfig {

    private String path;

    public ZoneConfig(String file) {
        this.path = file;
    }

    /**
     *
     * @param zone
     */
    public void save(ZoneBean zone) {
        String zoneLine = new ZoneUtils(zone).getZoneLine();
        ZoneOperation.insertNewLine(path, zoneLine);

        File f = new File(zone.getFile());
        if (!f.exists()) {
            try {
                f.createNewFile();
                ZoneOperation.insertNewLine(zone.getFile(), "$TTL " + zone.getTtl() + "\n");
            } catch (IOException ex) {
                Logger.getLogger(ZoneConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zone.getZoneAuthority() != null) {
            new ZoneAuthorityConfig().set(zone.getZoneAuthority());
        }

        if (zone.getZoneRecords() != null) {
            Set<ZoneRecordBean> set = zone.getZoneRecords();
            for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
                ZoneRecordBean zoneRecordBean = it.next();
                ZoneRecordConfig zrc = new ZoneRecordConfig();
                zrc.save(zoneRecordBean);
            }
        }
    }

    /**
     *
     * @param zone
     */
    public void update(ZoneBean zone) {
        String zoneLine = new ZoneUtils(zone).getZoneLine();
        ZoneOperation.replaceByLine(path, zone.getId(), zoneLine);
    }

    /**
     *
     * @param zo
     */
    public void delete(ZoneBean zo) {
        ZoneOperation.removeByLine(path, zo.getId());
        ZoneOperation.insertByLine(path, "", zo.getId());
        File f = new File(zo.getFile());
        if (f.exists()){
            f.delete();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public ZoneBean getById(int id) {
        String line = ZoneOperation.readByLine(path, id);

        //check whether line is null or line is empty and line start with zone
        //as a valid zone format
        if ((line != null || line.isEmpty()) && line.startsWith("zone")) {
            String[] generalSplit = line.split("\\s+", 4);
            //domain split
            String[] domainSplit = generalSplit[1].split("\\\"");
            String domain = domainSplit[domainSplit.length - 1];
            //end domain split

            //split type, file, and master
            String file = null;
            String type = null;
            String masters = null;

            String[] propertySplit = generalSplit[generalSplit.length - 1].split("\\;", 3);
            for (int i = 0; i < propertySplit.length; i++) {
                if (propertySplit[i].contains("file")) {
                    String[] fileSplit = propertySplit[i].split("\\s+|\\\"");
                    file = fileSplit[fileSplit.length - 1];
                }

                if (propertySplit[i].contains("type")) {
                    String[] typeSplit = propertySplit[i].split("\\s+");
                    type = typeSplit[typeSplit.length - 1];
                }

                if (propertySplit[i].contains("masters")) {
                    String[] masterSplit = propertySplit[i].split("\\s+|\\{|\\}|\\;");
                    masters = masterSplit[masterSplit.length - 1];
                }
            }
            //end split type, file, and master

            //initialize zone domain
            ZoneBean zone = new ZoneBean();
            zone.setId(id);
            zone.setDomain(domain);
            zone.setFile(file);
            zone.setType(type);
            zone.setMasters(masters);
            zone.setTtl(getTTL(zone));
            //end initialize zone domain

            //do lazy get information
            zone.setZoneAuthority(new ZoneAuthorityConfig().get(zone));
            zone.setZoneRecords(new ZoneRecordConfig().list(zone));

            return zone;
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public List<ZoneBean> list() {
        List<ZoneBean> list = new ArrayList<ZoneBean>();
        int cLine = ZoneOperation.countLine(path);
        for (int i = 1; i < cLine; i++) {
            ZoneBean zone = this.getById(i);
            if (zone != null) {
                list.add(zone);
            }
        }
        return list;
    }

    public ZoneBean getByDomain(String domain) {
        List<ZoneBean> list = list();
        ZoneBean foundZone = null;
        for (Iterator<ZoneBean> it = list.iterator(); it.hasNext();) {
            ZoneBean zoneBean = it.next();
            if (zoneBean.getDomain().equals(domain)) {
                foundZone = zoneBean;
            }
        }

        return foundZone;
    }

    public String getTTL(ZoneBean zone) {
        String line = ZoneOperation.readByLine(zone.getFile(), 1);
        String[] split = line.split("\\s+");
        return split[split.length - 1];
    }
}
