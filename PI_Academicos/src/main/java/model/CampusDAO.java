
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class CampusDAO extends GenericDAO {
     public  List<Campus> buscarCampus() throws SQLException{
         List<Campus> listCampus = new ArrayList<>();
        
       Connection con = conectarDAO();
       
       //Buscar tudo do Campus
        
        String queryCampus = "SELECT idCampus,nomeCampus, localCampus FROM campus";
        
        try(con){
            PreparedStatement stmtCampus = con.prepareStatement(queryCampus);
            
            ResultSet resultCampus = stmtCampus.executeQuery();
            
            
            
           
        while (resultCampus.next()) {
            Campus c = new Campus();
            c.setIdCampus(resultCampus.getInt("idCampus"));
            c.setNomeCampus(resultCampus.getString("nomeCampus"));
            c.setLocalCampus(resultCampus.getString("localCampus"));
            listCampus.add(c); 

   
}
        }
 return listCampus;
}
    
}

