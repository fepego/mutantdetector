package com.meli.pruebatecnica.mutantdna.serviceImp;

import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import com.meli.pruebatecnica.mutantdna.repository.DnaTestRepository;
import com.meli.pruebatecnica.mutantdna.service.MutantStatsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantStatsServiceImp implements MutantStatsService {

    @Autowired
    DnaTestRepository mutantRegistryPersistenceService;

    @Override
    public JSONObject GetMutantStats() throws WrapperException {
        try {
            JSONObject statsJson = new JSONObject();
            int nonmutantCount = mutantRegistryPersistenceService.GetDnaResultsCount(false);
            int mutantCount = mutantRegistryPersistenceService.GetDnaResultsCount(true);
            statsJson.put("count_mutant_dna", nonmutantCount);
            statsJson.put("count_human_dna", mutantCount);
            statsJson.put("ratio", (double)GetRatioMutant(nonmutantCount, mutantCount));

            return statsJson;
        } catch (Exception ex) {
             throw new WrapperException("Error getting stats, please contact administrator");
        }
    }

    float GetRatioMutant(int nonmutant, int mutantcount) {
        if(nonmutant == 0 || mutantcount == 0 ) return 0;
        return Float.valueOf((float) mutantcount) / ((float) nonmutant);
    }
}
