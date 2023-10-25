package br.ufpe.cin.motorola.banco.fachada;

import java.lang.reflect.Executable;
import java.lang.reflect.Field;

import br.ufpe.cin.motorola.banco.cliente.*;
import br.ufpe.cin.motorola.banco.conta.*;
import br.ufpe.cin.motorola.banco.excecoes.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FachadaTest {

    Fachada fachada;

    //Inicializando clientes e contas
    Cliente cliente, cliente2, cliente3;
    Conta c1, c2, c3, c4;
    Poupanca p1;
    ContaImposto ci2;
    ContaBonificada cb2;

    RepositorioContasMap RepConta;
    RepositorioClientesMap RepCliente;

    @BeforeEach
    void setUp() throws ClienteInexistenteException, ClienteExistenteException, ContaExistenteException, ClienteInvalidoException {
        fachada = Fachada.obterInstancia();

        //Criando clientes na memória e cadastrando
        cliente = new Cliente("12345678901", "john", TipoCliente.ESPECIAL);
        cliente2 = new Cliente("741236589", "john", TipoCliente.ESPECIAL);
        fachada.cadastrar(cliente);
        fachada.cadastrar(cliente2);

        RepConta = new RepositorioContasMap();
        RepCliente = new RepositorioClientesMap();

        //Criando contas na memória e cadastrando
        c1 = new Conta("1",100,cliente);
        fachada.cadastrar(c1);

        c2 = new Conta("2",100,cliente2);
        fachada.cadastrar(c2);
    }

    @AfterEach
    void tearDown() {
        try {
            Field instance = Fachada.class.getDeclaredField("instancia");
            instance.setAccessible(true);
            instance.set(null, null);
            fachada = null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    @Test
	public void obterInstanciaFachada() {
		Fachada f2 = Fachada.obterInstancia();
		assertEquals(fachada,f2);
	}

    @Test
    void atualizar() throws ClienteInexistenteException {

        //Verificando caso o cliente não exista
        Exception exception = assertThrows(ClienteInexistenteException.class, ()->{
            cliente = new Cliente("1234506", "john", TipoCliente.ESPECIAL);
            fachada.atualizar(cliente);
        });
        String actualMessage = exception.getMessage();
        String ExpectedMessage = "Cliente inexistente!";

        assertTrue(actualMessage.contains(ExpectedMessage));

        // Verificação antes de mudar o nome
        assertEquals("john", cliente.getNome());

        cliente = new Cliente("12345678901", "John", TipoCliente.VIP);
        fachada.atualizar(cliente);
        // Verficando se realmente mudou
        assertEquals("John", cliente.getNome());

    }

    @Test
    void procurarCliente() throws ClienteInexistenteException {

        //Verificando caso o cliente não exista
        Exception exception = assertThrows(ClienteInexistenteException.class, ()-> {
           fachada.procurarCliente("12345678910");
        });

        String expectedMessage = "Cliente inexistente!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        //Verificando que o cliente realmente existe
        assertEquals(cliente, fachada.procurarCliente("12345678901"));

    }

    @Test
    void cadastrar() throws ClienteExistenteException, ClienteInexistenteException {

        //Verificação de cadastro caso o cliente já exista
        Exception exception = assertThrows(ClienteExistenteException.class, ()->{
            fachada.cadastrar(cliente);
        });

        String expectedMessage = "Cliente ja existe!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        //Verificação de cadastro caso o cliente não exista
        cliente2 = new Cliente("77777777777", "Projeto", TipoCliente.VIP);
        fachada.cadastrar(cliente2);

        assertEquals(cliente2, fachada.procurarCliente("77777777777"));
    }

    @Test
    void descadastrarCliente(){

       //Verificando o descadastramento de um cliente inexistente
       Exception exception = assertThrows(ClienteInexistenteException.class, ()-> {
           fachada.descadastrarCliente("88888");
        });

       String expectedMessage = "Cliente inexistente!";
       String actualMessage = exception.getMessage();

       assertTrue(actualMessage.contains(expectedMessage));

       //Verificando o descadastramento de um cliente
       Exception descadastrou = assertThrows(ClienteInexistenteException.class, ()->{
          fachada.descadastrarCliente("12345678901");
          fachada.procurarCliente("12345678901");
       });

        String actualMessage2 = descadastrou.getMessage();
        assertTrue(actualMessage2.contains(expectedMessage));


    }

    @Test
    void testAtualizar() throws ContaInexistenteException {
        //Verificando caso a conta não exista
        Exception exception = assertThrows(ContaInexistenteException.class, ()->{
            Conta c1 = new Conta("3636",100,cliente);
            fachada.atualizar(c1);
        });

        String actualMessage = exception.getMessage();
        String ExpectedMessage = "Conta Inexistente!";

       assertTrue(actualMessage.contains(ExpectedMessage));

       // Verificação antes de mudar a conta
        assertEquals(100, c1.getSaldo());

        Conta c1 = new Conta("1",300,cliente);
        fachada.atualizar(c1);

        // Verficando se realmente mudou
        assertEquals(300, c1.getSaldo());
    }

    @Test
    void procurarConta() throws ContaInexistenteException{

        //Verificando caso a conta não exista
        Exception exception = assertThrows(ContaInexistenteException.class, ()->{
           fachada.procurarConta("55");
        });

        String actualMessage = exception.getMessage();
        String ExpectedMessage = "Conta Inexistente!";

        assertTrue(actualMessage.contains(ExpectedMessage));
        //Verificando que a conta existe

        assertTrue(c2.equals(fachada.procurarConta("2")));
    }

    @Test
    void testCadastrar() throws ContaExistenteException, ClienteInexistenteException, ClienteInvalidoException, ContaInexistenteException {

        //Verificando caso a conta já exista
        Exception exception = assertThrows(ContaExistenteException.class, ()->{
            fachada.cadastrar(c1);
        });

        String actualMessage = exception.getMessage();
        String ExpectedMessage = "Conta ja existe!";

        assertTrue(actualMessage.contains(ExpectedMessage));

        //Verificando o cadastramento correto
        c3 = new Conta("99",100,cliente);
        ContaImposto ci = new ContaImposto("7", 1000, cliente);
        ContaBonificada cb = new ContaBonificada("8", 1000, cliente);



        fachada.cadastrar(c3);
        fachada.cadastrar(ci);
        fachada.cadastrar(cb);

        c4 = new Conta("1000", cliente);
        fachada.cadastrar(c4);
        p1 = new Poupanca("1003", cliente);
        fachada.cadastrar(p1);
        ci2 = new ContaImposto("1001", cliente);
        fachada.cadastrar(ci2);
        cb2 = new ContaBonificada("1002", cliente);
        fachada.cadastrar(cb2);


        //Verificando que as contas estão cadastradas
        assertEquals(p1, fachada.procurarConta("1003"));
        assertEquals(c4, fachada.procurarConta("1000"));
        assertEquals(ci2, fachada.procurarConta("1001"));
        assertEquals(cb2, fachada.procurarConta("1002"));
        assertTrue(c3.equals(fachada.procurarConta("99")));
        assertEquals(ci, fachada.procurarConta("7"));
        assertEquals(cb, fachada.procurarConta("8"));

    }

    @Test
    void descadastrarConta(){

        //Verificando o descadastramento de uma conta inexistente
        Exception exception = assertThrows(ContaInexistenteException.class, ()-> {
            fachada.descadastrarConta("148645");
        });

        String expectedMessage = "Conta Inexistente!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        //Verificando o descadastramento correto da conta
        Exception descadastrando = assertThrows(ContaInexistenteException.class, ()-> {
            fachada.descadastrarConta("1");
            fachada.procurarConta("1");
        });
        String MensagemReal = descadastrando.getMessage();
        assertTrue(MensagemReal.contains(expectedMessage));
    }
    @Test
    void creditar() throws ContaInexistenteException {

        //Verificando creditar em conta inexistente
        Exception exception = assertThrows(ContaInexistenteException.class, ()->{
            fachada.creditar("65461", 500);
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Conta Inexistente!";

        assertTrue(actualMessage.contains(expectedMessage));


        //Verificando com a conta existente
        fachada.creditar("1", 100);
        assertEquals(200, c1.getSaldo());
    }

    @Test
    void debitar() throws SaldoInsuficienteException, ContaInexistenteException {
        //Verificando debitar com saldo insuficiente
        Exception saldoIns = assertThrows(SaldoInsuficienteException.class, ()->{
            fachada.debitar("1", 50000);
        });

        String mensagemTrue = saldoIns.getMessage();
        String mensagemFixa = "Saldo insuficiente!";

        assertTrue(mensagemTrue.contains(mensagemFixa));



        //Verificando debitar em conta inexistente
        Exception exception = assertThrows(ContaInexistenteException.class, ()->{
            fachada.debitar("65461", 500);
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Conta Inexistente!";

        assertTrue(actualMessage.contains(expectedMessage));


        //Verificando com a conta existente
        fachada.debitar("1", 100);
        assertEquals(0, c1.getSaldo());
    }

    @Test
    void transferir() throws SaldoInsuficienteException, ContaInexistenteException {

        //transferindo da conta c1 para a conta c2
        fachada.transferir("1", "2", 50);

        assertEquals(150, c2.getSaldo());

        //transferindo com saldo insuficiente
        Exception saldoIns = assertThrows(SaldoInsuficienteException.class, ()->{
            fachada.transferir("1", "2" ,50000);
        });

        String mensagemTrue = saldoIns.getMessage();
        String mensagemFixa = "Saldo insuficiente!";

        assertTrue(mensagemTrue.contains(mensagemFixa));

        //Verificando transferir em conta inexistente
        Exception exception = assertThrows(ContaInexistenteException.class, ()->{
            fachada.transferir("65461","500",200);
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Conta Inexistente!";

        assertTrue(actualMessage.contains(expectedMessage));

    }

  @Test
    void clienteInvalido(){
        Exception exception = assertThrows(ClienteInvalidoException.class, ()->{
        c4 = new Conta("1000", cliente3);
        fachada.cadastrar(c4);

        assertEquals(c4, fachada.procurarConta("1000"));
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Cliente invalido!";

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void listarContas(){
        RepositorioContasArray lista = new RepositorioContasArray();
        assertEquals(100, lista.listar().size());

        RepositorioClientesArray listaCliente = new RepositorioClientesArray();
        assertEquals(100, listaCliente.listar().size());
    }


    @Test
    void repositorioContasMap(){
        assertAll("Teste repositorio conta", ()->{
               c4 = new Conta("1000", cliente3);
               RepConta.inserir(c4);
               assertEquals(c4, RepConta.procurar("1000"));
        }, ()->{
                Exception exception = assertThrows(ContaInexistenteException.class , ()->{
                    RepConta.atualizar(c1);
                });

                String actualMessage = exception.getMessage();
                String expectedMessage = "Conta Inexistente!";

                assertTrue(actualMessage.contains(expectedMessage));
        }, ()->{
            c4 = new Conta("1000", cliente);
            RepConta.atualizar(c4);

                assertEquals(cliente, c4.getCliente());
        }, ()->{
                Exception exception = assertThrows(ContaInexistenteException.class, ()->{
                    RepConta.remover("1000");
                    RepConta.procurar("1000");
                });
                String actualMessage = exception.getMessage();
                String expectedMessage = "Conta Inexistente!";

                assertTrue(actualMessage.contains(expectedMessage));

        }, ()->{
            Exception exception = assertThrows(ContaInexistenteException.class, ()->{
                RepConta.remover("2000");
            });
            String actualMessage = exception.getMessage();
            String expectedMessage = "Conta Inexistente!";

            assertTrue(actualMessage.contains(expectedMessage));
        }, ()->{
            int tam = RepConta.listar().size();
            assertEquals(0, tam);
        });
    }

    @Test
    void repositorioCliente() {
        assertAll("Teste repositorio cliente", () -> {
            Cliente cliente = new Cliente("12345678901", "john", TipoCliente.VIP);
            RepCliente.inserir(cliente);
            assertEquals(cliente, RepCliente.procurar("12345678901"));
        }, () -> {
            Exception exception = assertThrows(ClienteInexistenteException.class, () -> {
                RepCliente.atualizar(cliente2);
            });

            String actualMessage = exception.getMessage();
            String expectedMessage = "Cliente inexistente!";

            assertTrue(actualMessage.contains(expectedMessage));
        }, () -> {
            cliente = new Cliente("12345678901", "John John", TipoCliente.CLASS);
            RepCliente.atualizar(cliente);

            assertEquals("John John", cliente.getNome());
        }, () -> {
            Exception exception = assertThrows(ClienteInexistenteException.class, () -> {
                RepCliente.remover("12345678901");
                RepCliente.procurar("12345678901");
            });
            String actualMessage = exception.getMessage();
            String expectedMessage = "Cliente inexistente!";

            assertTrue(actualMessage.contains(expectedMessage));

        }, () -> {
            Exception exception = assertThrows(ClienteInexistenteException.class, () -> {
                RepCliente.remover("2000");
            });
            String actualMessage = exception.getMessage();
            String expectedMessage = "Cliente inexistente!";

            assertTrue(actualMessage.contains(expectedMessage));
        }, ()->{
            int tam = RepCliente.listar().size();
            assertEquals(0, tam);
        });
    }

    @Test
    void diminuiSaldo(){
        assertAll("Teste diminui saldo", ()->{
            ci2 = new ContaImposto("1212", 500, cliente);
            fachada.cadastrar(ci2);
            ci2.diminuiSaldo(100);

            assertEquals(400, ci2.getSaldo());
        }, ()->{
            ci2.diminuiSaldo(400);
            assertEquals(0, ci2.getSaldo());
        }, ()->{
            Exception exception = assertThrows(SaldoInsuficienteException.class, ()->{
                ci2.diminuiSaldo(600);
            });
            String actualMessage = exception.getMessage();
            String expectedMessage = "Saldo insuficiente!";

            assertTrue(actualMessage.contains(expectedMessage));
        });

    }
    @Test
    void clientemap() {
     Exception exception = assertThrows(ClienteInexistenteException.class, ()->{
         cliente3 = new Cliente("789456123", "John", TipoCliente.ESPECIAL);
         RepCliente.inserir(cliente3);
         RepCliente.remover("789456123");
         RepCliente.existe("789456123");
         RepCliente.procurar("789456123");
     });


        String actualResult = exception.getMessage();
        String expectedResult = "Cliente inexistente!";

        assertTrue(actualResult.contains(expectedResult));
    }
}