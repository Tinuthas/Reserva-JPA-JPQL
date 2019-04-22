package br.com.fiap.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;

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
	
	@Test
	void buscarPorNomeSemDiferenciar() {
		List<Cliente> lista = dao.buscaNomeSemDiferencia("T");
		assertNotNull(lista);
		assertEquals(2, lista.size());
		
	}
	
	@Test
	void buscarCountClientePorEstado() {
		long count = dao.countCliente("SP");
		assertNotNull(count);
	}
	
	@Test
	void buscarPorCpf() {
		List<Cliente> lista = dao.buscaPorCpf("98728018736");
		assertNotNull(lista);
		for (Cliente c : lista) {
			assertEquals("98728018736", c.getCpf());
		}
		
	}

}
