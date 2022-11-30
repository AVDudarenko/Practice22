package phone;

import java.util.List;
import java.util.Optional;

public interface Dao<K extends Number, T> {

	List<T> findAll();

	T findEntityById(K id);

	boolean delete(K id);

	boolean delete(T entity);

	boolean create(T entity);

	T update(T entity);
}
