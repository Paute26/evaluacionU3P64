package ec.edu.ups.sd64.eva.services;

import java.util.List;

//import config.ConfigJaeger;
import ec.edu.ups.sd64.eva.business.GestionUniversidades;
import ec.edu.ups.sd64.eva.model.Universidad;
//import io.opentracing.Span;
//import io.opentracing.Tracer;
//import io.opentracing.util.GlobalTracer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("universidad")
public class UniversidadServices {
	
	//private final Tracer tracer = GlobalTracer.get();

    @Inject
    private GestionUniversidades gUniversidades;
    
    //@Inject
    //private ConfigJaeger configjaeger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Universidad uni) {
    	//Span spanGuardarLibro = tracer.buildSpan("Crear Libro").start();
        try {
        	gUniversidades.guardarUni(uni);
            ErrorMessage error = new ErrorMessage(1, "OK");
            return Response.status(Response.Status.CREATED)
                    .entity(error)
                    .build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }finally {
        	//spanGuardarLibro.finish();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Universidad Uni) {
    	//Span spanActualizarLibro = tracer.buildSpan("Actualizar libro").start();
        try {
        	gUniversidades.actualizarUni(Uni);
            return Response.ok(Uni).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }finally {
        	//spanActualizarLibro.finish();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("codigo") int codigo) {
    	//Span spanEliminarLibro = tracer.buildSpan("Eliminar libro").start();
        try {
        	gUniversidades.borrarUni(codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }finally {
        	//spanEliminarLibro.finish();
        }
    }


    @GET
    @Path("{serial}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leerPorSerial(@PathParam("serial") String serial) {
    	//Span spanGetLibroPorSerial= tracer.buildSpan("Obtener Libro mediante el Serial").start();
        try {
        	Universidad uni = gUniversidades.getUniPorSerial(serial);
            return Response.ok(uni).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Libro no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }finally {
        	//spanGetLibroPorSerial.finish();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getLibros() {
    	//Span spanGetLibros = tracer.buildSpan("Obtener Libros ").start();
    	try {
    		List<Universidad> uni = gUniversidades.getUniversidades();
    		if (uni.size() > 0) {
    			return Response.ok(uni).build();
    		}else {
				ErrorMessage error = new ErrorMessage(6, "No se registran Libros");
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
    	}catch(Exception e){
	    	ErrorMessage error = new ErrorMessage(6, "No se registran libros");
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity(error)
	                .build();
    	}finally {
    		//spanGetLibros.finish();
        }
    	
    }
}
