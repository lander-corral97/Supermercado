package com.ipartek.formacion.supermercado.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {

	Client client;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		client = new Client();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetIdString() {
		String id = null;
		client.setId(id);

		assertNull(client.getId());
		assertNull(client.getErrorId());

		id = " ";
		client.setId(id);

		assertNull(client.getId());
		assertNull(client.getErrorId());

		id = "vbunvbndci";
		client.setId(id);

		assertNotNull(client.getErrorId());

		id = "5";
		client.setId(id);

		assertEquals(5L, client.getId());
		assertNull(client.getErrorId());
	}

	@Test
	void testSetBirthDateString() {
		String birthDate = null;

		client.setBirthDate(birthDate);

		assertNull(client.getErrorBirthDate());
		assertNull(client.getBirthDate());

		birthDate = "";

		client.setBirthDate(birthDate);

		assertNull(client.getErrorBirthDate());
		assertNull(client.getBirthDate());

		birthDate = "hvgcvfbdn";

		client.setBirthDate(birthDate);

		assertNotNull(client.getErrorBirthDate());

		birthDate = "1990-11-12";

		client.setBirthDate(birthDate);

		assertNotNull(client.getBirthDate());
		assertEquals(1990, client.getBirthDate().getYear());
		assertEquals(Month.NOVEMBER, client.getBirthDate().getMonth());
		assertEquals(12, client.getBirthDate().getDayOfMonth());
		assertNull(client.getErrorBirthDate());
	}

	@Test
	void testSetCorrecto() {
		fail("Not yet implemented");
	}

	@Test
	void testSetIdLong() {

		Long id = null;
		client.setId(id);

		assertNull(client.getId());
		assertNull(client.getErrorId());

		id = -5L;
		client.setId(id);

		assertNotNull(client.getErrorId());

		id = 7L;
		client.setId(id);

		assertEquals(id, client.getId());
		assertNull(client.getErrorId());
	}

	@Test
	void testSetFirstName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLastName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCif() {
		String cif = null;

		client.setCif(cif);

		assertNull(client.getCif());
		assertNotNull(client.getErrorCif());

		cif = " ";

		client.setCif(cif);

		assertNull(client.getCif());
		assertNotNull(client.getErrorCif());

		cif = "B12345678";

		client.setCif(cif);

		assertNull(client.getErrorCif());
		assertEquals(cif, client.getCif());

		cif = "X1234567Z";

		client.setCif(cif);

		assertNull(client.getErrorCif());
		assertEquals(cif, client.getCif());

		cif = "12345678Z";

		client.setCif(cif);

		assertNull(client.getErrorCif());
		assertEquals(cif, client.getCif());

		cif = "B1234567B";

		client.setCif(cif);

		assertNotNull(client.getErrorCif());
		assertNull(client.getCif());

		cif = "123456789";

		client.setCif(cif);

		assertNotNull(client.getErrorCif());
		assertNull(client.getCif());
	}

	@Test
	void testSetBirthDateLocalDate() {
		LocalDate date = null;

		client.setBirthDate(date);

		assertNull(client.getBirthDate());
		assertNull(client.getErrorBirthDate());

		date = LocalDate.now().minusYears(18);

		client.setBirthDate(date);

		assertNull(client.getErrorBirthDate());

		date = date.plusDays(1);

		client.setBirthDate(date);

		assertNotNull(client.getErrorBirthDate());
	}

}
