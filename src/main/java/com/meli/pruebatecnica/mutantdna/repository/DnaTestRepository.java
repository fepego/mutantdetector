package com.meli.pruebatecnica.mutantdna.repository;

import com.meli.pruebatecnica.mutantdna.entity.DnaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaTestRepository extends JpaRepository<DnaTest,Long> {
    @Query(value = "SELECT * FROM dna_test WHERE dna_key = ?1", nativeQuery = true)
    DnaTest findByDnaKey(String dnaKey);
    @Query(value="SELECT COUNT(*) FROM dna_test WHERE ismutant =?1", nativeQuery = true)
    int GetDnaResultsCount(boolean isMutant);
}
