/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo_projeto2;

/**
 *
 * @author filip
 */
public interface Doenças {

    //Termeinar se estão doentes ou não!
    public final int TEMPERATURA = 37;
    public final boolean SANGUE_CONTAMINADO = false;
    public final boolean TONTURAS = false;
    public final boolean TOSSE = false;

    //Doenças que o hospital consegue detetar!
    public void covid19(); //TEMPERATURA

    public void hiv(); // SANGUE_CONTANINADO 

    public void malária(); //TONTURAS 

    public void gripe();//TOSSE

    //Tentar curar as doenças
    public void vacinaCovid19();

    public void pastilhasHiv();

    public void remédioMalária();

    public void cháGripe();

}
