import java.sql.Date;

public class DatoGioco {
    private int Gioco;
    private String Utente;
    private Date Data_Acquisto;
    private float Ore_Gioco;

    public DatoGioco() {
        Gioco = 0;
        Utente = "";
        Data_Acquisto = null;
        Ore_Gioco = 0;
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

    public Date getData_Acquisto() {
        return Data_Acquisto;
    }

    public void setData_Acquisto(Date data_Acquisto) {
        Data_Acquisto = data_Acquisto;
    }

    public float getOre_Gioco() {
        return Ore_Gioco;
    }

    public void setOre_Gioco(float ore_Gioco) {
        Ore_Gioco = ore_Gioco;
    }

}
