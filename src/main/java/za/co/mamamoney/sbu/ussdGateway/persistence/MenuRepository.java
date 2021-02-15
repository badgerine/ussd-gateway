package za.co.mamamoney.sbu.ussdGateway.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.mamamoney.sbu.ussdGateway.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
