
package util;


//Ele nao esta aceitando espaço e acento 

public class Apenasletras {
  public static boolean isLetras(String text) {
    boolean letra = true;
    for(int i=0; i<text.length();i++){
    //    if(!text.matches("[a-zA-Z\s]+")){
        if(!Character.isLetter(text.charAt(i))){
           letra=false;
           break;
            
        }
        
    }
    if(letra){
        System.out.print("Contem apenas letras");
         return true;
    } else{
        System.out.print("Tem cacteres alem de letras");
        return false;
    }
   

//boolean sohLetrasEEspacos = String.matches("[a-zA-Z\s]+"); 
}   

}
