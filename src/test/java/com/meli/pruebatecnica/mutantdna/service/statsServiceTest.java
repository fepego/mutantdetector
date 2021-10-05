package com.meli.pruebatecnica.mutantdna.service;

import com.meli.pruebatecnica.mutantdna.exception.WrapperException;
import com.meli.pruebatecnica.mutantdna.repository.DnaTestRepository;
import com.meli.pruebatecnica.mutantdna.serviceImp.MutantStatsServiceImp;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class statsServiceTest {

    @Mock
    DnaTestRepository mutantRegistryPersistenceService;
    @InjectMocks
    MutantStatsService statsService = new MutantStatsServiceImp();

    @Test
    final void GivenZeroMutantsAndHumansResultsThenReturnTheJsonWithoutRatio() throws WrapperException, JSONException {
        when(mutantRegistryPersistenceService.GetDnaResultsCount(Mockito.any(boolean.class))).thenReturn(0);
        JSONObject response = statsService.GetMutantStats();
        assertEquals(response.getDouble("ratio"), 0);
    }
    @Test
    final void GivenZeroMutantsAndHumansResultsThenReturnTheJsonWithRatioEqualsToOne() throws WrapperException, JSONException {
        when(mutantRegistryPersistenceService.GetDnaResultsCount(Mockito.any(boolean.class))).thenReturn(2);
        JSONObject response = statsService.GetMutantStats();
        assertEquals(response.getDouble("ratio"), 1);
    }
}
