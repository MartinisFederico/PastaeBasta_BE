package com.fandeni.pastaandbastaBE.controller;

import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.model.Post;
import com.fandeni.pastaandbastaBE.service.PostService;
import com.fandeni.pastaandbastaBE.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class LoginController{
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<List<Post>> checkLogin(@RequestParam String username, @RequestParam String password){
        try {
            if(utenteService.checkLoginUsername(username, password)){
                List<Post> dashboard = postService.getDashboard();
                if(!dashboard.isEmpty())
                    return new ResponseEntity<>(postService.getDashboard() , HttpStatus.OK);
                else
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }else
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (NoUserFoundExceptioin e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
