package th.go.dxc.share.config;

import static org.jeasy.random.FieldPredicates.inClass;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.go.dxc.share.model.AuditableEntity;
import th.go.dxc.share.util.jeasy.randomizer.SexCodeRandomizer;
import th.go.dxc.share.util.jeasy.randomizer.ThaiFullNameRandomizer;
import th.go.dxc.share.util.jeasy.randomizer.ThaiGivenNameRandomizer;
import th.go.dxc.share.util.jeasy.randomizer.ThaiIdentificationNumberRandomizer;
import th.go.dxc.share.util.jeasy.randomizer.ThaiSurNameRandomizer;
import th.go.dxc.share.util.jeasy.randomizer.YearRandomizer;

@Configuration
public class RandomConfig {
	@Bean
	public EasyRandom easyRandom() {
		LocalDate tenYearsAgo = LocalDate.of(LocalDate.now().getYear()-10, 1, 1);
		LocalDate nextTenYears = LocalDate.of(LocalDate.now().getYear()+10, 1, 1);
		EasyRandomParameters parameters = new EasyRandomParameters()
				.excludeField(inClass(AuditableEntity.class))
				   .seed(123L)
				   .objectPoolSize(100)
				   .randomizationDepth(3)
				   .charset(Charset.forName("UTF-8"))
				   .timeRange(LocalTime.of(9, 0), LocalTime.of(17, 0))
				   .dateRange(tenYearsAgo, nextTenYears)
				   .stringLengthRange(5, 50)
				   .collectionSizeRange(1, 10)
				   .scanClasspathForConcreteTypes(true)
				   .overrideDefaultInitialization(false)
				   .ignoreRandomizationErrors(true)
				   .randomize(yearField(),new YearRandomizer())
				   .randomize(sexCodeField(),new SexCodeRandomizer())
				   .randomize(numberField(), new IntegerRangeRandomizer(0, 1000))
				   .randomize(personGivenNameField(), new ThaiGivenNameRandomizer())
				   .randomize(personSurNameField(), new ThaiSurNameRandomizer())
				   .randomize(thaiIdentificationNumberField(), new ThaiIdentificationNumberRandomizer())
				   .randomize(thaifullNameField(), new ThaiFullNameRandomizer())
				   ;
		return new EasyRandom(parameters);
	}
	
	private Predicate<Field> personGivenNameField() {
		return FieldPredicates.named("(?i).*FirstName").and(FieldPredicates.ofType(String.class));
	}
	private Predicate<Field> personSurNameField() {
		return FieldPredicates.named("(?i).*LastName").and(FieldPredicates.ofType(String.class));
	}	
	
	private Predicate<Field> yearField() {
		return FieldPredicates.named("(?i).*year").and(FieldPredicates.ofType(Integer.class));
	}
	
	private Predicate<Field> numberField() {
		return FieldPredicates.named("(?i).*number").and(FieldPredicates.ofType(Integer.class));
	}
	
	private Predicate<Field> sexCodeField() {
		return FieldPredicates.named("(?i).*sex.*").and(FieldPredicates.ofType(String.class));
	}
	
	private Predicate<Field> thaiIdentificationNumberField() {
		return FieldPredicates.named("(?i).*pid.*|.*citizenCardNumber").and(FieldPredicates.ofType(String.class));
	}
	private Predicate<Field> thaifullNameField() {
		return FieldPredicates.named("(?i)name|.*fullName").and(FieldPredicates.ofType(String.class));
	}
	
	
//	public static void main(String[] args) {
//		RandomConfig randomConfig = new RandomConfig();
//		EasyRandom random = randomConfig.easyRandom();
//		PetAdmissionEntity petAdmission =  random.nextObject(PetAdmissionEntity.class);
//		log.debug("PetAdmissionEntity: "+petAdmission);
//		HappyPersonEntity person =  random.nextObject(HappyPersonEntity.class);
//		log.debug("person: "+person);
//	}
}
