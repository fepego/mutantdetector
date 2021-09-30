package com.meli.pruebatecnica.mutantdna.entity;

public class DnaVo {

    private int LenghtChain;
    private char[][] DnaChain;
    private String dnaKey;
    private boolean dnaChainValid;

    public DnaVo() {
    }

    public DnaVo(int lenghtChain, char[][] dnaChain, String dnaKey, boolean dnaChainValid) {
        LenghtChain = lenghtChain;
        DnaChain = dnaChain;
        this.dnaKey = dnaKey;
        this.dnaChainValid = dnaChainValid;
    }

    public int getLenghtChain() {
        return LenghtChain;
    }

    public void setLenghtChain(int lenghtChain) {
        LenghtChain = lenghtChain;
    }

    public char[][] getDnaChain() {
        return DnaChain;
    }

    public void setDnaChain(char[][] dnaChain) {
        DnaChain = dnaChain;
    }

    public String getDnaKey() {
        return dnaKey;
    }

    public void setDnaKey(String dnaKey) {
        this.dnaKey = dnaKey;
    }

    public boolean isDnaChainValid() {
        return dnaChainValid;
    }

    public void setDnaChainValid(boolean dnaChainValid) {
        this.dnaChainValid = dnaChainValid;
    }
}
