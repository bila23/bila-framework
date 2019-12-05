/*---------------------------------------------------------
* FILE: CrudResource.java
* PRODUCT: bila-framework
*----------------------------------------------------------
* IMPORTANT NOTICE
* This program is property of Terzus
* Its unauthorized use, as any code alteration without authorization 
* is prohibited
*----------------------------------------------------------
*/
package com.bila.framework.resource;

import com.bila.framework.service.ServiceCrudRest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;

/**
 * @author Terzus
 * <b>created by: </b>will
 * <b>for: </b>bila-framework
 * <b>on: </b>Dec 1, 2019 8:16:53 PM
 * <b>purpose</b> 
 * <p>
 *      Crud generico con manejo de rest-api
 * </p>
 */
public abstract class CrudResource<T, PK, VO> {
    
    public abstract ServiceCrudRest<T, PK, VO> getService();
    public abstract Logger getLog();
    
    @POST
    public Response save(VO vo){
        try{
            getService().saveVO(vo);
            return Response.ok().build();
        }catch(Exception e){
            getLog().error(e.getMessage(), e);
            return Response.status(Response.Status.CONFLICT).entity("Ha ocurrido un error al tratar de guardar el registro").build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") PK id){
        try{
            getService().deleteById(id);
            return Response.ok().build();
        }catch(Exception e){
            getLog().error(e.getMessage(), e);
            return Response.status(Response.Status.CONFLICT).entity("Ha ocurrido un error al tratar de eliminar el registro").build();
        }
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") PK id, VO vo){
        try{
            getService().updateVO(id, vo);
            return Response.ok().build();
        }catch(Exception e){
            getLog().error(e.getMessage(), e);
            return Response.status(Response.Status.CONFLICT).entity("Ha ocurrido un error al tratar de actualizar el registro").build();
        }
    }
    
    @GET
    public Response findAll() throws Exception{
        try{
            return Response.ok(getService().findAllVO()).build();
        }catch(Exception e){
            getLog().error(e.getMessage(), e);
            return Response.status(Response.Status.CONFLICT).entity("Ha ocurrido un error al tratar de recuperar los registros").build();
        }
    }
}