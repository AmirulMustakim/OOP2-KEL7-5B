package lab.phb.mhswebapp.controller;

import lab.phb.mhswebapp.repo.MahasiswaRepo;
import lab.phb.mhswebapp.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

@Controller
public class WebController {
    
    @Autowired
    private MahasiswaRepo mhsRepo;
	
    @RequestMapping("/data-parkir-motor")
    public void dataparkirmotor(Model model) {
        model.addAttribute("dataparkirmotor", 
                mhsRepo.findAll());
    }

    @RequestMapping("/tambah-data")
    public void tambahData(
    	@ModelAttribute("mhs") Mahasiswa mhs,
    	BindingResult result) {}

    @RequestMapping(value = "/tambah-data",
    	method = RequestMethod.POST) 
    public String simpanDataBaru(
    		@ModelAttribute("mhs") Mahasiswa mhs,
    		BindingResult result) {
    	System.out.println(mhs.getId());
    	mhsRepo.save(mhs);

    	return "redirect:/data-parkir-motor";
    }

    @RequestMapping("/edit")
    public void getEditForm(
    		@RequestParam("id") String id, 
    		Model model) {
    	Mahasiswa result = mhsRepo.findOne(id);
    	model.addAttribute("mhs", result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String simpanEditData(
    		@ModelAttribute("mhs") Mahasiswa mhs,
    		BindingResult result) {
    	System.out.println("id : " + mhs.getId());
    	mhsRepo.save(mhs);
    	return "redirect:/data-parkir-motor";
    }
    
    @RequestMapping("/hapus")
    public String hapusData(@RequestParam("id") String id) {
        mhsRepo.delete(id);
        return "redirect:/data-parkir-motor";
    }
    
}
