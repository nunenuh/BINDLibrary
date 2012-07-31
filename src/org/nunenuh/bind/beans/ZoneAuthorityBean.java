/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.beans;

/**
 *
 * @author nunenuh
 */
public class ZoneAuthorityBean {

    /**
     *
     */
    private int id;
    /**
     *
     */
    private ZoneBean zone;
    /**
     * name is the domain name where the RR is found.
     */
    private String name;
    /**
     * nameServer is the name of the primary name server
     */
    private String nameServer;
    /**
     * emailAddress defines the mailing address of the person responsible for
     * the data of zone. emailAddress must be connect by "." example :
     * admin@example.com to be admin.example.com
     */
    private String emailAddress;
    /**
     * yyyymmddss
     */
    private long serialNumber;
    /**
     * refresh states how often secondary servers should check their data. If
     * they discover during this check that they have data with a lower serial,
     * they will carry out a zone transfer using the TCP protocol.
     */
    private long refresh;
    /**
     * retry states that if the secondary server cannot contact the primary
     * server at the end of the refresh interval, it will keep trying to do so
     * every x seconds (x=retry interval).
     */
    private long retry;
    /**
     * expire states that if the secondary server does not manage to contact the
     * primary server within y seconds (y=expire interval), it will stop
     * providing information (the data is too old). The rule Expire >
     * Refreshmust be observed.
     */
    private long expire;
    /**
     * minimumCachTTL applies to all records in the database file (as a
     * defaultvalue), and the name server provides this value in each answer. It
     * askshow long the other servers (nonauthoritative servers) can keep the
     * particular record in their cache memory (zero prevents the records
     * frombeing saved into the cache).
     */
    private long minimumCacheTTL;

    public ZoneAuthorityBean() {
    }

    public ZoneAuthorityBean(int id, ZoneBean zone, String name, String nameServer, String emailAddress, long serialNumber, long refresh, long retry, long expire, long minimumCacheTTL) {
        this.id = id;
        this.zone = zone;
        this.name = name;
        this.nameServer = nameServer;
        this.emailAddress = emailAddress;
        this.serialNumber = serialNumber;
        this.refresh = refresh;
        this.retry = retry;
        this.expire = expire;
        this.minimumCacheTTL = minimumCacheTTL;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMinimumCacheTTL() {
        return minimumCacheTTL;
    }

    public void setMinimumCacheTTL(long minimumCacheTTL) {
        this.minimumCacheTTL = minimumCacheTTL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public long getRefresh() {
        return refresh;
    }

    public void setRefresh(long refresh) {
        this.refresh = refresh;
    }

    public long getRetry() {
        return retry;
    }

    public void setRetry(long retry) {
        this.retry = retry;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ZoneBean getZone() {
        return zone;
    }

    public void setZone(ZoneBean zone) {
        this.zone = zone;
    }
}