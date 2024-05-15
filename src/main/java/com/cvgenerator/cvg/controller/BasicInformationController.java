package com.cvgenerator.cvg.controller;


import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.enums.Gender;
import com.cvgenerator.cvg.service.BasicInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("basic-info")
public class BasicInformationController {
    private final BasicInformationService basicInformationService;

    public BasicInformationController(BasicInformationService basicInformationService) {
        this.basicInformationService = basicInformationService;
    }

    @GetMapping
    public String openBasicInformationListPage() {
        return "basicinformation/list_page";
    }

    @GetMapping("/open-form")
    public String openBasicInformationFormPage(Model model) {
        if (!model.containsAttribute("basicInformationDto")) {
            model.addAttribute("basicInformationDto", new BasicInformationDto());
        }
        model.addAttribute("genderList", Gender.getGenderList());
        return "basicinformation/form_page";
    }

    @PostMapping("/save")
    public String saveBasicInfo(@ModelAttribute BasicInformationDto basicInformationDto,
                                RedirectAttributes redirectAttributes) {
        basicInformationDto = basicInformationService.save(basicInformationDto);
        String message = "";
        if (basicInformationDto != null) {
            message = "Saved successfully";
        } else {
            message = "Save failed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("basicInformationDto", basicInformationDto);
        return "redirect:/basic-info/open-form";
    }

}
