package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IPostService;
import service.impl.PostServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("post/list");
        modelAndView.addObject("posts", postService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate() {
        ModelAndView modelAndView = new ModelAndView("post/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createPost(Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        modelAndView.addObject("post", postService.save(post));
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView formEdit(@PathVariable int id) {
        Post post = postService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("post/edit");
        modelAndView.addObject("posts", post);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deletePost(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.remove(id);
        return modelAndView;
    }
    @GetMapping ("/search")
    public ModelAndView findName(@RequestParam String input){
        ModelAndView modelAndView = new ModelAndView("post/search");
        Iterable<Post> postList = postService.findAllByTittleContaining(input);
        modelAndView.addObject("posts",postList);
        return modelAndView;
    }
    @GetMapping("/top4")
    public ModelAndView top4(){
        ModelAndView modelAndView = new ModelAndView("post/top4");
        modelAndView.addObject("posts",postService.getTop4New());
        return modelAndView;
    }
    @GetMapping("views/page")
    public String Pagination(Model model, @RequestParam("p")Optional<Integer> p){
        PageRequest pageable = PageRequest.of(p.orElse(0),5);
        Page<Post> page = postService.findAll(pageable);
        model.addAttribute("posts" , page);
        return "";
    }
    @GetMapping("/title-create-at")
    public ModelAndView searchTittleAndCreateAt(@RequestParam String title, @RequestParam String begin ,@RequestParam String end, @PageableDefault(value = 5) Pageable pageable) {
        return new ModelAndView("/post/search", "posts", postService.findByTittleAndCreateAt(title, begin,end, pageable));
    }
}
