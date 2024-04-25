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
			log.info("generando admin dummy...");
			createRoles(roleService);
			createSuperAdministrator(adminService, roleService, passwordEncoder);
			log.info("admin dummy creado.");
		};
	}

	private void createRoles(RoleService roleService) {
		roleService.saveRole(RoleEntity.builder().name(RoleEnum.SUPER_ADMIN).build());
		roleService.saveRole(RoleEntity.builder().name(RoleEnum.ADMIN).build());
		roleService.saveRole(RoleEntity.builder().name(RoleEnum.USER).build());
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
