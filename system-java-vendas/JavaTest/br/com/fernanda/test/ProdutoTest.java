package br.com.fernanda.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.fernanda.produto.Produto;
import br.com.fernanda.util.HibernateUtil;

public class ProdutoTest {

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
	
	//método vai ser utilizado antes de qualquer teste
	@Before //chama o metodo toda vez que executar um teste
	public void setup(){
		Produto p1 = new Produto("lote", "Caderno", new Date(), 50, 7.0f);
		Produto p2 = new Produto("lote2", "Regua", new Date(), 30, 2.5f);
		Produto p3 = new Produto("fardo", "Papel", new Date(), 300, 1.5f);
		Produto p4 = new Produto("edicao", "Livro", new Date(), 10, 30.0f);
		Produto p5 = new Produto("caixa", "Caneta", new Date(), 90, 1.5f);
		
		sessao.save(p1);
		sessao.save(p2);
		sessao.save(p3);
		sessao.save(p4);
		sessao.save(p5);
	}
	
	
	@Test
	public void salvarProdutoTest(){
		String sql = "from Produto p where p.descricao like :descricao";
		Query consulta = sessao.createQuery(sql);
		consulta.setString("descricao", "%Re%");
		Produto produtoPesquisado = (Produto) consulta.uniqueResult(); //busque um unico resultado da consulta
		
		assertEquals("lote2", produtoPesquisado.getUnidade());
	}
	
	
	/*
	@Test
	public void salvarProdutoTest(){
		Produto p1 = new Produto();
		p1.setDescrição("Produto Teste");
		p1.setEstoque(20);
		p1.setDataCadastro(new Date());
		p1.setUnidade("Quilo");
		p1.setValor(1.6f);
		
		//abreConexao(); //anotação nao necessária pois estamos usando o @BeforeClass
		this.sessao.save(p1);
		//fechaConexao(); //anotação nao necessária pois estamos usando o @AfterClass
		
		assertNull(null);
	}*/
}
