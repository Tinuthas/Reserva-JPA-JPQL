package br.com.fiap.dao;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

public interface PacoteDAO extends GenericDAO<Pacote,Integer>{
	
	List<Pacote> listarPorTransporte(Transporte t);
	
	List<Pacote> buscarPorDatas(Calendar start, Calendar end);
	
	double somaPrecoPacotesPorTransporte(Transporte t);
	
	List<Pacote> buscarPorPrecoMaximo(double preco);
}
