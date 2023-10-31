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
public class Enfermeiro extends Pessoa {

    private int anosDeCarreira; //Anos de carreira de cada enfermeiro
    private int numPacientes; //Numero máximo de pacientes
    private ArrayList<Pessoa> pacientesEmTratamento;

    //Construtor não sei se esta certo!
    public Enfermeiro() {
        super();
        numPacientes = 0;
        pacientesEmTratamento = new ArrayList<Pessoa>();
    }

    //Construtor para o Enfermeiro Base
    public Enfermeiro(String nome, int cc, int anoNascimento, int id, int anosDeCarreira) {
        super(nome, cc, anoNascimento, id);
        this.anosDeCarreira = anosDeCarreira;
        numPacientes = 0;
        pacientesEmTratamento = new ArrayList<Pessoa>();
    }

    //-----Adicionar e remover Pacientes do ARRAY-----//
    public void addPacientesEmTratamento(Pessoa paciente) {
        pacientesEmTratamento.add(paciente);
    }

    public void removerPacientesEmTratamento(Pessoa paciente) {
        pacientesEmTratamento.remove(paciente);
    }

    //----Adicionar e remover pacientes VARÍAVEL---/
    public void adicionarPacientes() {
        numPacientes++;
    }

    public void removerPacientes() {
        numPacientes--;
    }

    //GEts e sets necessários!
    public int getAnosDeCarreira() {
        return anosDeCarreira;
    }

    public void setAnosDeCarreira(int anosDeCarreira) {
        this.anosDeCarreira = anosDeCarreira;
    }

    public int getNumPacientes() {
        return numPacientes;
    }

    public void setNumPacientes(int numPacientes) {
        this.numPacientes = numPacientes;
    }

    public ArrayList<Pessoa> getPacientesEmTratamento() {
        return pacientesEmTratamento;
    }

    public void setPacientesEmTratamento(ArrayList<Pessoa> pacientesEmTratamento) {
        this.pacientesEmTratamento = pacientesEmTratamento;
    }

}
