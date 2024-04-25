package com.nttdata.users;

import com.nttdata.users.dto.AdminDTO;
import com.nttdata.users.entity.AdminEntity;
import com.nttdata.users.entity.RoleEntity;
import com.nttdata.users.enums.RoleEnum;
import com.nttdata.users.service.AdminService;
import com.nttdata.users.service.AuthenticationService;
import com.nttdata.users.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	/**
	 * Script for creation of dummy admins, in order to test the JWT integration
	 *
	 */
	@Bean
	CommandLineRunner run(AdminService adminService, RoleService roleService, PasswordEncoder passwordEncoder){
		return args -> {
			log.info("generando admins dummy...");

			// crea roles desde enum
			roleService.saveRole(RoleEntity.builder().name(RoleEnum.SUPER_ADMIN).build());
			roleService.saveRole(RoleEntity.builder().name(RoleEnum.ADMIN).build());
			roleService.saveRole(RoleEntity.builder().name(RoleEnum.USER).build());

			createSuperAdministrator(adminService, roleService, passwordEncoder);

			// crea admins
/*
			authService.signup(AdminDTO.builder()
					.name("Peter Parker")
					.password("spiderman")
					.email("spiderman@avengers.org")
					.role(RoleEnum.USER.name()).build());

			authService.signup(AdminDTO.builder()
					.name("Tony Stark")
					.password("ironman")
					.email("ronman@avengers.org")
					.role(RoleEnum.ADMIN.name()).build());
*/
			log.info("admins dummy creados");
		};
	}

	private void createSuperAdministrator(AdminService adminService, RoleService roleService, PasswordEncoder passwordEncoder) {
		AdminDTO superDto = AdminDTO.builder()
				.name("Super Admin")
				.email("super@admin.com")
				.password("123456")
				.build();

		Optional<RoleEntity> optRole = roleService.findByName(RoleEnum.SUPER_ADMIN);
		Optional<AdminEntity> optionalUser = adminService.findByEmail(superDto.getEmail());

		if (optionalUser.isPresent()) {
			throw new IllegalArgumentException("Super Admin already exists!");
		}

		adminService.saveAdmin(AdminEntity.builder()
				.name(superDto.getName())
				.email(superDto.getEmail())
				.password(passwordEncoder.encode(superDto.getPassword()))
				.role(optRole.get())
				.build());
	}
}
