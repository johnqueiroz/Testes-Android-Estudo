/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package br.ufpe.cin.motorola.banco;

import br.ufpe.cin.motorola.banco.cliente.Cliente;
import br.ufpe.cin.motorola.banco.cliente.TipoCliente;
import br.ufpe.cin.motorola.banco.conta.*;
import br.ufpe.cin.motorola.banco.excecoes.*;
import br.ufpe.cin.motorola.banco.fachada.Fachada;

public class App {
    //public static void main(String[] args) {
      //Fachada fachada = Fachada.obterInstancia();
 		//try {
 		  //  Cliente cli1 = new Cliente("123","Leopoldo",TipoCliente.CLASS);
 		    //Cliente cli2 = new Cliente("456","Maria",TipoCliente.VIP);
 		    //Cliente cli3 = new Cliente("789","Teste",TipoCliente.ESPECIAL);
             //eu que coloquei
 		    //Cliente cli4 = new Cliente("012","I", TipoCliente.ESPECIAL);


 		    //fachada.cadastrar(cli1);
 		    //fachada.cadastrar(cli2);
 		    //fachada.cadastrar(cli3);
            //fachada.cadastrar(cli4);
             /*
            fachada.descadastrarCliente("012");
            cli1.setCpf("012");
            fachada.atualizar(cli1);
            fachada.procurarCliente("012");
             até aqui
            */



 		    //Conta c1 = new Conta("1",100,cli1);
            //ContaBonificada cb1 = new ContaBonificada("2",100,cli2);
            //Poupanca p1 = new Poupanca("3",100,cli3);
            //fachada.cadastrar(c1);
            //fachada.cadastrar(cb1);
            //fachada.cadastrar(p1);


            /*feito por mim
            c1.setNumero("88");
            fachada.atualizar(c1);
            fachada.procurarConta("88");
            fachada.descadastrarConta("88");
            fachada.procurarConta("88");
            */


        //Conta c2 = new Conta("4",1000,cli2);
        //ContaBonificada cb2 = new ContaBonificada("5",1000,cli3);
        //Poupanca p2 = new Poupanca("6",1000,cli1);
        //fachada.cadastrar(c2);
        //fachada.cadastrar(cb2);
        //fachada.cadastrar(p2);


        //Conta c3= new Conta("7",1500,cli3);
        //ContaBonificada cb3 = new ContaBonificada("8",1500,cli1);
        //Poupanca p3 = new Poupanca("9",1500,cli2);
        //fachada.cadastrar(c3);
        //fachada.cadastrar(cb3);
        //fachada.cadastrar(p3);

        //fachada.creditar("4", 599);

//            ContaAbstrata c = fachada.procurarConta("598791872"); erro a exception de conta inexistente.
//            System.out.println(c.getSaldo()); saldo retornava apenas 0. Foi corrigido
//            c.debitar(10);


//            jeito certo abaixo

        //  ContaAbstrata c = fachada.procurarConta("4");
        //  fachada.procurarConta("4");
        //          System.out.println(c.getSaldo()); //verifica o saldo, agora corretamente com o return saldo.
//            fachada.debitar("4",10); //debita 10
        //System.out.println(c.getSaldo()); //retorna o saldo corretamente.

        //}
        //catch (SaldoInsuficienteException e) {
        //	e.printStackTrace();
        //}catch (ClienteExistenteException e) {
        //	e.printStackTrace();
        //} catch (ContaExistenteException e) {
        //	e.printStackTrace();
        //} catch (ClienteInexistenteException e) {
        //		e.printStackTrace();
        //} catch (ClienteInvalidoException e) {
        //	e.printStackTrace();
        //} catch (ContaInexistenteException e) {
        //	e.printStackTrace();
        //}
      //  }
}
