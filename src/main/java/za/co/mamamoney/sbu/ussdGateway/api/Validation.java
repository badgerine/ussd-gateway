package za.co.mamamoney.sbu.ussdGateway.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mamamoney.sbu.ussdGateway.Constants;
import za.co.mamamoney.sbu.ussdGateway.exceptionHandling.InvalidSessionException;
import za.co.mamamoney.sbu.ussdGateway.exceptionHandling.InvalidEntryException;
import za.co.mamamoney.sbu.ussdGateway.model.*;

import java.math.BigInteger;
import java.util.regex.Pattern;

@Component
public class Validation {
    @Autowired
    private Gson gson;

    public void validateSession(Session session, String msisdn) throws InvalidSessionException {
        if(session.getSessionStatus().equals(SessionStatus.NEW.name())){
            session.setSessionStatus(SessionStatus.PENDING.name());
        }
        if(!session.getSessionStatus().equals(SessionStatus.PENDING.name())){
            throw new InvalidSessionException(String.format("Session %s %s.", session.getSessionId(), session.getSessionStatus()));
        }
        if(!Pattern.matches("\\d{11}",msisdn)){
            throw new InvalidSessionException(String.format("MSISDN[%s] needs to be an 11 digit number",msisdn));
        }
        if(!msisdn.equals(session.getMsisdn())){
            throw new InvalidSessionException(String.format("Session[%s] is not valid for msisdn[%s].", session.getSessionId(), msisdn));
        }
    }

    public void captureValidRequest(MenuPresented menuPresented, Session session) {
        if(menuPresented.getAttempts() == null){
            menuPresented.setAttempts(Integer.valueOf(1));
        } else {
            menuPresented.setAttempts(Integer.valueOf(menuPresented.getAttempts()+1));
        }
        if(menuPresented.getAttempts() >= Constants.MAX_ATTEMPTS){
            session.setSessionStatus(SessionStatus.RETRIES_EXCEEDED.name());
            throw new InvalidSessionException("No more than 3 attempts per Step.");
        }

    }

    public void captureValidUserEntry(String userEntry, String sessionId, MenuPresented lastMenuPresented, Menu definition) {
        boolean validUserEntry = false;
        UserOptions lastMenuOptions = gson.fromJson(definition.getUserOptions(), UserOptions.class);
        if(lastMenuOptions.getType().equals(Constants.FIXED_MENU)){
            for(String option: lastMenuOptions.getOptions()){
                if(validUserEntry = option.equals(userEntry)){
                    break;
                }
            }
        } else if(new BigInteger(userEntry).compareTo(new BigInteger("0")) < 1){
            throw new InvalidEntryException("Non-decimal number greater than zero is required.");
        }
        if(!validUserEntry && lastMenuOptions.getType().equals(Constants.FIXED_MENU)){
            throw new InvalidEntryException("Invalid option selected.");
        }
        lastMenuPresented.setSelection(userEntry);
    }
}
