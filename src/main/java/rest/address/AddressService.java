package rest.address;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressByClient(int owner){
        return addressRepository.findByOwnerId(owner);
    }

    @Transactional(readOnly = true)
    public Address getAddressById(int id) {
        Optional<Address> emp = addressRepository.findById(id);
        return emp.orElse(null);
    }

    @Transactional
    public Address saveOrUpdateAddress(Address address){
        return addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }
}
