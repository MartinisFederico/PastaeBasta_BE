package com.fandeni.pastaandbastaBE.utlis;

import com.fandeni.pastaandbastaBE.DTO.AuthDTO;
import com.fandeni.pastaandbastaBE.DTO.PostVisualizationDTO;
import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.model.Post;
import com.fandeni.pastaandbastaBE.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUtils {

    @Autowired
    private static UtenteService utenteService;

    public static boolean checkAuth(AuthDTO user){
        if(user.getUsername().matches("^(.+)@(.+)$")){
            try {
                return utenteService.checkLoginUsername(user.getUsername(), user.getPassword());
            } catch (NoUserFoundExceptioin e) {
                e.printStackTrace();
                return false;
            }
        }else{
            try {
                return utenteService.checkLoginEmail(user.getUsername(), user.getPassword());
            } catch (NoUserFoundExceptioin e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static List<PostVisualizationDTO> preparePostVisualization(List<Post> dashboard){
        if(dashboard != null && !dashboard.isEmpty()){
            List<PostVisualizationDTO> dashboardDTO = new ArrayList<>();
            for(Post p: dashboard){
                dashboardDTO.add(new PostVisualizationDTO(p));
            }
            return dashboardDTO;
        }else
            return null;
    }

    public static PostVisualizationDTO preparePostVisualization(Post p){
        if(p != null){
            return new PostVisualizationDTO(p);
        }else
            return null;
    }
}
