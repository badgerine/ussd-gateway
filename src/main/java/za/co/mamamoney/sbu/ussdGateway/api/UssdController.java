package za.co.mamamoney.sbu.ussdGateway.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.mamamoney.sbu.ussdGateway.model.UssdRequest;
import za.co.mamamoney.sbu.ussdGateway.model.UssdResponse;

import javax.validation.Valid;

@RestController
public class UssdController {
    private Logger logger = LoggerFactory.getLogger(UssdController.class);

    @Autowired
    private Interaction interaction;

    @PostMapping(path="/ussd", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<UssdResponse> generateResponse(@RequestBody UssdRequest ussdRequest){
        logger.debug("Request",ussdRequest);
        return interaction.processRequest(ussdRequest);
    }
}
