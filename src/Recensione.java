import java.sql.Date;

public class Recensione {
    private int Gioco;
    private String Utente;
    private byte Stelle;
    private String Titolo;
    private String Testo;
    private Date Data_Recrensione;

    public Recensione() {
        this.Gioco = 0;
        this.Utente = "";
        this.Stelle = 0;
        this.Titolo = "";
        this.Testo = "";
        this.Data_Recrensione = null;
    }

    public int getGioco() {
        return Gioco;
    }

    public void setGioco(int gioco) {
        Gioco = gioco;
    }

    public String getUtente() {
        return Utente;
    }

    public void setUtente(String utente) {
        Utente = utente;
    }

    public byte getStelle() {
        return Stelle;
    }

    public void setStelle(byte stelle) {
        Stelle = stelle;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public String getTesto() {
        return Testo;
    }

    public void setTesto(String testo) {
        Testo = testo;
    }

    public Date getData_Recrensione() {
        return Data_Recrensione;
    }

    public void setData_Recrensione(Date data_Recrensione) {
        Data_Recrensione = data_Recrensione;
    }

}
