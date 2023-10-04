package sabackend.initspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabackend.initspringboot.enums.TypeSentiment;
import sabackend.initspringboot.models.Sentiment;

import java.util.List;

public interface SentimentRepository extends JpaRepository<Sentiment,Integer> {
    List<Sentiment> findByType(TypeSentiment type);
}
