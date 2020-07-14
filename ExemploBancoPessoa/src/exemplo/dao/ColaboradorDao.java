package exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.modelo.Colaborador;


public class ColaboradorDao implements IDao<Colaborador> {

	public ColaboradorDao() {
		try {
			createTable();
		} catch (SQLException e) {
			//throw new RuntimeException("Erro ao criar tabela colaborador");
			e.printStackTrace();
		}
	}
	
	// Cria a tabela se não existir
	private void createTable() throws SQLException {
		final String sqlCreate = "IF NOT EXISTS (" 
				+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
				+ "(t.schema_id = s.schema_id) WHERE s.name = 'dbo'" 
				+ "AND t.name = 'Colaborador')"
				+ "CREATE TABLE Colaborador"
				+ " (id	int	IDENTITY,"
				+ "  nome	VARCHAR(255),"
				+ "  PRIMARY KEY (id))";
		
		Connection conn = DatabaseAccess.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.execute(sqlCreate);
	}

	public List<Colaborador> getAll() {
		Connection conn = DatabaseAccess.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Colaborador> colaboradores = new ArrayList<Colaborador>();
		
		try {
			stmt = conn.createStatement();
			
			String SQL = "SELECT * FROM Colaborador"; 
	        rs = stmt.executeQuery(SQL); 
	        
	        while (rs.next()) {
	        	Colaborador c = getColaboradorFromRs(rs);
	        	
	        	colaboradores.add(c);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getAllColaboradores] Erro ao selecionar todos os colaboradores", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return colaboradores;		
	}
	
	public Colaborador getById(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Colaborador colaborador = null;
		
		try {
			String SQL = "SELECT * FROM Colaborador WHERE id = ?"; 
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        rs = stmt.executeQuery(); 
	        
	        while (rs.next()) {
	        	colaborador = getColaboradorFromRs(rs);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getColaboradorById] Erro ao selecionar o colaborador por id", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return colaborador;		
	}
	
	public void insert(Colaborador colaborador) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "INSERT INTO Colaborador (nome) VALUES (?)";
			stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	    	stmt.setString(1, colaborador.getNome()); 
	    	
			
	        stmt.executeUpdate(); 
	        
	        rs = stmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	        	colaborador.setId(rs.getInt(1));
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[insereColaborador] Erro ao inserir o colaborador", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	public void delete(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
			
		try {
			String SQL = "DELETE Colaborador WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        stmt.executeUpdate(); 			
		} catch (SQLException e) {
			throw new RuntimeException("[deleteColaborador] Erro ao remover o colaborador por id", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	public void update(Colaborador colaborador) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "UPDATE Colaborador SET nome = ? WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
	    	stmt.setString(1, colaborador.getNome()); 
	    	stmt.setInt(2, colaborador.getId());
	    	
	        stmt.executeUpdate(); // executa o UPDATE			
		} catch (SQLException e) {
			throw new RuntimeException("[updateColaborador] Erro ao atualizar o Colaborador", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	private Colaborador getColaboradorFromRs(ResultSet rs) throws SQLException {
		Colaborador c = new Colaborador(0, null, null); 
		c.setId(rs.getInt("id")); 
		c.setNome(rs.getString("nome")); 
		
		return c;
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
