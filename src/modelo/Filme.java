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

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

//@Embeddable
@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Filme implements Serializable {
	@Id 
	@GeneratedValue
	@Field(name="_id")	//chave
	private String id;
	private String titulo;
	private double preco;
	private int lancamento;
	
	@ManyToOne
	private Pedido pedido;
	
	public Filme (){}
	
	public Filme (String titulo, double preco, int lancamento, Pedido pedido) {
		this.titulo = titulo;
		this.preco = preco;
		this.lancamento = lancamento;
		this.pedido = pedido;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getLancamento() {
		return lancamento;
	}

	public void setLancamento(int lancamento) {
		this.lancamento = lancamento;
	}
	
	public Pedido getPedidos() {
		return pedido;
	}
	
	public void setPedidos(Pedido pedido) {
		this.pedido = pedido;
	}

		@Override
	public String toString() {
		String texto= "Filme [" + (titulo != null ? " Titulo=" + titulo +  ", " : "") +
				"Preco=" + preco + ", Lancamento=" + lancamento + ", " +
				(pedido != null ? "Pedido=" + pedido.getCodPedido() : "");
				texto +="]";
		return texto;
	}	
}
