package c0520g1.sprint_3.service;

import c0520g1.sprint_3.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(Long idRole);

}
