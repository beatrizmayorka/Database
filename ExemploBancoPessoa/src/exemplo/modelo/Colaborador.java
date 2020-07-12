package exemplo.modelo;

import java.util.List;

public class Colaborador {
	private int id;
	private String nome;
	private List<Produto> pedidos;
	
	public Colaborador(int id, String nome, List<Produto> pedidos) {		
		this.id = id;
		this.nome = nome;
		this.pedidos = pedidos;
	}

	public String toString() {
		return "Colaborador [id = " + id + ", nome = " + nome + ", pedidos = " + pedidos + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Produto> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Produto> pedidos) {
		this.pedidos = pedidos;
	}
	
}
