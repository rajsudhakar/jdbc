package com.laptopapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.laptop.model.Laptop;
import com.laptopapp.service.ILaptopService;


@SpringBootApplication
public class ComLaptopappBasicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ComLaptopappBasicApplication.class, args);
	}
	@Autowired
    ILaptopService laptopService ;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//laptopService.addLaptop(new Laptop("thoshiba", "netbook", 656576755, 26677.0));
		//laptopService.updateLaptop(5, 10000);
		//laptopService.deleteLaptop(656576755);
	//	System.out.println(laptopService.getById(5));
		//System.out.println(laptopService.getByPrice(26677));
		//laptopService.getByType("netbook").forEach(System.out::println);
		laptopService.getAll().forEach(System.out::println);
	}
}
