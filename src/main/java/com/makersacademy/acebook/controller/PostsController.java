package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.CurrentUser;
import com.makersacademy.acebook.model.LikesHandler;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.PostList;
import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    LikesRepository likesRepository;
    PostList postArrayList = new PostList();
    CurrentUser currentUser = new CurrentUser();

    @GetMapping("/posts")
    public String index(Model model) {
        postArrayList.setList(repository.findAll());
        model.addAttribute("posts", postArrayList.postArrayList);
        model.addAttribute("post", new Post());
        model.addAttribute("showLogout", true);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        currentUser.setUsername();
        post.populate(post.getContent(), LocalDateTime.now(), currentUser.getUsername(), 0);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/likes")
    public RedirectView likes(HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        LikesHandler likesHandler = new LikesHandler(repository, likesRepository, new CurrentUser());
        if (!likesHandler.liked(request,redirect)) {
            redirect.addFlashAttribute("User is Unable to like this Post");
        }
        return new RedirectView("/posts");
    }
}
