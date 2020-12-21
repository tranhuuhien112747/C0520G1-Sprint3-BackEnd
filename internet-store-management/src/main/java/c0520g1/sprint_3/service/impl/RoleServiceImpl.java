package c0520g1.sprint_3.service.impl;

import c0520g1.sprint_3.model.Role;
import c0520g1.sprint_3.repository.RoleRepository;
import c0520g1.sprint_3.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long idRole) {
        return roleRepository.findById(idRole).orElse(null);

    }
}

