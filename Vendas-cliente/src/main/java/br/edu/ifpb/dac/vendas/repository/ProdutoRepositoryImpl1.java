/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.repository;


import br.edu.ifpb.dac.venda.entidades.Produto;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ProdutoRepositoryImpl1  {

    @PersistenceContext(unitName = "br.edu.ifpb.dac_Vendas-cliente_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void save(Produto produto) {
        em.merge(produto);
    }

    public Produto find(int id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> findAll() {
        return em.createQuery("select p from Produto p ", Produto.class).getResultList();

    }
     public List<Produto> localizarPorDescricao(String desc) {
        List<Produto> result = Collections.EMPTY_LIST;
        String consulta = "SELECT * FROM Produto WHERE descricao ilike '" + desc + "%' ";

        Query query = em.createNativeQuery(consulta, Produto.class);
        result = query.getResultList();
        return result;
    }

    public void remove(int id) {
        Produto p = find(id);
        em.remove(p);
    }

}
