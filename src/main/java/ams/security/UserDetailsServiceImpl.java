package ams.security;

import ams.constant.AppConstant;
import ams.model.entity.Account;
import ams.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountService.findByAccount(username);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Account: \"" + username + "\" is not exist");
        }
        Account account = accountOptional.get();
        List<GrantedAuthority> roles = Collections.singletonList(
                new SimpleGrantedAuthority(AppConstant.USER_ROLE_PREFIX + account.getRole().name())
        );

        return new User(account.getAccount(),
                account.getPassword(), roles);
    }
}
