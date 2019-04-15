package br.com.fiap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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
		List<Cliente> lista = dao.listarPorNome("T");
		assertNotNull(lista);
		assertEquals(2, lista.size());
		
	}
	
	@Test
	void listarPorEstado() {
		List<Cliente> lista = dao.listarPorEstado("SP");
		assertNotNull(lista);
		assertEquals(2, lista.size());
		
	}
	
	@Test
	void buscarPorDias() {
		List<Cliente> lista = dao.listarPorDiasReserva(10);
		assertNotNull(lista);
	}
	
	@Test
	void buscarPorNomeCidade() {
		List<Cliente> lista = dao.buscar("Leandro", "Lon");
		assertNotNull(lista);
		for (Cliente cliente : lista) {
			assertTrue(cliente.getNome().contains("Leandro") && 
					cliente.getEndereco().getCidade().getNome().contains("Lon"));
		}
	}
	
	@Test
	void buscarPorEstados() {
		List<String> estados = new ArrayList<>();
		estados.add("SP");
		estados.add("RS");
		List<Cliente> lista = dao.buscarPorEstados(estados);
		assertNotNull(lista);
		assertEquals(2, lista.size());
		
		for (Cliente cliente : lista) {
			assertTrue(estados.contains(cliente.getEndereco().getCidade().getUf()));
		}
	}

}
