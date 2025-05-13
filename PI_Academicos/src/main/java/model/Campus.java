
package model;


public class Campus {

   private int idCampus;
   private String Projeto;
   private String nomeCampus;
   private String localCampus;
   
   
   
    /**
     * @return the idCampus
     */
    public int getIdCampus() {
        return idCampus;
    }

    /**
     * @param idCampus the idCampus to set
     */
    public void setIdCampus(int idCampus) {
        this.idCampus = idCampus;
    }

    /**
     * @return the Projeto
     */
    public String getProjeto() {
        return Projeto;
    }

    /**
     * @param Projeto the Projeto to set
     */
    public void setProjeto(String Projeto) {
        this.Projeto = Projeto;
    }

    /**
     * @return the nomeCampus
     */
    public String getNomeCampus() {
        return nomeCampus;
    }

    /**
     * @param nomeCampus the nomeCampus to set
     */
    public void setNomeCampus(String nomeCampus) {
        this.nomeCampus = nomeCampus;
    }

    /**
     * @return the localCampus
     */
    public String getLocalCampus() {
        return localCampus;
    }

    /**
     * @param localCampus the localCampus to set
     */
    public void setLocalCampus(String localCampus) {
        this.localCampus = localCampus;
    }
   @Override
    public String toString(){
        return nomeCampus;
    }
    
    public Campus(int idCampus) {
    this.idCampus = idCampus;
}
    public Campus(){
        
    }
   
}
