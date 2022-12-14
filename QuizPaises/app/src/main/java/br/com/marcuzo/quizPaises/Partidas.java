package br.com.marcuzo.quizPaises;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class Partidas implements Serializable {

    private String nomeJogador;
    private int pontuacao;

    public Partidas(String nomeJogador, int pontuacao) {
        this.nomeJogador = nomeJogador;
        this.pontuacao = pontuacao;
    }

    ArrayList<Integer> idPaises;
    EnumSet[] paises;

    public ArrayList<Integer> gerarOrdemPerguntas() {
        Random random = new Random();
        idPaises = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            int num = 1 + random.nextInt(10);
            if(idPaises.contains(num)) {
                i--;
            } else {
                idPaises.add(num);
            }
        }
        return idPaises;
    }

    public EnumPaises retornarPaises(int idPais) {
            switch(idPais){
                case 1:
                   return EnumPaises.BRASIL;
                case 2:
                    return EnumPaises.BULGARIA;
                case 3:
                    return EnumPaises.CANADA;
                case 4:
                    return EnumPaises.CHINA;
                case 5:
                    return EnumPaises.COREIA_SUL;
                case 6:
                    return EnumPaises.CUBA;
                case 7:
                    return EnumPaises.GUATEMALA;
                case 8:
                    return EnumPaises.HUNGRIA;
                case 9:
                    return EnumPaises.ITALIA;
                case 10:
                    return EnumPaises.USA;
        }
        return null;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
