/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.service;

import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.venda.entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class Carrinho implements CarrinhoIF {

    private List<Produto> produtos;
    private double total;
     private Cliente cliente;
     @EJB
     private ClienteService clienteService;

    public Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }
    

    

    @Override
    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public void setTotal(double total) {
        this.total = total;
    }

    
    @Override
    public boolean add(Produto p,int id) {
        if(!validarUsuario(id))
            return false;
            
        if (this.produtos.add(p)) {
            this.total = (this.total + p.getValor().doubleValue());
            
            return true;
        }
        return false;
    }

    @Override
    public boolean remover(int id, int cliente) {
        if(!validarUsuario(cliente)) return false;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                this.produtos.remove(produto);
                this.total = this.total - produto.getValor().doubleValue();

            }

        }
        return false;

    }

    private boolean validarUsuario(int id) {
        if(this.cliente!= null && this.cliente.getId() == id)
            return true;
        
        else{
            Cliente c = clienteService.find(id);
            if(c!=null){
                this.cliente= c;
                return true;
            }
        }
        return false;
    }
}
