public class Sviluppatore {
    private String  P_Iva;
    private String nome;
    private String Sito_Web;
    private String telefono;
    private String mail;
    private String indizzo;
    private String citta;
    private String cap;
    private String provincia;

    public Sviluppatore() {
        this.P_Iva = "";
        this.nome = "";
        this.Sito_Web = "";
        this.telefono = "";
        this.mail = "";
        this.indizzo = "";
        this.citta = "";
        this.cap = "";
        this.provincia = "";
    }

    public String getP_Iva() {
        return P_Iva;
    }

    public void setP_Iva(String p_Iva) {
        P_Iva = p_Iva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSito_Web() {
        return Sito_Web;
    }

    public void setSito_Web(String sito_Web) {
        Sito_Web = sito_Web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String  telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIndizzo() {
        return indizzo;
    }

    public void setIndizzo(String indizzo) {
        this.indizzo = indizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}
