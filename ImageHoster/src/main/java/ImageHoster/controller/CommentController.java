package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //This controller is called when the user tries to add comment by clicking on comment
    //and the request pattern will be .../comments
    //this will reference the image against which it is commented, user who commented and saves to comments table
    @RequestMapping("/image/{imageId}/{imageTitle}/comments")
    public String addComment(@RequestParam("comment") String commentDesc, @PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle,HttpSession session) {
        Comment newComment = new Comment();
        Image image = imageService.getImage(imageId);
        User userCommented = (User) session.getAttribute("loggeduser");
        LocalDate date = LocalDate.now();

        newComment.setText(commentDesc);
        newComment.setCreatedDate(date);
        newComment.setImage(image);
        newComment.setUser(userCommented);

        commentService.addComment(newComment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
