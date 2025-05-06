
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AreasConhecimentoDAO extends GenericDAO{
     public  List<AreasConhecimento> buscarCategorias() throws SQLException{
         List<AreasConhecimento> listCategorias= new ArrayList<>();
        
       Connection con = conectarDAO();
       String queryCategorias = "SELECT idArea,nomeArea FROM AreasConhecimento";
       try(con){
            PreparedStatement stmtCategorias = con.prepareStatement(queryCategorias);
            
            ResultSet resultCategoria = stmtCategorias.executeQuery();
            
            
            
           
        while (resultCategoria.next()) {
            AreasConhecimento a = new AreasConhecimento();
            a.setIdArea(resultCategoria.getInt("idArea"));
            a.setNomeArea(resultCategoria.getString("nomeArea"));
           
            listCategorias.add(a); 
       }
} 
    
 return listCategorias;
} 
    

}
