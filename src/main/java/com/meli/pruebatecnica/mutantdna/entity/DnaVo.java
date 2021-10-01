package com.meli.pruebatecnica.mutantdna.entity;

public class DnaVo {

    private int LenghtChain;
    private char[][] DnaChain;
    private String dnaKey;
    private boolean dnaChainValid;
    private int MatchedMutantDnaTokens;
    private boolean isMutant;

    public DnaVo() {
    }

    public DnaVo(int lenghtChain, char[][] dnaChain, String dnaKey, boolean dnaChainValid) {
        LenghtChain = lenghtChain;
        DnaChain = dnaChain;
        this.dnaKey = dnaKey;
        this.dnaChainValid = dnaChainValid;
        this.MatchedMutantDnaTokens = 0;
        this.isMutant = false;
    }

    public DnaVo(int lenghtChain, char[][] dnaChain, String dnaKey, boolean dnaChainValid, int mutantToken, boolean isMutant) {
        LenghtChain = lenghtChain;
        DnaChain = dnaChain;
        this.dnaKey = dnaKey;
        this.dnaChainValid = dnaChainValid;
        this.MatchedMutantDnaTokens = mutantToken;
        this.isMutant = isMutant;
    }

    public int getMatchedMutantDnaTokens() {
        return MatchedMutantDnaTokens;
    }

    public void setMatchedMutantDnaTokens(int matchedMutantDnaTokens) {
        this.MatchedMutantDnaTokens = matchedMutantDnaTokens;
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
