package com.justdo.glue.sticker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableFeignClients
//@EnableDiscoveryClient
public class StickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StickerApplication.class, args);
	}

}
