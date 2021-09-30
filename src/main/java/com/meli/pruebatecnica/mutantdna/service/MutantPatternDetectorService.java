package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import org.springframework.stereotype.Service;

@Service
public interface MutantPatternDetectorService {
    public void DetectMutantPatternOnDnaMatrix(DnaVo DnaInput);
}
