package rest.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select c from client c where name like ?1 or surname like ?1")
    List<Client> filter(String str);
}
