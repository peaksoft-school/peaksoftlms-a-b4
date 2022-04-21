package kg.peaksoft.peaksoftlmsab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PeaksoftlmsAB4Application {

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Peaksoftlms-A application!!!<h1/>";
    }

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsAB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-A!");
    }
}
