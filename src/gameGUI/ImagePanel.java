package gameGUI;

import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/** Cette classe est un heritage de Jpanel qui modifie le background par un background passé en parametre
 * on ne peut pas changer l'image de background de Jpanel, donc j'ai decidé de creer une classe qui est initialisé avec une image de background.
 * On ne peut pas faire autrement, une fois le Jpanel initialisé, le background reste inmodifiable
 * */
public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image image = null;

    /**Constructeur
     * 
     * @param fileName l'emplacement du fichier
     * @throws IOException une exception si le fichier n'est pas trouvé
     */
    public ImagePanel(String fileName) throws IOException  {
        this.image = ImageIO.read(new File(fileName));
        this.setOpaque(false);
    }

    /**image de fond
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
