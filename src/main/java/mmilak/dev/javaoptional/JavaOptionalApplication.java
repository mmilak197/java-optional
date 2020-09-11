package mmilak.dev.javaoptional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class JavaOptionalApplication {

	public static void main(String[] args) {
		Optional<Object> empty = Optional.empty();
		System.out.println(empty);
		System.out.println("empty.isPresent() " + empty.isPresent());
		System.out.println("empty.isEmpty() " + empty.isEmpty());


		Optional<String> hello = Optional.ofNullable("hello");
		System.out.println(hello);
		System.out.println("hello.isPresent() " + hello.isPresent());
		System.out.println("hello.isEmpty() " + hello.isEmpty());

		String orElse = hello
				.map(String::toUpperCase)
				.orElse("world");

		System.out.println(orElse);

		String errorOptional = hello.map(String::toUpperCase)
				.orElseThrow(() -> new IllegalArgumentException("test"));

		System.out.println(errorOptional);


		String test = hello
				.map(String::toUpperCase)
				.orElseGet(() -> {
					return "world";
				});
		System.out.println(test);


		Optional<String> someText = Optional.ofNullable(null);

		someText.ifPresent(word -> {
			System.out.println(word);
		});

		someText.ifPresentOrElse(word -> {
			System.out.println(word);
		}, () -> {
			System.out.println("missing text");
		});

		var person = new Person("John", null);
		String email = person.getEmail().map(String::toLowerCase).orElse("email not provided");
		System.out.println(email);
	}
}

class Person {
	private final String name;
	private final String email;

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}
}
