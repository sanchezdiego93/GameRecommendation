/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamerecommendation.Weatherconditions;

import java.io.InputStream;
import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author diegosanchez
 */
public class Clasificacion {
    
    public Clasificacion(){
    }
    
    public String clasificar(String[] testCases) throws Exception{
        String ruta = "model.model";

        InputStream classModelStream;
        classModelStream = getClass().getResourceAsStream(ruta);
        Classifier clasify = (Classifier)SerializationHelper.read(classModelStream);
        FastVector condition = new FastVector();
        condition.addElement("Cloudy");
        condition.addElement("Clear");
        condition.addElement("Sunny");
        condition.addElement("Fair");
        condition.addElement("Partly_Cloudy");
        condition.addElement("Mostly_Cloudy");
        condition.addElement("Showers");
        condition.addElement("Haze");
        condition.addElement("Dust");
        condition.addElement("Other");
        Attribute _condition = new Attribute("contition", condition); 

        FastVector temperature = new FastVector();
        temperature.addElement("Hot");
        temperature.addElement("Mild");
        temperature.addElement("Cool");
        Attribute _temperature = new Attribute("temperature", temperature);

        FastVector chill = new FastVector();
        chill.addElement("Regrettable");
        chill.addElement("Mint");
        Attribute _chill = new Attribute("chill", chill);

        FastVector direction = new FastVector();
        direction.addElement("Mint");
        direction.addElement("Fair");
        direction.addElement("Regular");
        Attribute _direction = new Attribute("direction", direction);

        FastVector speed = new FastVector();
        speed.addElement("Mint");
        speed.addElement("Fair");
        speed.addElement("Regular");
        Attribute _speed = new Attribute("speed", speed);

        FastVector humidity = new FastVector();
        humidity.addElement("High");
        humidity.addElement("Normal");
        humidity.addElement("Low");
        Attribute _humidity = new Attribute("humidity", humidity);

        FastVector visibility = new FastVector();
        visibility.addElement("Recommended");
        visibility.addElement("Not_Recommended");
        Attribute _visibility = new Attribute("visibility", visibility);

        FastVector preassure = new FastVector();
        preassure.addElement("Fair");
        preassure.addElement("Mint");
        Attribute _preassure = new Attribute("preassure", preassure);
        
        
        FastVector Class = new FastVector();
        Class.addElement("Recommended");
        Class.addElement("Not_Recommended");
        Attribute _Class = new Attribute("class", Class);
        
        FastVector atributos = new FastVector(9);
        atributos.addElement(_condition);
        atributos.addElement(_temperature);
        atributos.addElement(_chill);
        atributos.addElement(_direction);
        atributos.addElement(_speed);
        atributos.addElement(_humidity);
        atributos.addElement(_visibility); 
        atributos.addElement(_preassure);
        atributos.addElement(_Class);
        
        ArrayList<Attribute> atributs = new ArrayList<>();
        atributs.add(_condition);
        atributs.add(_temperature);
        atributs.add(_chill);
        atributs.add(_direction);
        atributs.add(_speed);
        atributs.add(_humidity);
        atributs.add(_visibility);
        atributs.add(_preassure);
        atributs.add(_Class);
        
        //Aquí se crea la instacia, que tiene todos los atributos del modelo
        Instances dataTest = new Instances("TestCases", atributos, 1);
        dataTest.setClassIndex(8);
        
        
        Instance setPrueba = new Instance(9);

        int index = -1;
        for(int i = 0; i<8; i++)
        {
            index = atributs.get(i).indexOfValue(testCases[i]);
            //System.out.println(i + " " + atributs.get(i)  + " " + index + " " + testCases[i]);
            setPrueba.setValue(atributs.get(i), index);
        }

        //Agregando el set que se desea evaluar.
        dataTest.add(setPrueba);

        //Realizando la Predicción
        //La instancia es la 0 debido a que es la unica que se encuentra.
        double valorP = clasify.classifyInstance(dataTest.instance(0));
        //get the name of the class value
        String prediccion=dataTest.classAttribute().value((int)valorP);
        
        return prediccion;
    }
}
