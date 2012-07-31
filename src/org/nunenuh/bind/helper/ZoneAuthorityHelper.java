/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.helper;

import org.nunenuh.bind.beans.ZoneAuthorityBean;
import org.nunenuh.bind.util.ZoneOperation;

/**
 *
 * @author nunenuh
 */
public class ZoneAuthorityHelper {

    /**
     *
     */
    private ZoneAuthorityBean zoneAuthority;
    /**
     *
     */
    private boolean match = false;

    /**
     *
     * @param zoneAuthority
     */
    public ZoneAuthorityHelper(ZoneAuthorityBean zoneAuthority) {
        this.zoneAuthority = zoneAuthority;
        doSOAMatcher();
    }

    /**
     *
     * @return
     */
    public boolean isMatch() {
        return match;
    }

    /**
     *
     * @return
     */
    public String SOALine() {
        StringBuilder sb = new StringBuilder();
        if (zoneAuthority.getName().contains(".")) {
            sb.append(zoneAuthority.getName()).append(".").append("\t");
        } else {
            sb.append(zoneAuthority.getName()).append("\t");
        }

        sb.append(zoneAuthority.getZone().getTtl()).append("\t");
        sb.append("IN").append("\t");
        sb.append("SOA").append("\t");
        sb.append(zoneAuthority.getNameServer()).append(". ");
        sb.append(zoneAuthority.getEmailAddress()).append(". (").append("\n");
        sb.append("\t\t\t\t\t").append(zoneAuthority.getSerialNumber()).append("\t\t; serial").append("\n");
        sb.append("\t\t\t\t\t").append(zoneAuthority.getRefresh()).append("\t\t; refresh").append("\n");
        sb.append("\t\t\t\t\t").append(zoneAuthority.getRetry()).append("\t\t; retry").append("\n");
        sb.append("\t\t\t\t\t").append(zoneAuthority.getExpire()).append("\t\t; expire").append("\n");
        sb.append("\t\t\t\t\t").append(zoneAuthority.getMinimumCacheTTL()).append(" )").append("\t\t; minimumCacheTTL").append("\n");
        sb.append(";").append("\n");

        return sb.toString();
    }

    private void doSOAMatcher() {
        String path = zoneAuthority.getZone().getFile();

        int cLine = ZoneOperation.countLine(path);
        int exLoc = 0;
        for (int i = 1; i < cLine; i++) {
            String line = ZoneOperation.readByLine(path, i);
            if (line.contains("SOA")) {
                exLoc = i;
                break;
            }
        }

        System.out.println(exLoc);
        if (exLoc != 0) {
            String lineGeneral = ZoneOperation.readByLine(path, exLoc);
            String lineSerial = ZoneOperation.readByLine(path, exLoc + 1);
            String lineRefresh = ZoneOperation.readByLine(path, exLoc + 2);
            String lineRetry = ZoneOperation.readByLine(path, exLoc + 3);
            String lineExpire = ZoneOperation.readByLine(path, exLoc + 4);
            String lineCache = ZoneOperation.readByLine(path, exLoc + 5);

            String[] generalSplit = lineGeneral.split("\\s+");
            String name = generalSplit[0].substring(0, generalSplit[0].length() - 1);
//        String ttl = generalSplit[1];
//        String clazz = generalSplit[2];
            String nameServer = generalSplit[4].substring(0, generalSplit[4].length() - 1);
            String emailAddress = generalSplit[5].substring(0, generalSplit[5].length() - 1);
            String serial = lineSerial.split("\\s+")[1];
            String refresh = lineRefresh.split("\\s+")[1];
            String retry = lineRetry.split("\\s+")[1];
            String expire = lineExpire.split("\\s+")[1];
            String cache = lineCache.split("\\s+")[1];

            if (name.equals(zoneAuthority.getName())) {
                if (nameServer.equals(zoneAuthority.getNameServer())) {
                    if (serial.equals(String.valueOf(zoneAuthority.getSerialNumber()))) {
                        if (refresh.equals(String.valueOf(zoneAuthority.getRefresh()))) {
                            if (retry.equals(String.valueOf(zoneAuthority.getRetry()))) {
                                if (expire.equals(String.valueOf(zoneAuthority.getExpire()))) {
                                    if (cache.equals(String.valueOf(zoneAuthority.getMinimumCacheTTL()))) {
                                        match = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
