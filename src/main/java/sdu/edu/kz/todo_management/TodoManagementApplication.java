package sdu.edu.kz.todo_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoManagementApplication {
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
	public static void main(String[] args) {
		SpringApplication.run(TodoManagementApplication.class, args);
	}

	//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/**")
//						.allowedMethods("*")
//						.allowedOrigins("http://localhost:3000");
//			}
//		};
//	}
}
