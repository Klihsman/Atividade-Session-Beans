/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.repository;


import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.venda.entidades.Venda;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class VendaRepositoryImpl  {

    @PersistenceContext(unitName = "br.edu.ifpb.dac_Vendas-cliente_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void save(Venda v) {
        em.merge(v);
    }

    public Venda find(int id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> findAll() {
        return em.createQuery("select v from Venda v ", Venda.class).getResultList();

    }

    public void remove(int id) {
        em.remove(find(id));
    }

}
