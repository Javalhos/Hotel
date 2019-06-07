package DAO;
import java.sql.SQLException;
import java.util.Collection;

/**
 * DAO
 */
public interface DAO<T> {
	// Insere um novo registro no banco
	public boolean create (T model) throws SQLException, ClassNotFoundException, Exception;

	// Atualiza um registro no banco
	public boolean update (T model) throws SQLException, ClassNotFoundException, Exception;

	// Remove um registro no banco
	public boolean destroy (T model) throws SQLException, ClassNotFoundException, Exception;

	// Busca um registro pela chave primária
	public T search (T model) throws SQLException, ClassNotFoundException, Exception;

	// Lista todos os registros de acordo com uma clausula WHERE
	public Collection<T> list (String clause) throws SQLException, ClassCastException, Exception;
}
