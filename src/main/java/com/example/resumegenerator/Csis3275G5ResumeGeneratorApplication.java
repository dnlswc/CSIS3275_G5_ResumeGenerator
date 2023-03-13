package com.example.resumegenerator;

import java.util.ArrayList;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.resumegenerator.model.Education;
import com.example.resumegenerator.model.Job;
import com.example.resumegenerator.model.MiscInfo;
import com.example.resumegenerator.model.PersonalInfo;
import com.example.resumegenerator.model.Resume;
import com.example.resumegenerator.model.User;
import com.example.resumegenerator.repository.UserRepo;

@SpringBootApplication
public class Csis3275G5ResumeGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(Csis3275G5ResumeGeneratorApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepo userRepo) {
		return args -> {
			loadData(userRepo);
		};
	}
	
	private void loadData(UserRepo userRepo) {
		ArrayList<User> users = new ArrayList<>();
		users.add(new User("johnd@resgen.com", "123456"));
		users.add(new User("richardr@resgen.com", "654321"));
		users.add(new User("unknown@resgen.com", "abcdef"));
		
		Resume user1Resume1 = new Resume();
		user1Resume1.addPersonalInfo(new PersonalInfo("John Doe", "johnd@resgen.com", "778-123-1234"));
		user1Resume1.addEducation(new Education("Diploma in Office Administration", "Douglas College", "2017"));
		user1Resume1.addEducation(new Education("High School", "Eastside High School", "2015"));
		user1Resume1.addJob(new Job("ABC Company", "Tour Guide", "2022-04-05", "2023-03-12"));
		user1Resume1.addJob(new Job("DEF Airlines", "Flight Attendant", "2020-09-17", "2022-02-15"));
		user1Resume1.addJob(new Job("GHI Company", "Factory Worker", "2018-03-28", "2020-09-15"));
		user1Resume1.addMiscInfo(new MiscInfo("Spring Boot"));
		users.get(0).addResume(user1Resume1);
		
		Resume user1Resume2 = new Resume();
		user1Resume2.addPersonalInfo(new PersonalInfo("John Doe", "johnd@resgen.com", "778-123-1234"));
		user1Resume2.addEducation(new Education("Diploma in Engineering", "Douglas College", "2020"));
		user1Resume2.addJob(new Job("123 Limited", "Engineer", "2021-02-15", "2023-03-12"));
		user1Resume2.addMiscInfo(new MiscInfo("Leadership"));
		user1Resume2.addMiscInfo(new MiscInfo("Active Listening"));
		users.get(0).addResume(user1Resume2);
		
		Resume user2Resume1 = new Resume();
		user2Resume1.addPersonalInfo(new PersonalInfo("Richard Roe", "richardr@resgen.com", "778-321-4321"));
		user2Resume1.addEducation(new Education("Diploma in Graphic Design", "Vancouver Community College", "2021"));
		user2Resume1.addEducation(new Education("High School", "Oak Ridge High School", "2019"));
		user2Resume1.addJob(new Job("No Name Limited", "Landscape Gardener", "2022-01-15", "2023-02-14"));
		user2Resume1.addMiscInfo(new MiscInfo("Pest control"));
		user2Resume1.addMiscInfo(new MiscInfo("Hardscaping"));
		user2Resume1.addMiscInfo(new MiscInfo("Soil and irrigation management"));
		users.get(1).addResume(user2Resume1);
		
		userRepo.saveAll(users);
	}

}
