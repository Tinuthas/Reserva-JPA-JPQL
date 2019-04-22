package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente,Integer>{
	
	List<Cliente> listar();
	
	List<Cliente> listarPorNome(String nome);
	
	List<Cliente> listarPorEstado(String uf);
	
	List<Cliente> listarPorDiasReserva(int dias);
	
	List<Cliente> buscar(String nome, String cidade);
	
	List<Cliente> buscarPorEstados(List<String> estados);
	
	List<Cliente> buscaNomeSemDiferencia(String nome);
	
	int countCliente(String estado);

}
