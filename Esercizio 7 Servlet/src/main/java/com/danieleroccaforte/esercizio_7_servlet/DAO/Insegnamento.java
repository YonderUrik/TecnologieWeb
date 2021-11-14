package com.danieleroccaforte.esercizio_7_servlet.DAO;

public class Insegnamento {
    private String corso;
    private String nome;
    private String cognome;

    @Override
    public String toString() {
        return "Insegnamento{" +
                "corso='" + corso + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCorso() {
        return corso;
    }

    public Insegnamento(String corso, String nome, String cognome) {
        this.corso = corso;
        this.nome = nome;
        this.cognome = cognome;
    }
}
