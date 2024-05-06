package telran.java52.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration   
public class ServiceConfiguration { // специальный конфигурационный класс мы его создаем сами в апликации 
	                                // который помещает в апликационный контекст новые бины
	
	
    @Bean
	ModelMapper getModelMapper() {// конкретно  этот класс -ModelMapper не является частью спринга и он (Спринг)
    	// не может автоматически создать обьекты этого класса в момент старта апликации, для этого мы создаем этот метод который
    	// возвращает экземпляр этого класса ставим анотацию   @Bean, эта аннотация позволяет поместить данный метод в Контекст, 
    	// и тогда в классе СтудентСервисИмпл мы используя поле "===final ModelMapper modelMapper===", Спринг увидит, что в контексте апликации
    	// есть метод который, возвращает экзетпляр класса( с анот @Bean), и он применит к нему анотацию Автовайред;
    	
    	ModelMapper modelMapper =new ModelMapper(); // сам класс ModelMapper является сторонней библиотекой и прописан 
    	                                            // в зависимостях и позволяет конвертировать обекты одного класса в другой используя 
    	                                            // обьект рефлексии класса в который мы хотим конвертировать наш
    	                                            // существующий экземпляр - 
    	                                            //                 Student student = modelMapper.map(studentAddDto, Student.class);
    	modelMapper.getConfiguration()
    	.setFieldMatchingEnabled(true)
    	.setFieldAccessLevel(AccessLevel.PRIVATE)
    	.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
