package com.janzdev.restfulapi.controller;

import com.janzdev.restfulapi.entity.Local;
import com.janzdev.restfulapi.error.LocalNotFoundException;
import com.janzdev.restfulapi.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocalController {

    @Autowired
    LocalService localService;

    @GetMapping("/findAllLocals")
    List<Local> findAllLocal(){
        return localService.findAllLocals();
    }

    @PostMapping("/saveLocal")
    Local saveLocal(@Valid @RequestBody Local local){
        return localService.saveLocal(local);
    }

    @PutMapping("/updateLocal/{id}")
    Local updateLocal(@PathVariable long id, @RequestBody Local local){
        return localService.updateLocal(id, local);
    }

    @DeleteMapping("/deleteLocal/{id}")
    String deleteLocal(@PathVariable long id){
        localService.deleteLocal(id);
        return "Successfully deleted";
    }

    @GetMapping("/findLocalByNameWithJPQL/{name}")
    Optional<Local> findLocalByNameWithJPQL(@PathVariable String name){
        return localService.findLocalByNameWithJPQL(name);
    }

    @GetMapping("/findByName/{name}")
    Optional<Local> findByName(@PathVariable String name){
        return localService.findByName(name);
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    Optional<Local> findByNameIgnoreCase(@PathVariable String name){
        return localService.findByNameIgnoreCase(name);
    }

    @GetMapping("/findLocalById/{id}")
    Local findLocalById(@PathVariable Long id) throws LocalNotFoundException {
        return localService.findLocalById(id);
    }

}
