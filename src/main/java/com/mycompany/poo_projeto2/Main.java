/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo_projeto2;

import java.util.Scanner;

/**
 *
 * @author filip
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*-----------Inicializando Hospital da Madeira---------------------*/
        Hospital madeiraH = new Hospital(); // Criando um novo Hospital
        //Médicos contratados:
        madeiraH.criarMédico("Jörge Estevinho", 15375902, 1980);
        madeiraH.criarMédico("Maria Gloria", 35402782, 1988);
        madeiraH.criarMédico("Jacinto Pinto", 53982414, 1995);
        //Enfermeiros Chefes:
        madeiraH.criarEnfermeiroChefe("Petra Pedra", 23849123, 1978);
        madeiraH.criarEnfermeiroChefe("Ricardo Milos", 24128492, 1988);
        madeiraH.criarEnfermeiroChefe("Parte Rocha", 92742142, 1985);
        //Enfermeiros Especialistas:
        madeiraH.criarEnfermeiroEspecialista("Luis Ferreira", 23648192, 1992);
        madeiraH.criarEnfermeiroEspecialista("Celeste Luz", 25384716, 1962);
        madeiraH.criarEnfermeiroEspecialista("Bernardo Gouveia", 54637289, 1992);
        madeiraH.criarEnfermeiroEspecialista("Jóse Teresa", 24142134, 1982);
        madeiraH.criarEnfermeiroEspecialista("Mario Paz", 24151223, 1997);
        madeiraH.criarEnfermeiroEspecialista("Joaquim Branco", 12412424, 1989);
        //Enfermeiros Auxiliares:
        madeiraH.criarEnfermeiroAuxiliar("Marta Lua", 23677782, 1998);
        madeiraH.criarEnfermeiroAuxiliar("Fernando Mendes", 21288842, 1984);
        madeiraH.criarEnfermeiroAuxiliar("Roberto Carlos", 44223891, 1969);
        madeiraH.criarEnfermeiroAuxiliar("Izalia Hölter", 63849179, 1986);
        madeiraH.criarEnfermeiroAuxiliar("Tony Tozé", 23641823, 1978);
        madeiraH.criarEnfermeiroAuxiliar("João Lucas", 29743123, 1995);
        madeiraH.criarEnfermeiroAuxiliar("Cristiana Vaz", 23547189, 1997);
        //Pacientes já em lista de espera:
        madeiraH.criarPaciente("Cláudia Caires", 23948123, 2000);
        madeiraH.criarPaciente("Cristina Ferreira", 23472829, 1987);
        madeiraH.criarPaciente("Xina Wuhan", 37324214, 2019);
        madeiraH.criarPaciente("Ricardo Reis", 24123134, 1887);
        madeiraH.criarPaciente("Fernando Pessoa", 54291492, 1888);
        madeiraH.criarPaciente("Keanu Reeves", 9999999, 2077);

        madeiraH.medTemEsp();//Médicos inciais têm enfmeiros Especialista logo de inicio.

        /*-----------------------------------------------------------------*/
        //---MENU INICIAL---//
        System.out.println("Bem-vindo ao Hospital da Madeira!");
        System.out.println("A que menu deseja aceder? \n");
        System.out.println("=======MENU INICIAL======");
        System.out.println("1 - Menu Médico");
        System.out.println("2 - Menu Enfermeiro");
        System.out.println("3 - Menu Administrador");
        System.out.println("0 - Nenhum, Sair");
        //Scanner usando no main inteiro
        Scanner scanner = new Scanner(System.in); //Scanner para todas as opções

        try {
            int escolha = scanner.nextInt(); //Não causar problemas ao inicializar
            while (escolha != 0) {
                switch (escolha) {
                    case 1: //Menu Médico
                        System.out.println("=======MENU MÉDICO=======");
                        System.out.println("1 - Listar pacientes em espera no hospital");
                        System.out.println("2 - Listar pacientes a aguardar alta");
                        System.out.println("3 - Diagnóstico ao paciente");
                        System.out.println("4 - Dar alta hospitalar");
                        System.out.println("5 - Requerimento de auxiliares");
                        System.out.println("6 - Voltar ao menu anterior");
                        int escolhaM1 = scanner.nextInt(); //Input para os menus do Médico
                        switch (escolhaM1) { //Programação dos casos
                            case 1: //Listar pacientes em espera no hospital
                                System.out.println(madeiraH.printPacientes());//Devolve o Array de Pacientes;
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 2: //Listar pacientes a aguardar alta
                                madeiraH.printPacientesAlta();
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 3: //Diagnóstico ao paciente
                                madeiraH.diagnosticarPaciente(madeiraH.escolherMed(), madeiraH.escolherEnf());
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 4: //Dar alta hospitalar
                                madeiraH.darAltaMédica();// Dá alta médica.
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 5: //Requerimento de auxiliares
                                //Requesitar o numero de enfermeiros auxiliares precisos e guarda juntamente com o nome do médico.
                                System.out.println(madeiraH.printMédicos());//Print dos médicos
                                System.out.println("Escolha o ID do Médico");
                                int id = scanner.nextInt();
                                System.out.println(madeiraH.pedirEnfermeiros(id, madeiraH.getMédicos().get(0).numEnfermeiros()));
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 6://MENU INICIAL
                                System.out.println("=======MENU INICIAL=======");
                                System.out.println("1 - Menu Médico");
                                System.out.println("2 - Menu Enfermeiro");
                                System.out.println("3 - Menu Administrador");
                                System.out.println("0 - Nenhum, Sair");
                                escolha = scanner.nextInt(); //Escolha do menu inicial
                                break;
                            default: //Valor inserido não é válido
                                System.out.println("Valor inserido inválido!");
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                        }
                        break;
                    case 2: //Menu Enfermeiro
                        System.out.println("=======MENU ENFERMEIRO=======");
                        System.out.println("1 - Listar Enfermeiros de médico");
                        System.out.println("2 - Listar pacientes a aguardar curativo");
                        System.out.println("3 - Atribuir enfermeiro-especialista a médico");
                        System.out.println("4 - Aplicar curativo a paciente");
                        System.out.println("5 - Voltar ao menu anterior");
                        int escolhaM2 = scanner.nextInt();//Input para os menus dos Enfermeiros
                        switch (escolhaM2) { // Programação dos casos
                            case 1: //Listar Enfermeiros de médico
                                System.out.println(madeiraH.printMédicos());//Print dos médicos
                                System.out.println("Escolha o ID do Médico");
                                int id = scanner.nextInt();
                                System.out.println(madeiraH.enfermeirosDoMédico(id));
                                scanner.nextLine(); //Consume \n do int escolhaM2 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 2: //Listar pacientes a aguardar curativo
                                madeiraH.printPacientesCurativo();
                                scanner.nextLine(); //Consume \n do int escolhaM2 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 3: //Atribuir enfermeiro-especialista a médico
                                System.out.println(madeiraH.printMédicos());
                                System.out.println("Escolha o ID do médico que quer adicionar um médico especialista");
                                id = scanner.nextInt();
                                madeiraH.atribuirEspecialista(id);
                                scanner.nextLine(); //Consume \n do int escolhaM2 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 4: //Aplicar curativo a paciente
                                madeiraH.aplicarCurativo();
                                scanner.nextLine(); //Consume \n do int escolhaM2 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 5: //MENU INICIAL
                                System.out.println("=======MENU INICIAL=======");
                                System.out.println("1 - Menu Médico");
                                System.out.println("2 - Menu Enfermeiro");
                                System.out.println("3 - Menu Administrador");
                                System.out.println("0 - Nenhum, Sair");
                                escolha = scanner.nextInt(); //Escolha do menu inicial
                                break;
                            default: //Valor inserido é inválido
                                System.out.println("Valor inserido inválido!");
                                scanner.nextLine(); //Consume \n do int escolhaM2 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                        }
                        break;
                    case 3: //Menu Administrador
                        System.out.println("=======MENU ADMINISTRADOR=======");
                        System.out.println("1 - Criar médico");
                        System.out.println("2 - Criar enfermeiro-especialista");
                        System.out.println("3 - Criar enfermeiro-auxiliar");
                        System.out.println("4 - Criar novo paciente");
                        System.out.println("5 - Promover enfermeiro a chefe");
                        System.out.println("6 - Aumentar anos de carreira de todos os enfermeiros");
                        System.out.println("7 - Listar enfermeiros");
                        System.out.println("8 - Listar médicos");
                        System.out.println("9 - Listar pedidos para enfermeiros-auxiliares");
                        System.out.println("10 - Listar pacientes em espera no hospital");
                        System.out.println("11 - Atirar pedidos para enfermeiros-auxiliares para a trituradora");
                        System.out.println("12 - Atende ao pedido para enfermeiros-auxiliares");
                        System.out.println("13 - Virus outbreak");
                        System.out.println("14 - Agenda dos tratamentos");
                        System.out.println("15 - Histórico de altas médicas");
                        System.out.println("16 - Histórico de Mortes");
                        System.out.println("17 - Voltar ao menu anterior");
                        System.out.println("0 - Sair da aplicação");
                        int escolhaM3 = scanner.nextInt(); //Input para os menus dos Adiministradores
                        switch (escolhaM3) { //Programação dos casos
                            case 1: //Criar médico
                                scanner.nextLine(); //Elimina o resto do scan do nextInt(). Fica na linha e lê \n quando fazemos scanner.nextLine();
                                System.out.println("Novo Médico");
                                System.out.println("Insira o NOME:");
                                String nome = scanner.nextLine();
                                System.out.println("Insira o CC: ");
                                int cc = scanner.nextInt();
                                System.out.println("Insira o ANO DE NASCIMENTO:");
                                int ano = scanner.nextInt();
                                madeiraH.criarMédico(nome, cc, ano);
                                System.out.println("Médico pronto para receber o ordenado!");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 2: //Criar Enfermeiro Especialista
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Novo Enfermeiro Especialista");
                                System.out.println("Insira o NOME: ");
                                nome = scanner.nextLine();
                                System.out.println("Insira o CC: ");
                                cc = scanner.nextInt();
                                System.out.println("Insira o ANO DE NASCIMENTO: ");
                                ano = scanner.nextInt();
                                madeiraH.criarEnfermeiroEspecialista(nome, cc, ano);
                                System.out.println("Enfermeiro Especialista pronto para o serviço!");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 3: //Criar Enfermeiro Auxiliar
                                scanner.nextLine();//Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Novo Enfermeiro Auxiliar");
                                System.out.println("Insira o NOME: ");
                                nome = scanner.nextLine();
                                System.out.println("Insira o CC: ");
                                cc = scanner.nextInt();
                                System.out.println("Insira o ANO DE NASCIMENTO: ");
                                ano = scanner.nextInt();
                                madeiraH.criarEnfermeiroAuxiliar(nome, cc, ano);
                                System.out.println("Enfermeiro Axuliar à espera de prestar auxilio!");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 4: //Criar novo paciente
                                scanner.nextLine();//Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Novo paciente a entrar no hospital");
                                System.out.println("Insira o NOME: ");
                                nome = scanner.nextLine();
                                System.out.println("Insira o CC: ");
                                cc = scanner.nextInt();
                                System.out.println("Insira o ANO DE NASCIMENTO: ");
                                ano = scanner.nextInt();
                                madeiraH.criarPaciente(nome, cc, ano);
                                System.out.println("Paciente à espera de consulta!");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 5: //Promover enfermeiro a chefe
                                System.out.println("Verificando se existe enfermeiros que cumprem os requesitos...");
                                if (madeiraH.promoverEnfermeiros()) {
                                    System.out.println("Enfermeiro Promovido!");
                                    scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                    System.out.println("Pressione ENTER para voltar ao menu!");
                                    scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                    break;
                                } else {
                                    System.out.println("Nenhum Enfermeiro cumpre os requesitos :(");
                                    scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                    System.out.println("Pressione ENTER para voltar ao menu!");
                                    scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                    break;
                                }
                            case 6://Aumentar anos de carreira de todos os enfermeiros
                                madeiraH.aumentarAnosDeCarreira();
                                System.out.println("Todos os enfermeiros tiveram os seus anos de carreira aumentados! +1 ");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 7: //Listar Enfermeiros
                                System.out.println(madeiraH.printEnfermeiros());
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 8: //Listar médicos
                                System.out.println(madeiraH.printMédicos());
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 9: //Listar pedidos para enfermeiros-auxiliares
                                System.out.println(madeiraH.printPedidosEnfermeiros());//Pedidos efetuados
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 10: //Listar pacientes em espera no hospital
                                System.out.print(madeiraH.printPacientes());
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 11: //Atirar pedidos para enfermeiros-auxiliares para a trituradora
                                madeiraH.arderPedidos();//Arde os pedidos todos
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 12: //Receber auxiliares
                            try {
                                madeiraH.entregarEnfermeiros();
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                            } catch (Exception e) {
                                System.out.println("Nenhum pedido pendente!");
                            } finally {
                                scanner.nextLine(); //Consume \n do int escolhaM1 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!!
                                break;
                            }
                            case 13: //Virus outbreak
                                madeiraH.pandemia();
                                System.out.println("Todos estão Insfetados! Que PANDEMIA!");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 14: //Agenda de Tratamentos
                                madeiraH.printAgendaTratamento();
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 15: //Histórico Alta
                                System.out.println(madeiraH.printHistóricoAlta());
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 16: //Histórico de Mortes
                                System.out.println(madeiraH.printHistóricoMorte());
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 17: //Voltar ao menu anterior
                                //MENU INICIAL
                                System.out.println("=======MENU INICIAL=======");
                                System.out.println("1 - Menu Médico");
                                System.out.println("2 - Menu Enfermeiro");
                                System.out.println("3 - Menu Administrador");
                                System.out.println("0 - Nenhum, Sair");
                                escolha = scanner.nextInt();
                                break;
                            default: //Valor inserido não válido
                                System.out.println("Valor inserido inválido!");
                                scanner.nextLine(); //Consume \n do int escolhaM3 = scanner.nextInt();
                                System.out.println("Pressione ENTER para voltar ao menu!");
                                scanner.nextLine(); //Enquanto não pressionar ENTER não volta ao menu!
                                break;
                            case 0: //Sair do Programa
                                System.out.println("Obrigado e volte sempre!");
                                escolha = 0;
                                break;
                        }
                        break;
                    case 0: //Sair do Programa
                        System.out.println("Obrigado e volte sempre!");
                        escolha = 0;
                        break;
                    default: //Valor inserido não é válido
                        System.out.println("Valor inserido inválido. Voltando ao Menu Inicial! \n");
                        System.out.println("=======MENU INICIAL======");
                        System.out.println("1 - Menu Médico");
                        System.out.println("2 - Menu Enfermeiro");
                        System.out.println("3 - Menu Administrador");
                        System.out.println("0 - Nenhum, Sair");
                        escolha = scanner.nextInt();
                        break;
                }
            }
        } catch (Exception erroFatal) {
            System.out.println("ERRO FATAL!\nDESLIGANDO O PROGRAMA!");
        }
    }
}
