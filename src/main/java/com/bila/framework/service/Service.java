/*----------------------------------------------------------
* FILE: Service.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.service;

import com.bila.framework.dao.Dao;
import java.util.List;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.service
 * <b>on </b> 3/12/2015 11:26:31 AM
 * <b>Purpose</b> 
 * <p>
 *  Servicio abstracto para la generacion de un CRUD
 *  @param <T> hace referencia al objeto Entidad que esta asociado a una tabla de la base de datos
 *  @param <PK> hace referencia al objeto que hace la función de llave primaria
 * </p>
 */
public abstract class Service<T, PK> extends ServiceFinder<T, PK>{

    @Override
    public abstract Dao<T, PK> getDao();
    
    /**
     * Metodo que sirve para guardar un registro
     * @param t objeto entidad que deseo guardar
     * @throws Exception 
     */
    public void save(T t) throws Exception{
        getDao().save(t);
    }
    
    /**
     * Metodo que sirve para actualizar un registro
     * @param t objeto entidad que deseo actualizar
     * @throws Exception 
     */
    public void update(T t) throws Exception{
        getDao().update(t);
    }
    
    /**
     * Metodo que elimina un registro
     * @param t objeto entidad que deseo eliminar
     * @throws Exception 
     */
    public void delete(T t) throws Exception{
        getDao().delete(t);
    }
    
    /**
     * Metodo que elimina un registro buscandolo por su llave primaria
     * @param pk objeto llave primaria de la entidad que deseo eliminar
     * @throws Exception 
     */
    public void deleteById(PK pk) throws Exception{
        getDao().deleteById(pk);
    }
    
    /**
     * Metodo que sirve para guardar una lista de entidades
     * @param list objeto de tipo List<T> que contiene las entidades que deseo guardar
     * @throws Exception 
     */
    public void insertList(List<T> list) throws Exception{
        getDao().insertList(list);
    }

    /**
     * Metodo que sirve para actualizar una lista de entidades
     * @param list objeto de tipo List<T> que contiene las entidades que deseo actualizar
     * @throws Exception 
     */
    public void updateList(List<T> list) throws Exception{
        getDao().updateList(list);
    }
    
    /**
     * Metodo que sirve para eliminar una lista de entidades
     * @param list objeto de tipo List<T> que contiene las entidades que deseo eliminar
     * @throws Exception 
     */
    public void deleteList(List<T> list) throws Exception{
        getDao().deleteList(list);
    }

    /**
     * Elimina un registro pero antes se verifica si este necesita o no un merge
     * @param t entidad que quiero eliminar
     * @throws Exception 
     */
    public void deleteWithMerge(T t) throws Exception{
        getDao().deleteWithMerge(t);
    }
    
}
