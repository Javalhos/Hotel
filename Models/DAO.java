import java.sql.SQLException;
import java.util.Collection;

/**
 * DAO
 */
public interface DAO<T> {
	// Insere um novo registro no banco
	public boolean create (T model) throws SQLException, ClassNotFoundException;

	// Atualiza um registro no banco
	public boolean update (T model) throws SQLException, ClassNotFoundException;

	// Remove um registro no banco
	public boolean destroy (T model) throws SQLException, ClassNotFoundException;

	// Busca um registro pela chave primária
	public T search (T model) throws SQLException, ClassNotFoundException;

	// Lista todos os registros de acordo com uma clausula WHERE
	public Collection<T> list (String clause) throws SQLException, ClassCastException;
}
