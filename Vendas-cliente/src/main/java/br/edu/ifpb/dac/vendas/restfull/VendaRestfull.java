/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.restfull;

import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.venda.entidades.Produto;
import br.edu.ifpb.dac.venda.entidades.Venda;
import br.edu.ifpb.dac.vendas.service.Carrinho;
import br.edu.ifpb.dac.vendas.service.CarrinhoIF;
import br.edu.ifpb.dac.vendas.service.VendaService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;

@Path("/venda")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class VendaRestfull implements Serializable {

    @Inject
    private VendaService service;
    @EJB
    private CarrinhoIF carrinho;

    @Path("/finalizar")
    @GET
    public Response finalizar() {

        Venda v = new Venda();
        v.setProdutos(carrinho.getProdutos());
        v.setTotal(carrinho.getTotal());
        v.setCliente(carrinho.getCliente());
            service.save(v);

            return Response.ok()
                    //              
                    .entity(v)
                    .build();
   

    }

    @GET
    public Response recuperar() {

        GenericEntity<List<Venda>> entity
                = new GenericEntity<List<Venda>>(service.findAll()) {
        };

        return Response.ok()
                //              
                .entity(entity)
                .build();

    }

    @PUT
    public Response adicionarAoCarrinho(Produto p, @HeaderParam("Authorization") int cliente) {

        boolean ok = this.carrinho.add(p, cliente);
        if (ok) {
            return Response.ok()
                    .entity(p)
                    .build();
        }
        return Response.status(Response.Status.fromStatusCode(401)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerDoCarrinho(@PathParam("id") int id, @HeaderParam("Authorization") int cliente) {

        boolean ok = this.carrinho.remover(id, cliente);if(!ok){
            return Response.status(Response.Status.fromStatusCode(401)).build();
        }
        return Response.ok() // 200
                .entity(new Produto())
                .build();

    }

}
