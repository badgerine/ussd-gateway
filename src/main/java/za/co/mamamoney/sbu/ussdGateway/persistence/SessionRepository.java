package za.co.mamamoney.sbu.ussdGateway.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import za.co.mamamoney.sbu.ussdGateway.model.Session;

import java.time.LocalDateTime;

public interface SessionRepository extends JpaRepository<Session,String> {

    @Transactional
    @Modifying
    @Query("update Session s set s.lastModified = ?2, s.sessionStatus = ?3, s.destinationCountry=?4 where s.sessionId = ?1")
    void setSessionById(String sessionId, LocalDateTime lastModified, String sessionStatus, String destinationCountry);

    @Transactional
    @Modifying
    @Query("update Session s set s.lastModified = ?2, s.randAmount = ?3 where s.sessionId = ?1")
    void setSessionById(String sessionId, LocalDateTime lastModified, String randAmount);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Session (session_Id, msisdn, last_Modified, session_Status)" +
            "VALUES (:sessionId,:msisdn, :lastModified, :sessionStatus)", nativeQuery = true)
    void setSessionById(String sessionId, String msisdn, LocalDateTime lastModified, String sessionStatus);

}
