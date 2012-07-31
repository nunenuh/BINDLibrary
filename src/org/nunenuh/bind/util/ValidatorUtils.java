/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nunenuh
 */
public class ValidatorUtils {

    /**
     *
     */
    private static final String IPV4PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    /**
     *
     */
    private static final String IPV6PATTERN = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
    /**
     *
     */
    private static final String DOMAIN_PATTERN = "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     *
     * @param ipv4
     * @return
     */
    public static boolean isValidIPv4(String ipv4) {
        return Pattern.compile(IPV4PATTERN).matcher(ipv4).matches();
    }

    /**
     *
     * @param ipv6
     * @return
     */
    public static boolean isValidIPv6(String ipv6) {
        return Pattern.compile(IPV6PATTERN).matcher(ipv6).matches();
    }

    /**
     *
     * @param domain
     * @return
     */
    public static boolean isValidDomainName(String domain) {
        return Pattern.compile(DOMAIN_PATTERN).matcher(domain).matches();
    }
}
