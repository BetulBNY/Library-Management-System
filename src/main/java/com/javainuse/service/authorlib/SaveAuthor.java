package com.javainuse.service.authorlib;

import com.javainuse.model.Author;
import com.javainuse.repository.AuthorRepo;
import com.javainuse.service.UserService;
import com.javainuse.service.strategies.AccessControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveAuthor {


    private final UserService userService;
    private final AuthorRepo authorRepo;
    private final AccessControlService accessControlService;


    public Author saveOneAuthor(Author newAuthor) {
        if(accessControlService.isCurrentUserLibrarian()){
            return authorRepo.save(newAuthor);
        }
        throw new RuntimeException("Can't access!");
    }
}
