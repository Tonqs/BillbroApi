package th.ac.ku.billbroapi.controller;


import org.springframework.web.bind.annotation.*;
import th.ac.ku.billbroapi.data.CrewmateRepository;
import th.ac.ku.billbroapi.model.Crewmate;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/crewmate")
public class CrewmateRestController {
    private CrewmateRepository repository;

    public CrewmateRestController(CrewmateRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Crewmate> getAll(){
        return repository.findAll();
    }

    @GetMapping("/task-party/{tId}")
    public List<Crewmate> getAllCrewmateID(@PathVariable int tId){
        return repository.findBytId(tId);
    }

    @GetMapping("/{id}")
    public Crewmate getOne(@PathVariable int id){
        //id of task-party
        try{
            return repository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @PostMapping
    public Crewmate create(@RequestBody Crewmate crewmate){
        repository.save(crewmate);
        return crewmate;
    }

    @PutMapping("/{id}")
    public Crewmate update(@PathVariable int id, @RequestBody Crewmate crewmate){
        Crewmate record = repository.findById(id).get();
        record.setCm_name(crewmate.getCm_name());
        record.setCm_email(crewmate.getCm_email());
        record.setCm_state(crewmate.getCm_state());
        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public Crewmate delete(@PathVariable int id){
        Crewmate record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }
}
