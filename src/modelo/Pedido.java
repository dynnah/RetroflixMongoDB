/**********************************

 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Equipe - George Lucas
 * 		  - Dynnah Hanna
 **********************************/

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
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


//@Embeddable
@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Pedido implements Serializable{
	@Id 
	@GeneratedValue
	@Field(name="_id")	//chave
	private String id;
	private String codPedido;
	private String statusPagamento;
	private String dataPedido;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Filme> filmes = new ArrayList<>();
	
	public Pedido (){}
	
	public Pedido (String codP, Cliente cli, String dataPed) {
		this.codPedido = codP;
		this.cliente = cli;
		this.statusPagamento = "PENDENTE";
		this.dataPedido = dataPed;
	}
	
	public void addFilme (Filme filme) {
		this.filmes.add(filme);
	}

	public String getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente (Cliente cli) {
		this.cliente = cli;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(ArrayList<Filme> filmes) {
		this.filmes = filmes;
	}

	@Override
	public String toString() { 
		String texto= "Pedido [" + (codPedido != null ? " Codigo=" + codPedido + ", " : "") +
				(statusPagamento != null ? "Status de Pagamento=" + statusPagamento + ", " : "") +
				(dataPedido != null ? "Data do Pedido=" + dataPedido + ", " : "") +
				(cliente != null ? "Cliente=" + cliente.getNome() : "");
				for (Filme fi : filmes) {
					texto +="\n" + fi;
				}
				texto +="]";
		return texto;
	}
	
	
	
}
