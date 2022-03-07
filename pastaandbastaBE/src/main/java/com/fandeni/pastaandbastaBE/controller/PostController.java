package com.fandeni.pastaandbastaBE.controller;

import com.fandeni.pastaandbastaBE.DTO.*;
import com.fandeni.pastaandbastaBE.customException.CategoriaNotFoundException;
import com.fandeni.pastaandbastaBE.customException.LikeException;
import com.fandeni.pastaandbastaBE.customException.PostNotFoundException;
import com.fandeni.pastaandbastaBE.customException.ReactionDoesntExistsException;
import com.fandeni.pastaandbastaBE.service.PostService;
import com.fandeni.pastaandbastaBE.service.UtenteService;
import com.fandeni.pastaandbastaBE.utlis.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class PostController {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PostService postService;

    @PostMapping(path="/addPost", consumes = "application/json")
    public ResponseEntity<Boolean> addPost(@RequestBody PostDTO post){
        try{
            if(!MyUtils.checkAuth(post.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            postService.addPost(post);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/profileDashboard", consumes = "application/json")
    public ResponseEntity<List<PostVisualizationDTO>> getProfileDashboard(@RequestBody SearchDTO searchParameter){
        try{
            if(!MyUtils.checkAuth(searchParameter.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            List<PostVisualizationDTO> dashboard = postService.searchProfile(searchParameter);
            return (dashboard == null || dashboard.isEmpty())
                    ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(dashboard, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/searchByCategoria", consumes = "application/json")
    public ResponseEntity<List<PostVisualizationDTO>> getPostsForCategoria(@RequestBody SearchDTO searchParameter){
        try{
            if(!MyUtils.checkAuth(searchParameter.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            List<PostVisualizationDTO> dashboard = postService.searchByCategoria(searchParameter.getCategorie());
            return (dashboard == null || dashboard.isEmpty())
                    ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(dashboard, HttpStatus.OK);
        }catch(CategoriaNotFoundException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/searchByInterval", consumes = "application/json")
    public ResponseEntity<List<PostVisualizationDTO>> getPostsInterval(@RequestBody SearchDTO searchParameter){
        try{
            if(!MyUtils.checkAuth(searchParameter.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            List<PostVisualizationDTO> dashboard = postService.searchByInterval(searchParameter.getStart(), searchParameter.getEnd());
            return (dashboard == null || dashboard.isEmpty())
                    ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(dashboard, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/searchByKeyword", consumes = "application/json")
    public ResponseEntity<List<PostVisualizationDTO>> getPostsKeyword(@RequestBody SearchDTO searchParameter){
        try{
            if(!MyUtils.checkAuth(searchParameter.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            List<PostVisualizationDTO> dashboard = postService.searchByKeyword(searchParameter.getCategorie());
            return (dashboard == null || dashboard.isEmpty())
                    ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(dashboard, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/reaction", consumes = "application/json")
    public ResponseEntity<Boolean> reactAPost(@RequestBody ReactionDTO reaction){
        try{
            if(!MyUtils.checkAuth(reaction.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            postService.react(reaction);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (ReactionDoesntExistsException | LikeException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/editPost", consumes = "application/json")
    public ResponseEntity<PostVisualizationDTO> editPost(@RequestBody PostUpdateDTO post){
        try{
            if(!MyUtils.checkAuth(post.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            PostVisualizationDTO postVis = postService.editPost(post.getPost());
            return new ResponseEntity<>(postVis, HttpStatus.OK);
        }catch (PostNotFoundException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "/deletePost", consumes = "application/json")
    public ResponseEntity<Boolean> deletePost(@RequestBody PostUpdateDTO post){
        try{
            if(!MyUtils.checkAuth(post.getUser()))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            postService.removePost(post.getPost());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (PostNotFoundException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
