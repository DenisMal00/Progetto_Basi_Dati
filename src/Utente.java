import java.util.Date;

public class Utente {
    private String Nickname;
    private String Nome;
    private String Cognome;
    private Date Data_Nascita;
    private String Mail;
    private String Telefono;
    private String indizzo;
    private String citta;
    private String cap;
    private String provincia;

    public Utente() {
        this.Nickname = "";
        this.Nome = "";
        this.Cognome = "";
        this.Data_Nascita=null;
        this.Mail = "";
        this.Telefono = "";
        this.indizzo = "";
        this.citta = "";
        this.cap = "";
        this.provincia = "";
    }

    public Date getData_Nascita() {
        return Data_Nascita;
    }

    public void setData_Nascita(Date data_Nascita) {
        Data_Nascita = data_Nascita;
    }
    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String  telefono) {
        Telefono = telefono;
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
