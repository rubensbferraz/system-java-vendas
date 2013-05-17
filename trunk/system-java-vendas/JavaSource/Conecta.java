

import org.hibernate.Session;

import br.com.fernanda.util.HibernateUtil;


public class Conecta {
	
	public static void main(String[] args) {
		Session sessao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession(); //abrir a sessao, instanciar nova sessao e add numa nova sessao
			System.out.println("Conectou!");
		} finally {
			sessao.close();
			System.out.println("Fechou conexão!");
		}
	}
}
