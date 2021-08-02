package rest.client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Client> getFilteredClients(String param){
        StringBuilder builder = new StringBuilder("%")
                .append(param)
                .append("%");
        return clientRepository.filter(builder.toString());
    }

    @Transactional(readOnly = true)
    public Client getClientById(int id) {
        Optional<Client> emp = clientRepository.findById(id);
        return emp.orElse(null);
    }

    @Transactional
    public Client saveOrUpdateClients(Client client){
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}