/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.service;
import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.vendas.repository.CliemteRepositoryImpl;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ClienteService {
    @EJB
    private CliemteRepositoryImpl repository;

    public void save(Cliente cliente) {
        repository.save(cliente);
    }

    public Cliente find(int id) {
        return repository.find(id);
    }
       

    public List<Cliente> findAll() {
        System.err.println("to no list sc");
        return repository.findAll();
    }

    public void remove(int id) {
        repository.remove(id);
    }
    
}
