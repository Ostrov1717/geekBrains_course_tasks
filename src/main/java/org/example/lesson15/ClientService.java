package org.example.lesson15;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Page<Client> search(ClientFilter filter, int page, int size, String sortBy, String direction) {
        Specification<Client> spec = ClientSpecificationsBuilder.build(filter);
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return clientRepository.findAll(spec,pageable);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }


}
