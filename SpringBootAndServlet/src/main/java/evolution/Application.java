package evolution;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	@RequestMapping(value = "/0", method = RequestMethod.GET)
	public void anyMethod(HttpServletRequest request) {
		// Show all the fields within the request object.
		Field[] fields = request.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object object;
			try {
				object = field.get(request);
				System.out.println(field.getName() + " : " + object + ".");
			} catch (Exception e) {}
		}
		// Call all the methods with 'get' prefix that don't come with an argument.
		Method[] methods = request.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().contains("get")) {
					System.out.println(method.getName() + " : " + method.invoke(request) + ".");
				}
			} catch (Exception e) {}
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
