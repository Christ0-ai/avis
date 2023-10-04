package sabackend.initspringboot.models;

import jakarta.persistence.*;
import sabackend.initspringboot.enums.TypeSentiment;

@Entity
@Table(name = "Sentiment")
public class Sentiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texte;
    private TypeSentiment type;

    // PLUSIEURS CLIENT PEUT DEPOSER UN AVIS
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    // PERSIST : PUISQUE UN SENTIMENT EST CREE PAR UN CLIENT, ON VA INSERER LA CLE PRIMAIRE DU CLIENT DANS LE CLE ETRANGERE DU SENTIMENT
    // MERGE : SI L'UTILISATEUR N'EST PAS CREE DANS LA BDD ALORS ON LE CREE SINON ON PREND LA CLE PRIMAIRE DU CLIENT ET INJECTER COMME ETANT LA CLE ETRANGERE DU SENTIMENT

    // @JoinColumn(name = "champsRef") PAS BESOIN DE RAJOUTER CETTE ANNOTATION PUISQUE DANS LA TABLE SENTIMENT LE NOM DU CHAMPS EST IDENTIQUE A CELUI NOTRE CLASS
    private Client client;

    public Sentiment() {}

    public Sentiment(int id, String texte, TypeSentiment type, Client client) {
        this.id = id;
        this.texte = texte;
        this.type = type;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public TypeSentiment getType() {
        return type;
    }

    public void setType(TypeSentiment type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
