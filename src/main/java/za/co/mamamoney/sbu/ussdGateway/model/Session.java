package za.co.mamamoney.sbu.ussdGateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="Session")
public class Session {
    @Id
    private String sessionId;
    @Column
    private String msisdn;
    @Column
    private LocalDateTime lastModified;
    @Column
    private String sessionStatus;
    @Column
    private String destinationCountry;
    @Column
    private String randAmount;

    public Session(){}

    public Session(String sessionId, String msisdn, LocalDateTime lastModified, String sessionStatus) {
        this.sessionId = sessionId;
        this.msisdn = msisdn;
        this.lastModified = lastModified;
        this.sessionStatus = sessionStatus;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public String getRandAmount() {
        return randAmount;
    }

    public void setRandAmount(String randAmount) {
        this.randAmount = randAmount;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

}
