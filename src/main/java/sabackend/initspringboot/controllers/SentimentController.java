package sabackend.initspringboot.controllers;

import org.springframework.web.bind.annotation.*;
import sabackend.initspringboot.enums.TypeSentiment;
import sabackend.initspringboot.models.Sentiment;
import sabackend.initspringboot.services.SentimentService;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "sentiment")
public class SentimentController {

    private SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Sentiment sentiment){
        this.sentimentService.create(sentiment);
    }

    @GetMapping
    // REQUEST PARAM : url "localhost/api/sentiment?type=''/
    public @ResponseBody List<Sentiment> search(@RequestParam(required = false) TypeSentiment type){return this.sentimentService.search(type);}

    @ResponseStatus(ACCEPTED)
    @DeleteMapping(path = "{id}")
    // PATH VARIABLE url localhost/api/sentiment/{id}
    public void delete(@PathVariable int id){
        this.sentimentService.delete(id);
    }
}
