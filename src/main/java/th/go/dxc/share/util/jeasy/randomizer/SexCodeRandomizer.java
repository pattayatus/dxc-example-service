package th.go.dxc.share.util.jeasy.randomizer;

import java.util.Random;

import org.jeasy.random.api.Randomizer;

import th.go.dxc.share.model.PersonSexCodeSimpleType;

public class SexCodeRandomizer implements Randomizer<String> {

	private final PersonSexCodeSimpleType[] values = PersonSexCodeSimpleType.values();
	private final Random random = new Random();
	@Override
	public String getRandomValue() {
		Integer randomValue = random.nextInt(values.length); 		
		return values[randomValue].code();
	}

}
