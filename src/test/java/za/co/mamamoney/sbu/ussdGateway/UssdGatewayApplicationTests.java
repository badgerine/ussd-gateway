package za.co.mamamoney.sbu.ussdGateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class UssdGatewayApplicationTests {

	@Autowired
	private UssdGatewayApplication ussdGatewayApplication;

	@Test
	void contextLoads() {
		assertThat(ussdGatewayApplication).isNotNull();
	}

}
