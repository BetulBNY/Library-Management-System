package com.javainuse.service.strategies;

import com.javainuse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActiveAccessControlService implements AccessControlService {
    private final UserService userService;

    @Override
    public boolean isCurrentUserLibrarian() {
        return userService.isCurrentUserLibrarian();
    }
}
