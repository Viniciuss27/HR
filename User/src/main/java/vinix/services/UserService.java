package vinix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vinix.entities.User;
import vinix.repostories.UserRepository;
import vinix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public User findById(Long id) {
        return rep.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User findByEmail(String email) {
        return rep.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException(email));
    }
}