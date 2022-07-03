import java.sql.Date;

public class Amicizia {
    private String Utente1;
    private String Utente2;
    private Date Data_amicizia;

    public Amicizia() {
        Utente1 = "";
        Utente2 = "";
        Data_amicizia = null;
    }

    public String getUtente1() {
        return Utente1;
    }

    public void setUtente1(String utente1) {
        Utente1 = utente1;
    }

    public String getUtente2() {
        return Utente2;
    }

    public void setUtente2(String utente2) {
        Utente2 = utente2;
    }

    public Date getData_amicizia() {
        return Data_amicizia;
    }

    public void setData_amicizia(Date data_amicizia) {
        Data_amicizia = data_amicizia;
    }

}
