package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void atualizarObjeto() {
        Cliente cliente = new Cliente();

        cliente.setId(1);
        cliente.setNome("Jose");


        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
        Assertions.assertEquals("Jose", clienteVerificacao.getNome());
    }

    @Test
    public void removerObjeto() {
        // se não buscarmos o objeto na base de dados irá dar um erro de detached(desanexado)
        Cliente cliente = entityManager.find(Cliente.class,2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

//        entityManager.clear(); Não é necessário na asserção para operação de remoção.

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNull(clienteVerificacao);
    }

    @Test
    public void inserirObjeto() {
        Cliente cliente = new Cliente();

        cliente.setId(3);
        cliente.setNome(" Camilo ");


        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear(); // Limpa a memória e obriga a buscar os dados direto a base.

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
    }

    @Test
    public void busarPorIdentificador() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals("Pranchana Jack", cliente.getNome());
    }


}
