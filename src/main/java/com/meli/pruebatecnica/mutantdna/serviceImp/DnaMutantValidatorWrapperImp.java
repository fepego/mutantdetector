package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.entity.DnaTest;
import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.exception.InputDnaChainException;
import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import com.meli.pruebatecnica.mutantdna.repository.DnaTestRepository;
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
    @Autowired
    DnaTestRepository mutantRegistryPersistenceService;

    @Override
    public boolean CheckInputDnaMatrixContainsMutantGenome(String[] dnaChainToEval) throws WrapperException {
        DnaVo parsedDnaChainInput = new DnaVo();
        try {
            parsedDnaChainInput = dnaInputValidatorService.VerifyDnaChainIsValid(dnaChainToEval);
            boolean isAlreadytested = GetDnaTestResultsFromDB(parsedDnaChainInput.getDnaKey());
            if(isAlreadytested) parsedDnaChainInput.setIsMutant(true);
            else
            ProcessDnaChainForSearchMutantGenome(parsedDnaChainInput);

        } catch (InputDnaChainException dnaEx) {
            throw new WrapperException("Dna Chain is not valid");
        }
        catch (Exception e) {
            throw new WrapperException("Error processing Dna");
        } finally {
            return parsedDnaChainInput.getIsMutant();
        }

    }

    private boolean GetDnaTestResultsFromDB(String dnaKey) {
        DnaTest result = mutantRegistryPersistenceService.findByDnaKey(dnaKey);
        if(result != null)
            return result.isIsmutant();
        else return false;
    }

    private void ProcessDnaChainForSearchMutantGenome(DnaVo parsedDnaChainInput) {
        horizontalPatternDetectorService.DetectMutantPatternOnDnaMatrix(parsedDnaChainInput);
        if(parsedDnaChainInput.getIsMutant()) {
            return;
        }
        verticalPatternDetectorService.DetectMutantPatternOnDnaMatrix(parsedDnaChainInput);
        if(parsedDnaChainInput.getIsMutant()) {
            return;
        }
        diagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(parsedDnaChainInput);
        PersistEvaluatedChainInDB(parsedDnaChainInput);
        return;
    }

    private void PersistEvaluatedChainInDB(DnaVo parsedDnaChainInput) {
        DnaTest newRegistry = new DnaTest();
        newRegistry.setDnaKey(parsedDnaChainInput.getDnaKey());
        newRegistry.setIsmutant(parsedDnaChainInput.getIsMutant());

        mutantRegistryPersistenceService.save(newRegistry);
    }
}
