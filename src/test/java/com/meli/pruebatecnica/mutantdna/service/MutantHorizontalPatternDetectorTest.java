package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.serviceImp.MutantHorizontalDetector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutantHorizontalPatternDetectorTest {

    @InjectMocks
    MutantPatternDetectorService horizontalPatternDetectorService = new MutantHorizontalDetector();
    @Mock
    private DnaChainValidator dnaInputValidatorService ;


    @Test
    final void WhenDnaHorizontalChainTokenContainsMutantGenomeThenReturnTrue() {
        char[] [] dnaMatrix = { {'A','T','G','C','G','A'}, {'C','A','A','A','A','C'} ,
                {'T','T','A','T','G','T'}, {'A','G','A','A','G','G'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAAAAC-TTATGT-AGAAGG-CCCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        when(dnaInputValidatorService.GetMutantSequenceCounter(Mockito.any(String.class))).thenReturn(1);
        horizontalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);

        assertEquals(inputValue.getMatchedMutantDnaTokens(), 2);
        assertEquals(inputValue.getIsMutant(), true);
    }
    @Test
    final void WhenDnaHorizontalChainTokenNoContainsMutantGenomeThenReturnFalse() {
        char[] [] dnaMatrix = { {'A','T','G','C','G','A'}, {'C','A','A','A','A','C'} ,
                {'T','T','A','T','G','T'}, {'A','G','A','A','G','G'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAAAAC-TTATGT-AGAAGG-CCCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        when(dnaInputValidatorService.GetMutantSequenceCounter(Mockito.any(String.class))).thenReturn(0);
        horizontalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);

        assertEquals(inputValue.getMatchedMutantDnaTokens(), 0);
        assertEquals(inputValue.getIsMutant(), false);
    }
    @Test
    final void WhenDnaHorizontalChainTokenContainsOneMutantGenomeThenReturnFalse() {
        char[] [] dnaMatrix = { {'A','T','G','C','G','A'}, {'C','A','A','A','A','C'} ,
                {'T','T','A','T','G','T'}, {'A','G','A','A','G','G'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAAAAC-TTATGT-AGAAGG-CCCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        when(dnaInputValidatorService.GetMutantSequenceCounter(Mockito.any(String.class))).thenReturn(1,0);
        horizontalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);

        assertEquals(inputValue.getMatchedMutantDnaTokens(), 1);
        assertEquals(inputValue.getIsMutant(), false);
    }
}
