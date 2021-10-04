package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.exception.InputDnaChainException;
import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import com.meli.pruebatecnica.mutantdna.service.DnaChainValidator;
import com.meli.pruebatecnica.mutantdna.service.DnaMutantValidatorWrapper;
import com.meli.pruebatecnica.mutantdna.service.MutantPatternDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DnaMutantValidatorWrapperImp implements DnaMutantValidatorWrapper {

    @Autowired
    DnaChainValidator dnaInputValidatorService;
    @Qualifier("MutantHorizontalDetectorService")
    @Autowired
    MutantPatternDetectorService horizontalPatternDetectorService;
    @Qualifier("MutantVerticalPatternDetectorService")
    @Autowired
    MutantPatternDetectorService verticalPatternDetectorService;
    @Qualifier("MutantDiagonalPatternDetectorService")
    @Autowired
    MutantPatternDetectorService diagonalPatternDetectorService;

    @Override
    public boolean CheckInputDnaMatrixContainsMutantGenome(String[] dnaChainToEval) throws WrapperException {
        DnaVo parsedDnaChainInput = new DnaVo();
        try {
            parsedDnaChainInput = dnaInputValidatorService.VerifyDnaChainIsValid(dnaChainToEval);
            horizontalPatternDetectorService.DetectMutantPatternOnDnaMatrix(parsedDnaChainInput);
            if(parsedDnaChainInput.getIsMutant())
                return parsedDnaChainInput.getIsMutant();
            verticalPatternDetectorService.DetectMutantPatternOnDnaMatrix(parsedDnaChainInput);
            if(parsedDnaChainInput.getIsMutant())
                return parsedDnaChainInput.getIsMutant();
            diagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(parsedDnaChainInput);

        } catch (InputDnaChainException dnaEx) {
            throw new WrapperException("Dna Chain is not valid");
        }
        catch (Exception e) {
            throw new WrapperException("Error processing Dna");
        } finally {
            return parsedDnaChainInput.getIsMutant();
        }

    }
}
