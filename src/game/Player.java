package game;

import game.util.exception.*;
import game.util.io.Colors;
import java.util.*;

/** Classe abstraite qui gère les info
 * sur le joueur du jeux
 */
public abstract class Player {

    /**nom du jouer  */
    protected String name; 
    /**ses points */
    protected int points; 
    /**or du palyer */
    protected int gold; 
    /** characters deployes */
    protected ArrayList<Character> deployedCharacters;
    /**ressources du player */
    protected Map<Resource, Integer> resource; 
    /** charcaters deployees sans nourriture */
    protected ArrayList<Character> characterhaveNotFood; 
    /**nb de nourriture */
    protected int food;
    /**interfactif ou pas  */
    protected boolean interactive; 

    /**Constructeur de la classe player
     * @param name : nom du joueur
     * @param gold : or posséder par le joueur
     * @param food : nourriture posséder par le joeur
     * @param interactive: tru pour joueur interfactif, flase sinon
     */
    protected Player(String name, int gold, int food, boolean interactive) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.points = 0;
        this.gold = gold;
        this.resource = new HashMap<Resource, Integer>();
        this.deployedCharacters = new ArrayList<>();
        this.food = food;
        this.interactive = interactive;
        this.characterhaveNotFood = new ArrayList<>();
    }

    /**
     * Cette méthode permet d'accéder
     * à l'attribut nom du joueur
     * @return de type string le nom du Joueur
     */
    public String getName() {
        return this.name;
    }

    /** Cette methode permet d'enelever de la nourriture
     * @param food : nbr de nouriture a enlever
     * @throws NotEnoughFoodException : se declanche quand le nombre de nourriture
     * a enlever est supérieur au nombre de nourriture posséder.
     */
    public void reduceFood(int food) throws NotEnoughFoodException {
        if (this.food - food >= 0) {
            this.food = this.food - food;
        } else {
            throw new NotEnoughFoodException();
        }
    }

    /**
     * Cette méthode permet d'accéder
     * à l'attribut or du joueur
     * cela nous permet de connaître le nombre d'or que dispose
     * le joueur
     * @return de type entier le nombre d'or du joueur
     */
    public int getGold() {
        return this.gold;
    }

    /** Méthode qui permet de connaitre le nombre de nourriture
     * que possède le joueur
     * @return : nbr de nourriture
     */
    public int getFood() {
        return this.food;
    }

    /** Méthode qui permet de donner le salaire d'un character
     * @param character : character que l'on souhaite payer
     * @throws NotEnoughGoldException se déclanche quand le joueur
     * ne possede pas assez d'or pour donne la paye
     */
    public void pay(Character character) throws NotEnoughGoldException {
        try {
            this.gold -= character.getPayed(this.gold);
        } catch (NotEnoughGoldException e) {
            throw new NotEnoughGoldException("Pas assez d'or pour payer");
        }
    }

    /** Méthode qui permet de gerer l'alimentation de l'armé
     * @param army : armé que posséde le joueur
     * @throws NotEnoughFoodException se déclanche quand le joueur na pas assez de nourriture
     */
    public void feed(Character army) throws NotEnoughFoodException {
        try {
            int nourriture = army.eat(this.food);
            this.reduceFood(nourriture);
        } catch (NotEnoughFoodException e) {
            this.characterhaveNotFood.add(army);
            String p = army.getOwner().getName();
            String t = army.getPosition().toString();
            int x = army.getPosition().getXposition();
            int y = army.getPosition().getYposition();
            System.out.println(
                Colors.RED +
                p +
                " n'a pas assez de nourriture pour " +
                "le Character posisioné sur " +
                t +
                "{" +
                x +
                ", " +
                y +
                "}" +
                Colors.RESET
            );
        }
    }

    /** Méthode qui permet d'acceder a l'attribut deployedCharacters.
     * @return liste des charactere
     */
    public ArrayList<Character> getDeployedCharacters() {
        return this.deployedCharacters;
    }

    /**
     * Cette méthode permet de supprimer un guerrier de l'armee
     * @param c Le guerrier à supprimer
     * le guerrier ne fait pas partie de l'armee
     */
    public void removeDeployedCharacter(Character c) {
        this.deployedCharacters.remove(c);
    }

    /**
     * Cette méthode permet d'ajouter un guerrier dans l'armée
     * @param c Le guerrier
     */
    public void adddeployedCharacter(Character c) {
        this.deployedCharacters.add(c);
    }

    /**
     * Cette méthode d'ajouter un nombre d'or à l'or que dispose le joueur
     * @param gold : nombre de gold a ajouter
     */
    public void addGold(int gold) {
        this.gold = this.gold + gold;
    }

    /**
     * Cette méthode permet d'ajouter un point au point du joueur
     * @param point : le point à ajouter
     */
    public void addPoints(int point) {
        this.points = this.points + point;
    }

    /** Méthode qui permet de compter les point de joueur
     * @return int nombre de points
     */
    public abstract int countPoint();

    /** Cette méthode permet d'accéder
     * à l'attribut point du joueur
     * @return int : le nombre de points de joueur
     */
    public int getPoints() {
        return this.points;
    }

    /** Méthode qui permet de rajouter une ressource
     * @param resource : ressource que l'on veut ajouter
     */
    public void addRessource(Resource resource) {
        if (this.resource.containsKey(resource)) {
            this.resource.put(resource, this.resource.get(resource) + 1);
        } else {
            this.resource.put(resource, 1);
        }
    }

    /** Méthode qui permet de rajouter une ressource
     * @param resource : ressource a ajouter
     * @param value : valeur de la ressource a ajouter
     */
    public void addRessourcewithValue(Resource resource, int value) {
        this.resource.put(resource, value);
    }

    /** Méthode qui retourne la liste des resource
     * @return liste des ressurce et de leur valeur
     */
    public Map<Resource, Integer> getResource() {
        return this.resource;
    }

    /** Méthode qui permet d'afficher les ressources
     * @return les ressource
     */
    public Map<String, Integer> showResources() {
        Map<String, Integer> resource = new HashMap<String, Integer>();
        resource.put("Wood", 0);
        resource.put("Rock", 0);
        resource.put("Sand", 0);
        resource.put("Corn", 0);
        for (Map.Entry<Resource, Integer> entry : this.resource.entrySet()) {
            String r = entry.getKey().toString();
            if (resource.containsKey(r)) {
                int res = resource.get(r) + entry.getValue();
                resource.put(r, res);
            } else {
                resource.put(r, entry.getValue());
            }
            /*score = entry.getValue();*/
        }
        return resource;
    }

    /** Méthode qui permet d'enlever toute les ressources
     */
    public void removeAllResource() {
        this.resource.clear();
    }

    /** Méthode qui permet d'alimenter tout les charactere
     * @throws NotEnoughFoodException se déclanche quand le joueur
     * na pas assez de nourriture
     */
    public void feedAllCharacter() throws NotEnoughFoodException {
        boolean res = false;
        for (Character a : this.deployedCharacters) {
            //System.out.println(a);
            this.feed(a);
        }

        for (Character a : this.characterhaveNotFood) {
            this.destroyCharacter(a);
            res = true;
        }
        if (res) {
            System.out.print(
                Colors.BLUE_BRIGHT +
                "********" +
                this.characterhaveNotFood.size() +
                " armées a été détruite par manque de nourriture  et vous avez gagné " +
                this.characterhaveNotFood.size() +
                " or de plus" +
                "******\n" +
                Colors.RESET
            );
        }
        this.characterhaveNotFood = new ArrayList<>();
    }

    /** Méthode  qui permet de supprimer un charactere
     * @param a : character a détruire
     */
    protected abstract void destroyCharacter(Character a);

    /** Méthode qui permer de savoir si le joueur
     * est interactive
     * @return boolean retourne True si il est interactive
     */
    public boolean isInterfactive() {
        return this.interactive;
    }

    /** Méthode qui permet d'ajouter de la nourriture
     * @param food : nourriture que l'on souhaite ajouter
     */
    public void addFood(int food) {
        this.food += food;
    }

    /** Méthode qui permet de créer un character
     * @param tile : tuile du charactere
     * @param size : taille
     * @return Character : charactere que l'on creer
     * @throws WrongSizeCharacterException se declanche quand la taille des caractere n'est pas accepeter
     */
    public abstract Character createCharcater(Tile tile, int size)
        throws WrongSizeCharacterException;

    /** Méthode qui ajoute le total des points au joueur
     * par rapport au gold des characteres.
     */
    public void setTotalPoints() {
        addPoints(countPoint());
    }

    /** Méthode qui permet de recolter
     */
    public abstract void harvest();

    /** Méthode qui permet payer les cgaractere deployé
     */
    public void payDeployedCharacters() {
        ArrayList<Character> deployed = this.deployedCharacters;
        Character c = null;
        for (int i = 0; i < deployed.size(); i++) {
            c = deployed.get(i);
            try {
                this.pay(c);
            } catch (NotEnoughGoldException e) {
                int x = c.getPosition().getXposition();
                int y = c.getPosition().getYposition();
                String t = c.getPosition().toString();
                System.out.print(
                    Colors.RED +
                    "- " +
                    c.getOwner().getName() +
                    " n'a pas d'or pour payer le Character posisioné sur " +
                    t +
                    "{" +
                    x +
                    "," +
                    y +
                    "}\n\n" +
                    Colors.RESET
                );
                this.removeDeployedCharacter(c);
            }
        }
    }

    /**methode qui nourrie tous les characters deployes.
     * */
    public void feedDeployedCharacters() {
        ArrayList<Character> deployed = this.deployedCharacters;
        Character c = null;
        for (int i = 0; i < deployed.size(); i++) {
            c = deployed.get(i);
            try {
                this.feed(c);
            } catch (NotEnoughFoodException e) {
                int x = c.getPosition().getXposition();
                int y = c.getPosition().getYposition();
                String t = c.getPosition().toString();
                System.out.println(
                    Colors.RED +
                    c.getOwner().getName() +
                    " n'a pas de nourriture pour nourrir le Character posisioné sur " +
                    t +
                    "{" +
                    x +
                    "," +
                    y +
                    "}" +
                    " celui-ci est retiré du jeu\n" +
                    Colors.RESET
                );
                this.removeDeployedCharacter(c);
            }
        }
    }
}
