package hu.webuni.transport.luterdav;

import hu.webuni.transport.luterdav.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportApplication implements CommandLineRunner {
 
  @Autowired
  InitDbService initDbService;
  
  public static void main(String[] args) {
    SpringApplication.run(TransportApplication.class, args);
  }
  
  public void run(String... args) throws Exception {
//	  initDbService.init();
  }
}
