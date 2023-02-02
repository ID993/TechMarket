package com.aegis.TechMarket;


import com.aegis.TechMarket.Services.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechMarketApplication implements CommandLineRunner{

	@Resource
    FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(TechMarketApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		//fileStorageService.deleteAll();
		fileStorageService.init();
	}
}
