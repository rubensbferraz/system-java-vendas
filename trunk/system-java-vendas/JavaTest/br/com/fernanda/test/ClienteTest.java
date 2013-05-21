package br.com.fernanda.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.fernanda.cliente.Cliente;
import br.com.fernanda.cliente.ClienteRN;
import br.com.fernanda.util.HibernateUtil;

public class ClienteTest {
	
	private static Session sessao;
	private static Transaction transacao;
	
	@BeforeClass
	public static void abreConexao(){
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		transacao = sessao.beginTransaction();
	}

	@AfterClass
	public static void fechaConexao(){
		try {
			transacao.commit();
			
		} catch (Throwable e) {
			System.out.println("Deu problema no commit: " + e.getMessage());
		}finally{
			try {
				if(sessao.isOpen()){
					sessao.close();
				}
			} catch (Exception e2) {
				System.out.println("Deu erro no fechamento da sessão" + e2.getMessage());
			}
			
		}
	}
	
	@Test
	public void salvarTeste(){
		
		Cliente c1 = new Cliente();
		c1.setNome("Fernanda");
		c1.setEndereco("Salvador");
		c1.setRenda(5000f);
		c1.setCpf("124578965");
				
		ClienteRN clienteRN = new ClienteRN();
		
		clienteRN.salvar(c1);
		
		assertEquals(true, true);
			
	}

}
