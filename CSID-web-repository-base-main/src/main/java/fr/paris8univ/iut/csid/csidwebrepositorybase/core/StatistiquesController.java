package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.Statistiques;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/repositories/{name}")
public class StatistiquesController {

    private final StatistiquesService statsService;

    @Autowired
    public StatistiquesController(StatistiquesService statsService) {
        this.statsService=statsService;
    }

    @GetMapping("/stats")
    public List<Statistiques> getStatistiques(@PathVariable String name, @RequestParam String statsTypes){
        return this.statsService.getList(name,statsTypes);
    }
}
