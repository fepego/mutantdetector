package com.meli.pruebatecnica.mutantdna.utils;

import com.meli.pruebatecnica.mutantdna.entity.DnaVo;
import com.meli.pruebatecnica.mutantdna.service.DnaChainValidator;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class DnaValidator implements DnaChainValidator {
    private final int subTokenLength = 4;
    private static final Pattern DNA_CHARS_BASE_ELEMENTS = Pattern.compile("^[ACGT]*$");
    @Override
    public DnaVo VerifyDnaChainIsValid(String [] dnaChain) throws Exception {
        DnaVo dnaVo = new DnaVo();
        dnaVo.setLenghtChain(dnaChain.length);
        char [][] dnaMatrix = new char[dnaChain.length][dnaChain.length];
        StringBuilder dnaIDKey = new StringBuilder();
        for(int i = 0; i < dnaChain.length; i++) {
            String dnaToken = dnaChain[i].toUpperCase();
            if(IsTokenWithCorrectLength(dnaChain.length, dnaToken) &&  IsDnaTokenWithCorrectElements(dnaToken)) {
                dnaIDKey.append(String.format("%s-",dnaToken));
                dnaMatrix[i] = dnaToken.toCharArray();
            } else {
                throw new Exception("Dna Chain cannot be process because the string is not well formed");
            }
        }
        dnaVo.setDnaChain(dnaMatrix);
        dnaVo.setDnaKey(dnaIDKey.deleteCharAt(dnaIDKey.length()-1).toString());
        return dnaVo;
    }

    @Override
    public int GetMutantSequenceCounter(String DnaToken) {
        int mutantMatch = 0;
        for(int i = 0; i< DnaToken.length(); i++) {
            if(i+(subTokenLength) <= DnaToken.length()) {
                if(mutantMatch(DnaToken.substring(i, i+subTokenLength))){
                    mutantMatch ++;
                }
            } else {
                return mutantMatch;
            }

        }
        return mutantMatch;
    }
    private boolean mutantMatch(String dnaSubToken) {
        String regexToken = String.format("(?:%s{%d})",dnaSubToken.charAt(0), subTokenLength);
        return Pattern.matches(regexToken, dnaSubToken);
    }

    private boolean IsTokenWithCorrectLength(int length, String dnaToken) {
        return dnaToken.length() == length;
    }
    private boolean IsDnaTokenWithCorrectElements(String dnaToken) {
        return DNA_CHARS_BASE_ELEMENTS.matcher(dnaToken).matches();
    }
}
