package com.meli.pruebatecnica.mutantdna.utils;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.service.DnaChainValidator;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class dnaChainValidatorTest {

    /**
     * DNA Input Validator Service
     */
    @InjectMocks
    private DnaChainValidator dnaInputValidator = new DnaValidator();

    private static final String[] correctDnaChain = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private static final String[] correctLowerCaseDnaChain = {"atgcga","CagtGc","TTATGT","agAAgg","CCCCTA","TCACTG"};
    private static final String [] DnaChainWithNonAllowedChars  = {"ATzCGA","CAGzGC","TTzTGT","AGAzGG","CCzCTA","TCAzTG"};
    private char[][] dnaMatrix;
    private String dnaKey;

    /**
     * Given: a Dna Chain
     * when: The input chain is valid
     * Then: return a dnaVo with a matrix, Dnakey and length
     * @throws Exception: If dna Chain contains errors
     */
    @Test
    final void GivenCorrectDnaChainInStringArrayThenReturnDnaVO() throws  Exception {
        char[] [] dnaMatrix = { {'A','T','G','C','G','A'}, {'C','A','G','T','G','C'} ,
                {'T','T','A','T','G','T'}, {'A','G','A','A','G','G'}, {'C','C','C','C','T','A'},
                {'T','C','A','C','T','G'}};
        String dnaKey = "ATGCGA-CAGTGC-TTATGT-AGAAGG-CCCCTA-TCACTG";

        DnaVo serviceResponse= dnaInputValidator.VerifyDnaChainIsValid(correctDnaChain);
        assertEquals(serviceResponse.getLenghtChain(), correctDnaChain.length);
        for (int i = 0; i < correctDnaChain.length ; i++) {
            assertEquals(correctDnaChain[i],new String(serviceResponse.getDnaChain()[i]));
        }
        assertEquals(dnaKey, serviceResponse.getDnaKey());
    }

    @Test
    final void GivenWrongDnaChainInStringWithDiffAllowedCharsThenThorowException() throws Exception {

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            dnaInputValidator.VerifyDnaChainIsValid(DnaChainWithNonAllowedChars);
        }).withMessage("Dna Chain cannot be process because the string is not well formed");

    }

}
