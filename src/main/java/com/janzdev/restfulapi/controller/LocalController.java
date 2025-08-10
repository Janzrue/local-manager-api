package com.janzdev.restfulapi.controller;

import com.janzdev.restfulapi.entity.Local;
import com.janzdev.restfulapi.error.LocalNotFoundException;
import com.janzdev.restfulapi.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locals")
public class LocalController {

    @Autowired
    LocalService localService;

    @GetMapping
    @PreAuthorize("hasAnyRole('READER', 'MANAGER', 'ADMIN')")
    public List<Local> findAllLocal(){
        return localService.findAllLocals();
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('READER', 'MANAGER', 'ADMIN')")
    public Local findLocalById(@PathVariable Long id) throws LocalNotFoundException {
        return localService.findLocalById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('READER', 'MANAGER', 'ADMIN')")
    public Optional<Local> findByName(@PathVariable String name){
        return localService.findByName(name);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public Local saveLocal(@Valid @RequestBody Local local){
        return localService.saveLocal(local);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public Local updateLocal(@PathVariable long id, @RequestBody Local local){
        return localService.updateLocal(id, local);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteLocal(@PathVariable long id){
        localService.deleteLocal(id);
        return "Successfully deleted";
    }

//    @GetMapping("jpql/{name}")
//    @PreAuthorize("hasAnyRole('READER', 'MANAGER', 'ADMIN')")
//    public Optional<Local> findLocalByNameWithJPQL(@PathVariable String name){
//        return localService.findLocalByNameWithJPQL(name);
//    }
//
//    @GetMapping("/findByNameIgnoreCase/{name}")
//    @PreAuthorize("hasAnyRole('READER', 'MANAGER', 'ADMIN')")
//    public Optional<Local> findByNameIgnoreCase(@PathVariable String name){
//        return localService.findByNameIgnoreCase(name);
//    }
}
