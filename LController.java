package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.locale.DisplayMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LandonController {

    @GetMapping("/welcome")

    public ResponseEntity displayMessage (@RequestParam("lang") String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        DisplayMessage displayMessage = new DisplayMessage(locale);
        return new ResponseEntity<String> (DisplayMessage.getDisplayMessage(), HttpStatus.OK);
    }
}
