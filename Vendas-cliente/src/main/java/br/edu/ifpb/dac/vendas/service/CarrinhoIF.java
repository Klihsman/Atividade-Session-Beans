/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.vendas.service;

import br.edu.ifpb.dac.venda.entidades.Cliente;
import br.edu.ifpb.dac.venda.entidades.Produto;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose
 */
public interface CarrinhoIF extends Serializable {

    boolean add(Produto p, int id);

    List<Produto> getProdutos();

    public Cliente getCliente();

    double getTotal();

    boolean remover(int id, int cliente);

    void setProdutos(List<Produto> produtos);

    void setTotal(double total);

}
