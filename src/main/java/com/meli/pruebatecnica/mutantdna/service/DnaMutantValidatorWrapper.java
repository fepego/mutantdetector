package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import org.springframework.stereotype.Service;

@Service
public interface DnaMutantValidatorWrapper {
    boolean CheckInputDnaMatrixContainsMutantGenome(String [] dnaChainToEval) throws WrapperException;
}
