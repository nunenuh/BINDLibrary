/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.helper;

import org.nunenuh.bind.beans.ZoneRecordBean;
import org.nunenuh.bind.beans.interfaces.ZoneRecordType;
import org.nunenuh.bind.util.ZoneOperation;

/**
 *
 * @author nunenuh
 */
public final class ZoneRecordHelper {

    private boolean recordMatch = false;
    private int recordLineNumber = 0;
    private boolean lineOfSOA = false;
    private ZoneRecordBean zoneRecord;

    public ZoneRecordHelper(ZoneRecordBean zoneRecord) {
        this.zoneRecord = zoneRecord;
        this.doRecordMatchLineNumber();
    }

    public boolean isRecordMatch() {
        return recordMatch;
    }

    public int getRecordLineNumber() {
        return recordLineNumber;
    }

    public String recordLineBuilder() {
        String ttl = zoneRecord.getZone().getTtl();
        StringBuilder sb = new StringBuilder();
        sb.append(zoneRecord.getName());

        if (zoneRecord.getName().contains(".")
                && !zoneRecord.getType().equals(ZoneRecordType.PTR)) {
            sb.append(".").append("\t");
        } else {
            sb.append("\t");
        }

        sb.append(ttl).append("\t");
        sb.append(zoneRecord.getClazz()).append("\t");
        sb.append(zoneRecord.getType());

        if (zoneRecord.getType().equals(ZoneRecordType.MX)) {
            sb.append(" ").append(zoneRecord.getPriority()).append("\t");
        } else {
            sb.append("\t");
        }

        sb.append(zoneRecord.getRecordData());
        if (zoneRecord.getRecordData().contains(".")
                && !zoneRecord.getType().equals(ZoneRecordType.A)
                && !zoneRecord.getType().equals(ZoneRecordType.AAAA)
                && !zoneRecord.getType().equals(ZoneRecordType.A6)) {
            sb.append(".");
        }

        return sb.toString();
    }

    public void doRecordMatchLineNumber() {
        String path = zoneRecord.getZone().getFile();
        String ttl = zoneRecord.getZone().getTtl();
        int cLine = ZoneOperation.countLine(path);
        int exLoc = 0;
        for (int i = 1; i < cLine; i++) {
            String line = ZoneOperation.readByLine(path, i);
            if (line.startsWith(zoneRecord.getName()) && !line.contains(ZoneRecordType.SOA)) { //check names and exclude TYPE SOA
                String[] split = line.split("\\s+");
                if (split[0].startsWith(zoneRecord.getName())) { //check "names"
                    if (split[1].equals(ttl)) { //check "TTL"
                        if (split[2].equals(zoneRecord.getClazz())) { //check clazz "IN"
                            if (split[3].equals(zoneRecord.getType())) { //check "TYPE"
                                if (!split[3].equals(ZoneRecordType.MX)) { //check if "TYPE" is NOT MX
                                    if (split[4].equals(zoneRecord.getRecordData())) { //check RECORD not with dot end "."
                                        recordLineNumber = i;
                                        recordMatch = true;
                                    }
                                    if (split[4].equals(zoneRecord.getRecordData() + ".")) { //check RECORD with dot end "."
                                        recordLineNumber = i;
                                        recordMatch = true;
                                    }
                                } else { //check if "TYPE" is MX
                                    if (split[4].equals(zoneRecord.getPriority())) { //check "PRIORITY"
                                        if (split[5].equals(zoneRecord.getRecordData())) { //check RECORD not with dot end "."
                                            recordLineNumber = i;
                                            recordMatch = true;
                                        }
                                        if (split[5].equals(zoneRecord.getRecordData() + ".")) { //check RECORD with dot end "."
                                            recordLineNumber = i;
                                            recordMatch = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } //end looping for
    }
}
