package com.gamerecommendation.Weatherconditions;

import com.teknikindustries.yahooweather.WeatherDisplay;
import com.teknikindustries.yahooweather.WeatherDoc;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Weather extends javax.swing.JFrame {

    private String location;
    private String unitDegree;
    
    public Weather(String location, String unitDegree) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        getWeather(location, unitDegree);
    }
    
    private void getWeather(String location, String unitDegree){
        
        this.location = getWoeid(location);
        this.unitDegree = getUnitDegree(unitDegree);
        
        WeatherDoc documentOfWeather = new WeatherDoc(this.location, this.unitDegree);
        WeatherDisplay weatherDisplay = new WeatherDisplay();
        
        loadComponents(documentOfWeather, weatherDisplay);
        
        String[] datos = setParametters(weatherDisplay.getTemperature(), 
                                        weatherDisplay.getCondition(), 
                                        weatherDisplay.getWindChill(), 
                                        weatherDisplay.getWindDirection(), 
                                        weatherDisplay.getWindSpeed(), 
                                        weatherDisplay.getHumidity(), 
                                        weatherDisplay.getVisibility(), 
                                        weatherDisplay.getPressure());
        
        try {
            setImage(weatherDisplay.getCondition(), clasify(datos));
        } catch (Exception ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadComponents(WeatherDoc documentOfWeather, WeatherDisplay weatherDisplay) {
        Temperature.setText(weatherDisplay.getTemperature());
        Conditions.setText(weatherDisplay.getCondition());
        Degrees.setText("\u00b0");
        SunRise.setText("SunRise: " + weatherDisplay.getSunrise());
        SunSet.setText("SunSet: " + weatherDisplay.getSunset());
        wChill.setText("Chill: " + weatherDisplay.getWindChill());
        wDirection.setText("Direction: " + weatherDisplay.getWindDirection());
        wSpeed.setText("Speed: " + weatherDisplay.getWindSpeed());
        aHumidity.setText("Humidity: " + weatherDisplay.getHumidity());
        aVisibility.setText("Visibility: " + weatherDisplay.getVisibility());
        aPressure.setText("Pressure: " + weatherDisplay.getPressure());
    }
    
    private void setImage(String condition, boolean result){
        ImageIcon conditionI;
                
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        
        switch(condition){
            case "Cloudy":
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
            break;
            case "Clear":
                if(hour > 6 && hour < 18 ){
                    conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
                }else{
                    conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + " N.png");
                }
            break;    
            case "Sunny":
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
            break;
            case "Fair":
                if(hour > 6 && hour < 18 ){
                    conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
                }else{
                    conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + " N.png");
                }
            break;
            case "Partly Cloudy":
                if(hour > 6 && hour < 18 ){
                    conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
                }else{
                    conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + " N.png");
                }
            break;
            case "Mostly Cloudy":
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
            break;
            case "Showers":
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
            break;
            case "Haze":
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
            break;
            case "Dust":
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + condition + ".png");
            break;
            default:
                conditionI = new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\icons\\" + "na.png");
            break;
        }
        ConditionIcon.setIcon(conditionI);
        match.setText("");
        if(result){
            match.setIcon(new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\true.png"));
            estado.setText("Mint conditions, let's play");
        }else{
            match.setIcon(new ImageIcon("src\\com\\gamerecommendation\\weatherconditions\\false.png"));
            estado.setText("Fair conditions, postponed match");
        }
    }
    
    private boolean clasify(String[] data) throws Exception{
        Clasificacion clasifier = new Clasificacion();
        String result = clasifier.clasificar(data);
        if(result.matches("Recommended")){
            System.out.println(result);
            return true;
        }
        return false;
    }
    
    private String[] setParametters(String temperature, String conditions, String chill, String direction, String speed, String humidity, String visibility, String pressure){

        String result[] = new String[9];
        if(conditions.matches("Partly Cloudy")) {
            result[0] = "Partly_Cloudy";
        } else if(conditions.matches("Mostly Cloudy")) {
            result[0] = "Mostly_Cloudy";
        } else {
            result[0] = conditions;
        }
        
        if(Integer.parseInt(temperature) >  30) {
            result[1] = "Hot";
        } else if(Integer.parseInt(temperature) < 18) {
            result[1] = "Cool";
        } else {
            result[1] = "Mild";
        }
        
        if(Integer.parseInt(chill) > 15 && Integer.parseInt(chill) < 35){
            result[2] = "Mint";
        } else {
            result[2] = "Regrettable";
        }
        
        if(Integer.parseInt(direction) > 90 && Integer.parseInt(direction) < 180){
            result[3] = "Regular";
        } else if(Integer.parseInt(direction) < 90){
            result[3] = "Fair";
        } else {
            result[3] = "Mint";
        }
        
        if(Double.parseDouble(speed) > 15 && Double.parseDouble(speed) < 30){
            result[4] = "Regular";
        } else if(Double.parseDouble(speed) < 15){
            result[4] = "Fair";
        } else {
            result[4] = "Mint";
        }
        
        if(Integer.parseInt(humidity) > 35 && Integer.parseInt(humidity) < 90){
            result[5] = "Normal";
        } else if(Integer.parseInt(direction) > 90){
            result[5] = "High";
        } else {
            result[5] = "Low";
        }
        
        if(Double.parseDouble(visibility) > 5 && Double.parseDouble(visibility) < 15){
            result[6] = "Recommended";
        } else {
            result[6] = "Not_Recommended";
        }
        
        if(Double.parseDouble(pressure) > 600 && Double.parseDouble(visibility) < 1150){
            result[7] = "Mint";
        } else {
            result[7] = "Fair";
        }
        
        result[8] = "?";
        
        return result;
    }
    
    private String getWoeid(String location) {
        if(!location.isEmpty()){
            if(location.matches("Santo Domingo")) {
                return "76456";
            } else if(location.matches("Moca")) {
                return "73992";
            } else {
                return "76453";
            }
        } else {
            return "76453";
        }
    }
    
    private String getUnitDegree(String unitDegree){
        if(!unitDegree.isEmpty()) {
            return unitDegree;
        }
        return "c";
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close = new javax.swing.JLabel();
        Componentes = new javax.swing.JPanel();
        ConditionIcon = new javax.swing.JLabel();
        Temperature = new javax.swing.JLabel();
        Conditions = new javax.swing.JLabel();
        Degrees = new javax.swing.JLabel();
        SunRise = new javax.swing.JLabel();
        SunSet = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        wChill = new javax.swing.JLabel();
        wDirection = new javax.swing.JLabel();
        wSpeed = new javax.swing.JLabel();
        aHumidity = new javax.swing.JLabel();
        aVisibility = new javax.swing.JLabel();
        aPressure = new javax.swing.JLabel();
        interC = new javax.swing.JLabel();
        realS = new javax.swing.JLabel();
        match = new javax.swing.JLabel();
        estado = new javax.swing.JLabel();
        hover = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Weatherconditions/close.png"))); // NOI18N
        close.setToolTipText("");
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, -1, 30));

        Componentes.setMaximumSize(new java.awt.Dimension(690, 500));
        Componentes.setOpaque(false);
        Componentes.setPreferredSize(new java.awt.Dimension(730, 500));

        ConditionIcon.setForeground(new java.awt.Color(255, 255, 255));

        Temperature.setFont(new java.awt.Font("Tahoma", 0, 80)); // NOI18N
        Temperature.setForeground(new java.awt.Color(255, 255, 255));

        Conditions.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Conditions.setForeground(new java.awt.Color(255, 255, 255));

        Degrees.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        Degrees.setForeground(new java.awt.Color(255, 255, 255));

        SunRise.setForeground(new java.awt.Color(255, 255, 255));

        SunSet.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Wind");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Atmosphere");

        wChill.setForeground(new java.awt.Color(255, 255, 255));

        wDirection.setForeground(new java.awt.Color(255, 255, 255));

        wSpeed.setForeground(new java.awt.Color(255, 255, 255));

        aHumidity.setForeground(new java.awt.Color(255, 255, 255));
        aHumidity.setPreferredSize(new java.awt.Dimension(80, 14));

        aVisibility.setForeground(new java.awt.Color(255, 255, 255));
        aVisibility.setPreferredSize(new java.awt.Dimension(80, 14));

        aPressure.setForeground(new java.awt.Color(255, 255, 255));
        aPressure.setPreferredSize(new java.awt.Dimension(80, 14));

        interC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Weatherconditions/IC.png"))); // NOI18N

        realS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Weatherconditions/RS.png"))); // NOI18N

        match.setText("jLabel3");

        estado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        estado.setForeground(new java.awt.Color(255, 255, 255));
        estado.setText("jLabel3");

        javax.swing.GroupLayout ComponentesLayout = new javax.swing.GroupLayout(Componentes);
        Componentes.setLayout(ComponentesLayout);
        ComponentesLayout.setHorizontalGroup(
            ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComponentesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ComponentesLayout.createSequentialGroup()
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(wChill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wDirection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(aPressure, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aHumidity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aVisibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(realS)
                        .addGap(2, 2, 2))
                    .addGroup(ComponentesLayout.createSequentialGroup()
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ConditionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Conditions, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(interC)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComponentesLayout.createSequentialGroup()
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ComponentesLayout.createSequentialGroup()
                                .addComponent(Temperature, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ComponentesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Degrees, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComponentesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(estado))))
                            .addGroup(ComponentesLayout.createSequentialGroup()
                                .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SunSet, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SunRise, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(match, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80))))
        );
        ComponentesLayout.setVerticalGroup(
            ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComponentesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ComponentesLayout.createSequentialGroup()
                        .addComponent(ConditionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Conditions, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Temperature, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Degrees, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ComponentesLayout.createSequentialGroup()
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(realS)
                            .addGroup(ComponentesLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(interC)))
                        .addGap(18, 18, 18)
                        .addComponent(estado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(match, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComponentesLayout.createSequentialGroup()
                        .addComponent(SunRise, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SunSet, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(aHumidity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wChill, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(aVisibility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wDirection, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aPressure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        getContentPane().add(Componentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 660, -1));

        hover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Weatherconditions/hover.png"))); // NOI18N
        getContentPane().add(hover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Weatherconditions/background.jpg"))); // NOI18N
        Background.setMaximumSize(new java.awt.Dimension(690, 500));
        Background.setMinimumSize(new java.awt.Dimension(690, 500));
        Background.setPreferredSize(new java.awt.Dimension(690, 500));
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 720, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        dispose();
    }//GEN-LAST:event_closeMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Weather(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel Componentes;
    private javax.swing.JLabel ConditionIcon;
    private javax.swing.JLabel Conditions;
    private javax.swing.JLabel Degrees;
    private javax.swing.JLabel SunRise;
    private javax.swing.JLabel SunSet;
    private javax.swing.JLabel Temperature;
    private javax.swing.JLabel aHumidity;
    private javax.swing.JLabel aPressure;
    private javax.swing.JLabel aVisibility;
    private javax.swing.JLabel close;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel hover;
    private javax.swing.JLabel interC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel match;
    private javax.swing.JLabel realS;
    private javax.swing.JLabel wChill;
    private javax.swing.JLabel wDirection;
    private javax.swing.JLabel wSpeed;
    // End of variables declaration//GEN-END:variables
}