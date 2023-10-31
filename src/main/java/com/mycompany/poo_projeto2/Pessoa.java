/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo_projeto2;

import java.util.Random; //Gerar numeros aleatórios

/**
 *
 * @author filip
 */
public class Pessoa implements Doenças { //Será esta a "classe" paciente

    //Inicializado no COnstrutor da Pessoa
    private String nome;
    private int cc; //Cartão de cidadão
    private int anoNascimento; //Ano de Nascimento da Pessoa
    private int id;//Id "Placeholder" para o ID da classe Hospital

    //Doenças
    private boolean tenhoCovid19;
    private boolean tenhoHiv;
    private boolean tenhoMalária;
    private boolean tenhoGripe;
    private boolean estouCurado;
    private boolean estouMorto;
    //Guardar os ids para depois serem identificados
    private int idMédico;
    private int idEnfermeiro;

    Random random = new Random(); //Para numeros aleatorios

    //Construtor
    public Pessoa() {
        nome = "";
        cc = 0;
        anoNascimento = 0;
        //Doenças.  Começa saudável,não sabe se tem doenças!
        tenhoCovid19 = false;
        tenhoHiv = false;
        tenhoMalária = false;
        tenhoGripe = false;
        estouCurado = true;
        estouMorto = false;

        //Agenda de tratamentos
        idMédico = 0;
        idEnfermeiro = 0;
    }

    //Construtor Principal
    public Pessoa(String nome, int cc, int anoNascimento, int id) {
        this.nome = nome;
        this.cc = cc;
        this.anoNascimento = anoNascimento;
        this.id = id;
        //Doenças.  Começa saudável,não sabe se tem doenças!
        tenhoCovid19 = false;
        tenhoHiv = false;
        tenhoMalária = false;
        tenhoGripe = false;
        estouCurado = true;
        estouMorto = false;

        //Agenda de tratamentos
        idMédico = 0;
        idEnfermeiro = 0;
    }

    //-------DOENÇAS-------//
    //TEMPERATURA
    @Override
    public void covid19() { //75% chance 
        if (random.nextInt(4) + 37 != TEMPERATURA) {
            tenhoCovid19 = true;
            estouCurado = false;
        }
    }

    // SANGUE_CONTANINADO 
    @Override
    public void hiv() { //1/6 chance
        if ((1 == random.nextInt(6)) != SANGUE_CONTAMINADO) {
            tenhoHiv = true;
            estouCurado = false;
        }
    }

    //TONTURAS
    @Override
    public void malária() { // 1/10 chance
        if ((1 == random.nextInt(10)) != TONTURAS) {
            tenhoMalária = true;
            estouCurado = false;
        }
    }

    //TOSSE
    @Override
    public void gripe() { //1/4 chance
        if ((1 == random.nextInt(4)) != TOSSE) {
            tenhoGripe = true;
            estouCurado = false;
        }
    }

    @Override
    public void vacinaCovid19() {
        if (random.nextInt(2) + 37 != TEMPERATURA) { //50%
            if (random.nextInt(5) == 0) { // 1/6 vai morrer
                tenhoCovid19 = false;
                estouMorto = true;
            } else { //Não ficou curado.
                tenhoCovid19 = false;
                estouCurado = true;
            }
        } else {
            tenhoCovid19 = false;
            estouCurado = true;
        }

    }

    @Override
    public void pastilhasHiv() {
        if (random.nextBoolean() != TONTURAS) { //50%
            if (random.nextInt(5) == 0) { // 1/6 vai morrer
                tenhoHiv = false;
                estouMorto = true;
            } else { //Não ficou curado.
                tenhoHiv = true;
                estouCurado = false;
            }
        } else {//Ficou Curado
            tenhoHiv = false;
            estouCurado = true;
        }
    }

    @Override
    public void remédioMalária() {
        if (random.nextBoolean() != TONTURAS) {//50%
            if (random.nextInt(7) == 0) { // 1/8 vai morrer
                tenhoMalária = false;
                estouMorto = true;
            } else { //Não ficou curado.
                tenhoMalária = true;
                estouCurado = false;
            }
        } else {//Ficou curado
            tenhoMalária = false;
            estouCurado = true;
        }
    }

    @Override
    public void cháGripe() {
        if (random.nextBoolean() != TOSSE) {//50%
            if (random.nextInt(9) == 0) { // 1/10 vai morrer
                tenhoGripe = false;
                estouMorto = true;
            } else { //Não ficou curado.
                tenhoGripe = true;
                estouCurado = false;
            }
        } else {//Ficou curado
            tenhoGripe = true;
            estouCurado = false;
        }
    }

    //-----GETS E SETS DE TODAS AS VARIÁVEIS E MÉTODOS-----//
    public String getNome() {
        return nome;
    }

    public int getCc() {
        return cc;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public int getId() {
        return id;
    }

    public boolean isTenhoCovid19() {
        return tenhoCovid19;
    }

    public void setTenhoCovid19(boolean tenhoCovid19) {
        this.tenhoCovid19 = tenhoCovid19;
    }

    public boolean isTenhoHiv() {
        return tenhoHiv;
    }

    public void setTenhoHiv(boolean tenhoHiv) {
        this.tenhoHiv = tenhoHiv;
    }

    public boolean isTenhoMalária() {
        return tenhoMalária;
    }

    public void setTenhoMalária(boolean tenhoMalária) {
        this.tenhoMalária = tenhoMalária;
    }

    public boolean isTenhoGripe() {
        return tenhoGripe;
    }

    public void setTenhoGripe(boolean tenhoGripe) {
        this.tenhoGripe = tenhoGripe;
    }

    public boolean isEstouCurado() {
        return estouCurado;
    }

    public void setEstouCurado(boolean estouCurado) {
        this.estouCurado = estouCurado;
    }

    public boolean isEstouMorto() {
        return estouMorto;
    }

    public void setEstouMorto(boolean estouMorto) {
        this.estouMorto = estouMorto;
    }

    public int getIdMédico() {
        return idMédico;
    }

    public void setIdMédico(int idMédico) {
        this.idMédico = idMédico;
    }

    public int getIdEnfermeiro() {
        return idEnfermeiro;
    }

    public void setIdEnfermeiro(int idEnfermeiro) {
        this.idEnfermeiro = idEnfermeiro;
    }

}
