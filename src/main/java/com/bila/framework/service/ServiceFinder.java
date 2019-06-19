/*----------------------------------------------------------
* FILE: ServiceFinder.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.service;

import com.bila.framework.dao.DaoFinder;
import java.util.List;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.service
 * <b>on </b> 3/12/2015 11:01:13 AM
 * <b>Purpose</b> 
 * <p>
 *  Clase abstracta que servira para definir un servicio para un CRUD,
 *  para hacer uso de esta clase el DAO debe haber heredado de la clase DaoFinder
 *  @param <T> hace referencia al objeto Entidad que esta asociado a una tabla de la base de datos
 *  @param <PK> hace referencia al objeto que hace la función de llave primaria
 * </p>
 */
public abstract class ServiceFinder<T, PK> {

    public abstract DaoFinder<T, PK> getDao();
    
    /**
     * Metodo que recupera una entidad por medio de su llave primaria
     * @param pk es la llave primaria con la cual se realiza la busqueda de la entidad
     * @return objeto de tipo T asociado a la entidad
     * @throws Exception 
     */
    public T findByKey(PK pk) throws Exception{
        T t = getDao().findByKey(pk);
        return t;
    }
    
    /**
     * Recupera todos los registros de la entidad
     * @return objeto de tipo List<T> con todos los registros de la entidad
     * @throws Exception 
     */
    public List<T> findAll() throws Exception{
        return getDao().findAll();
    }
    
    /**
     * Metodo que cuenta todos los registros de una entidad
     * @return retorna un int con el numero de registros de la entidad
     * @throws Exception 
     */
    public int countAll() throws Exception{
        return getDao().countAll();
    }
    
    /**
     * Recupera todos los registros de la tabla invocando el query nombrado
     * findAll. Por lo cual debe de existir en el modelo
     * @return objeto de tipo List<T>
     * @throws Exception 
     */
    public List<T> findAllWithNamedQuery() throws Exception{
        return getDao().findAllWithNamedQuery();
    }

    /**
     * Recupera todos los registros activos de la tabla invocando el query nombrado
     * findAllActive. Por lo cual debe de existir en el modelo
     * @return objeto de tipo List<T>
     * @throws Exception 
     */
    public List<T> findAllActive() throws Exception{
        return getDao().findAllActive();
    }
}
