package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import org.springframework.stereotype.Service;

@Service
public interface DnaChainValidator {

    DnaVo VerifyDnaChainIsValid(String[] dnaChain) throws Exception;
}
