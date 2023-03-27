package data;

import java.util.List;

/**
 * Interface for standaard behavior of all dao's for a single model
 *
 * @param <T> - a Model class (team or driver)
 * @author Julian Kruithof
 */
public interface DAO<T> {
    /**
     * Get all elements in the list of objects
     *
     * @return a list of objects
     */
    List<T> getAll();

    /**
     * Add an object to the list or update an object already inside the list
     *
     * @param object - object of the type of the DAO
     */
    void addOrUpdate(T object);

    /**
     * Remove object from objects list
     *
     * @param object - object of the type of the DAO
     */
    void remove(T object);

    /**
     * Save All the object from the master en detail models in a file,
     * determined by the DAO
     *
     * @return true if successful otherwise false
     */
    boolean save();

    /**
     * Load all the data into the views.
     *
     * @return true if successful otherwise false
     */
    boolean load();

}
