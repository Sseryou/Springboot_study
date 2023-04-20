package org.koreait.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing  //엔티티 상태 변화 감지 활성화
public class MvcConfig implements WebMvcConfigurer {

}
