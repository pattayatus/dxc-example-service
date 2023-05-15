package th.go.dxc.share.util.jeasy.randomizer;

import java.util.Random;

import org.jeasy.random.api.Randomizer;
import org.joda.time.LocalDate;

public class YearRandomizer implements Randomizer<Integer>{
	private final Random random;
	private final Integer start;
	private final Integer end;
	
	
	
	public YearRandomizer() {
		super();
		this.random = new Random();
		this.start = LocalDate.now().getYear()-50;
		this.end = this.start+100;
		
	}

	public YearRandomizer(Integer start,Integer end) {
		super();
		this.random = new Random();
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer getRandomValue() {
		return start+this.random.nextInt(end-start) ;
	}

}
