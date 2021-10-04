package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import com.meli.pruebatecnica.mutantdna.serviceImp.DnaMutantValidatorWrapperImp;
import com.meli.pruebatecnica.mutantdna.utils.DnaValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutantValidatorWrapperTest {

    @InjectMocks
    DnaMutantValidatorWrapper dnaValidatorWrapperService = new DnaMutantValidatorWrapperImp();
    @Mock
    @Qualifier("MutantVerticalPatternDetectorService")
    MutantPatternDetectorService verticalPatternDetectorService;
    @Mock
    @Qualifier("MutantDiagonalPatternDetectorService")
    MutantPatternDetectorService diagonalPatternDetectorService;
    @Mock
    @Qualifier("MutantHorizontalDetectorService")
    MutantPatternDetectorService horizontalPatternDetectorService;
    @Mock
    DnaChainValidator dnaInputValidatorService = new DnaValidator();

    final String[] ValidMutantDnaChain = {"TTGCGA","CAATAC","TTATGT","AGAAGG","ACCCTA","TCACTG"};
    final String[] NonValidMutantDnaChain = {"TTGCGAsf","CAATAC","TTATGT","AGAAGG","ACCCTA","TCACTG"};
    @Test
    final void GivenAValidDnaChainWithoutMutantGenomeThenReturnFalse () throws Exception {
        char[][] dnaMatrix = {{'T', 'T', 'G', 'C', 'G', 'A'}, {'C', 'A', 'A', 'T', 'A', 'C'},
                {'T', 'T', 'A', 'T', 'G', 'T'}, {'A', 'G', 'A', 'A', 'G', 'G'}, {'A', 'C', 'C', 'C', 'T', 'A'},
                {'T', 'C', 'A', 'C', 'T', 'G'}};
        String dnaKey = "TTGCGA-CAATAC-TTATGT-AGAAGG-ACCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        when(dnaInputValidatorService.VerifyDnaChainIsValid(Mockito.any(String[].class))).thenReturn(inputValue);

        boolean response = dnaValidatorWrapperService.CheckInputDnaMatrixContainsMutantGenome(ValidMutantDnaChain);
        assertEquals(response, false);
    }
    @Test
    final void GivenAValidDnaChainWithMutantGenomeThenReturnTrue () throws Exception {
        char[][] dnaMatrix = {{'T', 'T', 'G', 'C', 'G', 'A'}, {'C', 'A', 'A', 'T', 'A', 'C'},
                {'T', 'T', 'A', 'T', 'G', 'T'}, {'A', 'G', 'A', 'A', 'G', 'G'}, {'A', 'C', 'C', 'C', 'T', 'A'},
                {'T', 'C', 'A', 'C', 'T', 'G'}};
        String dnaKey = "TTGCGA-CAATAC-TTATGT-AGAAGG-ACCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        inputValue.setIsMutant(true);
        inputValue.setMatchedMutantDnaTokens(4);
        when(dnaInputValidatorService.VerifyDnaChainIsValid(Mockito.any(String[].class))).thenReturn(inputValue);

        boolean response = dnaValidatorWrapperService.CheckInputDnaMatrixContainsMutantGenome(ValidMutantDnaChain);
        assertEquals(response, true);
    }


}
