package pt.ua.ex2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class Ex2ApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
			.withUsername("duke")
			.withPassword("password")
			.withDatabaseName("test");

	@Autowired
	private EmployeeRepository employeeRepository;

	// requires Spring Boot >= 2.2.6
	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	void contextLoads() {
		Employee emp = new Employee();
		emp.setName("Testcontainers");
		emp.setEmail("teste@something.com");
		employeeRepository.save(emp);
		System.out.println("Context loads!");
	}

	@Test
	@Order(1)
	void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setName("ricardo");
		employee.setEmail("ricardo@something.com");
		employeeRepository.save(employee);
		System.out.println("Employee Created!");
	}

	@Test
	@Order(2)
	void testUpdateEmployee() {
		Employee employee = employeeRepository.findByName("ricardo");
		employee.setName("ricardo");
		employee.setEmail("ricardo@something.com");
		employeeRepository.save(employee);
		System.out.println("Employee updated!");
	}

	@Test
	@Order(3)
	void testReadEmployee() {
		Employee employee = employeeRepository.findByName("ricardo");
		assert employee != null;
		System.out.println("Employee Read!");
	}

	@Test
	@Order(4)
	void testDeleteEmployee() {
		Employee employee = employeeRepository.findByName("ricardo");
		employeeRepository.delete(employee);
		Employee employee_deleted = employeeRepository.findByName("ricardo");
		assert employee_deleted == null;
		System.out.println("Employee deleted!");
	}
}
