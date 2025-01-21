package engine.service;

import engine.mapping.UserMapper;
import engine.model.UserDto;
import engine.repository.UserDao;
import engine.repository.UserDaoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserDaoRepository userDaoRepository;
    private final PasswordEncoder passwordEncoder;
    final UserMapper userMapper;

    public UserService(UserDaoRepository userDaoRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userDaoRepository = userDaoRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDaoRepository.findUserDaoByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found."));
    }

    public void saveUser(UserDto userDto) {
        UserDao userDao = userMapper.userDtoToUserDao(userDto);
        userDao.setAuthority("ROLE_USER");
        userDao.setPassword(passwordEncoder.encode(userDto.password()));
        userDaoRepository.save(userDao);
    }

    public boolean existsByEmail(UserDto userDto) {
        return userDaoRepository.existsByEmail(userDto.email());
    }

    public UserDao getUserDaoByEmail(String email) {
        return userDaoRepository.findUserDaoByEmail(email).orElse(null);
    }
}
