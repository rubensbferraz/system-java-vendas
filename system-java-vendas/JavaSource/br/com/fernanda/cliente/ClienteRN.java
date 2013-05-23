package br.com.fernanda.cliente;

import java.util.List;

import br.com.fernanda.util.DAOFactory;

public class ClienteRN {
	
	private ClienteDAO clienteDAO;
	
	public ClienteRN(){
		this.clienteDAO = DAOFactory.criaClienteDAO();
	}

	public void salvar(Cliente c1) {
		this.clienteDAO.salvar(c1);
	}

	public List<Cliente> listar() {
		return this.clienteDAO.listar();
	}

}
