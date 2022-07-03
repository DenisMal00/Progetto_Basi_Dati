public class Dato_Confidenziale {
    private String Utente;
    private String Pwd;
    private String Numero_Carta;
    private String Scadenza;
    private String Cvv;

    public Dato_Confidenziale() {
        Utente = "";
        Pwd = "";
        Numero_Carta = "";
        Scadenza = "";
        Cvv = "";
    }

    public String getUtente() {
        return Utente;
    }

    public void setUtente(String utente) {
        Utente = utente;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getNumero_Carta() {
        return Numero_Carta;
    }

    public void setNumero_Carta(String  numero_Carta) {
        Numero_Carta = numero_Carta;
    }

    public String getScadenza() {
        return Scadenza;
    }

    public void setScadenza(String scadenza) {
        Scadenza = scadenza;
    }

    public String getCvv() {
        return Cvv;
    }

    public void setCvv(String cvv) {
        Cvv = cvv;
    }
}
