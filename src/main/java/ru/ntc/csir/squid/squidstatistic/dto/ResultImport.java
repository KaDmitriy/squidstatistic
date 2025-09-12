package ru.ntc.csir.squid.squidstatistic.dto;

public class ResultImport {

    private Integer countRow;
    private Integer sizefile;
    private Integer addrows;

    public ResultImport(Integer countRow, Integer sizefile, Integer addrows) {
        this.countRow = countRow;
        this.sizefile = sizefile;
        this.addrows = addrows;
    }

    public Integer getCountRow() {
        return countRow;
    }

    public void setCountRow(Integer countRow) {
        this.countRow = countRow;
    }

    public Integer getSizefile() {
        return sizefile;
    }

    public void setSizefile(Integer sizefile) {
        this.sizefile = sizefile;
    }

    public Integer getAddrows() {
        return addrows;
    }

    public void setAddrows(Integer addrows) {
        this.addrows = addrows;
    }
}
