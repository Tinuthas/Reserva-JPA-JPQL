package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Reserva;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Integer> implements ClienteDAO {

	public ClienteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Cliente> listar() {
		TypedQuery<Cliente> query = em.createQuery("from Cliente", Cliente.class);

		return query.getResultList();
	}

	@Override
	public List<Cliente> listarPorNome(String nome) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :nomeCliente", Cliente.class);
		query.setParameter("nomeCliente", "%" + nome + "%");

		return query.getResultList();
	}

	@Override
	public List<Cliente> listarPorEstado(String uf) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.endereco.cidade.uf = :estado",
				Cliente.class);
		query.setParameter("estado", uf);
		return query.getResultList();
	}

	@Override
	public List<Cliente> listarPorDiasReserva(int dias) {
		TypedQuery<Cliente> query = em
				.createQuery("select c from Reserva r join r.cliente c where r.numeroDias = :qtdDias", Cliente.class);
		query.setParameter("qtdDias", dias);

		return query.getResultList();
	}

	@Override
	public List<Cliente> buscar(String nome, String cidade) {
		return em.createQuery("from Cliente c where c.nome like :n and c.endereco.cidade.nome like :c", Cliente.class)
				.setParameter("n", "" + nome + "").setParameter("c", "" + cidade + "").getResultList();
	}

	@Override
	public List<Cliente> buscarPorEstados(List<String> estados) {
		return em.createQuery("from Cliente c where c.endereco.cidade.uf in (:e)", Cliente.class)
				.setParameter("e", estados)
				.getResultList();
	}

}
