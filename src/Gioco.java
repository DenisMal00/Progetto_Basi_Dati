import java.sql.Date;

public class Gioco {
    private int id_Gioco;
    private String titolo;
    private String Dimensioni;
    private byte Eta_Minima;
    private String Descrizione;
    private float prezzo;
    private Date Data_Pubb;
    private String Sviluppatore;

    public Gioco() {
        this.id_Gioco=0;
        this.titolo = "";
        this.Dimensioni = "";
        this.Eta_Minima = 0;
        this.Descrizione = "";
        this.prezzo = 0;
        this.Data_Pubb = null;
        this.Sviluppatore = "";
    }

    public int getId_Gioco() {
        return id_Gioco;
    }

    public void setId_Gioco(int id_Gioco) {
        this.id_Gioco = id_Gioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDimensioni() {
        return Dimensioni;
    }

    public void setDimensioni(String dimensioni) {
        Dimensioni = dimensioni;
    }

    public byte getEta_Minima() {
        return Eta_Minima;
    }

    public void setEta_Minima(byte eta_Minima) {
        Eta_Minima = eta_Minima;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Date getData_Pubb() {
        return Data_Pubb;
    }

    public void setData_Pubb(Date data_Pubb) {
        Data_Pubb = data_Pubb;
    }

    public String getSviluppatore() {
        return Sviluppatore;
    }

    public void setSviluppatore(String sviluppatore) {
        Sviluppatore = sviluppatore;
    }

}
