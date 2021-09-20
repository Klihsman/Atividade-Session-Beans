/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.restfull;

import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.vendas.service.ClienteService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;

@Path("/cliente")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class ClienteRestfull {

    @Inject
    private ClienteService service;

    @POST
    public Response adicionar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
            return Response.status(400).
                    entity("O nome do contato é obrigatório").
                    build();

        }
        service.save(cliente);
        return Response.ok()
                .entity(cliente)
                .build();
    }

    @GET
    public Response recuperarCliente() {

        GenericEntity<List<Cliente>> entity
                = new GenericEntity<List<Cliente>>(service.findAll()) {
        };

        return Response.ok()
                //              
                .entity(entity)
                .build();

    }
    // .../dac-jogador/api/jogador/{id}

    @GET
    @Path("/{id}")
    public Response recuperar(@PathParam("id") int id) {
        Cliente cliente = service.find(id);
        return Response.ok()
                //                .header("Access-Control-Allow-Origin", "*")
                .entity(cliente)
                .build();
    }
     @PUT
    @Path("/{id}")
    public Response atualizar(
            @PathParam("id") int id,
             Cliente c) {
            c.setId(id);
        this.service.save(c);
        return Response.ok()
                .entity(c)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        service.remove(id);
        return Response.ok() // 200
              
                .entity(new Cliente() )
                .build();
    }
}
