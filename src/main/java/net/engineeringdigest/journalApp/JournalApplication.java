package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);


//		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
//		ConfigurableEnvironment environment = context.getEnvironment();
//		System.out.println(environment.getActiveProfiles()[0]);

        // tell which profile is running
    }

    @Bean
    public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }



}

//PlatformTransactionManager
// MongoTransactionManager

//User :
//userName : ram
//password: ram


//Admin: ek admin jaruri hai phele ka tabhi dusra create hoga like first admin created by editing the mongo db manually adding ADMIN in roles
//1.
//userName : vipul
//password:  vipul

//2.
//"userName":"chetan",
//"password":"chetan"


