package th.go.dxc.share.util.orika.mapper;

import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import th.go.dxc.share.model.BasicPageRequest;
import th.go.dxc.share.model.PageableDto;
import th.go.dxc.share.util.orika.OrikaMapperFactoryConfigurer;
import th.go.dxc.share.util.orika.converter.LocalDateTimeToOffsetDateTimeConverter;
import th.go.dxc.share.util.orika.converter.LocalDateToOffsetDateTimeConverter;
import th.go.dxc.share.util.orika.converter.ObjectToIntegerConverter;
import th.go.dxc.share.util.orika.converter.ObjectToLocalDateConverter;
import th.go.dxc.share.util.orika.converter.ObjectToLocalDateTimeConverter;
import th.go.dxc.share.util.orika.converter.ObjectToLongConverter;
import th.go.dxc.share.util.orika.converter.ObjectToOffsetDateTimeConverter;
import th.go.dxc.share.util.orika.converter.PageableDtoToPageConverter;
import th.go.dxc.share.util.orika.converter.PersonSexCodeSimpleTypeStringConverter;
import th.go.dxc.share.util.orika.converter.StringToJsonNodeConverter;

@Slf4j
@Component
public class DefaultOrikaConfigurer implements OrikaMapperFactoryConfigurer{
	
	public final static String CONVERTER_SEX_CODE = "sexCode";
	public final static String CONVERTER_PAGEABLE = "pageable";
	public final static String CONVERTER_SEX_THAI = "sexThai";
	private final Type<Page<?>> pageType = new TypeBuilder<Page<?>>(){}.build();
	@Autowired
	private ObjectMapper objectMapper;
	@Override
	public void configureMapping(MapperFactory orikaMapperFactory) {
		log.info("DefaultOrikaMapper.configureMapping");
		ConverterFactory converterFactory = orikaMapperFactory.getConverterFactory();
		converterFactory.registerConverter(new ObjectToLongConverter());
		converterFactory.registerConverter(new ObjectToIntegerConverter());
		converterFactory.registerConverter(new ObjectToLocalDateConverter());
		converterFactory.registerConverter(new ObjectToLocalDateTimeConverter());
		converterFactory.registerConverter(new ObjectToOffsetDateTimeConverter());
		converterFactory.registerConverter(new LocalDateTimeToOffsetDateTimeConverter(ZoneOffset.of("+7")));
		converterFactory.registerConverter(new LocalDateToOffsetDateTimeConverter(ZoneOffset.of("+7")));
		converterFactory.registerConverter(new PageableDtoToPageConverter());
		converterFactory.registerConverter(new PersonSexCodeSimpleTypeStringConverter("F","M","X","U"));
		converterFactory.registerConverter(new PersonSexCodeSimpleTypeStringConverter("หญิง","ชาย","อื่นๆ","ไม่ระบุ"));
		converterFactory.registerConverter(new StringToJsonNodeConverter(objectMapper));
		orikaMapperFactory.registerConcreteType(Pageable.class, BasicPageRequest.class);
		orikaMapperFactory.registerConcreteType(PageableDto.class, PageableDto.class);
//		orikaMapperFactory.classMap(PageableDto.class,Pageable.class)
//		.customize(new PagealbeDtoToPageableMapper())
		orikaMapperFactory.registerObjectFactory(new PageObjectFactory(), pageType);
		orikaMapperFactory.registerMapper(new PageableDtoToPageableMapper());
		orikaMapperFactory.registerMapper(new PageableDtoToBasicPageRequestMapper());
		orikaMapperFactory.classMap(PageableDto.class,Pageable.class).customize(new PageableDtoToPageableMapper()).byDefault().register();;
	}

}
