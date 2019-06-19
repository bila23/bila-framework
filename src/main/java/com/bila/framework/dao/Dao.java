/*----------------------------------------------------------
* FILE: Dao.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.dao
 * <b>on </b> 2/12/2015 02:21:08 PM
 * <b>Purpose</b> 
 * <p>
 *  Clase abstracta que contiene los metodos necesarios
 *  para realizar un CRUD de una entidad
 *  @param <T> hace referencia al objeto Entidad que esta asociado a una tabla de la base de datos
 *  @param <PK> hace referencia al objeto que hace la función de llave primaria
 * </p>
 */
public abstract class Dao<T, PK> extends DaoFinder<T, PK> {

    public Dao(Class clazz) {
        super(clazz);
    }

    /**
     * Metodo que sirve para guardar un registro
     * @param t objeto entidad que deseo guardar
     * @throws Exception 
     */
    public void save(T t) throws Exception{
        getEntityManager().persist(t);
    }
    
    /**
     * Metodo que sirve para actualizar un registro
     * @param t objeto entidad que deseo actualizar
     * @throws Exception 
     */
    public void update(T t) throws Exception{
        getEntityManager().merge(t);
    }
    
    /**
     * Metodo que elimina un registro
     * @param t objeto entidad que deseo eliminar
     * @throws Exception 
     */
    public void delete(T t) throws Exception{
        getEntityManager().remove(t);
    }
    
    /**
     * Metodo que elimina un registro buscandolo por su llave primaria
     * @param pk objeto llave primaria de la entidad que deseo eliminar
     * @throws Exception 
     */
    public void deleteById(PK pk) throws Exception{
        T t = findByKey(pk);
        getEntityManager().remove(t);
    }
    
    /**
     * Metodo que sirve para guardar una lista de entidades
     * @param list objeto de tipo List<T> que contiene las entidades que deseo guardar
     * @throws Exception 
     */
    public void insertList(List<T> list) throws Exception{
        list.forEach((t) -> {
            getEntityManager().persist(t);    
        });
    }

    /**
     * Metodo que sirve para actualizar una lista de entidades
     * @param list objeto de tipo List<T> que contiene las entidades que deseo actualizar
     * @throws Exception 
     */
    public void updateList(List<T> list) throws Exception{
        list.forEach((t) -> {
            getEntityManager().merge(t);    
        });
    }
    
    /**
     * Metodo que sirve para eliminar una lista de entidades
     * @param list objeto de tipo List<T> que contiene las entidades que deseo eliminar
     * @throws Exception 
     */
    public void deleteList(List<T> list) throws Exception{
        list.forEach((t) -> {
            getEntityManager().remove(t);    
        });
    }
    
    /**
     * Ejecuta un update o delete definido como query nombrado
     * @param namedQuery objeto de tipo String con el eql del update o delete
     * @param parameters objeto detipo Map<String, Object> donde se define los parametros
     * @return dato primitivo de tipo int con la cantidad de los registros alterados
     */
    public int executeUpdateOrDelete(String namedQuery, Map<String, Object> parameters){
        Query query = getEntityManager().createNamedQuery(namedQuery);
        if (parameters != null) {
            parameters.entrySet().forEach((e) -> {
                query.setParameter(e.getKey(), e.getValue());
            });
            return query.executeUpdate();
        }
        return 0;
    }
    
    /**
     * Elimina un registro pero antes se verifica si este necesita o no un merge
     * @param t entidad que quiero eliminar
     * @throws Exception 
     */
    public void deleteWithMerge(T t) throws Exception{
        if(!getEntityManager().contains(t))
            t = getEntityManager().merge(t);
        getEntityManager().remove(t);
    }
    
}
