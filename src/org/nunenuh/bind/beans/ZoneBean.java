/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.beans;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nunenuh
 */
public class ZoneBean {
    
    /**
     * 
     */
    private int id;
    
    /**
     * 
     */
    private String ttl;
    
    /**
     * 
     */
    
    private String domain;
    
    /**
     * 
     */
    private String type;
    
    /**
     * 
     */
    private String masters;
    
    /**
     * 
     */
    private String file;
    
    /**
     * 
     */
    private ZoneAuthorityBean zoneAuthority;
    
    /**
     * 
     */
    private Set<ZoneRecordBean> zoneRecords = new HashSet<ZoneRecordBean>(0);

    public ZoneBean() {
    }

    public ZoneBean(int id, String ttl, String domain, String type, String masters, String file, ZoneAuthorityBean zoneAuthority) {
        this.id = id;
        this.ttl = ttl;
        this.domain = domain;
        this.type = type;
        this.masters = masters;
        this.file = file;
        this.zoneAuthority = zoneAuthority;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMasters() {
        return masters;
    }

    public void setMasters(String masters) {
        this.masters = masters;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZoneAuthorityBean getZoneAuthority() {
        return zoneAuthority;
    }

    public void setZoneAuthority(ZoneAuthorityBean zoneAuthority) {
        this.zoneAuthority = zoneAuthority;
    }

    public Set<ZoneRecordBean> getZoneRecords() {
        return zoneRecords;
    }

    public void setZoneRecords(Set<ZoneRecordBean> zoneRecords) {
        this.zoneRecords = zoneRecords;
    }
}
