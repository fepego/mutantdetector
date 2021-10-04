package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface MutantStatsService {
    JSONObject GetMutantStats() throws WrapperException;
}
