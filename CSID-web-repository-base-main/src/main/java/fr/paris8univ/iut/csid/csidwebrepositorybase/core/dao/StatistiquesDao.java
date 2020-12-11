package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatistiquesDao extends JpaRepository<StatistiquesEntity, String> {

    @Query(value = "select * from statistiques as stats where stats.name= ?1 and stats.entry_type= ?2 order by stats.value asc ",nativeQuery=true)
    List<StatistiquesEntity> findAllStatsOrderByValueASC(String repository, String statType);
}
