package com.meli.pruebatecnica.mutantdna.rest;

import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import com.meli.pruebatecnica.mutantdna.service.DnaMutantValidatorWrapper;
import com.meli.pruebatecnica.mutantdna.service.JsonParserUtils;
import com.meli.pruebatecnica.mutantdna.service.MutantStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MutantController extends Exception {

    @Autowired
    DnaMutantValidatorWrapper dnaMutantValidatorService;
    @Autowired
    JsonParserUtils jsonParserService;
    @Autowired
    MutantStatsService statsServices;

    @PostMapping("/mutant")
    @ResponseBody
    public ResponseEntity<Object> mutant(@RequestBody String body, HttpServletResponse response) throws Exception {
        try {
            JSONObject input = new JSONObject(body);
            String[] dna = jsonParserService.JsonBodytoStringArray(input.getJSONArray("dna"));
            if(dnaMutantValidatorService.CheckInputDnaMatrixContainsMutantGenome(dna)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN );
            }

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping(path = {"/stats"})
    @ResponseBody
    public ResponseEntity<Object> stats() {
        try {
            JSONObject stats = statsServices.GetMutantStats();
            return new ResponseEntity<Object>(stats.toString(), HttpStatus.OK);
        } catch (WrapperException wrapperException) {
            wrapperException.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, wrapperException.getMessage(), wrapperException);
        }
    }

}
