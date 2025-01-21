package engine.mapping;

import engine.model.UserDto;
import engine.repository.UserDao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T21:07:45-0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDao userDtoToUserDao(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserDao userDao = new UserDao();

        userDao.setEmail( userDto.email() );
        userDao.setPassword( userDto.password() );

        return userDao;
    }

    @Override
    public UserDto userDaoToUserDto(UserDao userDao) {
        if ( userDao == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = userDao.getEmail();
        password = userDao.getPassword();

        UserDto userDto = new UserDto( email, password );

        return userDto;
    }
}
