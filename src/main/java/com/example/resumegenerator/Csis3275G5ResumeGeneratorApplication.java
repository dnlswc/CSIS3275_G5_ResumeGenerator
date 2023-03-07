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
	
	private void loadData(UserRepo userRepo) {
		ArrayList<User> users = new ArrayList<>();
		users.add(new User("johnd@resgen.com", "123456"));
		users.add(new User("richardr@resgen.com", "654321"));
		users.add(new User("unknown@resgen.com", "abcdef"));
		
		Resume resume1 = new Resume();
		resume1.addPersonalInfo(
				new PersonalInfo(
						"John", "Doe", "778-123-1234",
						"johnd@resgen.com", "Tour Guide",
						"Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean."
				)
		);
		resume1.addJob(new Job("Tour Guide", "ABC Company", "Vancouver, BC", "2022-04-05", "Present", "Guiding tours"));
		resume1.addJob(new Job("Flight Attendant", "DEF Airlines", "Richmond, BC", "2020-09-17", "2022-02-15", "Ensuring the safety, security, and comfort of airline passengers"));
		resume1.addJob(new Job("Factory Worker", "GHI Company", "Surrey, BC", "2018-03-28", "2020-09-15", "Operating equipment in the factory"));
		resume1.addEducation(new Education("Diploma in Office Administration", "Douglas College", "New Westminster, BC", "2015-09-01", "2017-08-30", "Learned office administration"));
		resume1.addEducation(new Education("High School", "Eastside High School", "New Westminster, BC", "2011-09-01", "2015-06-30", "Completed secondary education"));
		resume1.addMiscInfo(new MiscInfo("Spring Boot"));
		
		users.get(0).addResume(resume1);
		
		Resume resume2 = new Resume();
		resume2.addPersonalInfo(
				new PersonalInfo(
						"Richard", "Roe", "778-321-4321",
						"richardr@resgen.com", "Landscape Gardener",
						"A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine."
				)
		);
		resume2.addJob(new Job("Landscape Gardener", "No Name Limited", "Burnaby, BC", "2022-01-15", "2023-02-14", "Maintaining lawns"));
		resume2.addEducation(new Education("Diploma in Graphic Design", "Vancouver Community College", "Vancouver, BC", "2019-09-01", "2021-08-30", "Learned graphic design"));
		resume2.addEducation(new Education("High School", "Oak Ridge High School", "Vancouver, BC", "2015-09-01", "2019-06-30", "Completed secondary education"));
		resume2.addMiscInfo(new MiscInfo("Pest control"));
		resume2.addMiscInfo(new MiscInfo("Hardscaping"));
		resume2.addMiscInfo(new MiscInfo("Soil and irrigation management"));
		
		users.get(1).addResume(resume2);
		
		userRepo.saveAll(users);
	}
	
	@Bean
	ApplicationRunner init(UserRepo userRepo) {
		return args -> {
			loadData(userRepo);
		};
	}

}
