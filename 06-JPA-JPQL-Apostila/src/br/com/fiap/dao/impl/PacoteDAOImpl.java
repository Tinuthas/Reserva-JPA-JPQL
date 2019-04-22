package br.com.fiap.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

public class PacoteDAOImpl extends GenericDAOImpl<Pacote,Integer> implements PacoteDAO{

	public PacoteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Pacote> listarPorTransporte(Transporte t) {
		TypedQuery<Pacote> query =  em.createQuery("from Pacote p where p.transporte = :trans", Pacote.class);
		query.setParameter("trans", t);
		return query.getResultList();
	}

	@Override
	public List<Pacote> buscarPorDatas(Calendar start, Calendar end) {
		return em.createQuery("from Pacote p where p.dataSaida between :inicio and :fim", Pacote.class)
				.setParameter("inicio", start)
				.setParameter("fim", end)
				.getResultList();
	}

	@Override
	public double somaPrecoPacotesPorTransporte(Transporte t) {
		TypedQuery<Double> query =  em.createQuery("select sum(p.preco) from Pacote p where p.transporte = :trans", Double.class);
		query.setParameter("trans", t);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pacote> buscarPorPrecoMaximo(double preco) {
		return em.createNativeQuery("SELECT * FROM PACOTE WHERE PRECO < :P", Pacote.class)
				.setParameter("P", preco)
				.getResultList();
	}

}
