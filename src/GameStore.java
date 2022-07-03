import org.apache.commons.lang3.RandomStringUtils;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
public class GameStore {

    private Connection conn;
    private Random rnd = new Random();


    public GameStore() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "GameStore?" + "user=root&" + "password=12345678");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    //funzioni per popolare il db


    public void inserisciUtenti(int Ripetizioni) throws SQLException {
        String[] nomi = {"Laura", "Gianni", "Luca", "Gianni", "Giuseppe", "Giovanni", "Maria", "Anna", "Antonio", "Giuseppina", "Mario", "Antonio", "Rosa", "Giuseppina", "Luigi", "Francesco", "Angela", "Giovanna", "Angelo", "Giovanna", "Angelo", "Teresa", "Vincenzo", "Lucia", "Carmela", "Pietro", "Salvatore", "Caterina", "Carlo", "Francesca", "Franco", "Domenico", "Antonietta", "Bruno", "Carla"};
        String[] cognomi = {"Rossi", "Russo", "Ferrari", "Esposito", "Bianchi", "Romano", "Colombo", "Ricci", "Marino", "Greco", "Bruno", "Gallo", "Conti", "Mancini", "Costa", "Giordano", "Rizzo", "Lombardi", "Moretti", "Barbieri", "Fontana", "Santoro", "Mariani", "Rinaldi", "Caruso", "Ferrara", "Galli", "Martini", "Leone", "Longo"};
        String[] simboli = {"00", "__", "..", "!", "?", "&", "%", "//", "##", "||", "^^", "[]", "()", "{}"};
        String[] province = {"AG", "AN", "BA", "BT", "BO", "CZ", "CT", "EN", "FM", "GE", "GO", "LI", "MC", "LU", "MS", "NA", "OR", "PA", "PD", "RG", "SA", "SV", "SS", "TA", "UD", "TS"};
        String[] citta = {"Agrigento", "Ancona", "Bari", "Barletta", "Bologna", "Catanzaro", "Catania", "Enna", "Fermo", "Genova", "Gorizia", "Livorno", "Macerata", "Lucca", "Massa", "Napoli", "Oristano", "Palermo", "Padova", "Ragusa", "Salerno", "Savona", "Sassari", "Taranto", "Udine", "Trieste"};
        String[] indirizzi = {"Via Ferri 7", "Borgo Riva 6", "Strada Barbieri 30", "Strada Zelida 848", "Rotonda Demian 9", "Strada Mauro 658", "Rotonda Lorenzo 22", "Via Samira 843", "Via Gallo 6", "Incrocio Cesidia 06", "Strada Secondo 128", "Rotonda Nathan 4", "Incrocio Costanzo 48", "Piazza Gallo 59", "Strada Carbone 3", "Rotonda Davis 9", "Rotonda Martino 484", "Contrada Montanari 73", "Borgo Maggiore 8", "Strada Rufo 963", "Rotonda Gregorio 5", "Piazza Pagano 4 ", "Piazza Timoteo 7", "Contrada Kris 30", "Via Gentile 3", "Piazza Lazzaro 128", "Via Ferretti 757", "Borgo Diana 1", "Incrocio Serse 3"};
        String numeri= "0123456789";
        RandomDate rd;
        for (int i = 0; i < Ripetizioni; i++) {
            String telefono = "";
            String cap = "";
            telefono= RandomStringUtils.random( 10, numeri );
            cap=RandomStringUtils.random(5,numeri);
            int ind = rnd.nextInt(indirizzi.length);
            int prov = rnd.nextInt(province.length);
            int simb = rnd.nextInt(simboli.length);
            int nom = rnd.nextInt(nomi.length);
            int cogn = rnd.nextInt(cognomi.length);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Utente (Nickname,Nome, Cognome,telefono,Mail,cap,citta,provincia,indirizzo,data_Nascita) VALUES (?, ?, ?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, nomi[nom] + cognomi[cogn] + simboli[simb]);
            preparedStatement.setString(2, nomi[nom]);
            preparedStatement.setString(3, cognomi[cogn]);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, nomi[nom] + cognomi[cogn] + simboli[simb] + "@gmail.com");
            preparedStatement.setString(6, cap);
            preparedStatement.setString(7, citta[prov]);
            preparedStatement.setString(8, province[prov]);
            preparedStatement.setString(9, indirizzi[ind]);
            rd= new RandomDate(LocalDate.of(1980, 1, 1), LocalDate.of(2000, 12, 31));
            preparedStatement.setDate(10,Date.valueOf(rd.nextDate()));
            preparedStatement.execute();
        }

    }
    public void inserisciVersioniDefault() throws SQLException {
        int x=0;
        Statement stmt= conn.createStatement();
        ResultSet rs;
        Date data=null;
        LocalDate data_Pubb = null;
        LocalDate data_Beta = null;
        LocalDate data_Alfa;
        String[] tipo={"alfa","beta","release"};
        rs=stmt.executeQuery("SELECT COUNT(*) as Total FROM gioco;");
        while (rs.next())
            x=rs.getInt("total");
        for (int i=0;i<x;i++){
            rs=stmt.executeQuery("SELECT Data_Pubb as Date FROM gioco where id_Gioco="+(i+1)+";");
            while (rs.next())
                data=rs.getDate("Date");

            data_Pubb=LocalDate.parse(data.toString());
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Versione (gioco,tipo, versione,descrizione,data_Versione) VALUES (?, ?, ?,?,?)");
            preparedStatement.setInt(1, i+1);
            preparedStatement.setString(2,tipo[2]);
            preparedStatement.setString(3, "1.00");
            preparedStatement.setString(4,"Versione Release");
            preparedStatement.setDate(5,Date.valueOf(data_Pubb));
            preparedStatement.execute();

            RandomDate rd;
            rd= new RandomDate(LocalDate.of(2010, 1, 1), LocalDate.of(data_Pubb.getYear(), data_Pubb.getMonth(), data_Pubb.getDayOfMonth()));
            data_Beta= rd.nextDate();
            preparedStatement.setInt(1, i+1);
            preparedStatement.setString(2,tipo[1]);
            preparedStatement.setString(3, "1.00");
            preparedStatement.setString(4,"Versione Beta");
            preparedStatement.setDate(5,Date.valueOf(data_Beta));
            preparedStatement.execute();

            rd=new RandomDate(LocalDate.of(2010, 1, 1), LocalDate.of(data_Beta.getYear(), data_Beta.getMonth(), data_Beta.getDayOfMonth()));
            data_Alfa=rd.nextDate();
            preparedStatement.setInt(1, i+1);
            preparedStatement.setString(2,tipo[0]);
            preparedStatement.setString(3, "1.00");
            preparedStatement.setString(4,"Versione Alfa");
            preparedStatement.setDate(5,Date.valueOf(data_Alfa));
            preparedStatement.execute();
        }

    }

    public void inserisciVersioni(int num) throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs;

        int n=0; //numero di giochi nel db
        LocalDate data_Aggiornamento;
        Date data_Agg = null;
        String versione = ""; //servono per incrementare la versione del giovo
        String versione2;//"
        int versione_Int;//"
        int versione_Int2;//"
        rs=stmt.executeQuery("SELECT COUNT(*) as Total FROM gioco;");
        while (rs.next())
            n=rs.getInt("total");
        for (int i=0;i<num;i++){
            int indice = rnd.nextInt(n)+1;
            rs=stmt.executeQuery("SELECT versione as vers from Versione where Gioco="+indice+";");
            while (rs.next())
                versione=rs.getString("vers");
            versione_Int=versione.charAt(0);
            versione_Int2=versione_Int+1;
            versione2=versione.replace(Character.toString((char) versione_Int),Character.toString((char) versione_Int2) ); //Converte da ASCII (int) nel carattere corrispondnenti
            rs=stmt.executeQuery("SELECT Data_Versione as Date FROM Versione where Gioco="+indice+" AND tipo='release';");
            while (rs.next())
                data_Agg=rs.getDate("Date");
            RandomDate rd;
            data_Aggiornamento=LocalDate.parse(data_Agg.toString());
            rd= new RandomDate(LocalDate.of(data_Aggiornamento.getYear(), data_Aggiornamento.getMonth(), data_Aggiornamento.getDayOfMonth()), LocalDate.of(2020,12,31));
            data_Aggiornamento= rd.nextDate();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Versione (gioco,tipo, versione,descrizione,data_Versione) VALUES (?, ?, ?,?,?)");
            preparedStatement.setInt(1, indice);
            preparedStatement.setString(2,"release");
            preparedStatement.setString(3, versione2);
            preparedStatement.setString(4,"Versione Release");
            preparedStatement.setDate(5,Date.valueOf(data_Aggiornamento));
            preparedStatement.execute();
        }
    }

    public void inserisciRecensioni(int num) throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs;
        ArrayList<String> nickname = new ArrayList<>();
        String[] recensioniBuone={"Gioco Fantastico","La migliore","APP consigliata","molto originale","ottima come passatempo","molto bella","Wow","Molto bello"};
        String[] recensioneBrutte={"Molti bug","Scriptato","pay to win","Troppi problemi","che delusione","pessimo","non giocateci","Gioco sbilanciato"};
        int n=0; // numero di giochi
        int stelle=0;
        int indiceGioco=0;
        int indiceUtente=0;
        int indiceRecensine=0;
        Date data_Gioco = null;
        LocalDate ldata_Gioco;
        rs = stmt.executeQuery("SELECT distinct utente from dato_Gioco");
        while (rs.next()) {
            nickname.add(rs.getString("utente"));
        }
        rs=stmt.executeQuery("SELECT COUNT(*) as Total FROM gioco;");
        while (rs.next())
            n=rs.getInt("total");

        for (int i=0;i<num;i++){
            indiceUtente= rnd.nextInt(nickname.size());
            while (data_Gioco == null) {
                indiceGioco= rnd.nextInt(n)+1;
                rs=stmt.executeQuery("SELECT Data_Acquisto as Date FROM dato_Gioco where Gioco="+indiceGioco+" and Utente='"+ nickname.get(indiceUtente)+"' ;");
                while (rs.next())
                    data_Gioco=rs.getDate("Date");
            }
            RandomDate rd;
            ldata_Gioco=LocalDate.parse(data_Gioco.toString());
            rd= new RandomDate(LocalDate.of(ldata_Gioco.getYear(), ldata_Gioco.getMonth(), ldata_Gioco.getDayOfMonth()), LocalDate.of(2020,12,31));
            ldata_Gioco= rd.nextDate();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Recensione (gioco,utente, stelle,titolo,data_Recensione) VALUES (?, ?, ?,?,?)");
            preparedStatement.setInt(1, indiceGioco);
            preparedStatement.setString(2,nickname.get(indiceUtente));
            preparedStatement.setDate(5,Date.valueOf(ldata_Gioco));
            stelle=rnd.nextInt(5)+1;
            indiceRecensine= rnd.nextInt(8);
            preparedStatement.setInt(3,stelle);
            if(stelle<3)
                preparedStatement.setString(4,recensioneBrutte[indiceRecensine]);
            else
                preparedStatement.setString(4,recensioniBuone[indiceRecensine]);
            preparedStatement.execute();
        }

    }

    public void inserisciAmicizie(int num) throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs;
        int indiceUtente1=0;
        int indiceUtente2=0;
        ArrayList<String> nickname = new ArrayList<>();
        LocalDate data_Amicizia;
        rs = stmt.executeQuery("SELECT nickname from utente");
        while (rs.next()) {
            nickname.add(rs.getString("nickname"));
        }
        for (int i=0;i<num;i++){
            indiceUtente1=rnd.nextInt(nickname.size());
            do {
                indiceUtente2= rnd.nextInt(nickname.size());
            }while(indiceUtente1==indiceUtente2);
            RandomDate rd;
            rd= new RandomDate(LocalDate.of(2010, 1, 1), LocalDate.of(2020,12,31));
            data_Amicizia= rd.nextDate();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO amicizia (utente1,utente2, data_Amicizia) VALUES ( ?,?,?)");
            preparedStatement.setString(1,nickname.get(indiceUtente1));
            preparedStatement.setString(2, nickname.get(indiceUtente2));
            preparedStatement.setDate(3,Date.valueOf(data_Amicizia));
            preparedStatement.execute();
        }
    }

    public void inserisciDatiGioco(int num) throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs;
        int indiceUtente=0;
        int indiceGioco=0;
        int n=0;  //numero di giochi
        DecimalFormat df = new DecimalFormat("##,#");
        df.setRoundingMode(RoundingMode.DOWN); //non arrotonda il numero
        Date dataUscitaGioco = null;
        LocalDate ldataUscitaGioco;
        ArrayList<String> nickname = new ArrayList<>();
        rs = stmt.executeQuery("SELECT nickname from utente");
        while (rs.next()) {
            nickname.add(rs.getString("nickname"));
        }
        rs=stmt.executeQuery("SELECT COUNT(*) as Total FROM gioco;");
        while (rs.next())
            n=rs.getInt("total");
        for (int i=0;i<num;i++){
            indiceUtente= rnd.nextInt(nickname.size());
            indiceGioco= rnd.nextInt(n)+1;
            rs=stmt.executeQuery("SELECT Data_Pubb as Date FROM Gioco where id_Gioco="+indiceGioco+" ;");
            while (rs.next())
                dataUscitaGioco=rs.getDate("Date");
            RandomDate rd;
            ldataUscitaGioco=LocalDate.parse(dataUscitaGioco.toString());
            rd= new RandomDate(LocalDate.of(ldataUscitaGioco.getYear(), ldataUscitaGioco.getMonth(), ldataUscitaGioco.getDayOfMonth()), LocalDate.of(2020,12,31));
            ldataUscitaGioco= rd.nextDate();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dato_Gioco (gioco,utente, data_Acquisto,Ore_Gioco) VALUES (?, ?,?,?)");
            preparedStatement.setInt(1, indiceGioco);
            preparedStatement.setString(2,nickname.get(indiceUtente));
            preparedStatement.setDate(3,Date.valueOf(ldataUscitaGioco));
            preparedStatement.setDouble(4, Double.parseDouble((df.format(rnd.nextDouble(100)))));
            preparedStatement.execute();
        }

    }

    public void inserisciDatiPrivati() throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs;
        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String pwd;
        String pwdCripted;
        String numeri= "0123456789";
        String numcarta;
        String numcartaCritt;
        LocalDate scadenzaProva;
        String scadenza="";
        String CvvCript="";
        CriptData cript=new CriptData();
        ArrayList<String> nickname = new ArrayList<>();
        rs = stmt.executeQuery("SELECT nickname from utente");
        while (rs.next())
            nickname.add(rs.getString("nickname"));

        for(int i=0;i<nickname.size();i++){
            pwd= RandomStringUtils.random( 15, caratteri );
            pwdCripted=cript.encrypt(pwd);
            numcarta = RandomStringUtils.random( 16, numeri );
            numcartaCritt=cript.encrypt(numcarta);
            CvvCript=cript.encrypt(String.valueOf(rnd.nextInt(9999-100)+100));
            RandomDate rd= new RandomDate(LocalDate.of(2022, 8, 31), LocalDate.of(2026,6,30));
            scadenzaProva=rd.nextDate();
            int anno=scadenzaProva.getYear()-2000;
            scadenza = scadenzaProva.getMonthValue() + "/" + anno;
            if(scadenza.length()==4)
                scadenza="0"+scadenza;
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dato_Confidenziale (utente,pwd, numero_Carta,scadenza,cvv) VALUES (?,?, ?,?,?)");
            preparedStatement.setString(1, nickname.get(i));
            preparedStatement.setString(2,pwdCripted);
            preparedStatement.setString(3,numcartaCritt);
            preparedStatement.setString(4,scadenza);
            preparedStatement.setString(5,CvvCript);
            preparedStatement.execute();
        }
    }

    //fine funzioni per popolare il db


    public void GiochiPiuOre() throws SQLException {
        Statement stmt= conn.createStatement();
        PreparedStatement preparedStatement;
        ResultSet rs=stmt.executeQuery("select g.id_Gioco,g.titolo,truncate(sum(Ore_Gioco),1) as Totale_Ore "+
                "from dato_Gioco inner join gioco g on dato_Gioco.Gioco = g.id_Gioco\n" +
                "group by Gioco\n" +
                "order by Totale_Ore desc\n" +
                "limit 5;");
        System.out.println("---Classifica giochi piu' giocati---");
        int indice=0;
        while (rs.next()){
            preparedStatement= conn.prepareStatement("select Ore_Gioco, Utente from dato_Gioco \n"+
                    "where Gioco=?\n"+
                    "ORDER BY Ore_Gioco desc\n"+
                    "limit 1;");
            preparedStatement.setInt(1,rs.getInt("id_Gioco"));
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            double ore=rs.getDouble("Totale_Ore");
            double oreGiocatore=resultSet.getDouble("Ore_Gioco");
            System.out.println((++indice)+")"+rs.getString("titolo")+ ", Ore di gioco: "+(int) ore+"h "+((int)((ore-(int)ore)*60)+1)+
                    "m , Utente con piu' ore: "+ resultSet.getString("Utente") +" "+ (int)oreGiocatore + "h "+((int)((oreGiocatore-(int)oreGiocatore)*60)+1)+"m");
        }
    }

    public void GiochiGratisScaricati() throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs=stmt.executeQuery("select g.titolo, COUNT(*) as Numero_Download \n"+
                        "from gioco g inner join dato_Gioco dG on g.id_Gioco = dG.Gioco \n"+
                        "where g.prezzo is null \n"+
                        "group by titolo \n"+
                        "order by Numero_Download desc \n"+
                        "limit 5;");
        System.out.println("---Classifica giochi gratis piu' Scaricati---");
        int indice=0;
        while (rs.next())
            System.out.println((++indice)+")"+rs.getString("titolo")+", Numero di download: "+rs.getInt("Numero_Download"));
    }

    public void GiochiPagamentoScaricati() throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs=stmt.executeQuery("select g.titolo, COUNT(*) as Numero_Download \n"+
                "from gioco g inner join dato_Gioco dG on g.id_Gioco = dG.Gioco \n"+
                "where g.prezzo is not null \n"+
                "group by titolo \n"+
                "order by Numero_Download desc \n"+
                "limit 5;");
        System.out.println("---Classifica giochi a Pagamento piu' scaricati---");
        int indice=0;
        while (rs.next())
            System.out.println((++indice)+")"+rs.getString("titolo")+", Numero di download: "+rs.getInt("Numero_Download"));
    }

    public void OreGiocoUtenti() throws SQLException {
        Statement stmt= conn.createStatement();
        ResultSet rs=stmt.executeQuery("select Nickname, truncate(Sum(dG.Ore_Gioco),1) as Totale_Ore  \n" +
                "from Utente inner join dato_Gioco dG on Utente.Nickname = dG.Utente\n" +
                "group by Nickname\n" +
                "order by Totale_Ore desc\n" +
                "limit 10;");
        System.out.println("---Classifica giocatori con piu' ore di gioco---");
        int indice=0;
        while (rs.next()){
            double ore=rs.getDouble("Totale_Ore");
            System.out.println((++indice)+")"+rs.getString("nickname")+", Ore di gioco: "+(int)ore+"h "+((int)((ore-(int)ore)*60)+1)+"m");
        }
    }

    public void CatalogoUtente(String nick) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet rs;
        preparedStatement=conn.prepareStatement("select titolo,dg.Ore_Gioco \n" +
                "from gioco  inner join dato_Gioco dG on gioco.id_Gioco = dG.Gioco\n" +
                "where dg.Utente=?;");
        preparedStatement.setString(1,nick);

        rs=preparedStatement.executeQuery();
        int indice=0;
        System.out.println("---Catalogo giochi dell'utente "+nick+" ---");
        while (rs.next()){
            double ore=rs.getDouble("Ore_Gioco");
            System.out.println((++indice)+")"+rs.getString("titolo")+ ", Ore Giocate: "+(int)ore+"h "+((int)((ore-(int)ore)*60)+1)+"m");
        }
        preparedStatement=conn.prepareStatement("select TRUNCATE(sum(g.prezzo),2) as somma_Prezzi \n" +
                "from dato_Gioco inner join gioco g on Gioco=g.id_Gioco\n" +
                "where Utente=?;");
        preparedStatement.setString(1,nick);
        rs=preparedStatement.executeQuery();
        rs.next();
        System.out.println("Soldi spesi: "+rs.getDouble("somma_Prezzi")+"€");
    }

    public void InserisciUtente(Utente u) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into Utente(nickname, nome, cognome, data_nascita, telefono, mail, indirizzo, citta, cap, provincia) values (?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,u.getNickname());
        preparedStatement.setString(2,u.getNome());
        preparedStatement.setString(3,u.getCognome());
        preparedStatement.setDate(4, (Date) u.getData_Nascita());
        preparedStatement.setString(5,u.getTelefono());
        preparedStatement.setString(6,u.getMail());
        preparedStatement.setString(7,u.getIndizzo());
        preparedStatement.setString(8,u.getCitta());
        preparedStatement.setString(9,u.getCap());
        preparedStatement.setString(10,u.getProvincia());
        preparedStatement.execute();
    }

    public void InserisciGioco(Gioco g) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into gioco (titolo, Dimensioni, Eta_Minima, Descrione, Data_Pubb, prezzo, Sviluppatore)  values (?,?,?,?,?,?,?)");
        preparedStatement.setString(1,g.getTitolo());
        preparedStatement.setString(2,g.getDimensioni());
        preparedStatement.setByte(3,g.getEta_Minima());
        preparedStatement.setString(4, g.getDescrizione());
        preparedStatement.setDate(5,g.getData_Pubb());
        preparedStatement.setString(7,g.getSviluppatore());
        if(g.getPrezzo()!=0)
            preparedStatement.setFloat(6,g.getPrezzo());
        else
            preparedStatement.setNull(6, Types.NULL);
        preparedStatement.execute();
    }

    public void InserisciSviluppatore(Sviluppatore s) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Sviluppatore (P_Iva,Nome, Sito_Web,telefono,Mail,indirizzo,citta,cap,provincia) VALUES (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,s.getP_Iva());
        preparedStatement.setString(2,s.getNome());
        preparedStatement.setString(3,s.getSito_Web());
        preparedStatement.setString(4, s.getTelefono());
        preparedStatement.setString(5,s.getMail());
        preparedStatement.setString(6,s.getIndizzo());
        preparedStatement.setString(7,s.getCitta());
        preparedStatement.setString(8,s.getCap());
        preparedStatement.setString(9,s.getProvincia());
        preparedStatement.execute();
    }

    public void InserisciVersione(Versione v) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO versione (gioco,tipo, versione,descrizione,data_Versione) VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1,v.getGioco());
        preparedStatement.setString(2,v.getTipo());
        preparedStatement.setString(3,v.getVersione());
        preparedStatement.setString(4, v.getDescrizione());
        preparedStatement.setDate(5,v.getData_Versione());
        preparedStatement.execute();
    }

    public void InserisciDatiAcq(DatoGioco dg) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dato_gioco (gioco,utente, data_Acquisto,ore_Gioco) VALUES (?,?,?,?)");
        preparedStatement.setInt(1,dg.getGioco());
        preparedStatement.setString(2,dg.getUtente());
        preparedStatement.setDate(3,dg.getData_Acquisto());
        preparedStatement.setInt(4, 0);
        preparedStatement.execute();
    }

    public void InserisciAmicizia(Amicizia a) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO amicizia (utente1,utente2, data_Amicizia) VALUES (?,?,?)");
        preparedStatement.setString(1,a.getUtente1());
        preparedStatement.setString(2,a.getUtente2());
        preparedStatement.setDate(3,a.getData_amicizia());
        preparedStatement.execute();
    }

    public void InserisciRecensione(Recensione r) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO recensione (Gioco,utente, stelle,titolo,data_Recensione,testo) VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1,r.getGioco());
        preparedStatement.setString(2,r.getUtente());
        preparedStatement.setByte(3,r.getStelle());
        preparedStatement.setString(4,r.getTitolo());
        preparedStatement.setDate(5,r.getData_Recrensione());
        preparedStatement.setString(6,r.getTesto());
        preparedStatement.execute();
    }

    public void InserisciDatiConf(Dato_Confidenziale dc) throws SQLException {
        CriptData cript=new CriptData();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Dato_Confidenziale (utente,pwd, numero_carta,scadenza,cvv) VALUES (?,?,?,?,?)");
        preparedStatement.setString(1,dc.getUtente());
        preparedStatement.setString(2,cript.encrypt(dc.getPwd()));
        preparedStatement.setString(3,cript.encrypt(dc.getNumero_Carta()));
        preparedStatement.setString(4,dc.getScadenza());
        preparedStatement.setString(5,cript.encrypt(dc.getCvv()));
        preparedStatement.execute();
    }

    public void IncrementaOre(String n,int g,int o) throws SQLException {
        PreparedStatement preparedStatement= conn.prepareStatement("UPDATE dato_Gioco\n" +
                "            set Ore_Gioco=Ore_Gioco + ?\n" +
                "        where dato_Gioco.Utente=? and dato_Gioco.Gioco=?;");
        preparedStatement.setInt(1,o);
        preparedStatement.setString(2,n);
        preparedStatement.setInt(3,g);
        preparedStatement.execute();
    }
}
