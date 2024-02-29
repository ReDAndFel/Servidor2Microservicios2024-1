package app.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.UserDTO;
import app.model.User;
import app.services.interfaces.DateInterface;

@Service
public class UserImpl {

    @Autowired
    DateInterface dateInterface;
    
    String createUser(UserDTO userDTO) throws Exception{
        
        User user = new User();
        
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(dateInterface.convertStr2Date(userDTO.getBirthday()));

        
        return "a";
    }

    String UpdateUser(String idUser, UserDTO userDTO) throws Exception{

        return "a";
    }

    UserDTO getUser(String idUser) throws Exception{

        UserDTO userDTO = new UserDTO();

        return userDTO ;
    }

    void deleteUser(String idUser) throws Exception{
        
    }

}
