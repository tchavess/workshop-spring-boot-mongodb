package com.tchaves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tchaves.workshopmongo.domain.User;
import com.tchaves.workshopmongo.dto.UserDTO;
import com.tchaves.workshopmongo.repository.UserRepository;
import com.tchaves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}

	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	

	// É melhor pratica criar este metodo aqui(Ao inves da classe UserDTO) devido esta classe ja possuir
	// dependencia com o BD, para caso precisemos usar o metodo com consulta ao
	// mesmo
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
