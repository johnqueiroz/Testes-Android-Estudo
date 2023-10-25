package br.ufpe.cin.motorola.banco.cliente;

import br.ufpe.cin.motorola.banco.fachada.Fachada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    Cliente cliente;


    @BeforeEach
    void setUp() {
        cliente = new Cliente(null,null, null);
    }

    @Test
    void getCpf() {
        assertNull(cliente.getCpf());
        cliente.setCpf("12345678910");
        assertNotEquals("9638521147", cliente.getCpf());
        assertEquals("12345678910", cliente.getCpf());
    }

    @Test
    void getNome() {
        assertNull(cliente.getNome());
        cliente.setNome("Java");
        assertNotEquals("John", cliente.getNome());
        assertEquals("Java", cliente.getNome());
        assertNotNull(cliente.getNome());
    }

    @Test
    //Usando o toString() para retornar o tipo como string e não o objeto e verificar com o valor esperado.
    void getTipo() {
        assertNull(cliente.getTipo());
        cliente.setTipo(TipoCliente.CLASS);
        assertNotNull(cliente.getTipo().toString());
        assertNotEquals("VIP", cliente.getTipo().toString());
        assertEquals("CLASS", cliente.getTipo().toString());
    }

    @Test
    void setCpf() {
        assertNull(cliente.getCpf());
        cliente.setCpf("1234567891");
        assertNotNull(cliente.getCpf());
        assertEquals("1234567891", cliente.getCpf());
        assertNotEquals("123456789102", cliente.getCpf());
    }

    @Test
    void setNome() {
        assertNull(cliente.getNome());
        cliente.setNome("John");
        assertEquals("John", cliente.getNome());
        assertNotEquals("Java", cliente.getNome());
        assertNotNull(cliente.getNome());
    }

    @Test
    void setTipo() {
        assertNull(cliente.getTipo());
        cliente.setTipo(TipoCliente.VIP);
        assertNotNull(cliente.getTipo());
        assertNotEquals("Class", cliente.getTipo().toString());
        assertEquals("VIP", cliente.getTipo().toString());
    }
}