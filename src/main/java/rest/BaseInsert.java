package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.address.Address;
import rest.address.AddressRepository;
import rest.client.Client;
import rest.client.ClientRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaseInsert {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;


    @PostConstruct
    public void insertToDB(){
        List<Address> list = new ArrayList<>();
        Client client1 = new Client("Vadim", "Ivanov");
        clientRepository.save(client1);
        Address address1 = new Address("London", client1);
        addressRepository.save(address1);

        Client client2 = new Client("Andrew", "Petrov");
        clientRepository.save(client2);
        Address address2 = new Address("Moscow", client2);
        addressRepository.save(address2);

        Client client3 = new Client("Petr", "Petrov");
        clientRepository.save(client3);

        Address address3 = new Address("Paris", client3);
        addressRepository.save(address3);

        Client client4 = new Client("Nikolay", "Kazancev");
        clientRepository.save(client4);

        Address address4 = new Address("Str", client4);
        addressRepository.save(address4);

    }
}
