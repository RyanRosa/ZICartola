package com.zicartola.ZICartola;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.zicartola.ZICartola.entites.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ZiCartolaApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testInsertUserSucess() {
		User user = new User( "ryan", "123");
		webTestClient
		.post()
		.uri("/users")
		.bodyValue(user)
		.exchange()
		.expectStatus().isCreated();
	}
	
	@Test
	void testInsertUserNullFail(){
		User user = new User("", "");
		webTestClient
		.post()
		.uri("/users")
		.bodyValue(user)
		.exchange()
		.expectStatus().is5xxServerError();
	}
	
	@Test
	void testFindAllUsersSucess() {		
		webTestClient
		.get()
		.uri("/users")
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$").isArray()
		.jsonPath("$.length()").isEqualTo(4);
	}
	
	@Test
	void testFindByIdSucess() {
		webTestClient
		.get()
		.uri("/users/1")
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.id").isEqualTo(1)
		.jsonPath("$.name").isEqualTo("Ryan");
	}
	
	@Test
	void testFindByIdNotExistFail() {
		webTestClient
		.get()
		.uri("/users/99")
		.exchange()
		.expectStatus().is5xxServerError();
	}	

}
