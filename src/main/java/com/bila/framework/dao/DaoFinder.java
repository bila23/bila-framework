/*----------------------------------------------------------
* FILE: DaoFinder.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.dao
 * <b>on </b> 1/12/2015 11:09:32 AM
 * <b>Purpose</b> 
 * <p>
 *  DAO que proporciona los metodos para realizar consulta de las entidades
 *  @param <T> hace referencia al objeto Entidad que esta asociado a una tabla de la base de datos
 *  @param <PK> hace referencia al objeto que hace la función de llave primaria
 * </p>
 */
public abstract class DaoFinder<T, PK> {

    public abstract EntityManager getEntityManager();
    private final Class<T> clazz;
    
    public DaoFinder(Class clazz){
        this.clazz = clazz;
    }
    
    /**
     * Metodo que recupera una entidad por medio de su llave primaria
     * @param pk es la llave primaria con la cual se realiza la busqueda de la entidad
     * @return objeto de tipo T asociado a la entidad
     * @throws Exception 
     */
    public T findByKey(PK pk) throws Exception{
        T t = (T) getEntityManager().find(clazz, pk);
        return t;
    }
    
    /**
     * Realiza una consulta por medio de un query nombrado
     * @param namedQuery objeto de tipo String con el nombre del query nombrado
     * @return recupera un objeto de tipo List<T> con los registros que recupera el query nombrado
     * @throws Exception 
     */
    public List<T> findWithNamedQuery(String namedQuery) throws Exception{
        Query query = getEntityManager().createNamedQuery(namedQuery);
        return query.getResultList();
    }
    
    /**
     * Realiza una consulta por medio de un query nombrado, el segundo parametro es un Map
     * con los parametros del query. Es importante que los parametros vayan en el mismo orden
     * en los cuales se han declarado en el query
     * @param namedQuery objeto de tipo String con el nombre del query nombrado
     * @param parameters objeto de tipo Map con los parametros del query
     * @return recupera un objeto de tipo List<T> con los registros que recupera el query nombrado
     * @throws Exception 
     */
    public List<T> findWithNamedQuery(String namedQuery, Map<String, Object> parameters) throws Exception{
        Query query = getEntityManager().createNamedQuery(namedQuery);
        if(parameters != null)
            parameters.entrySet().forEach((e) -> {
                query.setParameter(e.getKey(), e.getValue());
        });
        return query.getResultList();
    }
    
    /**
     * Recupera todos los registros de la entidad
     * @return objeto de tipo List<T> con todos los registros de la entidad
     * @throws Exception 
     */
    public List<T> findAll() throws Exception{
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    /**
     * Metodo que ejecuta un query generado por EQL
     * @param query objeto de tipo String con el query que se desea ejecutar
     * @param parameters objeto de tipo Map con los parametros del query
     * @return objeto de tipo List<T> con los registros que recupero el query
     * @throws Exception 
     */
    public List<T> findByQuery(String query, Map<String, Object> parameters) throws Exception{
        Query q = getEntityManager().createQuery(query);
        if(parameters != null)
            parameters.entrySet().forEach((e) -> {
                q.setParameter(e.getKey(), e.getValue());
        });
        return q.getResultList();
    }
    
    /**
     * Metodo que cuenta todos los registros de una entidad
     * @return retorna un int con el numero de registros de la entidad
     * @throws Exception 
     */
    public int countAll() throws Exception{
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        return getEntityManager().createQuery(cq).getResultList().size();
    }
    
    /**
     * Retorna la cantidad de registros de un query generado por EQL
     * @param query objeto String con el query que deseo saber cuantos registros recupera
     * @param parameters objeto de tipo Map<String, Object> con los parametros del query
     * @return recupera un int con el numero de registros que recupero el query
     * @throws Exception 
     */
    public int countByQuery(String query, Map<String, Object> parameters) throws Exception{
        Query q = getEntityManager().createQuery(query);
        if(parameters != null)
            parameters.entrySet().forEach((e) -> {
                q.setParameter(e.getKey(), e.getValue());
        });
        return q.getResultList().size();
    }

    /**
     * Retorna la cantidad de registros de un query nombrado
     * @param namedQuery objeto String con el nombre del query nombrado
     * @param parameters objeto de tipo Map<String, Object> con los parametros del query
     * @return recupera un int con el numero de registros que recupero el query
     * @throws Exception 
     */
    public int countByNamedQuery(String namedQuery, Map<String, Object> parameters) throws Exception{
        Query q = getEntityManager().createNamedQuery(namedQuery);
        if(parameters != null)
            parameters.entrySet().forEach((e) -> {
                q.setParameter(e.getKey(), e.getValue());
        });
        return q.getResultList().size();
    }
    
    /**
     * Recupera un unico registro de tipo T del query nombrado que se pasa como parametro
     * @param namedQuery objeto String con el nombre del query nombrado que se desea ejecutar
     * @param parameters objeto de tipo Map<String, Object> con los parametros del query
     * @return objeto de tipo T
     * @throws Exception 
     */
    public T findNamedQuerySingleResult(String namedQuery, Map<String, Object> parameters) throws Exception {
        Query query = getEntityManager().createNamedQuery(namedQuery);
        if (parameters != null) 
            parameters.entrySet().forEach((e) -> {
                query.setParameter(e.getKey(), e.getValue());
        });
        return (T) query.getSingleResult();
    }

    /**
     * Recupera un unico registro de tipo T del query en formato EQL que se pasa como parametro
     * @param query objeto String con el nombre del query nombrado que se desea ejecutar
     * @param parameters objeto de tipo Map<String, Object> con los parametros del query
     * @return objeto de tipo T
     * @throws Exception 
     */
    public T findQuerySingleResult(String query, Map<String, Object> parameters) throws Exception {
        Query q = getEntityManager().createQuery(query);
        if (parameters != null) 
            parameters.entrySet().forEach((e) -> {
                q.setParameter(e.getKey(), e.getValue());
        });
        return (T) q.getSingleResult();
    }
    
    /**
     * Recupera todos los registros de la tabla invocando el query nombrado
     * findAll. Por lo cual debe de existir en el modelo
     * @return objeto de tipo List<T>
     * @throws Exception 
     */
    public List<T> findAllWithNamedQuery() throws Exception{
        Query q = getEntityManager().createNamedQuery(clazz.getSimpleName().concat(".findAll"));
        return q.getResultList();
    }

    /**
     * Recupera todos los registros activos de la tabla invocando el query nombrado
     * findAllActive. Por lo cual debe de existir en el modelo
     * @return objeto de tipo List<T>
     * @throws Exception 
     */
    public List<T> findAllActive() throws Exception{
        Query q = getEntityManager().createNamedQuery(clazz.getSimpleName().concat(".findAllActive"));
        return q.getResultList();
    }    
    
}
