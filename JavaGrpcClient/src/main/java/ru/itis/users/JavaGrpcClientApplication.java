package ru.itis.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.users.service.UsersService;

import java.util.Scanner;

@SpringBootApplication
public class JavaGrpcClientApplication implements CommandLineRunner {

	@Autowired
	private UsersService usersService;

	public static void main(String[] args) {
		SpringApplication.run(JavaGrpcClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		String id = scanner.nextLine();

		String userName = usersService.getNameOfUser(id);

		System.out.println(userName);
	}
}
