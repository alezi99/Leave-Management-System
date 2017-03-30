package condgemaladiev2;

/**
 *
 * @author ikerfah
 */
public class FullEmployee {
    
    private int id;
    private String nomFr;
    private String prenomFr;
    private String poste;   
    private String obs;
    private int nbVac;
    private int restVac;
    private String typeConge;
    private String dateDebut;
    private int nbJours;
    private String dateFin;

    public FullEmployee() {
    }

    public FullEmployee(int id, String nomFr, String prenomFr, String poste, String obs) {
        this.id = id;
        this.nomFr = nomFr;
        this.prenomFr = prenomFr;
        this.poste = poste;
        this.obs = obs;
    }

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }
    public int getNbVac() {
        return nbVac;
    }

    public void setNbVac(int nbVac) {
        this.nbVac = nbVac;
    }

    public int getRestVac() {
        return restVac;
    }

    public void setRestVac(int restVac) {
        this.restVac = restVac;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getPrenomFr() {
        return prenomFr;
    }

    public void setPrenomFr(String prenomFr) {
        this.prenomFr = prenomFr;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
    
}
