package com.meli.pruebatecnica.mutantdna.service;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public interface JsonParserUtils {
    String [] JsonBodytoStringArray(JSONArray array);
}
