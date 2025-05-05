package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProjetoDAO extends GenericDAO{
    
    public void cadastraprojeto(Projeto projeto) throws SQLException{
        
       Connection con = conectarDAO();
        
        String queryProjeto = "INSERT INTO projetos(,tituloProjeto,resumo,idCampus,edital,dataInicio,dataFim,prorrogacao,emAndamento) VALUES(?,?,?,?,?,?,?,?)";
        
  try  ( 
          con
   )
     
  {
         PreparedStatement stmtProjeto = con.prepareStatement(queryProjeto, PreparedStatement.RETURN_GENERATED_KEYS);
    stmtProjeto.setString(1, projeto.getTitulo());
    stmtProjeto.setString(2, projeto.getResumo());
  
   stmtProjeto.setInt(3, projeto.getCampus().getIdCampus());
 
    stmtProjeto.setString(4, projeto.getEdital());
    stmtProjeto.setDate(5, Date.valueOf(projeto.getDataInicio()));  
    stmtProjeto.setDate(6,Date.valueOf(projeto.getDataFim()) ); 
    stmtProjeto.setDate(7, Date.valueOf(projeto.getProrroacao())); 
    stmtProjeto.setBoolean(8, projeto.isEmAndamento()); 
    stmtProjeto.executeUpdate();
   

   ResultSet keys = stmtProjeto.getGeneratedKeys();
    if (keys.next()) {
     int idGerado = keys.getInt(1);
                projeto.setIdProjeto(idGerado);
                System.out.println("Projeto cadastrado com ID: " + idGerado);
             }
  }

         catch (SQLException e) {
            e.printStackTrace();
        
}
    }
}

    


