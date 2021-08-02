package rest.client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

//    private final ModelMapper mapper;

    public ClientController(@Autowired ClientService clientService
                            ) {
        this.clientService = clientService;

    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id) {
        Client response = clientService.getClientById(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>>
    getClient(@RequestParam(value = "filter",
            required = false) String filter) {
        List<Client> list;
        if (filter != null) {
            list = clientService
                    .getFilteredClients(filter)
                    .stream()
                    .collect(Collectors.toList());
        } else {
            list = clientService
                    .getAllClient()
                    .stream()
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        if (client.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/client")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        if (client.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
