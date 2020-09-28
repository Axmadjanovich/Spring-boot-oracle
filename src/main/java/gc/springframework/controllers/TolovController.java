package gc.springframework.controllers;

import gc.springframework.commands.TolovForm;
import gc.springframework.converters.TolovToTolovForm;
import gc.springframework.domain.Abonentlar;
import gc.springframework.domain.Tolovlar;
import gc.springframework.services.AbonentService;
import gc.springframework.services.TolovService;
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
public class TolovController {
    private TolovService tolovService;
    private AbonentService abonentService;
    private TolovToTolovForm tolovToTolovForm;

    private final Logger log = LoggerFactory.getLogger(TolovController.class);

    @Autowired
    public void settolovToTolovForm(TolovToTolovForm tolovToTolovForm) {
        this.tolovToTolovForm = tolovToTolovForm;
    }

    @Autowired
    public void setTolovService(TolovService tolovService) {
        this.tolovService = tolovService;
    }

    @Autowired
    public void setAbonentService(AbonentService abonentService){
        this.abonentService = abonentService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/tolov/list";
    }

    @RequestMapping({"/tolov/list", "/tolov"})
    public String listTolovs(Model model){
        model.addAttribute("tolovlar", tolovService.listAll());
        return "tolov/list";
    }

    //tolov amalga oshganidan so'ng ma'lumotlani ko'rsatish
    @RequestMapping("/tolov/show/{id}")
    public String getTolov(@PathVariable String id, Model model){
        System.out.println("------------------------------------------"+id);
        model.addAttribute("tolov", tolovService.getById(Long.valueOf(id)));
        return "tolov/show";
    }

    @RequestMapping("tolov/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        //kelgan id orqali bazadagi to'lovni topish, uni toloFormga convert qilib thymeleafga uzatish
        Tolovlar tolov = tolovService.getById(Long.valueOf(id));
        TolovForm tolovForm = tolovToTolovForm.convert(tolov);
        model.addAttribute("tolovForm", tolovForm);
        model.addAttribute("error", true);
        //edit - true
        model.addAttribute("edit", true);
        return "tolov/tolovform";
    }

    @RequestMapping("/tolov/new")
    public String newTolov(Model model){
        //bo'sh tolovForm jo'natish
        model.addAttribute("tolovForm", new TolovForm());
        model.addAttribute("error", true);
        model.addAttribute("edit", false);
        return "tolov/tolovform";
    }

    @PutMapping("/tolov")
    public String saveOrUpdateTolov(@Valid TolovForm tolovForm,
                                    BindingResult bindingResult,
                                    Model model){

        log.info(tolovForm.toString());
        //xatolik bo'lsa qaytarish
        if(bindingResult.hasErrors()){
            model.addAttribute("error", true);
            model.addAttribute("edit", true);
            return "tolov/tolovform";
        }

        Tolovlar tolov = new Tolovlar();
        Abonentlar abonent = null;
        boolean bor = true;
        //bazadan kiritilgan formadagi inn tegishli bo'lgan abonent
        // bor yo'qligini tekshirish, yo'q bo'lsa qaytarish
        try {
            abonent = abonentService.findByInn(tolovForm.getInn());
        }catch (Exception e){
            System.out.println("topilmadi");
            //INN yo'qligi haqida xabar
            model.addAttribute("error",false);
            bor = false;
        }
        //INN bor bo'lsa bazaga to'lovni yozib qo'yish
        if (bor) {
            model.addAttribute("error", true);
            model.addAttribute("edit", false);
            tolov.setAbonentlar(abonent);
            tolov = tolovService.saveOrUpdateTolovForm(tolovForm);

            return "redirect:/tolov/show/" + tolov.getId();
        }
        else return "tolov/tolovform";
        }

        @PostMapping("/tolov")
        public String update(@Valid TolovForm tolovForm,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("edit", false);
            model.addAttribute("error", true);
            return "tolov/tolovform";
        }
        boolean mavjud;
        Abonentlar abonentlar = null;
        Tolovlar tolovlar = new Tolovlar();
        //yangilangan inn ni mavjudligini tekshirish
        try {
            abonentlar = abonentService.findByInn(tolovForm.getInn());
            mavjud = true;
        }catch (Exception e){
            mavjud = false;

            model.addAttribute("error", false);
            model.addAttribute("edit", false);
        }
        if (mavjud){
            model.addAttribute("error", true);
            model.addAttribute("edit", false);
            tolovlar.setAbonentlar(abonentlar);
            tolovlar = tolovService.saveOrUpdateTolovForm(tolovForm);
            return "redirect:/tolov/show/" + tolovlar.getId();
        }
        return "tolov/tolovform";
        }

    @RequestMapping("/tolov/delete/{id}")
    public String delete(@PathVariable String id){
        tolovService.delete(Long.valueOf(id));
        return "redirect:/tolov/list";
    }
}
