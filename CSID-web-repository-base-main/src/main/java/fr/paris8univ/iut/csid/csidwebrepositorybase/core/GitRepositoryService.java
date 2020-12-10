package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepositoryRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class GitRepositoryService {

    private final GitRepositoryRepository gitRepoRepo;

    public GitRepositoryService(GitRepositoryRepository gitRepoRepo) {
        this.gitRepoRepo = gitRepoRepo;
    }

    public List<GitRepository> getRepositories() {
        return this.gitRepoRepo.getRepositories();
    }

    public Optional<GitRepository> getOneRepository(String name) throws URISyntaxException {
        return this.gitRepoRepo.getOneRepository(name);
    }

    public boolean add(GitRepository gitRepo) {
        this.gitRepoRepo.add(gitRepo);
        return true;
    }

    public boolean delete(String nomGitRepo) {
       this.gitRepoRepo.delete(nomGitRepo);
       return true;
    }

    public boolean update(String name, GitRepository gitRepo) {
        this.gitRepoRepo.update(name, gitRepo);
        return false;
    }

    public boolean partialUpdate(String name, GitRepository gitRepo) {
        this.gitRepoRepo.partialUpdate(name, gitRepo);
        return true;
    }
}