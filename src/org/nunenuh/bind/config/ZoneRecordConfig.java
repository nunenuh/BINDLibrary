/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.beans.interfaces.ZoneRecordType;
import org.nunenuh.bind.helper.ZoneRecordHelper;
import org.nunenuh.bind.util.ZoneOperation;

/**
 *
 * @author nunenuh
 */
public class ZoneRecordConfig {

    /**
     *
     * @param zoneRecord
     */
    public void save(ZoneRecordBean zoneRecord) {
        String path = zoneRecord.getZone().getFile();
        ZoneRecordHelper zru = new ZoneRecordHelper(zoneRecord);
        String recordLine = zru.recordLineBuilder();

        boolean match = zru.isRecordMatch();
        if (match == true) {
            int line = zru.getRecordLineNumber();
            ZoneOperation.replaceByLine(path, line, recordLine);
        } else {
            //insert after SOA
            ZoneAuthorityConfig zac = new ZoneAuthorityConfig();
            int idSOA = zac.get(zoneRecord.getZone()).getId();
            ZoneOperation.insertByLine(path, recordLine, (idSOA + 8));
        }
    }

    /**
     *
     * @param zoneRecord
     */
    public void update(ZoneRecordBean zoneRecord) {
        String path = zoneRecord.getZone().getFile();
        ZoneRecordHelper zru = new ZoneRecordHelper(zoneRecord);
        String recordLine = zru.recordLineBuilder();

        //replace line
        ZoneOperation.replaceByLine(path, zoneRecord.getId(), recordLine);
    }

    /*
     *
     */
    public void deleteById(ZoneRecordBean zoneRecord) {
        ZoneOperation.replaceByLine(zoneRecord.getZone().getFile(), zoneRecord.getId(), "");
    }

    /*
     *
     */
    public ZoneRecordBean getById(ZoneBean zone, int id) {
        ZoneRecordBean zrb = null;

        String path = zone.getFile();
        String line = ZoneOperation.readByLine(path, id);
        if (!line.contains(ZoneRecordType.SOA) && !line.contains(";")) { //check names and exclude TYPE SOA
            String[] split = line.split("\\s+");
            if (split.length > 4) {
                if (!split[3].equals(ZoneRecordType.MX)) { //check if "TYPE" is NOT MX
                    zrb = new ZoneRecordBean();
                    String name = split[0];
                    String ttl = split[1];
                    String clazz = split[2];
                    String type = split[3];
                    String record = split[4];


                    if (name.contains(".")) {
                        name = name.substring(0, name.length() - 1);
                    }

                    if (!(type.equals(ZoneRecordType.A)
                            || type.equals(ZoneRecordType.A6)
                            || type.equals(ZoneRecordType.AAAA))) {
                        record = record.substring(0, record.length() - 1);
                    }



                    zrb.setName(name);
                    zrb.setClazz(clazz);
                    zrb.setType(type);
                    zrb.setRecordData(record);
                    zrb.setZone(zone);
                    zrb.setId(id);
                } else { //check if "TYPE" is MX
                    zrb = new ZoneRecordBean();
                    String name = split[0];
                    String ttl = split[1];
                    String clazz = split[2];
                    String type = split[3];
                    long priority = Long.valueOf(split[4]);
                    String record = split[5];


                    if (name.contains(".")) {
                        name = name.substring(0, name.length() - 1);
                    }

                    if (record.contains(".")) {
                        record = record.substring(0, record.length() - 1);
                    }



                    zrb.setName(name);
                    zrb.setClazz(clazz);
                    zrb.setType(type);
                    zrb.setPriority(priority);
                    zrb.setRecordData(record);
                    zrb.setZone(zone);
                    zrb.setId(id);
                }
            }
        }

        return zrb;
    }

    /*
     *
     */
    public Set<ZoneRecordBean> list(ZoneBean zone) {
        Set<ZoneRecordBean> set = new HashSet<ZoneRecordBean>();
        String path = zone.getFile();

        ZoneAuthorityConfig zac = new ZoneAuthorityConfig();
        int idSOA = zac.get(zone).getId();
        int cLine = ZoneOperation.countLine(path);
        for (int i = (idSOA + 7); i < cLine; i++) {
            ZoneRecordBean zrb = getById(zone, i);
            if (zrb != null) {
                set.add(zrb);
            }
        }

        return set;
    }

    /*
     *
     */
    public Set<ZoneRecordBean> listByType(ZoneBean zone, String type) {
        Set<ZoneRecordBean> set = list(zone);
        Set<ZoneRecordBean> setByType = new HashSet<ZoneRecordBean>();

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            if (zoneRecordBean.getType().equals(type)) {
                setByType.add(zoneRecordBean);
            }
        }

        return setByType;
    }

    /*
     *
     */
    public Set<ZoneRecordBean> listByName(ZoneBean zone, String name) {
        Set<ZoneRecordBean> set = list(zone);
        Set<ZoneRecordBean> setByType = new HashSet<ZoneRecordBean>();

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            if (zoneRecordBean.getName().equals(name)) {
                setByType.add(zoneRecordBean);
            }
        }

        return setByType;
    }
    /*
     *
     */

    public Set<ZoneRecordBean> listByNameAndType(ZoneBean zone, String name, String type) {
        Set<ZoneRecordBean> set = list(zone);
        Set<ZoneRecordBean> setByType = new HashSet<ZoneRecordBean>();

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            if (zoneRecordBean.getName().equals(name) && zoneRecordBean.getType().equals(type)) {
                setByType.add(zoneRecordBean);
            }
        }

        return setByType;
    }

    /*
     *
     */
    public Set<ZoneRecordBean> listByRecordData(ZoneBean zone, String recordData) {
        Set<ZoneRecordBean> set = list(zone);
        Set<ZoneRecordBean> setByType = new HashSet<ZoneRecordBean>();

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            if (zoneRecordBean.getRecordData().equals(recordData)) {
                setByType.add(zoneRecordBean);
            }
        }

        return setByType;
    }

    /*
     *
     */
    public void deleteByName(ZoneBean zone, String name) {
        Set<ZoneRecordBean> set = listByName(zone, name);

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            deleteById(zoneRecordBean);
        }
    }

    /*
     *
     */
    public void deleteByNameAndType(ZoneBean zone, String name, String type) {
        Set<ZoneRecordBean> set = listByNameAndType(zone, name, type);

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            deleteById(zoneRecordBean);
        }
    }

    /*
     *
     */
    public void deleteByRecordData(ZoneBean zone, String record) {
        Set<ZoneRecordBean> set = listByRecordData(zone, record);

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            deleteById(zoneRecordBean);
        }
    }

    /*
     *
     */
    public void deleteByType(ZoneBean zone, String type) {
        Set<ZoneRecordBean> set = listByType(zone, type);

        for (Iterator<ZoneRecordBean> it = set.iterator(); it.hasNext();) {
            ZoneRecordBean zoneRecordBean = it.next();
            deleteById(zoneRecordBean);
        }
    }

    public void deleteByProperty(ZoneBean zone, ZoneRecordBean zoneRecord) {
        Set<ZoneRecordBean> list = list(zone);
        for (Iterator<ZoneRecordBean> it = list.iterator(); it.hasNext();) {
            ZoneRecordBean zrb = it.next();
            if (zrb.getName().equals(zoneRecord.getName())) {
                if (zrb.getType().equals(zoneRecord.getType())) {
                    if (zrb.getRecordData().equals(zoneRecord.getRecordData())) {
                         ZoneOperation.replaceByLine(zrb.getZone().getFile(), zrb.getId(), "");
                    }
                }
            }
        }
    }

    public void resetIndexPosition(ZoneBean zone) {
        ZoneOperation.removeEmptyLines(zone.getFile());
    }
}
