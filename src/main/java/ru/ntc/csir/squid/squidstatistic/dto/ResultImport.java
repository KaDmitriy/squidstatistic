package ru.ntc.csir.squid.squidstatistic.dto;

public class ResultImport {

    private Integer countRow;
    private Integer countProcessed;

    public ResultImport(Integer countRow, Integer countProcessed) {
        this.countRow = countRow;
        this.countProcessed = countProcessed;
    }

    public Integer getCountRow() {
        return countRow;
    }

    public void setCountRow(Integer countRow) {
        this.countRow = countRow;
    }

    public Integer getCountProcessed() {
        return countProcessed;
    }

    public void setCountProcessed(Integer countProcessed) {
        this.countProcessed = countProcessed;
    }
}
