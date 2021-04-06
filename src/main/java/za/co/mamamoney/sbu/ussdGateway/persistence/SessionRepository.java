package za.co.mamamoney.sbu.ussdGateway.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import za.co.mamamoney.sbu.ussdGateway.model.Session;

import java.time.LocalDateTime;

public interface SessionRepository extends JpaRepository<Session,String> {
}
