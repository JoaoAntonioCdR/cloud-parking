package deploy.project.cloud_parking.Service;

import deploy.project.cloud_parking.Model.User;
import deploy.project.cloud_parking.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         // Busca o usuário pelo nome de usuário no banco de dados
         User user = userRepository.findUserByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

         // Cria e retorna um objeto UserDetails com os dados do usuário encontrado
         return org.springframework.security.core.userdetails.User
                 .withUsername(user.getUsername()) // nome de usuário
                 .password(user.getPassword())     // senha (já criptografada)
                 .roles(user.getRole())            // papel do usuário (ex: ADMIN, USER)
                 .build();
     }
}

