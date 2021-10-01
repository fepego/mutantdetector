package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.serviceImp.MutantHorizontalDetectorService;
import com.meli.pruebatecnica.mutantdna.serviceImp.MutantVerticalPatternDetectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutantVerticalPatternDetectorServiceTest {
    @InjectMocks
    MutantPatternDetectorService verticalPatternDetectorService = new MutantVerticalPatternDetectorService();


    @Test
    final void WhenDnaVerticalChainTokenContainsMutantGenomeThenReturnTrue() {
        char[] [] dnaMatrix = { {'A','T','G','C','G','T'}, {'A','A','A','A','A','T'} ,
                {'A','T','A','T','G','T'}, {'A','G','A','A','G','T'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAAAAC-TTATGT-AGAAGG-CCCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        verticalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);

        assertEquals(inputValue.getMatchedMutantDnaTokens(), 2);
        assertEquals(inputValue.getIsMutant(), true);
    }
    @Test
    final void WhenDnaVerticalChainTokenNoContainsMutantGenomeThenReturnFalse() {
        char[] [] dnaMatrix = { {'A','T','G','C','G','A'}, {'C','A','A','A','A','C'} ,
                {'T','T','A','T','G','T'}, {'A','G','A','A','G','G'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAAAAC-TTATGT-AGAAGG-CCCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        verticalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);

        assertEquals(inputValue.getMatchedMutantDnaTokens(), 0);
        assertEquals(inputValue.getIsMutant(), false);
    }
    @Test
    final void WhenDnaVerticalChainTokenContainsOneMutantGenomeThenReturnFalse() {
        char[] [] dnaMatrix = { {'A','T','G','C','G','A'}, {'A','A','A','A','A','C'} ,
                {'A','T','A','T','G','T'}, {'A','G','A','A','G','G'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAAAAC-TTATGT-AGAAGG-CCCCTA-TCACTG";
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, dnaKey, true);
        verticalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);

        assertEquals(inputValue.getMatchedMutantDnaTokens(), 1);
        assertEquals(inputValue.getIsMutant(), false);
    }
}
