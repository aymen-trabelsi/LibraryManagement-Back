package gestion.administration.application.controller;
import gestion.administration.application.dataTO.UserDTO;
import gestion.administration.application.entity.User;
import gestion.administration.application.repository.UserRepository;
import gestion.administration.jwt.models.AuthenticationRequest;
import gestion.administration.jwt.models.AuthenticationResponse;
import gestion.administration.jwt.services.MyUserDetailsService;
import gestion.administration.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
@CrossOrigin(allowedHeaders = "'*'")

public class AdministrationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value ="/user/{username}", method= RequestMethod.GET )
    public User getUserByUsername(@PathVariable("username") String username){
        return userRepository.findByUsername(username);
    }

    @RequestMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e );
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @RequestMapping(value = "/admins" , method = RequestMethod.GET)
    public Iterable<User> getAllAdmins () {
        return userRepository.findAll();
    }

    //delete
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void deleteAdmin(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }
}
