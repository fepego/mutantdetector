package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.serviceImp.DnaMutantGnomeChainValidatorImp;
import com.meli.pruebatecnica.mutantdna.serviceImp.MutantDiagonalsPatternDetectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MutantDiagonalsPatternDetectionServiceTest {


    @InjectMocks
    DnaMutantGnomeChainValidator DnaChainValidator = new DnaMutantGnomeChainValidatorImp();
    @InjectMocks
    MutantPatternDetectorService DiagonalPatternDetectorService = new MutantDiagonalsPatternDetectionService(DnaChainValidator);
    @Test
    final void WhenMainDiagonalsContainsMutantGenomeThenReturnTrue () {
        char[][] dnaMatrix = {{'A','C','G','T','C'},{'A','C','G','C','A'}, {'T','T','C','A','G'},
                {'A','C','G','C','A'}, {'A','T','T','C','C'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(2, inputValue.getMatchedMutantDnaTokens());
        assertEquals(true,inputValue.getIsMutant());
    }
    @Test
    final void WhenMainDiagonalsContainsOneMutantGenomeThenReturnFalse () {
        char[][] dnaMatrix = {{'A','C','G','T','C'},{'A','T','G','C','A'}, {'T','T','C','A','G'},
                {'A','C','G','C','A'}, {'A','T','T','C','C'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(1, inputValue.getMatchedMutantDnaTokens());
        assertEquals(false,inputValue.getIsMutant());
    }
    @Test
    final void GivenAUnpairDnaMatrixWhenColumnsDiagonalsContainsMorethanOneMutantGnomeThenReturnTrue() {
        char[][] dnaMatrix = {{'A','T','G','T','A'}, {'C','C','T','A','G'},{'A','T','G','T','C'},
                {'T','G','G','C','T'}, {'G','A','G','T','A'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(2, inputValue.getMatchedMutantDnaTokens());
        assertEquals(true,inputValue.getIsMutant());
    }
    @Test
    final void GivenApairDnaMatrixWhenColumnsDiagonalsContainsMorethanOneMutantGnomeThenReturnTrue() {
        char[][] dnaMatrix = {{'A','T','G','T','A','C'}, {'T','C','T','A','G','A'},{'T','C','A','T','C','C'},
                {'A','A','T','G','T','C'}, {'C','T','T','G','C','A'}, {'C', 'T', 'T','G','C','A'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(2, inputValue.getMatchedMutantDnaTokens());
        assertEquals(true,inputValue.getIsMutant());
    }
    @Test
    final void GivenApairDnaMatrixWhenRowsDiagonalsContainsMorethanOneMutantGnomeThenReturnTrue() {
        char[][] dnaMatrix = {{'A','T','C','G','A','C'},{'A','C','C','A','A','T'}, {'C','T','C','G','T','A'},
                {'A','T','T','T','C','C'}, {'C','A','T','T','G','T'}, {'C','A','T','T','T','A'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(2, inputValue.getMatchedMutantDnaTokens());
        assertEquals(true,inputValue.getIsMutant());
    }

    @Test
    final void GivenAUnpairDnaMatrixWhenRowsDiagonalsContainsMorethanOneMutantGnomeThenReturnTrue() {
        char[][] dnaMatrix = {{'A','T','C','G','A'}, {'C','G','T','A','C'}, {'T','C','T','C','T'},
                {'G','G','C','T','A'}, {'A','C','G','C','C'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(2, inputValue.getMatchedMutantDnaTokens());
        assertEquals(true,inputValue.getIsMutant());
    }
    @Test
    final void GivenAUnpairDnaMatrixWithoutMutantGnomeThenReturnFalse() {
        char[][] dnaMatrix = {{'A','T','C','G','A'}, {'T','G','T','A','T'}, {'T','C','T','C','T'},
                {'G','G','C','T','A'}, {'A','C','G','C','C'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(0, inputValue.getMatchedMutantDnaTokens());
        assertEquals(false,inputValue.getIsMutant());
    }
    @Test
    final void GivenApairDnaMatrixWithoutMutantGenomeThenReturnFalse() {
        char[][] dnaMatrix = {{'A','T','C','G','A','C'},{'A','C','C','A','A','T'}, {'C','A','C','G','A','A'},
                {'A','T','T','T','C','C'}, {'C','A','T','T','G','T'}, {'C','A','T','T','T','A'}};
        DnaVo inputValue = new DnaVo(dnaMatrix.length, dnaMatrix, "", true);
        DiagonalPatternDetectorService.DetectMutantPatternOnDnaMatrix(inputValue);
        assertEquals(0, inputValue.getMatchedMutantDnaTokens());
        assertEquals(false,inputValue.getIsMutant());
    }

}
