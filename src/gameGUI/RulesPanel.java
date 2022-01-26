package gameGUI;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
public class RulesPanel extends ImagePanel{

    
    private static final long serialVersionUID = 1L;
    private JButton backButton; //bouton pour revenir en arriere
    private JTextPane text; // explication du jeu (regles)
    private  final ImageIcon backImg =  new ImageIcon( new ImageIcon("../assets/images/buttons/Back.png").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
    private GameFrame gameGUI; // le jeu, pour pouvoir recuperer le menu et l'afficfer lors du click avec backButton

    /**
     * 
     */
    public RulesPanel(GameFrame gameGUI) throws IOException{


        super("../assets/images/background2.png");// Jpanel avec un background
        this.gameGUI= gameGUI; // le jeu
        this.backButton = new JButton(backImg); //bouton back
        this.text = new JTextPane(); // les regles
        setSize(700,485); 
        generateInfo();  
        setLayout(null); 
        validate();
        this.gameGUI.validate();
    }


    
    /** Cette methode genere le text et le bouton pour revenir en arriere
     */
    public void generateInfo() {
                //text.setBackground(Color.LIGHT_GRAY);
                this.text.setEditable(false);
                this.text.setBounds(50,25, 620, 200);
                this.text.setOpaque(false); 
                this.add(text);
               
                this.backButton.setBounds(300,300,90,50); 
                this.backButton.setOpaque(false);
                this.backButton.setContentAreaFilled(false);
                this.backButton.setBorderPainted(false);                
                this.add(backButton);
                
                //changer la taille de la fenetre, cacher le RulesPanel et afficher le menu 
                this.backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameGUI.playButtonclick();
                        gameGUI.setSize(400, 425); //taille de la fenetre
                        gameGUI.getMenu().setVisible(true);
                        setVisible(false);
                        validate();
                    }
                });

            }

    /** getter pour le bouton Back
     * @return le bouton back pour revenir en arriere
     */
    public JButton backButton(){
        return this.backButton;
    }
    /** cette methode nous permet de changer le test de this.text c'est à dire , changer le texte des regles
     * @param text les regles
     */
    public void setText(String text){
        this.text.setText(text);
    }
   
    /** getter pour le text
     * @return le text (les regles)
     */ 
    public JTextPane getText(){
        return this.text;
    }

}

