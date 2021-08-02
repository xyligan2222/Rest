package rest.client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    private final ModelMapper mapper;

    public ClientController(@Autowired ClientService clientService,
                            @Autowired ModelMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id) {
        Client response = clientService.getClientById(id);
        if (response != null) {
            return new ResponseEntity<>(mapper.map(response, Client.class), HttpStatus.OK);
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
                    .map(e -> mapper.map(e, Client.class))
                    .collect(Collectors.toList());
        } else {
            list = clientService
                    .getAllClient()
                    .stream()
                    .map(e -> mapper.map(e, Client.class))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        if (client.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Client response = clientService.saveOrUpdateClients(mapper.map(client, Client.class));
        return new ResponseEntity<>(mapper.map(response, Client.class), HttpStatus.OK);
    }

    @PutMapping("/client")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        if (client.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Client response = clientService.saveOrUpdateClients(mapper.map(client, Client.class));
        return new ResponseEntity<>(mapper.map(response, Client.class), HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
