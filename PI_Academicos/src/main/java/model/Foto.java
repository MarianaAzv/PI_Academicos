package model;

import java.io.File;

public class Foto {

    private int id;
    private byte[] dadosImagem;

    public Foto(int id, byte[] dadosImagem) {
        this.id = id;
        this.dadosImagem = dadosImagem;
    }

    public Foto(byte[] arquivo) {
        this.dadosImagem = arquivo;

    }

    public Foto() {

    }

    public int getId() {
        return id;
    }

    public byte[] getDadosImagem() {
        return dadosImagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDadosImagem(byte[] dadosImagem) {
        this.dadosImagem = dadosImagem;
    }

}
