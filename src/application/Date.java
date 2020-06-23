package application;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Jour */
    private String dd;
    
    /* Mois */
    private String mm;
    
    /* Année */
    private String yyyy;

    public Date(String day, String month, String year) {
        this.dd = day;
        this.mm = month;
        this.yyyy = year;
    }
    
    public static Date dateAujourdhui() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        //System.out.println(date);
        Date dateJour = new Date(date.substring(0, 2), date.substring(3, 5), date.substring(6, 10));
        return dateJour;
    }

    @Override
    public String toString() {
        return dd + "/" + mm + "/" + yyyy;
    }

    public String getDd() {
        return this.dd;
    }

    public String getMm() {
        return this.mm;
    }

    public String getYyyy() {
        return this.yyyy;
    }
}
