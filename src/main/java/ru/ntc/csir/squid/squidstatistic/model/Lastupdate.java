package ru.ntc.csir.squid.squidstatistic.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "lastupdate")
public class Lastupdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "node")
    private Short node;

    @Column(name = "date_update")
    private Instant dateUpdate;

    @Column(name = "size_file")
    private Long sizeFile;

    @Column(name = "add_row")
    private Integer addRow;

    @Column(name = "duration")
    private Integer duration;

    public Lastupdate() {
    }

    public Lastupdate(Short node, Instant dateUpdate, Long sizeFile, Integer addRow, Integer duration) {
        this.node = node;
        this.dateUpdate = dateUpdate;
        this.sizeFile = sizeFile;
        this.addRow = addRow;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNode() {
        return node;
    }

    public void setNode(Short node) {
        this.node = node;
    }

    public Instant getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(Long sizeFile) {
        this.sizeFile = sizeFile;
    }

    public Integer getAddRow() {
        return addRow;
    }

    public void setAddRow(Integer addRow) {
        this.addRow = addRow;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}