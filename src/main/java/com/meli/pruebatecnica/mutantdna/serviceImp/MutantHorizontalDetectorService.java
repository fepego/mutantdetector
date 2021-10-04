package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.service.DnaChainValidator;
import com.meli.pruebatecnica.mutantdna.service.MutantPatternDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("MutantHorizontalDetectorService")
public class MutantHorizontalDetectorService implements MutantPatternDetectorService {

    @Autowired
    private DnaChainValidator dnaValidatorService;

    @Override
    public void DetectMutantPatternOnDnaMatrix(DnaVo dnaInput) {
        int matchMutantSequence = 0;
        for(char [] dnaChainToken : dnaInput.getDnaChain()) {
            matchMutantSequence += dnaValidatorService.GetMutantSequenceCounter(String.valueOf(dnaChainToken));
            if(matchMutantSequence > 1) break;
        }
        dnaInput.setMatchedMutantDnaTokens(dnaInput.getMatchedMutantDnaTokens() + matchMutantSequence);
        dnaInput.setIsMutant(matchMutantSequence > 1);
        return;
    }
}
