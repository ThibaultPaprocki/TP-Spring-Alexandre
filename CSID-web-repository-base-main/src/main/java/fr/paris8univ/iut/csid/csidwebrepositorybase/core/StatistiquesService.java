package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.Statistiques;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.StatistiquesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatistiquesService {

    private final StatistiquesRepository statsRepo;

    public StatistiquesService(StatistiquesRepository statsRepo) {
        this.statsRepo = statsRepo;
    }
    public List<Statistiques> getList(String name,String statsTypes) {
        return statsRepo.getList(name,statsTypes);
    }
}
