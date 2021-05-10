package com.hashedin.assignments.NetflixRestApiAssignment;

import com.hashedin.assignments.NetflixRestApiAssignment.controller.NetflixDataShowController;
import com.sun.jdi.event.ExceptionEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NetflixRestApiAssignmentApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private NetflixDataShowController netflixDataShowController;

	@Test
	void contextLoads() throws  Exception {
		Assertions.assertThat(netflixDataShowController).isNotNull();
	}

	@Test
	void apiShouldReturnData() throws Exception{
		Assertions.assertThat(this.testRestTemplate.getForEntity("http://localhost:"
				+port+"/tvshows",String.class)).isNotNull();

	}

}
