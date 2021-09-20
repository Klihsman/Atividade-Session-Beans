/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.repository;


import br.edu.ifpb.dac.venda.entidades.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class CliemteRepositoryImpl  {

    @PersistenceContext(unitName = "br.edu.ifpb.dac_Vendas-cliente_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void save(Cliente cliente) {
        em.merge(cliente);
    }

    public Cliente find(int id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> findAll() {
        System.err.println("to no list repo");
        return em.createQuery("select c from Cliente c ", Cliente.class).getResultList();

    }

    public void remove(int id) {
        Cliente c = find(id);
        em.remove(c);
    }

}
