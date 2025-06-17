package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO extends GenericDAO{
    
    public void cadastraprojeto(Projeto projeto,int id) throws SQLException{
        
       Connection con = conectarDAO();
        
        String queryProjeto = "INSERT INTO projetos(tituloProjeto,resumo,idCampus,edital,dataInicio,dataFim,prorrogacao,emAndamento) VALUES(?,?,?,?,?,?,?,?)";
        String queryCoordenadorProjeto = "Insert into coordenadores_projetos(idUsuario,idProjeto,dataInicio,dataFim) values(?,?,?,?)";

        
        try  (con){
         PreparedStatement stmtProjeto = con.prepareStatement(queryProjeto, PreparedStatement.RETURN_GENERATED_KEYS);
         stmtProjeto.setString(1, projeto.getTitulo());
         stmtProjeto.setString(2, projeto.getResumo());
         stmtProjeto.setInt(3, projeto.getCampus().getIdCampus());
         stmtProjeto.setString(4, projeto.getEdital());
         stmtProjeto.setDate(5, Date.valueOf(projeto.getDataInicio()));  
         stmtProjeto.setDate(6,Date.valueOf(projeto.getDataFim()) ); 
         stmtProjeto.setDate(7, projeto.getProrroacao() != null ? java.sql.Date.valueOf(projeto.getProrroacao()) : null);
         stmtProjeto.setBoolean(8, projeto.isEmAndamento()); 
   
   
    int linhasAfetadas = stmtProjeto.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new SQLException("Falha ao inserir");
        }else{

   ResultSet keys = stmtProjeto.getGeneratedKeys();
    if (keys.next()) {
     int idGerado = keys.getInt(1);
                projeto.setIdProjeto(idGerado);
                System.out.println("Projeto cadastrado com ID: " + idGerado);
             }
  }
 
   PreparedStatement stmtCoordenadorProjeto = con.prepareStatement(queryCoordenadorProjeto, PreparedStatement.RETURN_GENERATED_KEYS);
   
   stmtCoordenadorProjeto.setInt(1,id);
   stmtCoordenadorProjeto.setInt(2,projeto.getIdProjeto());
   stmtCoordenadorProjeto.setDate(3,Date.valueOf(projeto.getDataInicio()));
   stmtCoordenadorProjeto.setDate(4,Date.valueOf(projeto.getDataFim()));
   linhasAfetadas = stmtCoordenadorProjeto.executeUpdate();
            System.out.println(linhasAfetadas + "linhas afetadas");
      System.out.println("ID do coordenador "+ id);
      
   
 
        
 
      

  }  catch (SQLException e) {
            e.printStackTrace();
          System.out.print("Erro ao cadatrar o projeto");
        
}
}
        
  
  
  
    
    
    public void atualizarProjeto(Projeto projeto) throws SQLException {
  
        Connection con = conectarDAO();
        
        String queryProjeto = "UPDATE projetos p join areas_projetos ap  on p.IdProjeto= ap.idProjeto  SET p.tituloProjeto = ?, p.resumo = ?, p.idCampus = ?, p.edital = ?, p.dataInicio = ?, p.dataFim = ?,p.prorrogacao = ?, p.emAndamento = ?, ap.idArea = ? WHERE p.IdProjeto = ?";
       

    try (con) {
    
   PreparedStatement stmtProjeto = con.prepareStatement(queryProjeto, PreparedStatement.RETURN_GENERATED_KEYS);
   stmtProjeto.setString(1, projeto.getTitulo());
   stmtProjeto.setString(2, projeto.getResumo());
  
   stmtProjeto.setInt(3, projeto.getCampus().getIdCampus());
 
    stmtProjeto.setString(4, projeto.getEdital());
    stmtProjeto.setDate(5, Date.valueOf(projeto.getDataInicio()));  
    stmtProjeto.setDate(6,Date.valueOf(projeto.getDataFim()) ); 
  stmtProjeto.setDate(7, projeto.getProrroacao() != null ? java.sql.Date.valueOf(projeto.getProrroacao()) : null);
   stmtProjeto.setBoolean(8, projeto.isEmAndamento());  
   stmtProjeto.setInt(9,projeto.getAreaConhecimento().getIdArea());
   stmtProjeto.setInt(10,projeto.getIdProjeto());
   stmtProjeto.executeUpdate();



        

  System.out.println("Projeto atualizado com ID: " + projeto.getIdProjeto());
 }catch(Exception e){
     e.printStackTrace();
 }
}
    
      public void atualizarProjetoSEmProrrogacao(Projeto projeto) throws SQLException {
  
        Connection con = conectarDAO();
        
        String queryProjeto = "UPDATE projetos p JOIN areas_projetos ap ON p.IdProjeto = ap.idProjeto SET   p.tituloProjeto = ?,   p.resumo = ?,  p.idCampus = ?, p.edital = ?, p.dataInicio = ?,  p.dataFim = ?, p.emAndamento = ?, ap.idArea = ?WHERE p.IdProjeto = ?";
       

    try (con) {
    
   PreparedStatement stmtProjeto = con.prepareStatement(queryProjeto, PreparedStatement.RETURN_GENERATED_KEYS);
   stmtProjeto.setString(1, projeto.getTitulo());
   stmtProjeto.setString(2, projeto.getResumo());
  
   stmtProjeto.setInt(3, projeto.getCampus().getIdCampus());
 
    stmtProjeto.setString(4, projeto.getEdital());
    stmtProjeto.setDate(5, Date.valueOf(projeto.getDataInicio()));  
    stmtProjeto.setDate(6,Date.valueOf(projeto.getDataFim()) ); 
 
   stmtProjeto.setBoolean(7, projeto.isEmAndamento()); 
    stmtProjeto.setInt(8,projeto.getAreaConhecimento().getIdArea());
   stmtProjeto.setInt(9,projeto.getIdProjeto());
   stmtProjeto.executeUpdate();



        

  System.out.println("Projeto atualizado com ID: " + projeto.getIdProjeto());
 }
}
    
    
      public List<Projeto> selecionarProjeto(Coordenador coordenador) throws SQLException {
         List<Projeto> projetos = new ArrayList<>();
         
          Connection con = conectarDAO();
          
        String sql = "SELECT p.*, c.*, ap.*, ac.* FROM PROJETOS as p inner join coordenadores_projetos as cp on p.idProjeto = cp.idProjeto inner join campus  as c on p.idCampus = c.idCampus inner join areas_projetos as ap on ap.idProjeto = p.idProjeto inner join areasdeconhecimento as ac on ac.idArea = ap.idArea where cp.idUsuario=?";
        
        try(con ){
             PreparedStatement stmtProjeto = con.prepareStatement(sql);
             stmtProjeto.setInt(1,coordenador.getId());
              ResultSet rs = stmtProjeto.executeQuery();
              
              
        while (rs.next()) {
            Campus campus = new Campus();
            campus.setIdCampus(rs.getInt("idCampus"));
            campus.setNomeCampus(rs.getString("nomeCampus"));
            campus.setLocalCampus(rs.getString("localCampus"));
            
            AreasConhecimento areasconhecimento = new AreasConhecimento();
            areasconhecimento.setIdArea(rs.getInt("idArea"));
            areasconhecimento.setNomeArea(rs.getString("nomeArea")); 

            Projeto projeto = new Projeto(
                rs.getString("tituloProjeto"),
                rs.getString("resumo"),
                campus,
                rs.getString("edital"),
                rs.getDate("dataInicio").toLocalDate(),
                rs.getDate("dataFim").toLocalDate(),
                rs.getDate("prorrogacao") != null ? rs.getDate("prorrogacao").toLocalDate() : null,
                rs.getBoolean("emAndamento")
            );
            projeto.setAreaConhecimento(areasconhecimento);
        

       projeto.setIdProjeto(rs.getInt("idProjeto"));

            projetos.add(projeto);
        }
        }
        return projetos;
        }

   public void AreaProjeto(Projeto projeto,AreasConhecimento AreasConhecimento){
        
            Connection con = conectarDAO();
            
              String queryAreasProjeto = "Insert into areas_projetos(idArea,idProjeto) values(?,?)";
              
              try(con){
               PreparedStatement stmtAreasProjeto = con.prepareStatement(queryAreasProjeto, PreparedStatement.RETURN_GENERATED_KEYS);
   
    stmtAreasProjeto.setInt(1, projeto.getAreaConhecimento().getIdArea());
    stmtAreasProjeto.setInt(2,projeto.getIdProjeto());
 

 int linhasAfetadas =  stmtAreasProjeto.executeUpdate();
          System.out.println(linhasAfetadas + "linhas afetadas");
          System.out.println("ID do coordenador ");
              } catch(SQLException e){
                e.printStackTrace();
              }
    }
   

   

    public List<Projeto> selecionarProjetoB( Bolsista bolsista) throws SQLException {
         List<Projeto> projetos = new ArrayList<>();
         
          Connection con = conectarDAO();
          
      //  String sql = "SELECT p.*, c.*, ap.* FROM PROJETOS as p inner join coordenadores_projetos as cp on p.idProjeto = cp.idProjeto inner join campus  as c on p.idCampus = c.idCampus inner join areas_projetos as ap on ap.idProjeto = p.idProjeto where cp.idUsuario=?";
        String sql = "SELECT p.*, c.*, ap.*, ac.* FROM PROJETOS as p INNER JOIN bolsistas_projetos as bp on p.idProjeto = bp.idProjeto INNER JOIN campus as c on p.idCampus = c.idCampus INNER JOIN areas_projetos as ap on ap.idProjeto = p.idProjeto INNER JOIN areasdeconhecimento as ac on ac.idArea = ap.idArea WHERE bp.idUsuario = ?";
        //try(con ){
             PreparedStatement stmtProjeto = con.prepareStatement(sql);
             stmtProjeto.setInt(1,bolsista.getId());
              ResultSet rs = stmtProjeto.executeQuery();
              
              
        while (rs.next()) {
            Campus campus = new Campus();
            campus.setIdCampus(rs.getInt("idCampus"));
            campus.setNomeCampus(rs.getString("nomeCampus"));
            campus.setLocalCampus(rs.getString("localCampus"));
            
            AreasConhecimento areasconhecimento = new AreasConhecimento();
            areasconhecimento.setIdArea(rs.getInt("idArea"));
            areasconhecimento.setNomeArea(rs.getString("idProjeto")); 

            Projeto projeto = new Projeto(
                rs.getString("tituloProjeto"),
                rs.getString("resumo"),
                campus,
                rs.getString("edital"),
                rs.getDate("dataInicio").toLocalDate(),
                rs.getDate("dataFim").toLocalDate(),
                rs.getDate("prorrogacao") != null ? rs.getDate("prorrogacao").toLocalDate() : null,
                rs.getBoolean("emAndamento")
            );
        

       projeto.setIdProjeto(rs.getInt("idProjeto"));

            projetos.add(projeto);
        }
        
        return projetos;
        
        
    }
    
    public List<Bolsista> selecioBolPProj(int idProjeto) throws SQLException{
         List<Bolsista> bolsistas = new ArrayList<>();
    Connection con = conectarDAO();

    
    String sql = "Select bp.*,b.*,u.* from bolsistas_projetos bp inner join bolsistas b on b.idUsuario=bp.idUsuario inner join usuarios u  on b.idUsuario=u.idUsuario where bp.idProjeto = ?";
    
     try(con) {
             PreparedStatement stmtProjetobolsista = con.prepareStatement(sql);
              stmtProjetobolsista.setInt(1, idProjeto);
        ResultSet rs = stmtProjetobolsista.executeQuery();

        while (rs.next()) {
                Bolsista b = new Bolsista(
                rs.getLong("matricula"),
                rs.getString("curso"),
                rs.getDate("dataInicio").toLocalDate(),
                rs.getDate("dataFim") != null ? rs.getDate("dataFim").toLocalDate() : null,
                idProjeto 
            );

          
            b.setId(rs.getInt("idUsuario"));
            b.setNome(rs.getString("nome"));

            bolsistas.add(b);
        }
    } 

    return bolsistas;
}
    
    public void Destivar(int idUsuario, int idProjeto) throws SQLException {
    String sql = "DELETE FROM bolsistas_projetos WHERE idUsuario = ? AND idProjeto = ?";

    try (Connection con = conectarDAO();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idProjeto);
        stmt.executeUpdate();
    }
}
    
    

}

    


