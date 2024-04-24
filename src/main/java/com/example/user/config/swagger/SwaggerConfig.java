package com.example.user.config.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //swaggerUI를 스네이크 표기법으로 설정하기 위해 커스텀
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){ //스프링이 objectMapper 빈을 찾아서 주입해줌.
        return new ModelResolver(objectMapper);
    }
}
