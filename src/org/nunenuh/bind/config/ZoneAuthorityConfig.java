/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.config;

import org.nunenuh.bind.beans.ZoneAuthorityBean;
import org.nunenuh.bind.beans.ZoneBean;
import org.nunenuh.bind.helper.ZoneAuthorityHelper;
import org.nunenuh.bind.util.ZoneOperation;

/**
 *
 * @author nunenuh
 */
public class ZoneAuthorityConfig {

    public void set(ZoneAuthorityBean zoneAuthority) {
        String path = zoneAuthority.getZone().getFile();
        //preparing standard format of SOA
        ZoneAuthorityHelper zah = new ZoneAuthorityHelper(zoneAuthority);
        String soaLine = zah.SOALine();
        boolean match = zah.isMatch();
        
        //find SOA line number
        int cLine = ZoneOperation.countLine(path);
        int exLoc = 0;
        for (int i = 1; i < cLine; i++) {
            String line = ZoneOperation.readByLine(path, i);
            if (line.contains("SOA")) {
                exLoc = i;
                break;
            }
        }

        if (exLoc == 0) {
            ZoneOperation.insertByLine(path, "", 1);
            ZoneOperation.insertByLine(path, soaLine, 2);
            ZoneOperation.removeByLine(path, 9);
        } else {
            if (match == false) {
                ZoneOperation.removeLine(path, exLoc, exLoc + 6);
                ZoneOperation.insertByLine(path, "", exLoc - 1);
                ZoneOperation.insertByLine(path, soaLine, exLoc);
                ZoneOperation.removeByLine(path, exLoc + 7);
            }

        }

    }

    public ZoneAuthorityBean get(ZoneBean zone) {
        String path = zone.getFile();

        int cLine = ZoneOperation.countLine(path);
        int exLoc = 0;
        for (int i = 1; i < cLine; i++) {
            String line = ZoneOperation.readByLine(path, i);
            if (line.contains("SOA")) {
                exLoc = i;
                break;
            }
        }

        String lineGeneral = ZoneOperation.readByLine(path, exLoc);
        String lineSerial = ZoneOperation.readByLine(path, exLoc + 1);
        String lineRefresh = ZoneOperation.readByLine(path, exLoc + 2);
        String lineRetry = ZoneOperation.readByLine(path, exLoc + 3);
        String lineExpire = ZoneOperation.readByLine(path, exLoc + 4);
        String lineCache = ZoneOperation.readByLine(path, exLoc + 5);

        String[] generalSplit = lineGeneral.split("\\s+");
        String name = generalSplit[0].substring(0, generalSplit[0].length() - 1);
        String ttl = generalSplit[1];
        String clazz = generalSplit[2];
        String nameServer = generalSplit[4].substring(0, generalSplit[4].length() - 1);
        String emailAddress = generalSplit[5].substring(0, generalSplit[5].length() - 1);
        String serial = lineSerial.split("\\s+")[1];
        String refresh = lineRefresh.split("\\s+")[1];
        String retry = lineRetry.split("\\s+")[1];
        String expire = lineExpire.split("\\s+")[1];
        String cache = lineCache.split("\\s+")[1];


        ZoneAuthorityBean zab = new ZoneAuthorityBean();
        zab.setName(name);
        zab.setNameServer(nameServer);
        zab.setEmailAddress(emailAddress);
        zab.setSerialNumber(Long.valueOf(serial));
        zab.setRefresh(Long.valueOf(refresh));
        zab.setRetry(Long.valueOf(retry));
        zab.setExpire(Long.valueOf(expire));
        zab.setMinimumCacheTTL(Long.valueOf(cache));
        return zab;
    }
}
