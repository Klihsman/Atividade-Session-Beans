/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.restfull;

import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.venda.entidades.Produto;
import br.edu.ifpb.dac.vendas.service.ProdutoService;
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

@Path("/produto")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class ProdutoRestfull {

    @Inject
    private ProdutoService service;

    @POST
    public Response adicionar(Produto p) {
        if (p.getDescricao() == null) {
            return Response.status(400).
                    entity("A descrição é obrigatório").
                    build();

        }
        service.save(p);
        return Response.ok()
                .entity(p)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(
            @PathParam("id") int id,
            Produto p) {
        p.setId(id);
        this.service.save(p);
        return Response.ok()
                .entity(p)
                .build();
    }

    @GET
    public Response recuperar() {

        GenericEntity<List<Produto>> entity
                = new GenericEntity<List<Produto>>(service.findAll()) {
        };

        return Response.ok()
                //              
                .entity(entity)
                .build();

    }
    // .../dac-jogador/api/jogador/{id}
      @GET
      @Path("/desc/{desc}")
    public Response recuperarPorDescricao(@PathParam("desc")String desc) {

        GenericEntity<List<Produto>> entity
                = new GenericEntity<List<Produto>>(service.
                        produtosPorDescicao( 
                            desc)) {
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
        Produto p = service.find(id);
        return Response.ok()
                //                .header("Access-Control-Allow-Origin", "*")
                .entity(p)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        service.remove(id);
        return Response.ok() // 200

                .entity(new Cliente())
                .build();
    }
}
