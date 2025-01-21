package engine.mapping;

import engine.model.UserDto;
import engine.repository.UserDao;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDao userDtoToUserDao(UserDto userDto);
    UserDto userDaoToUserDto(UserDao userDao);
}
