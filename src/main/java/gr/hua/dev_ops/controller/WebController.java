package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.entity.Broker;
import gr.hua.dev_ops.entity.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/listings")
    public String listings() {
        return "listings";
    }

    @GetMapping("/listings/add")
    public String addListing() {
        return "addListing";
    }

    @GetMapping("/listings/broker/{brokerId}")
    public ModelAndView getBrokerListings(@PathVariable Long brokerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("brokerListings");
        modelAndView.addObject("brokerId", brokerId);  // Pass the brokerId to the view
        return modelAndView;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("broker", new Broker());
        return "register";
    }

    @GetMapping("/admin")
    public String showAdminLandingPage() {
        return "admin";
    }

    @GetMapping("/admin/manage/users")
    public String showManageUsers() {
        return "adminManageUsers";
    }

    @GetMapping("/admin/update/user")
    public String showUpdateUserForm() {
        return "updateUser";
    }

    @GetMapping("/admin/manage/listings")
    public String showManageListings() {
        return "adminManageListings";
    }

    @GetMapping("/admin/update/listing")
    public String showUpdateListingForm() {
        return "updateListing";
    }

    @GetMapping("/broker/manage/listings")
    public String showManageListingsBroker() {
        return "brokerManageListings";
    }

    @GetMapping("/admin/add/listing")
    public String showAdminAddListingForm() {
        return "adminAddListing";
    }

    @GetMapping("/broker/unassigned")
    public String showUnassignedListings() {
        return "unassignedListings";
    }
}
