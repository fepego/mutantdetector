package com.meli.pruebatecnica.mutantdna.entity;

public class MutantSequenceValidator {
    int totalMatchFound;
    int mutantSequenceMatch;
    int inverseMutantSequenceMatch;
    char dnaLetter;
    char inverseDnaLetter;

    public MutantSequenceValidator(char dnaLetter, char dnaInverseLetter) {
        this.totalMatchFound = 0;
        this.mutantSequenceMatch = 0;
        this.inverseMutantSequenceMatch = 0;
        this.dnaLetter = dnaLetter;
        this.inverseDnaLetter = dnaInverseLetter;
    }

    public int getTotalMatchFound() {
        return totalMatchFound;
    }

    public void setTotalMatchFound(int totalMatchFound) {
        this.totalMatchFound = totalMatchFound;
    }

    public int getMutantSequenceMatch() {
        return mutantSequenceMatch;
    }

    public void setMutantSequenceMatch(int mutantSequenceMatch) {
        this.mutantSequenceMatch = mutantSequenceMatch;
    }

    public int getInverseMutantSequenceMatch() {
        return inverseMutantSequenceMatch;
    }

    public void setInverseMutantSequenceMatch(int inverseMutantSequenceMatch) {
        this.inverseMutantSequenceMatch = inverseMutantSequenceMatch;
    }

    public char getDnaLetter() {
        return dnaLetter;
    }

    public void setDnaLetter(char dnaLetter) {
        this.dnaLetter = dnaLetter;
    }

    public char getInverseDnaLetter() {
        return inverseDnaLetter;
    }

    public void setInverseDnaLetter(char inverseDnaLetter) {
        this.inverseDnaLetter = inverseDnaLetter;
    }
}
