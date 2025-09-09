package ru.ntc.csir.squid.squidstatistic.model;

import jakarta.persistence.*;

import java.net.InetAddress;
import java.time.Instant;

@Entity
@Table(name = "access")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "node_id")
    private Short nodeId;

    @Column(name = "datetime")
    private Instant datetime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "client_address")
    private InetAddress clientAddress;

    @Column(name = "result_code_id")
    private Short resultCodeId;

    @Column(name = "result_code_val")
    private Short resultCodeVal;

    @Column(name = "size")
    private Integer size;

    @Column(name = "request_method_id")
    private Short requestMethodId;

    @Column(name = "url_name", length = Integer.MAX_VALUE)
    private String urlName;

    @Column(name = "url_port")
    private Integer urlPort;

    @Column(name = "\"user\"", length = Integer.MAX_VALUE)
    private String user;

    @Column(name = "hierarchy_code_id")
    private Short hierarchyCodeId;

    @Column(name = "hierarchy_code_val", length = Integer.MAX_VALUE)
    private String hierarchyCodeVal;

    @Column(name = "type_id")
    private Short typeId;

    public Access() {
    }

    public Access(Short nodeId, Instant datetime, Integer duration, InetAddress clientAddress, Short resultCodeId, Short resultCodeVal, Integer size, Short requestMethodId, String urlName, Integer urlPort, String user, Short hierarchyCodeId, String hierarchyCodeVal, Short typeId) {
        this.nodeId = nodeId;
        this.datetime = datetime;
        this.duration = duration;
        this.clientAddress = clientAddress;
        this.resultCodeId = resultCodeId;
        this.resultCodeVal = resultCodeVal;
        this.size = size;
        this.requestMethodId = requestMethodId;
        this.urlName = urlName;
        this.urlPort = urlPort;
        this.user = user;
        this.hierarchyCodeId = hierarchyCodeId;
        this.hierarchyCodeVal = hierarchyCodeVal;
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNodeId() {
        return nodeId;
    }

    public void setNodeId(Short nodeId) {
        this.nodeId = nodeId;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Short getResultCodeId() {
        return resultCodeId;
    }

    public void setResultCodeId(Short resultCodeId) {
        this.resultCodeId = resultCodeId;
    }

    public Short getResultCodeVal() {
        return resultCodeVal;
    }

    public void setResultCodeVal(Short resultCodeVal) {
        this.resultCodeVal = resultCodeVal;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Short getRequestMethodId() {
        return requestMethodId;
    }

    public void setRequestMethodId(Short requestMethodId) {
        this.requestMethodId = requestMethodId;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public Integer getUrlPort() {
        return urlPort;
    }

    public void setUrlPort(Integer urlPort) {
        this.urlPort = urlPort;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Short getHierarchyCodeId() {
        return hierarchyCodeId;
    }

    public void setHierarchyCodeId(Short hierarchyCodeId) {
        this.hierarchyCodeId = hierarchyCodeId;
    }

    public String getHierarchyCodeVal() {
        return hierarchyCodeVal;
    }

    public void setHierarchyCodeVal(String hierarchyCodeVal) {
        this.hierarchyCodeVal = hierarchyCodeVal;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

}