package com.example.rest.controller;

import com.example.rest.domaine.ArticleDTO;
import com.example.rest.service.IService;
import com.example.rest.service.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final IService service;

    @Autowired
    public ArticleController(IService service){
        this.service=service;
    }

    @GetMapping(value="/all" , produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public List<ArticleDTO>getAll(){
        return service.getAll();
    }


    @GetMapping(value="/id/{id}")
    public ResponseEntity<Object>getArticleById(@PathVariable(value="id")Long id){
        ArticleDTO articleFound = service.getById(id);
        if(articleFound == null)
            return new ResponseEntity<>("Article with id=" + id + "doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(articleFound,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getArticleByUsingParam(@RequestParam(value="id") Long id){
        ArticleDTO articleFound = service.getById(id);
        if(articleFound == null)
            return new ResponseEntity<>("Article with id="+ id + "doesn't exist",HttpStatus.OK);
        return new ResponseEntity<>(articleFound,HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createArticle(@Valid @RequestBody ArticleDTO dto){
        service.create(dto);
        return new ResponseEntity<>("Article is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value="update/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable(name="id") Long id, @RequestBody ArticleDTO dto){
        ArticleDTO articleFound = service.getById(id);
        if(articleFound == null)
            return new ResponseEntity<>("article with id="+ id +"doesn't exist",HttpStatus.OK);
        service.update(id,dto);
        return new ResponseEntity<>("Article is updated successfully",HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable(name="id")Long id){
        ArticleDTO articleFound =service.getById(id);
        if(articleFound == null)
            throw new BusinessException("Article with id=" + id + " doesn't exist");
        service.deleteById(id);
        return new ResponseEntity<>("Article is deleted successfully", HttpStatus.OK);
    }

}
