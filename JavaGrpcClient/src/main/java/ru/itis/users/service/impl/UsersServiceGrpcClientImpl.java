package ru.itis.users.service.impl;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.itis.users.grpc.pb.UserRequest;
import ru.itis.users.grpc.pb.UserServiceGrpc;
import ru.itis.users.service.UsersService;


@Service
public class UsersServiceGrpcClientImpl implements UsersService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub usersService;

    @Override
    public String getNameOfUser(String id) {
        return usersService.getUser(UserRequest.newBuilder()
                .setId(id)
                .build()).getName();
    }
}
