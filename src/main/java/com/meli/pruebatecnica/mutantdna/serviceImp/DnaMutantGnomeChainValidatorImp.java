package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.entity.MutantSequenceValidator;
import com.meli.pruebatecnica.mutantdna.service.DnaMutantGnomeChainValidator;
import org.springframework.stereotype.Service;

@Service
public class DnaMutantGnomeChainValidatorImp implements DnaMutantGnomeChainValidator {

    @Override
    public void CheckLetterMatchSequence(MutantSequenceValidator sequenceController, char dnaLetterComp, char inverseDnaLetterComp) {
        CheckLeftToRightMutantDnaSequence(sequenceController, dnaLetterComp);
        CheckRightToLeftMutantDnaSequence(sequenceController, inverseDnaLetterComp);
        return;
    }

    @Override
    public void CheckLeftToRightMutantDnaSequence(MutantSequenceValidator sequenceController, char dnaLetterComp) {
        if(sequenceController.getDnaLetter() == dnaLetterComp) {
            sequenceController.setMutantSequenceMatch(sequenceController.getMutantSequenceMatch() +1);
            if(sequenceController.getMutantSequenceMatch() == 4) {
                sequenceController.setTotalMatchFound(sequenceController.getTotalMatchFound() +1);
                sequenceController.setMutantSequenceMatch(0);
            }
        } else {
            sequenceController.setDnaLetter(dnaLetterComp);
            sequenceController.setMutantSequenceMatch(1);
        }
    }

    @Override
    public void CheckRightToLeftMutantDnaSequence(MutantSequenceValidator sequenceController, char dnaLetterComp) {
        if(sequenceController.getInverseDnaLetter() == dnaLetterComp) {
            sequenceController.setInverseMutantSequenceMatch(sequenceController.getInverseMutantSequenceMatch() +1);
            if(sequenceController.getInverseMutantSequenceMatch() == 4) {
                sequenceController.setTotalMatchFound(sequenceController.getTotalMatchFound() +1);
                sequenceController.setInverseMutantSequenceMatch(0);
            }
        } else {
            sequenceController.setInverseDnaLetter(dnaLetterComp);
            sequenceController.setInverseMutantSequenceMatch(1);
        }
    }
}
