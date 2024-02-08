package com.packt.cardatabase;

import java.time.LocalDate;
import java.util.Arrays;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final PetRepository prepository;


	public CardatabaseApplication(CarRepository repository, OwnerRepository orepository, PetRepository prepository) {
		this.repository = repository;
		this.orepository = orepository;
		this.prepository = prepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add owner objects and save these to db (10 owner entries)
		Owner owner1 = new Owner("John" , "Johnson");
		Owner owner2 = new Owner("Mary" , "Robinson");
		Owner owner3 = new Owner("Fred" , "Warner");
		Owner owner4 = new Owner("Jeremy" , "Zahrndt");
		Owner owner5 = new Owner("Nate" , "Gebru");
		Owner owner6 = new Owner("Nancy" , "Karen");
		Owner owner7 = new Owner("Marley" , "Plum");
		Owner owner8 = new Owner("Mike" , "Williams");
		Owner owner9 = new Owner("Gary" , "Wright");
		Owner owner10 = new Owner("John" , "Johnson");
		orepository.saveAll(Arrays.asList(owner1, owner2, owner3, owner4, owner5, owner6, owner7, owner8, owner9, owner10));

		// 10 car entries
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));
		repository.save(new Car("Audi", "A4", "Grey", "AAA-0000", 2004, 5500, owner4));
		repository.save(new Car("Buick", "Verando", "Blue", "BBB-1111", 2012, 10000, owner4));
		repository.save(new Car("Mazda", "3", "Purple", "CCC-2222", 2002, 3000, owner6));
		repository.save(new Car("Audi", "", "R8", "DDD-3333", 2022, 60000, owner7));
		repository.save(new Car("BMW", "I8", "Green", "EEE-4444", 2010, 40000, owner7));
		repository.save(new Car("Toyota", "Corolla", "Red", "FFF-5555", 1999, 4000, owner10));
		repository.save(new Car("Nissan", "350z", "Pink", "GGG-6666", 2021, 15000, owner9));

		// 10 pet entries
		prepository.save(new Pet("Bear", "German Shepard", LocalDate.parse("2020-02-10"), owner4));
		prepository.save(new Pet("Marley", "Cocker Spaniel", LocalDate.parse("2010-01-20"), owner4));
		prepository.save(new Pet("Shasta", "American Eskimo", LocalDate.parse("2008-08-08"), owner4));
		prepository.save(new Pet("Smokey", "Cocker Spaniel", LocalDate.parse("1999-04-08"), owner4));
		prepository.save(new Pet("Shasta", "Cocker Spaniel", LocalDate.parse("2000-02-18"), owner4));
		prepository.save(new Pet("Ralph", "Fish", LocalDate.parse("2015-05-15"), owner5));
		prepository.save(new Pet("Ruby", "Beagle", LocalDate.parse("2018-08-20"), owner6));
		prepository.save(new Pet("Daisy", "Bearded Dragon", LocalDate.parse("2016-12-25"), owner7));
		prepository.save(new Pet("Ricky", "Eskimo", LocalDate.parse("2005-07-25"), owner8));
		prepository.save(new Pet("Shadow", "Lab", LocalDate.parse("2011-11-11"), owner9));

		// Fetch all cars and log to console
		for (Car car : repository.findAll()) {
			logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
		}

		for (Pet pet : prepository.findAll()) {
			logger.info("name: {}, species: {}, dob: {}", pet.getName(), pet.getSpecies(), pet.getDob());
		}
	}
}
