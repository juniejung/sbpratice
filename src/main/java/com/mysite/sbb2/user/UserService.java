package com.mysite.sbb2.user;

import com.mysite.sbb2.DataNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.mysite.sbb2.DataNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
//        BCryptPasswordEncoder 객체를 직접 new로 생성하는 방식보다는
//        PasswordEncoder 빈(bean)으로 등록해서 사용하는 것이 좋다.
//        왜냐하면 암호화 방식을 변경하면 BCryptPasswordEncoder를 사용한
//        모든 프로그램을 일일이 찾아서 수정해야 하기 때문이다.
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username){
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if(siteUser.isPresent()){
            return siteUser.get();
        }else {
            throw new DataNotFoundException("siteUser not found");
        }
    }
}
