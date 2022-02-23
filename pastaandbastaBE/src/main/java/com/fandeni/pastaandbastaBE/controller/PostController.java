package com.fandeni.pastaandbastaBE.controller;

import com.fandeni.pastaandbastaBE.DTO.PostDTO;
import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.repository.UtenteCrudRepository;
import com.fandeni.pastaandbastaBE.service.PostService;
import com.fandeni.pastaandbastaBE.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class PostController {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PostService postService;

    @PostMapping("/addPost")
    public ResponseEntity<Boolean> addPost(@RequestBody PostDTO post){
        try{
            if(post.getUser().getUsername().matches("^(.+)@(.+)$")){
                if(!utenteService.checkLoginUsername(post.getUser().getUsername(), post.getUser().getPassword())){
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }else{
                if(!utenteService.checkLoginEmail(post.getUser().getUsername(), post.getUser().getPassword())){
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }
            postService.addPost(post);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }catch(NoUserFoundExceptioin ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
