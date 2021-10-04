package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.entity.MutantSequenceValidator;
import com.meli.pruebatecnica.mutantdna.service.DnaMutantGnomeChainValidator;
import com.meli.pruebatecnica.mutantdna.service.MutantPatternDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantDiagonalsPatternDetectionService implements MutantPatternDetectorService {


    DnaMutantGnomeChainValidator DnaChainValidatorService;
    @Autowired
    public MutantDiagonalsPatternDetectionService(DnaMutantGnomeChainValidator dnaChainValidator) {
        DnaChainValidatorService = dnaChainValidator;
    }

    @Override
    public void DetectMutantPatternOnDnaMatrix(DnaVo dnaInput) {

        int mainDiagonalsCounter = CheckMaxDiagonalsMutantSequence(dnaInput);
        dnaInput.setMatchedMutantDnaTokens(mainDiagonalsCounter);
        if(mainDiagonalsCounter > 1) {
            dnaInput.setIsMutant(true);
            return;
        }
        int columnDiagonalsCounter = CheckColumnDiagonalsMutantSequence(dnaInput);
        dnaInput.setMatchedMutantDnaTokens(dnaInput.getMatchedMutantDnaTokens() + columnDiagonalsCounter);
        if(dnaInput.getMatchedMutantDnaTokens() > 1) {
            dnaInput.setIsMutant(true);
            return;
        }
        int rowsDiagonalsCounter = CheckRowsDiagonalsMutantSequence(dnaInput);
        dnaInput.setMatchedMutantDnaTokens(dnaInput.getMatchedMutantDnaTokens() + rowsDiagonalsCounter);
        if(dnaInput.getMatchedMutantDnaTokens() > 1) {
            dnaInput.setIsMutant(true);
            return;
        }

        return;
    }

    private int CheckMaxDiagonalsMutantSequence(DnaVo dnaInput) {
        MutantSequenceValidator sequenceController =
                new MutantSequenceValidator(dnaInput.getDnaChain()[0][0],
                        dnaInput.getDnaChain()[0][dnaInput.getLenghtChain()-1]);
        int inverseSequenceIterator = dnaInput.getLenghtChain() -1;

        for (int i = 0; i < dnaInput.getLenghtChain(); i++) {
            DnaChainValidatorService.CheckLetterMatchSequence(sequenceController, dnaInput.getDnaChain()[i][i],
                    dnaInput.getDnaChain()[i][inverseSequenceIterator]);
            if(sequenceController.getTotalMatchFound() > 1) break;
            inverseSequenceIterator --;
        }

        return sequenceController.getTotalMatchFound();
    }
    private int CheckColumnDiagonalsMutantSequence(DnaVo dnaInput) {
        MutantSequenceValidator sequenceController =
                new MutantSequenceValidator(dnaInput.getDnaChain()[0][1],
                        dnaInput.getDnaChain()[0][dnaInput.getLenghtChain()-2]);
        int middleMatrixDnaChain = dnaInput.getLenghtChain() / 2;
        int columnLimiter = dnaInput.getLenghtChain() % 2 == 0 ? middleMatrixDnaChain -1: middleMatrixDnaChain;
        int inverseSequenceIterator = dnaInput.getLenghtChain() -2;
        for (int i = 1; i < columnLimiter; i++) {
            int inverseColumn = inverseSequenceIterator;
            for (int j = i; j < dnaInput.getLenghtChain(); j++) {
                DnaChainValidatorService.CheckLetterMatchSequence(sequenceController, dnaInput.getDnaChain()[j-1][j],
                        dnaInput.getDnaChain()[j-1][inverseColumn]);
                if(sequenceController.getTotalMatchFound() > 1) break;
                inverseColumn--;
            }
            inverseSequenceIterator --;
        }
        return sequenceController.getTotalMatchFound();
    }
    private int CheckRowsDiagonalsMutantSequence(DnaVo dnaInput) {
        MutantSequenceValidator sequenceController =
                new MutantSequenceValidator(dnaInput.getDnaChain()[1][0],
                        dnaInput.getDnaChain()[1][dnaInput.getLenghtChain()-1]);
        int middleMatrixDnaChain = dnaInput.getLenghtChain() / 2;
        int rowLimiter = middleMatrixDnaChain;
        int inverseSequenceIterator = dnaInput.getLenghtChain()-1;
        for (int i = 1; i < rowLimiter; i++) {
            int inverseColumn = inverseSequenceIterator;
            for (int j = 0; j < dnaInput.getLenghtChain()- i; j++) {
                DnaChainValidatorService.CheckLetterMatchSequence(sequenceController, dnaInput.getDnaChain()[i+j][j],
                        dnaInput.getDnaChain()[i+j][inverseColumn]);
                if(sequenceController.getTotalMatchFound() > 1) break;
                inverseColumn--;
            }
            inverseSequenceIterator --;
        }
        return sequenceController.getTotalMatchFound();
    }
}



