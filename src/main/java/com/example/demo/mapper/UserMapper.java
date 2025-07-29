package com.example.demo.mapper;
import com.example.demo.entity.User;
import com.example.demo.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequest request);
}