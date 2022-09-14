package ru.spring.Project.Controllers;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.Project.Models.Role;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Collection;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "КаЛьКуЛюТоР");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
        System.out.println("ffff:"+hasUserRole);
        return "home";
    }

    @GetMapping("/res")
    public String Calc(){
        return "Result";
    }

    @PostMapping("/res")
    public String CalcPost(
            @RequestParam(name="var1", required=true) String a,
            @RequestParam(name="var2", required=true) String b,
            Model model) throws ScriptException {


        ScriptEngineManager manager = new ScriptEngineManager();
        manager.registerEngineName("customScriptEngineFactory",new NashornScriptEngineFactory());
        ScriptEngine engine = manager.getEngineByName("javascript");
        Object res1 = new Object();
        Object res2 = new Object();
        try{
            res1 = engine.eval(a);
        }
        catch (Exception e){
            res1 = "Введите КОРРЕКТНОЕ значение выражения №1";
        }
        try{
            res2 = engine.eval(b);
        }
        catch (Exception e){
            res2 = "Введите КОРРЕКТНОЕ значение выражения №2";
        }
        model.addAttribute("name1", res1);
        model.addAttribute("name2", res2);
        return "Result";
    }
}
