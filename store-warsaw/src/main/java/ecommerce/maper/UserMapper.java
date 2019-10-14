package ecommerce.maper;

import ecommerce.domain.UserEntity;
import ecommerce.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserEntity mapToUser(final UserDto userDto) {
        return new UserEntity(userDto.getUserName(), userDto.getUserIsBlocked());
    }

    public List<UserDto> mapToUserDtoList(final List<UserEntity> users) {
        return users.stream()
                .map(user -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    public UserDto mapToUserDto(final UserEntity user) {
        return new UserDto(user.getId(), user.getUserName(), user.isBlocked());
    }
}
