package kr.co.sist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.sist.interceptor.UserInterceptor;

//설정용 class
@Configuration
//1.WebMvcConfigurer를 구현 
public class IntercepterConfig implements WebMvcConfigurer{
	// application.properties에 있는 속성값을  List에 저장

	// 2. 제작한 Interceptor를 의존성 주입
	@Value("${myapp.user.addPath}")
	List<String> addPathUserList;
	
	@Value("${myapp.user.excludePath}")
	List<String> excludePathUserList;
	// 2. 제작한 Interceptor를 의존성 주입

	@Autowired
	private UserInterceptor ui;

	
	/**
	 * 3. 제작된 Intercepteor 가 동작할 URI를 설정하여 등록
	 */
	
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {

		 
		//UserInterceptor 등록
		 registry.addInterceptor(ui).addPathPatterns(addPathUserList).
		 excludePathPatterns(excludePathUserList);
	  
	  }//addInterceptors
		
	
	
}//class
