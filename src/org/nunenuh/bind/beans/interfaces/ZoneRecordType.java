/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.beans.interfaces;

/**
 * 
 * @author nunenuh nunenuh(at)gmail.com
 * 
 * This class for get types of valid RRs
 */
public interface ZoneRecordType {
    
    /**
     * a host address.
     */
    public static final String A = "A";
    
    /**
     * 128-bit IP address (IP version 6).
     */
    public static final String AAAA = "AAAA";
    
    /**
     * Can contain up to three fields :
     * prefix length, part of an IP version 6 address, and prefix name.
     */
    public static final String A6 = "A6";
    
    /**
     * identifies the canonical name of an alias.
     */
    public static final String CNAME = "CNAME";
    
    /**
     * for delegation of reverse addresses. Replaces the domain name
     * specified with another name to be looked up. Described in RFC2672
     */
    public static final String DNAME = "DNAME";
    
    /**
     * identifies the CPU and OS used by a host.
     */
    public static final String HINFO = "HINFO";
    
    /**
     * identifies a mail exchange for the domain. See RFC 974 for details.
     */
    public static final String MX = "MX";
    
    /**
     * name authority pointer.
     */
    public static final String NAPTR = "NAPTR";
    
    /**
     * a network service access point.
     */
    public static final String NSAP = "NSAP";
    
    /**
     * the authoritative nameserver for the domain.
     */
    public static final String NS = "NS";
    
    /**
     * used in DNSSEC to securely indicate that RRs with an owner
     * name in a certain name interval do not exist in a zone and
     * indicate what RR types are present for an existing name. 
     * SeeRFC 2535 for details.
     */
    public static final String NXT = "NXT";
    
    /**
     * a pointer to another part of the domain name space.
     */
    public static final String PTR = "PTR";
    
    /**
     * identifies the start of a zone of authority.
     */
    public static final String SOA = "SOA";
    
    /**
     * text records.
     */
    public static final String TXT = "TXT";
    
}
