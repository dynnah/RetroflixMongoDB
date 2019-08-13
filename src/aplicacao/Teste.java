package aplicacao;
/*
 * IFPB - POB
 * Aplica��o JPA + Mongodb
 */


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Filme;

public class Teste {
	private EntityManager manager;

	public Teste() {
		abrir();
		//cadastrar();
		//atualizar();
		//excluir();
		listar();
		//consultar();
		fechar();
		
		System.out.println("fim da aplicacao");
	}

	public  void abrir() {
		// obs: lembrar de abrir antes o servidor (mongod)
		manager = Persistence.createEntityManagerFactory("mongodb").createEntityManager();
		
	}
	public  void fechar() {
		if (manager != null) {
			manager.close();
		}
	}
	
	public void cadastrar() {
		Cliente cliente;
		Pedido pedido;
		Filme filme;
		//criar cliente 1
		manager.getTransaction().begin();
		cliente = new Cliente("Dynnah Hanna", "eudynnah@gmail.com", "55555-555", "999.999.999-99");
		pedido = new Pedido("1", cliente, "10/08/2019");
		pedido.addFilme(new Filme("Spider-Man", 14.90, 2017, pedido));
		cliente.addPedido(pedido);
		manager.persist(cliente);
		manager.getTransaction().commit();
		
		//criar cliente 2
		manager.getTransaction().begin();
		cliente = new Cliente("Helena Juliana", "euhelena@gmail.com", "44444-444", "888.888.888-88");
		pedido = new Pedido("2", cliente, "13/08/2019");
		pedido.addFilme(new Filme("Room", 14.90, 2016, pedido));
		pedido.addFilme(new Filme("Spider-Man", 14.90, 2017, pedido));
		cliente.addPedido(pedido);
		manager.persist(cliente);
		manager.getTransaction().commit();
		
		//criar cliente 3
		manager.getTransaction().begin();
		cliente = new Cliente("George Gomes", "eugeorge@gmail.com", "33333-333", "777.777.777-77");
		pedido = new Pedido("3", cliente, "13/08/2019");
		pedido.addFilme(new Filme("Avengers: Endgame", 39.90, 2019, pedido));
		cliente.addPedido(pedido);
		pedido = new Pedido("4", cliente, "13/08/2019");
		pedido.addFilme(new Filme("Room", 14.90, 2016, pedido));
		pedido.addFilme(new Filme("Spider-Man", 14.90, 2017, pedido));
		cliente.addPedido(pedido);
		manager.persist(cliente);
		manager.getTransaction().commit();
		System.out.println("\ncadastrou!\n");
	}
	
	public  void atualizar(){
		Cliente cliente;
//		Pedido2 pedido;
		//atualizar pedido de Dynnah Hanna
		manager.getTransaction().begin();
		cliente = localizar("Dynnah Hanna"); 
		cliente.getPedidos().get(0).addFilme(new Filme("Avengers: Endgame", 39.90, 2019, cliente.getPedidos().get(0)));
		manager.merge(cliente);
		manager.getTransaction().commit();
		System.out.println("\natualizou!\n");

	}

	public void excluir(){
		Cliente cliente;
		//remover cliente 2
		manager.getTransaction().begin();
		cliente = localizar("Dynnah Hanna"); 
		manager.remove(cliente);
		manager.getTransaction().commit();
		System.out.println("\nexcluiu!\n");
	}

	public Cliente localizar(String nome){
		Cliente cliente;
		Query q;

		q = manager.createQuery("SELECT c FROM Cliente c WHERE c.nome = '"+nome+"'");
		cliente = (Cliente)q.getSingleResult();
		System.out.println("\nlocalizando cliente(jpql) de "+nome+":\n"+cliente);

		/**
		 * native MongoDB query (o mesmo comando usado no Mongo shell)
		 * Observar que o nome dos campos s�o maiusculos.
		 */
		cliente = (Cliente)manager
				.createNativeQuery("db.CLIENTE.findOne({NOME : \""+nome+"\"})", Cliente.class)
				.getSingleResult();
		System.out.println("\nlocalizando cliente(nativo) de "+nome+":\n"+cliente);

		return cliente;
	}

	public void listar(){
//		Query q;
//		q = manager.createQuery("SELECT c FROM Cliente c ");
//		List<Cliente> clientes = q.getResultList();
//		System.out.println("\n**********listagem dos clientes:");
//		for(Cliente c : clientes)
//			System.out.println(c);
		
		Query q;
		q = manager.createQuery("SELECT f FROM Filme f ");
		List<Filme> filmes = q.getResultList();
		System.out.println("\n**********listagem dos filmes:");
		for(Filme f : filmes)
			System.out.println(f);

	}

	public void consultar() {
		
	}



	public static void main(String[] args) {
		new Teste();
	}
}