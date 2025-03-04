package tech.buildrun.agregadordeinvestimentos.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech.buildrun.agregadordeinvestimentos.controller.dto.AccountResponseDTO;
import tech.buildrun.agregadordeinvestimentos.controller.dto.CreateAccountDTO;
import tech.buildrun.agregadordeinvestimentos.controller.dto.CreateUserDTO;
import tech.buildrun.agregadordeinvestimentos.controller.dto.UpdateUserDTO;
import tech.buildrun.agregadordeinvestimentos.entity.Account;
import tech.buildrun.agregadordeinvestimentos.entity.BillingAddress;
import tech.buildrun.agregadordeinvestimentos.entity.User;
import tech.buildrun.agregadordeinvestimentos.repository.AccountRepository;
import tech.buildrun.agregadordeinvestimentos.repository.BillingAddressRepository;
import tech.buildrun.agregadordeinvestimentos.repository.UserRepository;

@Service
public class UserService {
  private AccountRepository accountRepository;
  // private BillingAddressRepository billingAddressRepository;
  private UserRepository userRepository;

  public UserService(
    AccountRepository accountRepository,
    BillingAddressRepository billingAddressRepository,
    UserRepository userRepository
    ) {
    this.accountRepository = accountRepository;
    // this.billingAddressRepository = billingAddressRepository;
    this.userRepository = userRepository;
  }

  public UUID createUser(CreateUserDTO createUserDto){
    // converter DTO -> entity
    var entity = new User(
      null,
      createUserDto.username(),
      createUserDto.email(),
      createUserDto.password(),
      Instant.now(),
      null
    );

    var userSaved = userRepository.save(entity);

    return userSaved.getUserId();
  }

  public Optional<User> getUserById(String userId) {
    return userRepository.findById(UUID.fromString(userId));
  }

  public List<User> listUsers(){
    return userRepository.findAll();
  }

  public void updateUserById(String userId, UpdateUserDTO updateUserDTO){
    var id = UUID.fromString(userId);

    var userEntity = userRepository.findById(id);

    if (userEntity.isPresent()){
      var user = userEntity.get();

      if (updateUserDTO.username() != null)
        user.setUsername(updateUserDTO.username());

      if (updateUserDTO.password() != null)
        user.setPassword(updateUserDTO.password());

      userRepository.save(user);
    }
  }

  public void deleteById(String userId) {
    var id = UUID.fromString(userId);

    var userExists = userRepository.existsById(id);

    if (userExists) userRepository.deleteById(id);
  }

  public void createAccount(String userId, CreateAccountDTO createAccountDTO){

    var user = userRepository.findById(UUID.fromString(userId))
      .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND));

    // Criar Account
    var account = new Account();
    account.setUser(user);
    account.setDescription(createAccountDTO.description());

    // Criar BillingAddress e associar à Account
    var billingAddress = new BillingAddress();
    billingAddress.setAccount(account);
    billingAddress.setStreet(createAccountDTO.street());
    billingAddress.setNumber(createAccountDTO.number());

    // Associar o endereço à conta
    account.setBillingAddress(billingAddress);

    // Salvar Account (BillingAddress será salvo junto devido ao cascade)
    accountRepository.save(account);
  }

  // erro
  // public void createAccount(String userId, CreateAccountDTO createAccountDTO){
  //   var user = userRepository.findById(UUID.fromString(userId))
  //     .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND));
  //   // DTO to Entity
  //   var account = new Account(
  //     UUID.randomUUID(),
  //     user,
  //     null,
  //     createAccountDTO.description(),
  //     new ArrayList<>()
  //   );
  //   var accountCreated = accountRepository.save(account);
  //   var billingAddress = new BillingAddress(
  //     accountCreated.getAccountId(),
  //     account,
  //     createAccountDTO.street(),
  //     createAccountDTO.number()
  //   );
  //   billingAddressRepository.save(billingAddress);
  // }

  public List<AccountResponseDTO> listAccountsByUserId(String userId) {

    var user = userRepository.findById(UUID.fromString(userId))
      .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND));

    return user.getAccounts()
      .stream()
      .map(
        account -> new AccountResponseDTO(
          account.getAccountId().toString(),
          account.getDescription()
        )
      )
      .toList();
  }

}


// 49m00s
