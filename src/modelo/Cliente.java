/* 
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Equipe - George Lucas
 * 		  - Dynnah Hanna
 */

package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

//import antlr.collections.List;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Cliente implements Serializable{
	@Id
	@GeneratedValue
	@Field(name="_id")
	private String id;
	
	private String nome;
	private String email;
	private String cep;
	private String cpf;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();
	
	//Construtor vazio
	public Cliente () {}
	
	public Cliente (String nome, String email, String cep2, String cpf2) {
		super();
		this.nome = nome;
		this.email = email;
		this.cep = cep2;
		this.cpf = cpf2;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void addPedido (Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
	@Override
	public String toString() {
		String texto= "Cliente [" + (id != null ? "id=" + id + ", " : "") +
				(nome != null ? "Nome=" + nome + ", " : "") +
				(email != null ? "Email=" + email + ", " : "") +
				(cep != null ? "CEP=" + cep + ", " : "") +
				(cpf != null ? "CPF=" + cpf + ", " : "");
				for (Pedido pe : pedidos) {
					texto+="\n"+ pe ;
				}
				texto += "]";

		return texto;
	}
}
