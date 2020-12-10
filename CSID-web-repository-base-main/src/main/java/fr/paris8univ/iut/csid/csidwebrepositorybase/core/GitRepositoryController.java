package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value="/repositories")
    public class GitRepositoryController {

        private final GitRepositoryService repositoryService;

        @Autowired
        public GitRepositoryController(GitRepositoryService repoServ) {
            this.repositoryService = repoServ;
        }

        @GetMapping
        public List<GitRepository> getRepositories() {
            return this.repositoryService.getRepositories();
        }

        @GetMapping("/{name}")
        public ResponseEntity<GitRepository> getOneRepository(@PathVariable String name) throws URISyntaxException {
            return repositoryService.getOneRepository(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/add")
        public ResponseEntity<GitRepository> createOneRepository(@RequestBody GitRepository gitRepo) throws URISyntaxException {
            repositoryService.add(gitRepo);
            URI location = new URI("/repositories/add/"+gitRepo.getName());
            return ResponseEntity.created(location).build();
        }

        @DeleteMapping("/{name}")
        public ResponseEntity<GitRepository> deleteOneRepository(@PathVariable String name) throws URISyntaxException {
            repositoryService.delete(name);
            URI location = new URI("/repositories/delete/"+name);
            return ResponseEntity.created(location).build();
        }

        @PutMapping("/{name}")
        public ResponseEntity<GitRepository> putOneRepository(@PathVariable String name, @RequestBody GitRepository gitRepo) {
            repositoryService.update(name, gitRepo);
            return ResponseEntity.noContent().build();
        }

        @PatchMapping("/{name}")
        public ResponseEntity<GitRepository> patchOneRepository(@PathVariable String name, @RequestBody GitRepository gitRepo) {
            repositoryService.partialUpdate(name, gitRepo);
            return ResponseEntity.noContent().build();
        }
    }