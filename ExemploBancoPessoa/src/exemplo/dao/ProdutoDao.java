package exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.modelo.Produto;

public class ProdutoDao implements IDao<Produto> {
	public List<Produto> getAll() {
		Connection conn = DatabaseAccess.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			stmt = conn.createStatement();
			
			String SQL = "SELECT * FROM Produto"; // consulta de SELECT
	        rs = stmt.executeQuery(SQL); // executa o SELECT
	        
	        while (rs.next()) {
	        	Produto pr = getProdutoFromRs(rs);
	        	
	        	produtos.add(pr);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getAllProdutos] Erro ao selecionar todos os produtos", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return produtos;		
	}
	
	public Produto getById(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Produto produto = null;
		
		try {
			String SQL = "SELECT * FROM Produto WHERE id = ?"; // consulta de SELECT
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        rs = stmt.executeQuery(); // executa o SELECT
	        
	        while (rs.next()) {
	        	produto = getProdutoFromRs(rs);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getProdutoById] Erro ao selecionar o produto por id", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return produto;		
	}
	
	public void insert(Produto produto) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "INSERT INTO Produto (nome, preco) VALUES (?, ?)";
			stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	    	stmt.setString(1, produto.getNome());
	    	stmt.setInt(2, produto.getId()); 
	    	stmt.setDouble(3, produto.getPreco());
			
	        stmt.executeUpdate(); // executa o SELECT
	        
	        rs = stmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	        	produto.setId(rs.getInt(1));
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[insereProduto] Erro ao inserir o produto", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	public void delete(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
			
		try {
			String SQL = "DELETE Produto WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        stmt.executeUpdate(); 			
		} catch (SQLException e) {
			throw new RuntimeException("[deleteProduto] Erro ao remover a produto por id", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	public void update(Produto produto) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "UPDATE Produto SET nome = ?, preco = ? WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
	    	stmt.setString(1, produto.getNome()); // insira na primeira ? o nome do produto
	    	stmt.setDouble(2, produto.getPreco()); // insira na segunda ? o preco do produto
	    	stmt.setInt(3, produto.getId()); // insira na última ? o id do produto
	    	
	        stmt.executeUpdate(); // executa o UPDATE			
		} catch (SQLException e) {
			throw new RuntimeException("[updateProduto] Erro ao atualizar a pessoa", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	private Produto getProdutoFromRs(ResultSet rs) throws SQLException {
		Produto pr = new Produto(0, null, 0); // cria um objeto de produto
		pr.setId(rs.getInt("id")); // insere id recuperado do banco no produto
		pr.setNome(rs.getString("nome")); // insere nome recuperado do banco no produto
		pr.setPreco(rs.getDouble("preco")); // insere preco recuperado do banco no produto
		
		return pr;
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos.", e);
		}
	}
}
