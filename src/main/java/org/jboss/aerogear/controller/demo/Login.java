package org.jboss.aerogear.controller.demo;

import org.abstractj.cuckootp.Totp;
import org.abstractj.cuckootp.api.Base32;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.jboss.aerogear.security.persistence.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Login {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserRepository userRepository;

    public void index() {
        System.out.println("Login page!");
    }

    public AeroGearUser login(AeroGearUser user) {

        AeroGearUser aeroGearUser = userRepository.findById(user.getId());

        if (aeroGearUser == null) {
            String secret = new Base32().random();
            Totp totp = new Totp(secret);
            user.setSecret(secret);
            userRepository.store(user);
        }

        authenticationManager.login(user);
        return user;
    }

    public void logout() {
        System.out.println("User logout!");
        authenticationManager.logout();
    }
}
