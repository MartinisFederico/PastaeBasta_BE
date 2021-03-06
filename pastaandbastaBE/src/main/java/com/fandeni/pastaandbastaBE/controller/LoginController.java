package com.fandeni.pastaandbastaBE.controller;

import com.fandeni.pastaandbastaBE.DTO.AuthDTO;
import com.fandeni.pastaandbastaBE.DTO.PostVisualizationDTO;
import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.customException.UserAlreadyExistsException;
import com.fandeni.pastaandbastaBE.model.Post;
import com.fandeni.pastaandbastaBE.model.Utente;
import com.fandeni.pastaandbastaBE.service.PostService;
import com.fandeni.pastaandbastaBE.service.UtenteService;
import com.fandeni.pastaandbastaBE.utlis.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class LoginController{
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PostService postService;

    @PostMapping(path = "/login/byUsername", consumes = "application/json")
    public ResponseEntity<List<PostVisualizationDTO>> checkLogin(@RequestBody AuthDTO info){
        System.out.println("Performing login...");
        try {
            if(utenteService.checkLoginUsername(info.getUsername(), info.getPassword())){
                List<PostVisualizationDTO> dashboard = MyUtils.preparePostVisualization(postService.getDashboard(info.getUsername()));
                return (dashboard != null)
                        ? new ResponseEntity<>(dashboard, HttpStatus.OK)
                        : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

    @PostMapping("/login/byEmail")
    public ResponseEntity<List<PostVisualizationDTO>> checkLoginEmail(@RequestBody AuthDTO info){
        try {
            if(utenteService.checkLoginEmail(info.getUsername(), info.getPassword())){
                List<PostVisualizationDTO> dashboard = MyUtils.preparePostVisualization(postService.getDashboard(info.getUsername()));
                return (dashboard != null)
                        ? new ResponseEntity<>(dashboard, HttpStatus.OK)
                        : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

    @PostMapping("/registration")
    public ResponseEntity<Boolean> register(@RequestBody Utente utente){
        try{
            if(utenteService.checkRegistration(utente)){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(UserAlreadyExistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
