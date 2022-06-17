package controller;

import com.google.protobuf.Internal;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.IPostService;

import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    IPostService iPostService;

    @GetMapping
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("post" , iPostService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("post" , iPostService.findAll());
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(Post post , RedirectAttributes redirect){
        iPostService.save(post);
//        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        redirect.addFlashAttribute("success", "Save customer successfully!");
        return "redirect:/post";
    }

    @GetMapping("/top-4")
    public ResponseEntity<Iterable<Post>> getTop4(){
        Iterable<Post> post = iPostService.findTop4Likes();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        post.setCreateAt(date);
        post.setLikes(0);
        iPostService.save(post);
        return new ResponseEntity(post, HttpStatus.OK);
    }


}
