package ru.ntc.csir.squid.squidstatistic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "request_method")
public class RequestMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short id;

    @Column(name = "name", nullable = false)
    private String name;

    public RequestMethod() {
    }

    public RequestMethod(String name) {
        this.name = name;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}