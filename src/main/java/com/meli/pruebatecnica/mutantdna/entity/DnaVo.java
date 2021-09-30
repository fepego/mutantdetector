package com.meli.pruebatecnica.mutantdna.entity;

public class DnaVo {

    private int LenghtChain;
    private char[][] DnaChain;
    private String dnaKey;
    private boolean dnaChainValid;
    private int mutantToken;
    private boolean isMutant;

    public DnaVo() {
    }

    public DnaVo(int lenghtChain, char[][] dnaChain, String dnaKey, boolean dnaChainValid) {
        LenghtChain = lenghtChain;
        DnaChain = dnaChain;
        this.dnaKey = dnaKey;
        this.dnaChainValid = dnaChainValid;
    }

    public DnaVo(int lenghtChain, char[][] dnaChain, String dnaKey, boolean dnaChainValid, int mutantToken, boolean isMutant) {
        LenghtChain = lenghtChain;
        DnaChain = dnaChain;
        this.dnaKey = dnaKey;
        this.dnaChainValid = dnaChainValid;
        this.mutantToken = mutantToken;
        this.isMutant = isMutant;
    }

    public int getMutantToken() {
        return mutantToken;
    }

    public void setMutantToken(int mutantToken) {
        this.mutantToken = mutantToken;
    }

    public boolean getIsMutant() {
        return isMutant;
    }

    public void setIsMutant(boolean isMutant) {
        this.isMutant = isMutant;
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
