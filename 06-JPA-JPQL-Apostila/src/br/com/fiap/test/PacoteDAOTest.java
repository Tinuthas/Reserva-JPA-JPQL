package br.com.fiap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.dao.TransporteDAO;
import br.com.fiap.dao.impl.PacoteDAOImpl;
import br.com.fiap.dao.impl.TransporteDAOImpl;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

class PacoteDAOTest {
	
	public static PacoteDAO pacoteDao;
	public static TransporteDAO transporteDao;
	public static EntityManager em;
	
	@BeforeAll
	public static void init() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		pacoteDao = new PacoteDAOImpl(em);
		transporteDao = new TransporteDAOImpl(em);
	}
	
	@Test
	void listarPorTransporte() { 
		List<Pacote> lista = pacoteDao.listarPorTransporte(em.find(Transporte.class, 1));
		assertEquals(2, lista.size());
	}
	
	@Test
	void buscarPacotesPorData() { 
		Calendar inicio = new GregorianCalendar(2017, Calendar.JANUARY, 1);
		Calendar fim = new GregorianCalendar(2018, Calendar.JANUARY, 1);
		
		List<Pacote> lista = pacoteDao.buscarPorDatas(inicio, fim);
		for (Pacote pacote : lista) {
			assertTrue(pacote.getDataSaida().after(inicio) && 
					pacote.getDataSaida().before(fim));
		}
		
	}
	
	@Test
	void buscarSomaPrecoQuantidadePorTransporte() {
		double somaPreco = pacoteDao.somaPrecoPacotesPorTransporte(em.find(Transporte.class, 1));
		assertNotNull(somaPreco);
	}

}
