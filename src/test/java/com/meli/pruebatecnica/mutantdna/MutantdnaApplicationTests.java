package com.meli.pruebatecnica.mutantdna;

import com.meli.pruebatecnica.mutantdna.rest.MutantController;
import com.meli.pruebatecnica.mutantdna.service.DnaChainValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {DnaChainValidator.class})
class MutantdnaApplicationTests {

	@Test
	void contextLoads() {

	}

}
