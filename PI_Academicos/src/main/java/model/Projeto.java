package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto {

    private int idProjeto;
    private String titulo;
    private AreasConhecimento areaConhecimento;
    private String resumo;
    private String edital;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate prorrogacao;
    private boolean emAndamento;
    private String cocoordenadores;
    private Campus campus;
    private List<Bolsista> bolsistas = new ArrayList<>();
    private Foto fotoPerfil;

    

    public Projeto(String titulo, String resumo, Campus campus, String edital, LocalDate dataInicio, LocalDate dataFim, LocalDate prorrogacao, boolean emAndamento) {
        this.titulo = titulo;

        this.resumo = resumo;
        this.campus = campus;
        this.edital = edital;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.prorrogacao = prorrogacao;
        this.emAndamento = emAndamento;
    }
    
    // construtor completo + foto
    public Projeto(String titulo, String resumo, Campus campus, String edital, LocalDate dataInicio, LocalDate dataFim, LocalDate prorrogacao, boolean emAndamento, Foto fotoPerfil) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.campus = campus;
        this.edital = edital;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.prorrogacao = prorrogacao;
        this.emAndamento = emAndamento;
        this.fotoPerfil = fotoPerfil;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the areaConhecimento
     */
    public AreasConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    /**
     * @param areaConhecimento the areaConhecimento to set
     */
    public void setAreaConhecimento(AreasConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    /**
     * @return the resumo
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * @param resumo the resumo to set
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    /**
     * @return the edital
     */
    public String getEdital() {
        return edital;
    }

    /**
     * @param edital the edital to set
     */
    public void setEdital(String edital) {
        this.edital = edital;
    }

    /**
     * @return the dataInicio
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the prorroacao
     */
    public LocalDate getProrroacao() {
        return prorrogacao;
    }

    /**
     * @param prorroacao the prorroacao to set
     */
    public void setProrroacao(LocalDate prorroacao) {
        this.prorrogacao = prorroacao;
    }

    /**
     * @return the emAndamento
     */
    public boolean isEmAndamento() {
        return emAndamento;
    }

    /**
     * @param emAndamento the emAndamento to set
     */
    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    /**
     * @return the cocoordenadores
     */
    public String getCocoordenadores() {
        return cocoordenadores;
    }

    /**
     * @return the campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * @param cocoordenadores the cocoordenadores to set
     */
    public void setCocoordenadores(String cocoordenadores) {
        this.cocoordenadores = cocoordenadores;
    }

    /**
     * @return the bolsistas
     */
    public List<Bolsista> getBolsistas() {
        return bolsistas;
    }

    /**
     * @param bolsistas the bolsistas to set
     */
    public void setBolsistas(List<Bolsista> bolsistas) {
        this.bolsistas = bolsistas;
    }
    
    public void setProrrogacao(LocalDate prorrogacao) {
        this.prorrogacao = prorrogacao;
    }

    public void setFotoPerfil(Foto fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public LocalDate getProrrogacao() {
        return prorrogacao;
    }

    public Foto getFotoPerfil() {
        return fotoPerfil;
    }

    public Projeto() {

    }

}
