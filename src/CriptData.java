
public class CriptData {

    char[] parola;
    String prova="";
    public String encrypt(String dato){
        parola=new char[dato.length()];
        prova="";
        parola=dato.toCharArray();
        for(int i=0;i<dato.length();i++){
            parola[i]= (char) (parola[i]+1);
            prova+=parola[i];
        }
        return prova;
    }

    public String decrypt(String datoCript){
        parola=new char[datoCript.length()];
        prova="";
        parola=datoCript.toCharArray();
        for(int i=0;i<datoCript.length();i++){
            parola[i]= (char) (parola[i]-1);
            prova+=parola[i];
        }
        return prova;
    }
}
