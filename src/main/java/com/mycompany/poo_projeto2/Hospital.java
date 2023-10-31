/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo_projeto2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author filip
 */
public class Hospital {

    private ArrayList<Pessoa> pacientes; //Listar Pacientes em lista de espera.
    private ArrayList<Pessoa> pacientesEsperaCurativo;//Listar Pacientes aguardando Alta
    private ArrayList<Pessoa> pacientesEsperaAlta;//Listar todos os pacientes à espera de alta no hospital.
    private ArrayList<Médico> médicos; //Listar Médicos
    private ArrayList<Enfermeiro> enfermeirosChefes;//Listar Enfermeiros Chefes
    private ArrayList<Enfermeiro> enfermeirosEspecialistas;//Listar Enfermeiros Especialistas
    private ArrayList<Enfermeiro> enfermeirosAuxiliares;//Listar Enfermeiros Auxiliares
    private int id; //id interno do hospital

    //Array para os pedidos dos enfermeiros auxiliares - ID MEDICO, ENFERMEIRO
    private ArrayList<Integer> pediuEnfermeiros;//Cuida dos enfermeiros que estão a cargo de um médico.

    private final int ANOS_ENFER_CHEFE = 15;// Anos que o enfermeiro chefe começa e anos necessários para receber uma promoção

    private int idAgenda;
    private HashMap<Integer, String> agenda; //trata das consultas do hospital
    private String históricoAlta; //histórico das altas dos pacientes
    private String históricoMorte;  //histórico dos pacientes mortos no tratamneto

    //Construtor 
    public Hospital() {
        //Iniciar os ArrayList no construtor
        pacientes = new ArrayList<Pessoa>();
        médicos = new ArrayList<Médico>();
        enfermeirosChefes = new ArrayList<Enfermeiro>();
        enfermeirosEspecialistas = new ArrayList<Enfermeiro>();
        enfermeirosAuxiliares = new ArrayList<Enfermeiro>();
        //ArrayLists para o funcionamento interno
        pediuEnfermeiros = new ArrayList<Integer>();
        pacientesEsperaCurativo = new ArrayList<Pessoa>();
        pacientesEsperaAlta = new ArrayList<Pessoa>();
        //Agenda de consultas
        agenda = new HashMap<Integer, String>();
        históricoAlta = "Estes são os pacientes que tiveram alta:\n";
        históricoMorte = "Estes são os pacientes que morream:\n";
        idAgenda = 1;
        id = 1; // O id interno do hospital começa a 1;
    }

    //-------MENU MÉDICO-------//
    //Listar Paciente que querem ter alta!
    public void printPacientesAlta() {
        try {
            if (pacientesEsperaAlta.size() == 0) {
                System.out.println("Não existe pacientes a aguardar alta Médica!");
            }
            Pessoa paciente = new Pessoa();
            for (int i = 0; i < pacientesEsperaAlta.size(); i++) {
                paciente = pacientesEsperaAlta.get(i);
                System.out.println("Paciente: " + paciente.getNome() + " está à espera de ter alta médica!");
            }

        } catch (Exception erro) {
            System.out.println("Algo de inesperado aconteceu!");
        }
    }

    //Método par dar alta a todos os pacientes que podem
    public void darAltaMédica() {
        try {

            Pessoa paciente = pacientesEsperaAlta.get(0); //Criar um paciente provisório
            Médico med = new Médico(); //Criar um médico porvisório
            for (int i = 0; i < médicos.size(); i++) { //Descobrir o médico responsável
                if (médicos.get(i).getId() == paciente.getIdMédico()) {
                    med = médicos.get(i);
                }
            }
            addhistóricoAlta(paciente, med);//Adiciona na lista de pacientes que recebream alta.
            med.removerPacientes();//Remove o contrador de pacientes
            med.addPacientesEsperaAlta(paciente);//remove o paciente em si
            System.out.println("Paciente " + paciente.getNome() + ", recebeu alto pelo médico " + med.getNome());
            pacientesEsperaAlta.remove(0);//remove o paciente da lista

        } catch (Exception erro) {
            System.out.println("Não existe pacientes válidos à espera de alta!");
        }
    }

    //USANDO NO DIAGNOSTICAR PACIENTE
    //Método para escolher médicos livres para receberem pacientes
    public Médico escolherMed() {
        Médico med = new Médico();
        for (int i = 0; i < médicos.size(); i++) {
            if (médicos.get(i).getNumPacientes() < 3) {
                med = médicos.get(i);
                break;
            }
        }
        return med;
    }
    //USANDO NO DIAGNOSTICAR PACIENTE
    //Método para escolcher enfermeiros livres para receberem pacientes

    public Enfermeiro escolherEnf() {
        Enfermeiro enf = new Enfermeiro();
        for (int i = 0; i < enfermeirosAuxiliares.size(); i++) {
            if (enfermeirosAuxiliares.get(i).getNumPacientes() < 3) {
                enf = enfermeirosAuxiliares.get(i);
                break;
            }
        }
        return enf;
    }

    //Método para diagnosticar o Paciente
    public void diagnosticarPaciente(Médico médico, Enfermeiro enfermeiro) {
        try {
            //Escolher o primeiro paciente do ArrayList
            Pessoa paciente = pacientes.get(0);
            paciente.setIdMédico(médico.getId());
            paciente.setIdEnfermeiro(enfermeiro.getId());

            //Adicionar o paciente a cargo do médico e do enfermeiro
            médico.adicionarPacientes();
            enfermeiro.addPacientesEmTratamento(paciente);
            enfermeiro.adicionarPacientes();

            //Testar o paciente
            paciente.covid19();
            paciente.hiv();
            paciente.malária();
            paciente.gripe();

            //Dependendo dos resultados o tratamento será agendado
            if (paciente.isTenhoCovid19()) {
                agendaTratamento(paciente, médico, enfermeiro, "Covid-19");
                setIdAgenda(idAgenda + 1);
            }
            if (paciente.isTenhoHiv()) {
                agendaTratamento(paciente, médico, enfermeiro, "Hiv");
                setIdAgenda(idAgenda + 1);
            }
            if (paciente.isTenhoMalária()) {
                agendaTratamento(paciente, médico, enfermeiro, "Malária");
                setIdAgenda(idAgenda + 1);
            }
            if (paciente.isTenhoGripe()) {
                agendaTratamento(paciente, médico, enfermeiro, "Gripe");
                setIdAgenda(idAgenda + 1);
            }
            //Resultado dos diagnosticos:
            if (paciente.isEstouCurado() == true) {
                System.out.println("Paciente: " + paciente.getNome() + "Diagnosticos Completos!\nNão tinha nada, espere o médico dar alta.");
                enfermeiro.removerPacientesEmTratamento(paciente);
                enfermeiro.removerPacientes();
                médico.addPacientesEsperaAlta(paciente);//Lista do Médico
                pacientesEsperaAlta.add(paciente);//Adiciona à lista para ter alta
                pacientes.remove(paciente);//Remove da lista de espera.

            } else {
                System.out.println("Paciente: " + paciente.getNome() + " Diagnosticos Completos!\nEstá Doente, foi lhe agendado tratamento.");
                pacientesEsperaCurativo.add(paciente);
                pacientes.remove(paciente);

            }
        } catch (Exception semPacientes) {
            System.out.println("Não existe pacientes á espera!");
        }

    }

    //Pedir o Id do médico e o numero de auxiliares
    public String pedirEnfermeiros(int id, int num) {
        try {
            this.id = id;
            for (int i = 0; i < médicos.size(); i++) {
                if (id == médicos.get(i).getId()) {
                    //Ira criar uma lista onde index 0 é o id do médico e index 1 é o numero de auxiliares
                    //Para mais pedidos é só acrescentar+1 no dois lados.
                    pediuEnfermeiros.add(id);
                    pediuEnfermeiros.add(num);
                    return "Pedido Envidado!";
                }
            }
            return "ID INVÁLIDO";
        } catch (Exception e) {
            return "Pedido não foi executado com sucesso!";
        }
    }

    //-------MENU ENFERMEIRO-------//
    //Listar pacientes há espera de curativo
    public void printPacientesCurativo() {
        try {
            if (pacientesEsperaCurativo.size() == 0) {
                System.out.println("Não existe pacientes a aguardar Cura!");
            }
            Pessoa paciente = new Pessoa();
            //Imprime todos os pacientes
            for (int i = 0; i < pacientesEsperaCurativo.size(); i++) {
                paciente = pacientesEsperaCurativo.get(i);
                System.out.println("Paciente: " + paciente.getNome() + ", id:" + paciente.getId() + ", está à espera de tratamento");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu algum erro..");
        }
    }

    //Listar os enfermeiros de Médico
    public String enfermeirosDoMédico(int id) {
        try {
            this.id = id;
            String info = "O Médico tem este enfermeiros ao seu dispor: \n";
            for (int i = 0; i < médicos.size(); i++) {
                if (id == médicos.get(i).getId()) {
                    ArrayList<Enfermeiro> enfermeiros = médicos.get(i).getEnfermeirosRequesitados();
                    for (int e = 0; e < enfermeiros.size(); e++) {
                        info += "Nome: " + enfermeiros.get(e).getNome() + " || Anos de Carreira: " + enfermeiros.get(e).getAnosDeCarreira() + " || CC: " + enfermeiros.get(e).getCc() + " || Ano de Nascimento: " + enfermeiros.get(e).getAnoNascimento() + " || ID: " + enfermeiros.get(e).getId() + " \n";
                    }
                }
            }
            return info;
        } catch (Exception e) {
            return "Erro inesperado";
        }
    }

    //Aplica o curativo
    public void aplicarCurativo() {
        //Criar novos funcionário base paraa serem depois substituídos pelos funcionários
        Pessoa paciente = new Pessoa();
        Médico med = new Médico();
        Enfermeiro enf = new Enfermeiro();

        try {
            paciente = pacientesEsperaCurativo.get(0); //Retire a lista de espera de curarivo
            int idMed = paciente.getIdMédico(); //Id do médico do paciente no diagnostico
            int idEnf = paciente.getIdEnfermeiro(); //Id do enfermeiro do paciente no diagnostico
            //Quem são o médico e o enfermeiro
            //Substitui pelo médico e enfermeiro provisório
            for (int i = 0; i < enfermeirosAuxiliares.size(); i++) {
                if (enfermeirosAuxiliares.get(i).getId() == idEnf) {
                    enf = enfermeirosAuxiliares.get(i);
                }
            }
            for (int e = 0; e < médicos.size(); e++) {
                if (médicos.get(e).getId() == idMed) {
                    med = médicos.get(e);
                }
            }
            //aplicar o curativo
            if (paciente.isTenhoCovid19()) {//Se tem
                paciente.vacinaCovid19();//Aplica vacina
            }
            if (paciente.isTenhoHiv()) {//se tem
                paciente.pastilhasHiv();//Aplica Pastilhas
            }
            if (paciente.isTenhoMalária()) {//Se está
                paciente.remédioMalária();//Aplica remédio
            }
            if (paciente.isTenhoGripe()) {//Se tem 
                paciente.cháGripe();//Dá Chá
            }
            //RESULTADOS
            if (paciente.isEstouMorto()) {//Se morreu!
                System.out.println("Paciente:" + paciente.getNome() + ", infelizmente morreu :'(");
                //Remove de todas as listas onde está
                enf.removerPacientes();//remove paciente
                enf.removerPacientesEmTratamento(paciente);//remove paciente
                med.removerPacientes();//remove paciente
                addhistóricoMorte(paciente);//Histórico de morte
                pacientesEsperaCurativo.remove(paciente); //Remove o paciente do hospital

            } else if (paciente.isEstouCurado()) {//Sucesso está curado
                System.out.println("Paciente:" + paciente.getNome() + ", está curado Parabéns. Espere o médico lhe dar alta");

                enf.removerPacientes();//remove o paciente
                enf.removerPacientesEmTratamento(paciente); //remove o pocainete
                med.addPacientesEsperaAlta(paciente); //adiciona o medico
                pacientesEsperaCurativo.remove(paciente); //remove o paciente
                pacientesEsperaAlta.add(paciente); //adiciona o paciente
            } else { //Está doente
                //Volta á lista de espera pacientes
                pacientes.add(paciente);
                pacientesEsperaCurativo.remove(paciente); //remove o paciente

                System.out.println("Paciente:" + paciente.getNome() + ", infelizmente não está curado. Voltou para a lista de espera");
            }
            //Remover as entradas na agenda!
        } catch (Exception e) {
            agenda.clear();
            System.out.println("Não tem nenhum paciente agendado!");
        }

    }

    //Todos os médicos iniciais começam com enfermeiros Especialistas
    public void medTemEsp() {
        try {
            for (int i = 0; i < médicos.size(); i++) {
                int id = médicos.get(i).getId();
                médicos.get(i).addEnfermeiros(enfermeirosEspecialistas.get(0));
                enfermeirosEspecialistas.add(enfermeirosEspecialistas.get(0));//Adiciona uma cópia do enfermeiro no final do Arralist.
                enfermeirosEspecialistas.remove(0);
            }
        } catch (Exception e) {
            System.out.println("Médico não conseguiu arranjar enfermeiro especialista");
        }
    }

    //Atribuí um Especialista a um médico com id válido
    public void atribuirEspecialista(int id) {
        this.id = id;
        char existeID = 0;
        try {
            for (int i = 0; i < médicos.size(); i++) {
                if (médicos.get(i).getId() == id) {
                    médicos.get(i).addEnfermeiros(enfermeirosEspecialistas.get(0));
                    enfermeirosEspecialistas.add(enfermeirosEspecialistas.get(0));//Adiciona uma cópia do enfermeiro no final do Arralist.
                    enfermeirosEspecialistas.remove(0);//Elimina o enfermeiro especialista do inicio do ArrayList.
                    //removo e volto a colocar para dar oportundade de todos os enfermeiros serem escolhidos ao longo de vários implementações.
                    System.out.println("Infermeiro Especialista atribuido ao Médico.");
                    existeID = 1;
                }
            }
            if (existeID == 0) {
                System.out.println("Não existe nenhum médico com esse ID.");
            }
        } catch (Exception notID) {
            System.out.println("Não foi possível completar a ação!");
        }
    }

    //-------MENU ADMINISTRADOR-------//
    //Cria um novo Paciente
    public void criarPaciente(String nome, int cc, int anoNascimento) {
        pacientes.add(new Pessoa(nome, cc, anoNascimento, id));
        setId(id + 1);
    }

    //Cria um novo Médico
    public void criarMédico(String nome, int cc, int anoNascimento) {
        médicos.add(new Médico(nome, cc, anoNascimento, id));
        setId(id + 1);
    }

    //Criar todos os tipos de Enfermeiros
    public void criarEnfermeiroChefe(String nome, int cc, int anoNascimento) {
        enfermeirosChefes.add(new Enfermeiro(nome, cc, anoNascimento, id, ANOS_ENFER_CHEFE));
        setId(id + 1);
    }

    public void criarEnfermeiroEspecialista(String nome, int cc, int anoNascimento) {
        enfermeirosEspecialistas.add(new Enfermeiro(nome, cc, anoNascimento, id, 5));
        setId(id + 1);
    }

    public void criarEnfermeiroAuxiliar(String nome, int cc, int anoNascimento) {
        enfermeirosAuxiliares.add(new Enfermeiro(nome, cc, anoNascimento, id, 0));
        setId(id + 1);
    }

    //Aumentar os anos de Carreira dos Enfermeiros (+1)!
    public void aumentarAnosDeCarreira() {
        for (int i = 0; i < enfermeirosChefes.size(); i++) {
            enfermeirosChefes.get(i).setAnosDeCarreira(enfermeirosChefes.get(i).getAnosDeCarreira() + 1);
        }
        for (int i = 0; i < enfermeirosEspecialistas.size(); i++) {
            enfermeirosEspecialistas.get(i).setAnosDeCarreira(enfermeirosEspecialistas.get(i).getAnosDeCarreira() + 1);
        }
        for (int i = 0; i < enfermeirosAuxiliares.size(); i++) {
            enfermeirosAuxiliares.get(i).setAnosDeCarreira(enfermeirosAuxiliares.get(i).getAnosDeCarreira() + 1);
        }
    }

    //Promover os emfermeiros chefes com ANOS_ENFER_CHEFE ou mais anos de experiência
    public boolean promoverEnfermeiros() {
        char promovido = 0;
        for (int i = 0; i < enfermeirosEspecialistas.size(); i++) {
            if (enfermeirosEspecialistas.get(i).getAnosDeCarreira() >= ANOS_ENFER_CHEFE) {
                enfermeirosChefes.add(enfermeirosEspecialistas.get(i));
                enfermeirosEspecialistas.remove(i);
                promovido = 1;
            }
        }
        return promovido != 0;
    }

    //Listar todos os enfermeiros do Hospital
    public String printEnfermeiros() {
        String info;
        info = "Enfermeiros do Hospital: \n";
        info += "-------Enfermeiros Chefes------- \n";
        for (int i = 0; i < enfermeirosChefes.size(); i++) {
            info += "Nome: " + enfermeirosChefes.get(i).getNome() + " || Posição: Enfermeiro Chefe " + " || Anos de Carreira: " + enfermeirosChefes.get(i).getAnosDeCarreira() + " || CC: " + enfermeirosChefes.get(i).getCc() + " || Ano de Nascimento: " + enfermeirosChefes.get(i).getAnoNascimento() + " || ID: " + enfermeirosChefes.get(i).getId() + " \n";
        }
        info += "-------Enfermeiros Especialistas------- \n";
        for (int i = 0; i < enfermeirosEspecialistas.size(); i++) {
            info += "Nome: " + enfermeirosEspecialistas.get(i).getNome() + " || Posição: Enfermeiro Especialista" + " || Anos de Carreira: " + enfermeirosEspecialistas.get(i).getAnosDeCarreira() + " || CC: " + enfermeirosEspecialistas.get(i).getCc() + " || Ano de Nascimento: " + enfermeirosEspecialistas.get(i).getAnoNascimento() + " || ID: " + enfermeirosEspecialistas.get(i).getId() + " \n";
        }
        info += "-------Enfermeiros Auxiliares------- \n";
        for (int i = 0; i < enfermeirosAuxiliares.size(); i++) {
            info += "Nome: " + enfermeirosAuxiliares.get(i).getNome() + " || Posição: Enfermeiro Auxiliar" + " || Anos de Carreira: " + enfermeirosAuxiliares.get(i).getAnosDeCarreira() + " || CC: " + enfermeirosAuxiliares.get(i).getCc() + " || Ano de Nascimento: " + enfermeirosAuxiliares.get(i).getAnoNascimento() + " || ID: " + enfermeirosAuxiliares.get(i).getId() + " \n";
        }
        return info;
    }

    //Listar os Pacientes em lista de espera!
    public String printPacientes() {
        String info;
        info = "Pacientes à espera: \n";
        for (int i = 0; i < pacientes.size(); i++) {
            info += "Nome: " + pacientes.get(i).getNome() + " || CC: " + pacientes.get(i).getCc() + " || Ano de Nascimento: " + pacientes.get(i).getAnoNascimento() + " || ID: " + pacientes.get(i).getId() + " \n";
        }
        return info;
    }

    //Listar os Médicos no Hospital
    public String printMédicos() {
        String info;
        info = "Médicos no Hospital: \n";
        for (int i = 0; i < médicos.size(); i++) {
            info += "Nome: " + médicos.get(i).getNome() + " || CC: " + médicos.get(i).getCc() + " || Ano de Nascimento: " + médicos.get(i).getAnoNascimento() + " || ID: " + médicos.get(i).getId() + " \n";
        }
        return info;
    }

    //Listar pedidos para enfermeiros-auxiliares
    public String printPedidosEnfermeiros() {
        String info = "";
        try {
            if (pediuEnfermeiros.isEmpty()) {
                info += "Não existe pedidos!";
            }
            for (int i = 0; i < pediuEnfermeiros.size(); i++) {
                info += "Médico com o ID: " + pediuEnfermeiros.get(i) + " Pediu: " + pediuEnfermeiros.get(i + 1) + " enfermeiros auxiliares. \n";
                i++;
            }

        } catch (Exception e) {
            info += "Ocorreu um erro..";
        }
        return info;
    }

    //Destruir os pedidos de enfermeiros auxiliares
    public void arderPedidos() {
        try {
            pediuEnfermeiros.clear();
            System.out.println("Pedidos foram destruídos! >:)");
        } catch (Exception e) {
            System.out.println("ERRO...");
        }
    }

    //Atende ao pedido para enfermeiros-auxiliares
    public void entregarEnfermeiros() {
        //ArrayList pediuEnfermeiros i=0 ID; i=1 NUM;
        int id = pediuEnfermeiros.get(0);//id médico
        int num = pediuEnfermeiros.get(1);//num de enfs que pediu
        try {
            if (enfermeirosEspecialistas.size() >= 1) {//Tem Enfermeiros especialistas disponíveis
                try {
                    if (enfermeirosAuxiliares.size() >= num) { //Tem Auxiliares Disponíveis
                        try {
                            if (enfermeirosChefes.size() >= 1) { //Tem Algum Enfermeiro Chefe para realizar o requerimento
                                for (int i = 0; i < num; i++) {
                                    médicos.get(id).addEnfermeiros(enfermeirosAuxiliares.get(0));//Adiciona o enfermeiro 
                                    enfermeirosAuxiliares.add(enfermeirosAuxiliares.get(0));//Adiciona uma cópia do enfermeiro no final do Arralist
                                    enfermeirosAuxiliares.remove(0);//Elimina o enfermeiro auxiliar do inicio do ArrayList
                                    //removo e volto a colocar para dar oportundade de todos os enfermeiros serem escolhidos ao longo de vários requerimentos
                                }
                                //Remover o pedido da lista
                                pediuEnfermeiros.remove(0);//Remove o ID
                                pediuEnfermeiros.remove(0);//Remove o NUM
                                System.out.println("Médico:" + médicos.get(id).getNome() + ", pedido foi aceite.");
                                System.out.println("Enfermeiros entregues a seu cargo.");
                            }
                        } catch (Exception enfChefe) {
                            System.out.println("Médico:" + médicos.get(id).getNome() + ", pedido foi recusado.");
                            System.out.println("Sem Enfermeiros Chefes disponíveis");
                        }
                    }

                } catch (Exception enfAux) {
                    System.out.println("Médico:" + médicos.get(id).getNome() + " pedido foi recusado.");
                    System.out.println("Sem Enfermeiros Axíliares disponíveis");
                }
            }
        } catch (Exception enfEsps) {
            System.out.println("Médico:" + médicos.get(id).getNome() + " pedido foi recusado.");
            System.out.println("Sem Enfermeiros Especialistas disponíveis!");
        }
    }

    ///-------PANDEMIA-------///
    public void pandemia() {
        try {
            if (pacientes.isEmpty() == false) {
                for (int i = 0; i < pacientes.size(); i++) {
                    pacientes.get(i).setTenhoCovid19(true);
                }
            }
            if (pacientesEsperaCurativo.isEmpty() == false) {
                for (int i = 0; i < pacientesEsperaCurativo.size(); i++) {
                    pacientesEsperaCurativo.get(i).setTenhoCovid19(true);
                    pacientes.add(pacientesEsperaCurativo.get(i));//Está á espera de ser tratado
                }
            }
            if (pacientesEsperaAlta.isEmpty() == false) {
                for (int i = 0; i < pacientesEsperaAlta.size(); i++) {
                    pacientesEsperaAlta.get(i).setTenhoCovid19(true);
                    pacientes.add(pacientesEsperaAlta.get(i));//Está á espera de ser tratado
                }
            }
            if (médicos.isEmpty() == false) {
                for (int i = 0; i < médicos.size(); i++) {
                    médicos.get(i).setTenhoCovid19(true);
                    pacientes.add(médicos.get(i));//Está á espera de ser tratado
                }
                médicos.clear();//Não pode ser médico enquanto está doente
            }
            if (enfermeirosChefes.isEmpty() == false) {
                for (int i = 0; i < enfermeirosChefes.size(); i++) {
                    enfermeirosChefes.get(i).setTenhoCovid19(true);
                    pacientes.add(enfermeirosChefes.get(i));//Está á espera de ser tratado
                }
                enfermeirosChefes.clear();//remove os enfermeiros Chefes
            }
            if (enfermeirosEspecialistas.isEmpty() == false) {
                for (int i = 0; i < enfermeirosEspecialistas.size(); i++) {
                    enfermeirosEspecialistas.get(i).setTenhoCovid19(true);
                    pacientes.add(enfermeirosEspecialistas.get(i));//Está á espera de ser tratado
                }
                enfermeirosEspecialistas.clear();//remove os enfermeiros especialistas
            }
            if (enfermeirosAuxiliares.isEmpty() == false) {
                for (int i = 0; i < enfermeirosAuxiliares.size(); i++) {
                    enfermeirosAuxiliares.get(i).setTenhoCovid19(true);
                    pacientes.add(enfermeirosAuxiliares.get(i));//Está á espera de ser tratado
                }
                enfermeirosAuxiliares.clear();//Remove os enfermeiros
            }

        } catch (Exception e) {
            System.out.println("Não foi possível infetar todos, alguém lavou bem as mãos!");
        }

    }

    //-------Histórico do Hospital-------//
    //Agenda dos tratamentos ainda por fazer!
    public void agendaTratamento(Pessoa paciente, Médico médico, Enfermeiro enfermeiro, String doença) {
        String info = "Paciente: " + paciente.getNome() + ", do médico " + médico.getNome() + ", diagnosticado pelo auxiliar " + enfermeiro.getNome() + ", tem: " + doença + " \n";
        agenda.put(idAgenda, info);
    }

    //Dá print dos tratamentos agendados
    public void printAgendaTratamento() {
        System.out.println(agenda.values());
    }

    //Acrescentar doentes ao histórico
    public void addhistóricoAlta(Pessoa paciente, Médico med) {
        históricoAlta += "O paciente: " + paciente.getNome() + " portador do cc " + paciente.getCc() + " levou alta médico, por parte do médico " + med.getNome() + ", ID: " + med.getId() + "\n";
    }

    //Listar o histórico de cura
    public String printHistóricoAlta() {
        return históricoAlta;
    }

    //Acresentar Mortes
    public void addhistóricoMorte(Pessoa paciente) {
        históricoMorte += "O paciente: " + paciente.getNome() + " portador do cc: " + paciente.getCc() + " MORREU!\n";
    }

    //Listar o Histórico
    public String printHistóricoMorte() {
        return históricoMorte;
    }

    //-------GETS AND SETS DA CLASSE HOSPITAL-------//
    //Get e Set do id do Hospital!
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //GETS && SETS De tudo o que existe na classe.
    public ArrayList<Enfermeiro> getEnfermeirosAuxiliares() {
        return enfermeirosAuxiliares;
    }

    public void setEnfermeirosAuxiliares(ArrayList<Enfermeiro> enfermeirosAuxiliares) {
        this.enfermeirosAuxiliares = enfermeirosAuxiliares;
    }

    public ArrayList<Enfermeiro> getEnfermeirosChefes() {
        return enfermeirosChefes;
    }

    public void setEnfermeirosChefes(ArrayList<Enfermeiro> enfermeirosChefes) {
        this.enfermeirosChefes = enfermeirosChefes;
    }

    public ArrayList<Enfermeiro> getEnfermeirosEspecialistas() {
        return enfermeirosEspecialistas;
    }

    public void setEnfermeirosEspecialistas(ArrayList<Enfermeiro> enfermeirosEspecialistas) {
        this.enfermeirosEspecialistas = enfermeirosEspecialistas;
    }

    public ArrayList<Pessoa> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Pessoa> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Médico> getMédicos() {
        return médicos;
    }

    public void setMédicos(ArrayList<Médico> médicos) {
        this.médicos = médicos;
    }

    public ArrayList<Integer> getPediuEnfermeiros() {
        return pediuEnfermeiros;
    }

    public void setPediuEnfermeiros(ArrayList<Integer> pediuEnfermeiros) {
        this.pediuEnfermeiros = pediuEnfermeiros;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public HashMap<Integer, String> getAgenda() {
        return agenda;
    }

    public void setAgenda(HashMap<Integer, String> agenda) {
        this.agenda = agenda;
    }

    public ArrayList<Pessoa> getPacientesEsperaCurativo() {
        return pacientesEsperaCurativo;
    }

    public void setPacientesEsperaCurativo(ArrayList<Pessoa> pacientesEsperaCurativo) {
        this.pacientesEsperaCurativo = pacientesEsperaCurativo;
    }

    public ArrayList<Pessoa> getPacientesEsperaAlta() {
        return pacientesEsperaAlta;
    }

    public void setPacientesEsperaAlta(ArrayList<Pessoa> pacientesEsperaAlta) {
        this.pacientesEsperaAlta = pacientesEsperaAlta;
    }

    public String getHistóricoAlta() {
        return históricoAlta;
    }

    public void setHistóricoAlta(String históricoAlta) {
        this.históricoAlta = históricoAlta;
    }

    public String getHistóricoMorte() {
        return históricoMorte;
    }

    public void setHistóricoMorte(String históricoMorte) {
        this.históricoMorte = históricoMorte;
    }

}
