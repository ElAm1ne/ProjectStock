package com.example.ProjectStock.Service;
import com.example.ProjectStock.Modele.Users;
import com.example.ProjectStock.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Repository.StockEntityRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void registerUser(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    public boolean login(String username, String password) {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
