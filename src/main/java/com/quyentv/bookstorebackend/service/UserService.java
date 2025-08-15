package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.constant.PredefinedRole;
import com.quyentv.bookstorebackend.dto.request.UserCreationRequest;
import com.quyentv.bookstorebackend.dto.response.UserResponse;
import com.quyentv.bookstorebackend.entity.Role;
import com.quyentv.bookstorebackend.entity.User;
import com.quyentv.bookstorebackend.exception.AppException;
import com.quyentv.bookstorebackend.exception.ErrorCode;
import com.quyentv.bookstorebackend.mapper.UserMapper;
import com.quyentv.bookstorebackend.repository.RoleRepository;
import com.quyentv.bookstorebackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }
}