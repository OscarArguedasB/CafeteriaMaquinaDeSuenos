
package model;

public class YearGender {

    private int year;
    private String genero;
    private int total;

    public YearGender() {
    }
    
    
    public YearGender(int year, String genero, int total) {
        this.year = year;
        this.genero = genero;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    

}
