import java.sql.Date;
public class Versione {
    private int Gioco;
    private String Tipo;
    private String Versione;
    private String Descrizione;
    private Date Data_Versione;

    public Versione() {
        this.Gioco = 0;
        this.Tipo = "";
        this.Versione = "";
        this.Descrizione = "";
        this.Data_Versione = null;
    }

    public int getGioco() {
        return Gioco;
    }

    public void setGioco(int gioco) {
        Gioco = gioco;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getVersione() {
        return Versione;
    }

    public void setVersione(String versione) {
        Versione = versione;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public Date getData_Versione() {
        return Data_Versione;
    }

    public void setData_Versione(Date data_Versione) {
        Data_Versione = data_Versione;
    }

}
