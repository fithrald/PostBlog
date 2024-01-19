package project.com.PostBlog.Post;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/post")
public class PostController {
//adss
    private final PostService postService;
//asdds
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/info")
    public String postsInfo(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "posts/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "posts/postEdit";
    }

    @PatchMapping("/{id}")
    public String editPost(@PathVariable("id") int id, @ModelAttribute Post post) {
        postService.update(id, post);
        return "redirect:/post/info";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") int id) {
        postService.delete(id);
        return "redirect:/post/info";
    }

    @GetMapping("/new")
    public String postNew(@ModelAttribute Post post) {
        return "posts/newPost";
    }

    @PostMapping("/new")
    public String producing(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/post/info";
    }

}
