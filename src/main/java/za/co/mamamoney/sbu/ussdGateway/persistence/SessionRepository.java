package za.co.mamamoney.sbu.ussdGateway.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.mamamoney.sbu.ussdGateway.model.Session;

public interface SessionRepository extends JpaRepository<Session,String> {
}
