package com.meli.pruebatecnica.mutantdna.utils;

import com.meli.pruebatecnica.mutantdna.service.JsonParserUtils;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public class JsonParserUtilsImp implements JsonParserUtils {

    @Override
    public String[] JsonBodytoStringArray(JSONArray array) {
        if (array == null)
            return null;

        String[] arr = new String[array.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.optString(i);
        }
        return arr;
    }
}
