package sabackend.initspringboot.services;

import org.springframework.stereotype.Service;
import sabackend.initspringboot.enums.TypeSentiment;
import sabackend.initspringboot.models.Client;
import sabackend.initspringboot.models.Sentiment;
import sabackend.initspringboot.repositories.SentimentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SentimentService {

    // NE PAS UTILISER L'AUTOWIRED ( MAUVAISE PRATIQUE )
    // BEAN : une class qu'on peut instancier
    private final ClientService clientService;
    private final SentimentRepository sentimentRepository;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void create(Sentiment sentiment){
        Client client = this.clientService.getOrCreate(sentiment.getClient());
        sentiment.setClient(client);

        if(sentiment.getTexte().contains("pas")){
            sentiment.setType(TypeSentiment.NEGATIF);
        }else{
            sentiment.setType(TypeSentiment.POSITIF);
        }

        this.sentimentRepository.save(sentiment);
    }

    public List<Sentiment> search(TypeSentiment type) {
      return type == null ? this.sentimentRepository.findAll() : this.sentimentRepository.findByType(type);
    }

    public void delete(int id) {
        this.sentimentRepository.deleteById(id);
    }
}
