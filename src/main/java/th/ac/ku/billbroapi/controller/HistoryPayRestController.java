package th.ac.ku.billbroapi.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.billbroapi.data.HistoryPayRepository;
import th.ac.ku.billbroapi.model.HistoryPay;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/history-pay")
public class HistoryPayRestController {
    private HistoryPayRepository repository;

    public HistoryPayRestController(HistoryPayRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<HistoryPay> getAll(){
        return repository.findAll();
    }

    @GetMapping("/crewmate/{cmId}")
    public List<HistoryPay> getAllHistoryPayID(@PathVariable int cmId){
        return repository.findByCmId(cmId);
    }

    @GetMapping("/{id}")
    public HistoryPay getOne(@PathVariable int id){
        try{
            return repository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @PostMapping
    public HistoryPay create(@RequestBody HistoryPay historyPay){
        System.out.println(historyPay);
        repository.save(historyPay);
        return historyPay;
    }

    @PutMapping("/{id}")
    public HistoryPay update(@PathVariable int id, @RequestBody HistoryPay historyPay){
        HistoryPay record = repository.findById(id).get();
        record.setHp_dept(historyPay.getHp_dept());
        record.setHp_payed(historyPay.getHp_payed());
        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public HistoryPay delete(@PathVariable int id){
        HistoryPay record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }
}
