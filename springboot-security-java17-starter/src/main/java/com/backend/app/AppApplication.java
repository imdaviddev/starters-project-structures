package com.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backend.app.model.Permission;
import com.backend.app.model.Role;
import com.backend.app.model.RoleEnum;
import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			/** Crear permisos */
			Permission createPermission = Permission.builder()
				.name("CREATE")
				.build();

			Permission readPermission = Permission.builder()
				.name("READ")
				.build();

			Permission updatePermission = Permission.builder()
				.name("UPDATE")
				.build();

			Permission deletePermission = Permission.builder()
				.name("DELETE")
				.build();

			Permission refactorPermission = Permission.builder()
				.name("REFACTOR")
				.build();

			/** Crear roles */
			Role roleAdmin = Role.builder()
				.roleEmun(RoleEnum.ADMIN)
				.permissionList(Set.of(readPermission, createPermission, updatePermission, deletePermission))
				.build();

			Role roleUser = Role.builder()
				.roleEmun(RoleEnum.USER)
				.permissionList(Set.of(readPermission, createPermission))
				.build();

			Role roleInvited = Role.builder()
				.roleEmun(RoleEnum.INVITED)
				.permissionList(Set.of(readPermission))
				.build();	
			
			Role roleDeveloper = Role.builder()
				.roleEmun(RoleEnum.DEVELOPER)
				.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
				.build();

			/** Crear usuarios */
			User admin = User.builder()
				.username("admin")
				.password("1234@@1234")
				.isEnabled(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.roles(Set.of(roleAdmin))
				.build();
				
			User developer = User.builder()
				.username("dev")
				.password("1234")
				.isEnabled(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.roles(Set.of(roleDeveloper))
				.build();

							
			User JORGE = User.builder()
				.username("JorgeElMasCapo")
				.password("contrasenia")
				.isEnabled(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.roles(Set.of(roleUser))
				.build();

			userRepository.saveAll(List.of(admin, developer, JORGE));
		};

	}

}
