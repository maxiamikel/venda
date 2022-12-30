package com.maxi.venda.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxi.venda.entities.Pedido;
import com.maxi.venda.entities.Pessoa;
import com.maxi.venda.entities.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
@Transactional
public class VendaService {
        @Autowired
        private EntityManager em;

        public void incluirDadosIniciais() {
                em.createQuery("delete from ItemPedido").executeUpdate();
                em.createQuery("delete from Pedido").executeUpdate();
                em.createQuery("delete from Pessoa").executeUpdate();
                em.createQuery("delete from Produto").executeUpdate();

                Pessoa amikel = new Pessoa("Amikel Maxi");

                Produto cerveja = new Produto("Cerveja Scol, caixa com 12 unidades / 350ml", new BigDecimal("48.99"));
                Produto arroz = new Produto("Arroz prato fino", new BigDecimal("29.99"));

                Pedido p01Amikel = new Pedido(1L, new Date(), amikel);

                p01Amikel.adicionarItem(cerveja, new BigDecimal("2.00"), new BigDecimal("10.00"));
                p01Amikel.adicionarItem(arroz, new BigDecimal("2.50"), new BigDecimal("10.00"));

            

                em.persist(amikel);
                em.persist(cerveja);
                em.persist(arroz);
                em.persist(p01Amikel);
                em.flush();

        }

        public Pedido recuperarPedidoPeloNúmero(long númeroDoPedido) {
                System.out.println("==================");
                TypedQuery<Pedido> query = em.createQuery("select p from Pedido p where p.numero = :numero", Pedido.class);
                Pedido recuperado = query.setParameter("numero", númeroDoPedido).getSingleResult();
                System.out.println("======================");
                return recuperado;
        }

}