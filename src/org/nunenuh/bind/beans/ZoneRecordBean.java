/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.beans;

/**
 *
 * @author nunenuh
 */
public class ZoneRecordBean {

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
     * clazz is an encoded 16 bit value that identifies a protocol family or
     * instance of a protocol.
     *
     */
    private String clazz = "IN";
    /**
     * type is an encoded 16 bit value that specifies the type of the resource
     * in this resource record. Types refer to abstract resources
     */
    private String type;
    /**
     *
     */
    private Long priority;
    /**
     * recordData is the type and sometimes class-dependent data that describes
     * the resource.
     */
    private String recordData;

    public ZoneRecordBean() {
    }

    public ZoneRecordBean(int id, ZoneBean zone, String name, String type, long priority, String recordData) {
        this.id = id;
        this.zone = zone;
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.recordData = recordData;
    }

    public ZoneRecordBean(int id, ZoneBean zone, String name, String type, String recordData) {
        this.id = id;
        this.zone = zone;
        this.name = name;
        this.type = type;
        this.recordData = recordData;
    }

    public ZoneRecordBean(ZoneBean zone, String name, String type, Long priority, String recordData) {
        this.zone = zone;
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.recordData = recordData;
    }

    public ZoneRecordBean(ZoneBean zone, String name, String type, String recordData) {
        this.zone = zone;
        this.name = name;
        this.type = type;
        this.recordData = recordData;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZoneBean getZone() {
        return zone;
    }

    public void setZone(ZoneBean zone) {
        this.zone = zone;
    }
}
