package sabackend.initspringboot.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import sabackend.initspringboot.models.Client;
import sabackend.initspringboot.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    // NE PAS UTILISER L'AUTOWIRED ( MAUVAISE PRATIQUE )

    private ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void create(Client client){
        Client clientFromDataBase = this.clientRepository.findByEmail(client.getEmail());
        if(clientFromDataBase != null) this.clientRepository.save(client);
    }

    public List<Client> search(){
        return this.clientRepository.findAll();
    }

    public Client getClientById(int id) {
        // OPTIONAL : L'objet peut etre null
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        return optionalClient.orElseThrow(() -> new EntityNotFoundException("Aucun client n'existe avec cet id"));
    }

    public Client getOrCreate(Client client) {
        Client clientFromDatabase = this.clientRepository.findByEmail(client.getEmail());

        if(clientFromDatabase == null) clientFromDatabase = this.clientRepository.save(client);
        return clientFromDatabase;

    }
    public void update(int id, Client client) {
        Client clientFromDataBase = this.clientRepository.findByEmail(client.getEmail());

        if(clientFromDataBase.getId() == client.getId()){
            clientFromDataBase.setEmail(client.getEmail());
            clientFromDataBase.setTelephone(client.getTelephone());
            this.clientRepository.save(clientFromDataBase);
        }
    }
}
