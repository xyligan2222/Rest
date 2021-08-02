package rest.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByOwnerId(int id);
//    Address findById(int id);
//    Address deleteById(int id);
}
