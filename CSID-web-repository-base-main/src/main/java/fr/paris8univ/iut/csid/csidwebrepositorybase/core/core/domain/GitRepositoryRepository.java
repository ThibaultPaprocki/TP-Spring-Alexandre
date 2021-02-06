package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GitRepositoryRepository {

    final private GitRepositoryDao repDAO;
    final private GithubRepositoryDao githubRepDAO;
    final private StatistiquesDao statsDAO;

    @Autowired
    public GitRepositoryRepository(GitRepositoryDao repDAO, GithubRepositoryDao githubRepDAO, StatistiquesDao statsDAO) {
        this.repDAO=repDAO;
        this.githubRepDAO=githubRepDAO;
        this.statsDAO=statsDAO;
    }

    public List<GitRepository> getRepositories() {
        List<GitRepositoryEntity> repositories = repDAO.findAll();
        return repositories.stream().map(x -> new GitRepository(x.getName(), x.getOwner(), x.getOpen_issues(), x.getForks())).collect(Collectors.toList());
    }

    public Optional<GitRepository> getOneRepository(String name) throws URISyntaxException {
        GitRepositoryEntity gitE = repDAO.findById(name).get();
        GitRepository gitRepo = new GitRepository(gitE.getName(),gitE.getOwner(),gitE.getForks(),gitE.getOpen_issues());
        //TODo Créer variable de temps qui calcule à chaque update afin de faire une maj après 5 minutes (patcher les issues et forks).
        //if(tempsUpdate>5){
        //   GitRepositoryDTO gitDTO = this.githubRepDAO.getGithubLink(gitRepo.getName(),gitRepo.getOwner());
        //   gitRepo.setForks(gitDTO.getForks());
        //   gitRepo.setOpen_issues(gitDTO.getOpen_issues());
        //   this.partialUpdate(name,gitRepo);
        //}
        this.statsDAO.save(new StatistiquesEntity(0,gitRepo.getName(),"issues", LocalDateTime.now().toString(),gitRepo.getOpen_issues()));
        this.statsDAO.save(new StatistiquesEntity(0,gitRepo.getName(),"forks", LocalDateTime.now().toString(),gitRepo.getForks()));
        return Optional.of(gitRepo);
    }

    public boolean add(GitRepository gitRepo) {
        this.repDAO.save(new GitRepositoryEntity(gitRepo.getName(), gitRepo.getOwner(), gitRepo.getForks(),  gitRepo.getOpen_issues()));

        
        return true;
    }

    public boolean delete(String nomGitRepo) {
        this.repDAO.deleteById(nomGitRepo);
        return true;
    }

    public boolean update(String name, GitRepository gitRepo) {
        if (repDAO.findById(name).isPresent()) {
            this.delete(name);
            this.add(gitRepo);
            return true;
        }
        return false;
    }

    public boolean partialUpdate(String name, GitRepository gitRepo) {
        GitRepository newRepo = new GitRepository("placeholder", "placeholder", 270, 270);
        GitRepositoryEntity originalRepoEntity = repDAO.getOne(name);
        newRepo.setName(originalRepoEntity.getName());
        newRepo.setOwner(originalRepoEntity.getOwner());
        newRepo.setOpen_issues(originalRepoEntity.getOpen_issues());
        newRepo.setForks(originalRepoEntity.getForks());

        if (gitRepo.getOwner() != null)
            newRepo.setOwner(gitRepo.getOwner());
        if (gitRepo.getOpen_issues() != null)
            newRepo.setOpen_issues(gitRepo.getOpen_issues());
        if (gitRepo.getForks() != null)
            newRepo.setForks(gitRepo.getForks());
        this.update(name, newRepo);
        return true;
    }

    public IssueResponse createIssue(String nomRepo, IssueRequest issue) throws URISyntaxException {
        GitRepositoryEntity gitRep = this.repDAO.getOne(nomRepo);
        return this.githubRepDAO.createIssue(gitRep.getOwner(), nomRepo, issue);
    }
}
