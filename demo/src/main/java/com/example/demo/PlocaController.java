package com.example.demo;

import com.example.demo.PlocaDTO;
import com.example.demo.PlocaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/ploca")
public class PlocaController {

    @Autowired
    private PlocaService plocaService;

    @PostMapping
    public String save(@RequestBody PlocaDTO vO) {
        return plocaService.save(vO).toString();
    }

    @GetMapping
    public List<Ploca> getAll() {
        return plocaService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ploca> getById(@PathVariable  Integer id) {
        Ploca p =  plocaService.id(id);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
}
