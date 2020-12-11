package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.StatistiquesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatistiquesRepository {

    private final StatistiquesDao statsDAO;

    @Autowired
    public StatistiquesRepository(StatistiquesDao statsDAO){
        this.statsDAO=statsDAO;
    }

    public List<Statistiques> getList(String name, String statsTypes) {
        return statsDAO.findAllStatsOrderByValueASC(name, statsTypes).stream()
                .map(x-> new Statistiques(x.getId(),x.getName(),x.getEntry_type(),x.getValue()))
                .collect(Collectors.toList());
    }
}
