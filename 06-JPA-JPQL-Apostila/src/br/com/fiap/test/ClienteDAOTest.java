package br.com.fiap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Reserva;

class ClienteDAOTest {
	
	public static ClienteDAO dao;
	
	@BeforeAll
	public static void init() {
		EntityManager em = EntityManagerFactorySingleton.getInstance()
				.createEntityManager();
		dao = new ClienteDAOImpl(em);
	}
	
	@Test
	void listar() {
		List<Cliente> lista = dao.listar();
		assertNotEquals(0, lista.size());
	}
	
	@Test
	void listarPorNome() {
		List<Cliente> lista = dao.listarPorNome("J");
		assertEquals(2, lista.size());
		
	}
	
	@Test
	void listarPorEstado() {
		List<Cliente> lista = dao.listarPorEstado("SP");
		assertEquals(1, lista.size());
		
	}
	
	@Test
	void buscarPorDias() {
		List<Cliente> lista = dao.listarPorDiasReserva(10);
		assertEquals(2, lista.size());
	}

}
