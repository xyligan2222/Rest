package rest.address;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class AddressController {
    private final AddressService addressService;


    public AddressController(@Autowired AddressService addressService,
                             @Autowired ModelMapper mapper) {
        this.addressService = addressService;

    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAddresses(@RequestParam(value = "client",
            required = false) Integer clientId) {
        List<Address> list;
        if (clientId != null) {
            list = addressService
                    .getAddressByClient(clientId)
                    .stream()
                    .collect(Collectors.toList());
        } else {
            list = addressService
                    .getAllAddress()
                    .stream()
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable int id) {
        Address response = addressService.getAddressById(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/address")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        if (address.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/address")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        if (address.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
