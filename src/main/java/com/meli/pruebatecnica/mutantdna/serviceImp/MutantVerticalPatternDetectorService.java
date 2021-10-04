package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.service.DnaChainValidator;
import com.meli.pruebatecnica.mutantdna.service.MutantPatternDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("MutantVerticalPatternDetectorService")
public class MutantVerticalPatternDetectorService implements MutantPatternDetectorService {

    @Autowired
    DnaChainValidator dnaChainValidatorService;

    @Override
    public void DetectMutantPatternOnDnaMatrix(DnaVo dnaInput) {
        int mutantMatchSequence = 0;
        int mutantLetterCounter = 0;
        char mutantLetterEvaluator;
        for (int i = 0; i < dnaInput.getLenghtChain() ; i++) {
            mutantLetterEvaluator = dnaInput.getDnaChain()[i][0];
            for (int j = 0; j < dnaInput.getLenghtChain(); j++) {
                if(mutantLetterEvaluator ==  dnaInput.getDnaChain()[j][i]) {
                    mutantLetterCounter ++;
                    if(mutantLetterCounter == 4) {
                        mutantLetterCounter=0;
                        mutantMatchSequence ++;
                    }
                } else {
                    mutantLetterCounter = 0;
                    mutantLetterEvaluator = dnaInput.getDnaChain()[j][i];
                }
                if(mutantMatchSequence > 1) {
                    break;
                }
            }
        }
        dnaInput.setIsMutant(mutantMatchSequence > 1);
        dnaInput.setMatchedMutantDnaTokens(dnaInput.getMatchedMutantDnaTokens() + mutantMatchSequence);
        return;
    }
}
