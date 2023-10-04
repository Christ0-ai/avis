package sabackend.initspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabackend.initspringboot.models.Client;

// pas utile d'ajouter l'annotation Repository puisque notre class extends de JpaRepository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByEmail(String email);
}
