package th.go.dxc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;
//@EnabledIf(expression = "#{environment.acceptsProfiles('sit')}", loadContext = false)
@EnabledIf("false")
@SpringBootTest
class DxcExampleServiceApplicationTest {

	@Test
	void contextLoads() {
	}
}
