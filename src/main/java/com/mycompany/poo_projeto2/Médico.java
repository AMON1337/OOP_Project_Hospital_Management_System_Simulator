/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo_projeto2;

import java.util.ArrayList;

/**
 *
 * @author filip
 */
public class Médico extends Pessoa {

    private ArrayList<Enfermeiro> enfermeirosRequesitados; //Lista dos enfermeiros requesitados pelo doutor
    private int numPacientes; //Numero máximo de pacientes
    //Não tenho a certeza se faz sentido
    private ArrayList<Pessoa> pacientesEsperaAlta;//pacientes que o doutor tem

    public Médico() {
        super();
        enfermeirosRequesitados = new ArrayList<Enfermeiro>();
        pacientesEsperaAlta = new ArrayList<Pessoa>();
        numPacientes = 0;
    }

    //Contrutor Principal
    public Médico(String nome, int cc, int anoNascimento, int id) {
        super(nome, cc, anoNascimento, id);
        enfermeirosRequesitados = new ArrayList<Enfermeiro>();
        pacientesEsperaAlta = new ArrayList<Pessoa>();
        numPacientes = 0;
    }

    //Retorna o numero de Infermeiros que o médico precisa.
    public int numEnfermeiros() {
        return super.random.nextInt(3) + 1; //Requesitar 1,2 ou 3 enfermeiros auxiliares

    }

    //Lista que cada doutor tem de pacientes
    public void addPacientesEsperaAlta(Pessoa paciente) {
        pacientesEsperaAlta.add(paciente);
    }

    public void removerPacientesEsperaAlta(Pessoa paciente) {
        pacientesEsperaAlta.remove(paciente);
    }

    public void adicionarPacientes() {
        numPacientes++;
    }

    public void removerPacientes() {
        numPacientes--;
    }

    //Adiciona enfermeiros requisitados ao Array
    public void addEnfermeiros(Enfermeiro e) {
        enfermeirosRequesitados.add(e);
    }

    public ArrayList<Enfermeiro> getEnfermeirosRequesitados() {
        return enfermeirosRequesitados;
    }

    public void setEnfermeirosRequesitados(ArrayList<Enfermeiro> enfermeirosRequesitados) {
        this.enfermeirosRequesitados = enfermeirosRequesitados;
    }

    public int getNumPacientes() {
        return numPacientes;
    }

    public void setNumPacientes(int numPacientes) {
        this.numPacientes = numPacientes;
    }

    public ArrayList<Pessoa> getPacientesEsperaAlta() {
        return pacientesEsperaAlta;
    }

    public void setPacientesEsperaAlta(ArrayList<Pessoa> pacientesEsperaAlta) {
        this.pacientesEsperaAlta = pacientesEsperaAlta;
    }

}
