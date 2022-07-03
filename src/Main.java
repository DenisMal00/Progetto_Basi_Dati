import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException, IOException, ParseException {
        GameStore db = new GameStore();
        Scanner sc=new Scanner(System.in);
        //db.inserisciUtenti(500);  //servono per popolare inizialmente il db
        //db.inserisciVersioniDefault();
        //db.inserisciVersioni(20);
        //db.inserisciDatiGioco(200);
        //db.inserisciRecensioni(50);
        //db.inserisciAmicizie(1000);
        //db.inserisciDatiPrivati();
        String scelta;
        do {
            System.out.println("0)Esci dal programma ");
            System.out.println("1)Stampa i primi 5 giochi con piu' giocati e il giocatore con piu' ore di gioco ");
            System.out.println("2)Stampa i primi 5 giochi gratis piu' scaricati ");
            System.out.println("3)Stampa i primi 5 giochi a pagamento piu' scaricati ");
            System.out.println("4)Stampa i primi 10 utenti con piu' ore di gioco in totale ");
            System.out.println("5)Stampa il catalogo giochi di un certo giocatore ed i soldi spesi per acquistare i giochi");
            System.out.println("6)Incrementa ore di gioco");
            System.out.println("7)Inserisci dati");
            System.out.println("Scelta: ");
            scelta="";
            scelta= sc.next();
            switch(scelta) {
                case "1":
                    db.GiochiPiuOre();
                    esci();
                    break;
                case "2":
                    db.GiochiGratisScaricati();
                    esci();
                    break;
                case "3":
                    db.GiochiPagamentoScaricati();
                    esci();
                    break;
                case "4":
                    db.OreGiocoUtenti();
                    esci();
                    break;
                case "5":
                    System.out.println("Inserisci il nickname del giocatore:" );
                    String nick=sc.next();
                    db.CatalogoUtente(nick);
                    esci();
                    break;
                case "6":
                    System.out.println("Inserisci il nickname del giocatore:" );
                    String nickname=sc.next();
                    System.out.println("Inserisci l'id del gioco:" );
                    int id=sc.nextInt();
                    System.out.println("Inserisci le ore da incrementare:" );
                    int ore=sc.nextInt();
                    db.IncrementaOre(nickname,id,ore);
                    System.out.println("ore Incrementate");
                    esci();
                    break;
                case "7":
                    do {
                        System.out.println("\n\n\n\n\n\n\n\n\n");
                        System.out.println("1)Inserisci gioco ");
                        System.out.println("2)Inserisci utente");
                        System.out.println("3)Inserisci sviluppatore");
                        System.out.println("4)Inserisci versione di un gioco");
                        System.out.println("5)Inserisci dati acquisto di un gioco");
                        System.out.println("6)Inserisci amicizia");
                        System.out.println("7)Inserisci Recensione");
                        System.out.println("8)Inserisci Dati confidenziali di un utente");
                        System.out.println("9)Ritorna al menu ");
                        System.out.println("Scelta: ");
                        scelta="";
                        scelta= sc.next();
                        switch (scelta){
                            case "1":
                                Gioco gioco=new Gioco();
                                sc.nextLine();
                                System.out.println("Inserisci titolo: ");
                                gioco.setTitolo(sc.nextLine());
                                System.out.println("Inserisci dimensioni: ");
                                gioco.setDimensioni(sc.next());
                                System.out.println("Inserisci Eta minima: ");
                                gioco.setEta_Minima(sc.nextByte());
                                sc.nextLine();
                                System.out.println("Inserisci Descrizione: ");
                                gioco.setDescrizione(sc.nextLine());
                                System.out.println("Inserisci prezzo (se è gratis non inserire 0): ");
                                gioco.setPrezzo(Float.parseFloat(sc.next()));
                                System.out.println("Inserisci P.Iva dello sviluppatore: ");
                                gioco.setSviluppatore(sc.next());
                                System.out.println("Inserisci data di pubblicazione (YYYY-MM-DD) : ");
                                java.sql.Date dataPubb = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()).getTime());
                                gioco.setData_Pubb(dataPubb);
                                db.InserisciGioco(gioco);
                                System.out.println("Gioco Inserito!!!");
                                esci();
                                break;
                            case "2":
                                Utente utente = new Utente();
                                System.out.println("Inserisci nickname: ");
                                utente.setNickname(sc.next());
                                System.out.println("Inserisci nome: ");
                                utente.setNome(sc.next());
                                System.out.println("Inserisci cognome: ");
                                utente.setCognome(sc.next());
                                System.out.println("Inserisci telefono: ");
                                utente.setTelefono(sc.next());
                                System.out.println("Inserisci mail: ");
                                utente.setMail(sc.next());
                                sc.nextLine();
                                System.out.println("Inserisci indizzo e civico: ");
                                utente.setIndizzo(sc.nextLine());
                                System.out.println("Inserisci citta: ");
                                utente.setCitta(sc.nextLine());
                                System.out.println("Inserisci cap: ");
                                utente.setCap(sc.next());
                                System.out.println("Inserisci provincia (sigla) : ");
                                utente.setProvincia(sc.next());
                                System.out.println("Inserisci data di nascita (YYYY-MM-DD) : ");
                                java.sql.Date dataNascita = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()).getTime());
                                utente.setData_Nascita(dataNascita);
                                db.InserisciUtente(utente);
                                System.out.println("Utente Inserito!!!");
                                esci();
                                break;
                            case "3":
                                Sviluppatore sviluppatore=new Sviluppatore();
                                System.out.println("Inserisci Partita iva: ");
                                sviluppatore.setP_Iva(sc.next());
                                sc.nextLine();
                                System.out.println("Inserisci Nome: ");
                                sviluppatore.setNome(sc.nextLine());
                                System.out.println("Inserisci sito web: ");
                                sviluppatore.setSito_Web(sc.next());
                                System.out.println("Inserisci telefono: ");
                                sviluppatore.setTelefono(sc.next());
                                System.out.println("Inserisci mail: ");
                                sviluppatore.setMail(sc.next());
                                sc.nextLine();
                                System.out.println("Inserisci indizzo e civico: ");
                                sviluppatore.setIndizzo(sc.nextLine());
                                System.out.println("Inserisci citta: ");
                                sviluppatore.setCitta(sc.nextLine());
                                System.out.println("Inserisci cap: ");
                                sviluppatore.setCap(sc.next());
                                System.out.println("Inserisci provincia (sigla) : ");
                                sviluppatore.setProvincia(sc.next());
                                db.InserisciSviluppatore(sviluppatore);
                                System.out.println("Sviluppatore Inserito!!!");
                                esci();
                                break;
                            case "4":
                                Versione versione=new Versione();
                                System.out.println("Inserisci l'id del gioco: ");
                                versione.setGioco(sc.nextInt());
                                System.out.println("Inserisci il tipo della versione (alfa,beta,release): ");
                                versione.setTipo(sc.next());
                                System.out.println("Inserisci la versione del gioco: ");
                                versione.setVersione(sc.next());
                                sc.nextLine();
                                System.out.println("Inserisci la descrizione della versione: ");
                                versione.setDescrizione(sc.nextLine());
                                System.out.println("Inserisci data della versione (YYYY-MM-DD) : ");
                                java.sql.Date dataVersione = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()).getTime());
                                versione.setData_Versione(dataVersione);
                                db.InserisciVersione(versione);
                                System.out.println("versione Inserita!!!");
                                esci();
                                break;
                            case "5":
                                DatoGioco datoGioco=new DatoGioco();
                                System.out.println("Inserisci l'id del gioco: ");
                                datoGioco.setGioco(sc.nextInt());
                                System.out.println("Inserisci il nickname dell'utente: ");
                                datoGioco.setUtente(sc.next());
                                System.out.println("Inserisci data dell'acquisto (YYYY-MM-DD) : ");
                                java.sql.Date dataAcq = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()).getTime());
                                datoGioco.setData_Acquisto(dataAcq);
                                db.InserisciDatiAcq(datoGioco);
                                System.out.println("Dati di acquisto Inseriti!!!");
                                esci();
                                break;
                            case "6":
                                Amicizia amicizia=new Amicizia();
                                System.out.println("Inserisci il nickname del primo utente: ");
                                amicizia.setUtente1(sc.next());
                                System.out.println("Inserisci il nickname del secondo utente: ");
                                amicizia.setUtente2(sc.next());
                                System.out.println("Inserisci data dell'amicizia (YYYY-MM-DD) : ");
                                java.sql.Date dataAm = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()).getTime());
                                amicizia.setData_amicizia(dataAm);
                                db.InserisciAmicizia(amicizia);
                                System.out.println("amicizia Inserita!!!");
                                esci();
                                break;
                            case "7":
                                Recensione recensione=new Recensione();
                                System.out.println("Inserisci l'id del gioco: ");
                                recensione.setGioco(sc.nextInt());
                                System.out.println("Inserisci il nickname dell'utente: ");
                                recensione.setUtente(sc.next());
                                System.out.println("Inserisci le stelle di valutazione (da 1 a 5): ");
                                recensione.setStelle(sc.nextByte());
                                sc.nextLine();
                                System.out.println("Inserisci il titolo della recensione: ");
                                recensione.setTitolo(sc.nextLine());
                                System.out.println("Inserisci il testo della recensione: ");
                                recensione.setTesto(sc.nextLine());
                                System.out.println("Inserisci data della recensione (YYYY-MM-DD) : ");
                                java.sql.Date dataRec = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()).getTime());
                                recensione.setData_Recrensione(dataRec);
                                db.InserisciRecensione(recensione);
                                System.out.println("Recensione Inserita!!!");
                                esci();
                                break;
                            case "8":
                                Dato_Confidenziale datoConfidenziale=new Dato_Confidenziale();
                                System.out.println("Inserisci il nickname dell'utente: ");
                                datoConfidenziale.setUtente(sc.next());
                                System.out.println("Inserisci la password: ");
                                datoConfidenziale.setPwd(sc.next());
                                System.out.println("Inserisci numero della carta: ");
                                datoConfidenziale.setNumero_Carta(sc.next());
                                System.out.println("Inserisci la scadenza: ");
                                datoConfidenziale.setScadenza(sc.next());
                                System.out.println("Inserisci il cvv: ");
                                datoConfidenziale.setCvv(sc.next());
                                db.InserisciDatiConf(datoConfidenziale);
                                System.out.println("Dati Privati inseriti Inserita!!!");
                                esci();
                                break;
                            default:
                                System.out.println("Hai inserito un carattere sbagliato!!!");
                        }

                    }while (!scelta.equals("9"));
                    System.out.println("\n\n\n\n\n\n\n\n\n");
                    break;
                case "0":
                    System.out.println("Arrivederci");
                    break;
                default:
                    System.out.println("Hai inserito un carattere sbagliato!!!");
                    esci();
            }
            System.out.println();
        }while (!scelta.equals("0"));
    }

    public static void esci() throws IOException {
        System.out.println("premi invio per continuare......\n");
        System.in.read();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}


