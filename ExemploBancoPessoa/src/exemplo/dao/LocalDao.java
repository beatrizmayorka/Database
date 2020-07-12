package exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.modelo.Setor;

public class LocalDao implements IDao<Localr> {
	
	public LocalDao() {
		try {
			createTable();
		} catch (SQLException e) {
			//throw new RuntimeException("Erro ao criar tabela local");
			e.printStackTrace();
		}
	}
	
	// Cria a tabela se não existir
	private void createTable() throws SQLException {
		final String sqlCreate = "IF NOT EXISTS (" 
				+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
				+ "(t.schema_id = s.schema_id) WHERE s.name = 'dbo'" 
				+ "AND t.name = 'Setor')"
				+ "CREATE TABLE Setor"
				+ " (id	int	IDENTITY,"
				+ "  nome	VARCHAR(255),"
				+ "  PRIMARY KEY (id))";
		
		Connection conn = DatabaseAccess.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.execute(sqlCreate);
	}

	public List<Local> getAll() {
		Connection conn = DatabaseAccess.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Local> locais = new ArrayList<Localr>();
		
		try {
			stmt = conn.createStatement();
			
			String SQL = "SELECT * FROM Local"; // consulta de SELECT
	        rs = stmt.executeQuery(SQL); // executa o SELECT
	        
	        while (rs.next()) {
	        	Local l = getLocalFromRs(rs);
	        	
	        	locais.add(s);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getAllSetores] Erro ao selecionar todos os locais", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return setores;		
	}
	
	public Local getById(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Setor setor = null;
		
		try {
			String SQL = "SELECT * FROM Local WHERE id = ?"; 
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        rs = stmt.executeQuery(); 
	        
	        while (rs.next()) {
	        	setor = getLocalFromRs(rs);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getLocalById] Erro ao selecionar o local por id", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return local;		
	}
	
	public void insert(Local local) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "INSERT INTO Local (nome) VALUES (?)";
			stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	    	stmt.setString(1, local.getNome()); 
	    	
			
	        stmt.executeUpdate(); 
	        
	        rs = stmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	        	setor.setId(rs.getInt(1));
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[insereLocal] Erro ao inserir o local", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	public void delete(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
			
		try {
			String SQL = "DELETE Local WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        stmt.executeUpdate(); 			
		} catch (SQLException e) {
			throw new RuntimeException("[deleteLocal] Erro ao remover o local por id", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	public void update(Local local) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "UPDATE Setor SET nome = ? WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
	    	stmt.setString(1, setor.getNome()); 
	    	stmt.setInt(2, setor.getId());	    	
	        stmt.executeUpdate(); 	
	        
		} catch (SQLException e) {
			throw new RuntimeException("[updateSetor] Erro ao atualizar o local", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	private Local getLocalFromRs(ResultSet rs) throws SQLException {
		Local l = new Local();
		l.setId(rs.getInt("id")); 
		l.setNome(rs.getString("nome"));
		
		return l;
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
