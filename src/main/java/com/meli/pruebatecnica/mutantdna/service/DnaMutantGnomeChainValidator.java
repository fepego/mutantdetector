package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.entity.MutantSequenceValidator;
import org.springframework.stereotype.Service;

@Service
public interface DnaMutantGnomeChainValidator {
    void CheckLetterMatchSequence(MutantSequenceValidator sequenceController,char dnaLetterComp, char inverseDnaLetterComp);
    void CheckLeftToRightMutantDnaSequence(MutantSequenceValidator sequenceController, char dnaLetterComp);
    void CheckRightToLeftMutantDnaSequence(MutantSequenceValidator sequenceController, char dnaLetterComp);

}
