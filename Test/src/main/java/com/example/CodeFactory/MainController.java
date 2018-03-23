package com.example.CodeFactory;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.CodeFactory.News;
import com.example.CodeFactory.NewsRepository;
import com.example.CodeFactory.AppUserDAO;

import security.EncryptedPasswordUtils;


@Controller  
@EnableWebSecurity
@RequestMapping(path="/") 
public class MainController {
	@Autowired 
	private NewsRepository newsRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private AppUserDAO appUserDao;
	
	@Autowired 
	private NewsDAO newsDao;
	
    @GetMapping("/add")
    public String addingForm(Model model) {
    	News news = new News();
        model.addAttribute("news", news);
        return "addnews";
    }

	@PostMapping("/add") 
	public String addNews (@Valid News news, BindingResult bindingResult, @RequestParam String name,
								@RequestParam String text, @RequestParam String preview) {
		if (bindingResult.hasErrors()) {
            return "addnews";
        }
		News n = new News();
		n.setName(name);
		n.setText(text);
		n.setPreview(preview);
		newsRepository.save(n);
		return "redirect:/admin/allnews"; 
	}

	@GetMapping("/all")
	public String  getAllNews(Model model) {
		model.addAttribute("posts", newsRepository.findAll());
		return "news";
	}
	
	 @GetMapping("/") 
	 public String home(Model model) {
		 model.addAttribute("posts", newsRepository.findAll());
	 return "home"; 
	 }
	 
	 @GetMapping("/admin") 
	 public String admin() {
	 return "admin"; 
	 }
	
	 @GetMapping("/login")
	 public String loginPage() {

	    return "login";
	 }
	 
	 @GetMapping("/admin/adduser")
	 public String manage(Model model) {
		 AppUser user = new AppUser();
		 model.addAttribute("user", user);
		 List<AppRole> list = AppRoleDAO.getRoles();
		    model.addAttribute("roles", list);   
	    return "adduser";
	 }

	 @PostMapping("/admin/adduser") 
		public String addUser (@Valid @ModelAttribute("user") AppUser user, BindingResult bindingResult, Model model, @RequestParam String roleId, 
										@RequestParam String userName, @RequestParam String encrytedPassword) {
			if (bindingResult.hasErrors()) {
				List<AppRole> list = AppRoleDAO.getRoles();
			    model.addAttribute("roles", list);
	            return "adduser";
	        }
			AppUser users = new AppUser();
			users.setRoleId(Long.parseLong(roleId));
			users.setUserName(userName);
			users.setEncrytedPassword(EncryptedPasswordUtils.encryptePassword(encrytedPassword));
			userRepository.save(user);
			return "redirect:/admin/adduser"; 
		}
			
	 @GetMapping("/403")
	    public String accessDenied(Model model, Principal principal) {
	        if (principal != null) {
	            String message = "Hi " + principal.getName() //
	                    + "<br> You do not have permission to access this page!";
	            model.addAttribute("message", message);
	        }
	 	        return "403Page";
	    }
	 @GetMapping("/logoutSuccessful")
		 public String logout() {
		 	return "logoutSuccessfulPage";
	 	}
	 
	 @GetMapping("/updateUser/{userId}")
	 public String update(Model model, @PathVariable("userId") String userId) {
		 model.addAttribute("user", userRepository.findOne(Long.parseLong(userId)));
		 List<AppRole> list = AppRoleDAO.getRoles();
		    model.addAttribute("roles", list);
		 return "userupdate";
	 }
	 
	 @PostMapping("/updateUser/{userId}") 
		public RedirectView editUser (@PathVariable("userId") String userId, @RequestParam long roleId, @RequestParam String userName, @RequestParam String encrytedPassword) {	 
			RedirectView view = new RedirectView();
			view.setUrl("/admin/allusers");
			appUserDao.updateUser(Long.parseLong(userId), roleId, userName, EncryptedPasswordUtils.encryptePassword(encrytedPassword));
			return view; 
		}
	 
		@GetMapping("/admin/allusers")
		public String getAllUsers(Model model) {
			model.addAttribute("users", userRepository.findAll());
			return "users";	 
		}

		@GetMapping("/deleteUser/{userId}")
		public RedirectView  removeUser(@PathVariable("userId") String userId) {
			RedirectView view = new RedirectView();
			view.setUrl("/admin/allusers");
			userRepository.delete(Long.parseLong(userId));
			return view; 
		}
		
		@GetMapping("/admin/allnews")
		public String getNews(Model model) {
			model.addAttribute("posts", newsRepository.findAll());
			return "adminnews";	 
		}
		
		@GetMapping("/deleteNews/{id}")
		public RedirectView  removeNews(@PathVariable("id") String id) {
			RedirectView view = new RedirectView();
			view.setUrl("/admin/allnews");
			newsRepository.delete(Long.parseLong(id));
			return view; 
		}
		
		@GetMapping("/editNews/{id}")
		public String  updateNews(Model model, @PathVariable("id") String id) {
			model.addAttribute("posts", newsRepository.findOne(Long.parseLong(id)));
			return "newsupdate"; 
		}
		
		@PostMapping("/editNews/{id}")
		public RedirectView  updateNews(@PathVariable("id") String id, @RequestParam String name, @RequestParam String text, @RequestParam String preview) {
			RedirectView view = new RedirectView();
			view.setUrl("/admin/allnews");
			newsDao.editNews(Long.parseLong(id), name, text, preview);
			return view; 
		}
}
