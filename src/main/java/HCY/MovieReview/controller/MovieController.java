package HCY.MovieReview.controller;

import HCY.MovieReview.dto.MovieDTO;
import HCY.MovieReview.dto.PageRequestDTO;
import HCY.MovieReview.dto.PageResponseDTO;
import HCY.MovieReview.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movie/")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {
        log.info("register view...");
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("register post...");

        movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("msg", movieDTO.getId());

        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void getList(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        log.info("get movie list....");
        log.info(pageRequestDTO);

        PageResponseDTO<Object[], MovieDTO> result = movieService.list(pageRequestDTO);
        model.addAttribute("result", result);
    }

}
