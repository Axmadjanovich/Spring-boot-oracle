package gc.springframework.controllers;

import gc.springframework.commands.AbonentForm;
import gc.springframework.converters.AbonentToAbonentForm;
import gc.springframework.domain.Abonentlar;
import gc.springframework.services.AbonentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AbonentController {

    private AbonentService abonentService;
    private AbonentToAbonentForm abonentToAbonentForm;
    private final Logger log = LoggerFactory.getLogger(TolovController.class); //logga chiqarish uchun


    @Autowired
    public void setAbonentToAbonentForm(AbonentToAbonentForm abonentToAbonentForm) {
        this.abonentToAbonentForm = abonentToAbonentForm;
    }

    @Autowired
    public void setAbonentService(AbonentService abonentService){
        this.abonentService = abonentService;
    }


    @RequestMapping("/al")
    public String redirToList(){
        return "redirect:/abonent/list";
    }

    @RequestMapping({"/abonent/list", "/abonent"})
    public String listTolovs(Model model){
        //abonent service dan kelgan listni attribut sifatida thymeleafga uzatish
        model.addAttribute("abonentlar", abonentService.listAll());
        return "abonent/list";
    }

    @RequestMapping("abonent/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        //id orqali abonent bazadan topiladi, uni abonentFormga convert qilinib thymeleafga uzatiladi
        Abonentlar abonent = abonentService.getById(Long.valueOf(id));
        AbonentForm abonentForm = abonentToAbonentForm.convert(abonent);
        model.addAttribute("abonentForm", abonentForm);
        //error attributni qoshish
        model.addAttribute("error", true);
        //edit attributi true - ma'lumotni o'zgartirish uchun thymeleafda o'zgarish
        model.addAttribute("editory", true);
        return "abonent/abonentform";
    }

    @RequestMapping("/abonent/new")
    public String newTolov(Model model){
        model.addAttribute("abonentForm", new AbonentForm());
        //error va editory default holatda
        model.addAttribute("error", true);
        model.addAttribute("editory", false);
        return "abonent/abonentform";
    }

    @PostMapping("/abonent")
    public String saveOrUpdateTolov(@Valid AbonentForm abonentForm,
                                    BindingResult bindingResult,
                                    Model model){

        //formadagi ma'lumotlar logda chiqadi
        log.info(abonentForm.toString());
        if(bindingResult.hasErrors()){
            //formaga qaytganda attribut null qaytarmaslik uchun default qo'shish
            model.addAttribute("error", true);
            model.addAttribute("editory", false);
            return "abonent/abonentform";
        }
        boolean mavjud;
        //bazadan formadan kelgan inn ni qidirish va agar mavjud bo'lsa xatolik haqida xabar berish
        try {
         abonentService.findByInn(abonentForm.getInn());
         mavjud = true;
        }catch (Exception e){
            mavjud= false;
        }
        if (mavjud){
            log.info("Bunday INN mavjud");
            //xatolik haqida xabar
            model.addAttribute("error", false);
            model.addAttribute("editory", false);
            return "abonent/abonentform";
        }
        //xatolik bo'lmasa abonentni bazaga yozish
        abonentService.saveOrUpdateAbonentForm(abonentForm);
        return "redirect:/abonent/list";
    }

    //mavjud abonentning ma'lumotlarini o'zgartirish
    @PutMapping("/abonent")
    public String update(@Valid AbonentForm abonentForm,
                         BindingResult bindingResult,
                         Model model){
        model.addAttribute("error", true);
        model.addAttribute("editory", false);
        if (bindingResult.hasErrors()){
            return "redirect:/abonent/abonentform";
        }
        abonentService.saveOrUpdateAbonentForm(abonentForm);
        return "redirect:/abonent/list";
    }

    @RequestMapping("/abonent/delete/{id}")
    public String delete(@PathVariable String id){
        abonentService.delete(Long.valueOf(id));
        return "redirect:/abonent/list";
    }
}
