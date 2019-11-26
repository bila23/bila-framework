/*----------------------------------------------------------
* FILE: ServiceCrudRest.java
* PRODUCT: bila-framework
*----------------------------------------------------------
* CEL - UNIDAD DE INFORMÁTICA
* ÁREA DE DESARROLLO DE SISTEMAS
*----------------------------------------------------------
*/
package com.bila.framework.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 * @author CEL
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.service
 * <b>on </b> 11-26-2019 03:34:55 PM
 * <b>Purpose</b> 
 * <p>
 *      Servicio generico para API's REST
 * </p>
 */
public abstract class ServiceCrudRest<T, PK, V> extends Service<T, PK>{
    
    private final Class<V> voClass;
    
    public ServiceCrudRest(Class<V> voClass){
        this.voClass = voClass;
    }

    public abstract T createModel(V vo) throws Exception;
    
    public abstract T updateModel(T model, V vo) throws Exception;
    
    public V createVO(T model) throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(model, voClass);
    }
    
    /**
     * Genero la lista de VO a partir de una lista de objeto de tipo T
     * @param list lista de objetos de tipo T
     * @return lista de objetos tipo V
     * @throws Exception 
     */
    public List<V> createListVO(List<T> list) throws Exception{
        if(list == null || list.isEmpty()) return null;
        int i = 0, size = list.size();
        V vo = null;
        T model = null;
        List<V> lst = new ArrayList();
        for(i=0; i<size; i++){
            model = list.get(i);
            vo = createVO(model);
            lst.add(vo);
        }
        vo = null;
        return lst;     
    }
    
    /**
     * Guardo un objeto modelo a partir de su VO
     * @param vo objeto de tipo V
     * @throws Exception 
     */
    public void saveVO(V vo) throws Exception{
        T model = createModel(vo);
        getDao().save(model);
    }
    
    /**
     * Actualiza un objeto modelo a partid de su VO
     * @param id llave del objeto modelo de tipo PK
     * @param vo objeto de tipo V
     * @throws Exception 
     */
    public void updateVO(PK id, V vo) throws Exception{
        T model = getDao().findByKey(id);
        if(model == null) return;        
        getDao().update(updateModel(model, vo));
    }
    
    /**
     * Recupero el listado completo de VO
     * @return objeto de tipo List<V>
     * @throws Exception 
     */
    public List<V> findAllVO() throws Exception{
        List<T> list = getDao().findAllWithNamedQuery();
        return createListVO(list);
    }
    
}