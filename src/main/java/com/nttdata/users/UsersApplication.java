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
	CommandLineRunner run(AdminService adminService, RoleService roleService, AuthenticationService authService){
		return args -> {
			log.info("generando admins dummy...");

			// crea roles desde enum
			//RoleEntity roleAdmin = roleService.saveRole(RoleEntity.builder().name(RoleEnum.ADMIN).build());
			//RoleEntity roleUser = roleService.saveRole(RoleEntity.builder().name(RoleEnum.USER).build());
			roleService.saveRole(RoleEntity.builder().name(RoleEnum.ADMIN).build());
			roleService.saveRole(RoleEntity.builder().name(RoleEnum.USER).build());

			// crea admins


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

			log.info("admins dummy creados");
		};
	}
/*
	private void generateAdmin(AdminService adminService, String name, String pass, String mail, RoleEntity role) {
		adminService.saveAdmin(AdminEntity.builder()
				.name(name)
				.password(pass)
				.email(mail)
				.role(role)
				.build());
	}

 */
}
