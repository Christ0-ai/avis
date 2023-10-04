package sabackend.initspringboot.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sabackend.initspringboot.dto.ErrorEntity;
import sabackend.initspringboot.models.Client;
import sabackend.initspringboot.services.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController /* API REST NORME DESIGNER NOS URL */
@RequestMapping(path = "client")
public class ClientController {

      /*  @Autowired
        ClientService clientService; */

    // PAS LA BONNE FACON DE FAIRE
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE) /* ON SOUHAITE MANIPULER QUE DES FICHIERS EN FORMAT JSON*/
    public void create(@RequestBody Client client) {
        this.clientService.create(client);
    }

    /* RESPONSE STATUS 200 PAR DEFAULT */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Client> search() {
        return this.clientService.search();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)  //localhost/api/client/{id}
    public ResponseEntity getClientById(@PathVariable int id) {

        // ON EFFECTUE LE TRY CATCH ICI CAR POTENTIELLEMENT ON A UNE EXCEPTION DANS LE SERVICE
        // AUTRE POSSIBILITE ON ENELVE LE TRY CATCH ET ON UTILISE L'ANNOTATION EXECEPTIONHANDLER SUR UNE METHODE QUI RETOURNE UNE ERROR ENTITY
        try {
            Client client = this.clientService.getClientById(id);
            /*
            RETURN OK
            {
                "id": 1,
                "email": "toto@email.fr",
                "telephone": "0102030405"
            }
             */
            return ResponseEntity.ok(client);
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, entityNotFoundException.getMessage()));
            /*
             RETURN BAD REQUEST
             {
                "code" : null,
                "message" "Aucun client n'existe avec cet id" :
             }
             */
        }
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Client client) {
        this.clientService.update(id, client);
    }
}
