package app.services.interfaces;

import app.dto.UserDTO;

public interface UserInterface {

    int createUser(UserDTO userDTO) throws Exception;

    int UpdateUser(String idUser, UserDTO userDTO) throws Exception;

    UserDTO getUser(String idUser) throws Exception;

    void deleteUser(String idUser) throws Exception;
}
