package za.co.mamamoney.sbu.ussdGateway.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import za.co.mamamoney.sbu.ussdGateway.exceptionHandling.InvalidEntryException;
import za.co.mamamoney.sbu.ussdGateway.exceptionHandling.InvalidSessionException;
import za.co.mamamoney.sbu.ussdGateway.model.*;
import za.co.mamamoney.sbu.ussdGateway.persistence.MenuPresentedRepository;
import za.co.mamamoney.sbu.ussdGateway.persistence.MenuRepository;
import za.co.mamamoney.sbu.ussdGateway.persistence.SessionRepository;
import za.co.mamamoney.sbu.ussdGateway.utility.Util;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class Interaction {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private MenuPresentedRepository menuRepository;
    @Autowired
    private MenuRepository definitionRepository;
    @Autowired
    private Validation validation;
    private Logger logger = LoggerFactory.getLogger(Interaction.class);

    public ResponseEntity<UssdResponse> processRequest(UssdRequest ussdRequest) throws InvalidSessionException, InvalidEntryException {
        String sessionId = ussdRequest.getSessionId().trim();
        Optional<Session> sessionWrapper = sessionRepository.findById(sessionId);
        Session session = null;
        MenuPresented lastMenuPresented = null;
        Menu lastDefinition = null;
        MenuPresented nextMenu = null;
        try {
            if(sessionWrapper.isPresent()){
                session = sessionWrapper.get();

                //validate sessionId and msisdn
                validation.validateSession(session, ussdRequest.getMsisdn());
                //validate request
                lastMenuPresented = menuRepository.findLatestBySessionId(sessionId);
                validation.captureValidRequest(lastMenuPresented, session);
                //validate user entry
                lastDefinition = definitionRepository.findById(lastMenuPresented.getMenuId()).get();
                validation.captureValidUserEntry(ussdRequest.getUserEntry(), sessionId, lastMenuPresented, lastDefinition);

                //determine next step
                String custom = null;
                nextMenu = new MenuPresented(sessionId);
                switch (lastMenuPresented.getMenuId()) {
                    case 1:
                        Util.processCountry(lastMenuPresented, session);
                        nextMenu.setMenuId(2);
                        custom = definitionRepository.findById(2).get().getMessage();
                        ;
                        nextMenu.setMessage(String.format(custom, session.getDestinationCountry()));
                        break;
                    case 2:
                        Util.processRandAmount(lastMenuPresented,session);
                        nextMenu.setMenuId(3);
                        custom = definitionRepository.findById(3).get().getMessage();
                        nextMenu.setMessage(String.format(custom,
                                Util.calculateAmount(session.getDestinationCountry(),session.getRandAmount()),
                                Util.generateCurrency(session.getDestinationCountry())));
                        break;
                    case 3:
                        StringBuilder sb = Util.processConfirmation(lastMenuPresented, session);
                        nextMenu.setMenuId(4);
                        custom = definitionRepository.findById(4).get().getMessage();
                        nextMenu.setMessage(sb.append(custom).toString());
                        break;
                }

            } else {
                session = new Session(sessionId,ussdRequest.getMsisdn(), LocalDateTime.now(), SessionStatus.NEW.name());
                nextMenu= new MenuPresented(sessionId,1);
                nextMenu.setMessage(definitionRepository.findById(1).get().getMessage());
            }
        }
        catch (InvalidSessionException e){
            throw new InvalidSessionException(e.getMessage());
        }catch (InvalidEntryException e){
            throw new InvalidEntryException(e.getMessage());
        }finally {
            if(lastMenuPresented != null){
                menuRepository.setMenuPresentedById(sessionId,lastMenuPresented.getMenuId(),ussdRequest.getUserEntry(),lastMenuPresented.getAttempts());
            }
            if(nextMenu != null) {
                menuRepository.setMenuPresentedById(sessionId, nextMenu.getMenuId());
            }
            sessionRepository.save(session);
        }

        return buildResponse(sessionId, nextMenu.getMessage());
    }

    private ResponseEntity<UssdResponse> buildResponse(String sessionId, String message){
        UssdResponse ussdResponse = new UssdResponse(sessionId.toString(),message);
        return new ResponseEntity<>(ussdResponse, HttpStatus.OK);
    }
}
