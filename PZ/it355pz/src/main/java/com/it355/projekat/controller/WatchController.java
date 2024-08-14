package com.it355.projekat.controller;

import com.it355.projekat.entity.Company;
import com.it355.projekat.entity.Watch;
import com.it355.projekat.entity.enums.WatchType;
import com.it355.projekat.service.CompanyService;
import com.it355.projekat.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/watches")
public class WatchController {
    private final WatchService watchService;
    private final CompanyService companyService;

    // PAGES START
    @GetMapping("/add")
    public String getAddWatchPage(Model model) {
        model.addAllAttributes(Map.of(
                "watch", new Watch(),
                "companies", companyService.findAll(),
                "types", WatchType.values()
        ));
        return "watch/save-watch";
    }

    @GetMapping("/update")
    public String getUpdateWatchPage(@RequestParam(value = "watchId", required = false) Integer watchId, Model model) {
        Watch watch = watchId != null ? watchService.findById(watchId) : new Watch();

        model.addAllAttributes(Map.of(
                "watch", watch,
                "companies", companyService.findAll(),
                "types", WatchType.values()
        ));

        return "watch/update-watch";
    }

    @GetMapping("")
    public String getAvailableWatches(Model model) {
        model.addAttribute("watches", watchService.findAll());
        return "watch/watches";
    }
    // PAGES END

    @PostMapping("")
    public String saveWatch(@Valid @ModelAttribute("watch") Watch watch,
                            @RequestParam("companyId") Integer companyId,
                            BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Watch data are invalid!");
        }

        Company company = companyService.findById(companyId);
        watch.setCompany(company);

        watchService.save(watch);
        return "redirect:/watches";
    }

    @PostMapping("/delete")
    public String deleteWatch(@RequestParam("id") Integer id) {
        watchService.delete(id);
        return "redirect:/watches";
    }

    @PostMapping("/update")
    public String updateWatch(@Valid @ModelAttribute("watch") Watch watch,
                              @RequestParam("watchId") Integer watchId,
                              @RequestParam("companyId") Integer companyId,
                              BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Watch data are invalid!");
        }
        watchService.updateWatch(watch, watchId, companyId);
        return "redirect:/watches/";
    }
}
