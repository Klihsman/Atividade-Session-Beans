/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.service;
import br.edu.ifpb.dac.venda.entidades.Venda;
import br.edu.ifpb.dac.vendas.repository.VendaRepositoryImpl;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class VendaService {
    @EJB
    private VendaRepositoryImpl repository;

    public void save(Venda v) {
        repository.save(v);
    }

    public Venda find(int id) {
        return repository.find(id);
    }
       

    public List<Venda> findAll() {
        return repository.findAll();
    }

    public void remove(int id) {
        repository.remove(id);
    }
    
}
