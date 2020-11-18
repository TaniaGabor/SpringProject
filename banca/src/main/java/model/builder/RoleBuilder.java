package model.builder;

import database.Constants;
import model.Right;
import model.Role;

import java.util.ArrayList;
import java.util.List;

import static database.Constants.Roles.ADMINISTRATOR;

public  class RoleBuilder {
    private Role role=new Role();


 private List<Right>  getWellRights()
  {
      List<Right> rights=new ArrayList<>();
     List<String> nameRight= Constants.getRolesRights().get(role.getRole());
      for(String s:nameRight)
      {
          Right nRight=new Right(role.getId(),s);
          rights.add(nRight);
      }

     return rights;

  }

    public  Role build(Long id, String namerole) {
        role.setId(id);
        role.setRole(namerole);
        role.setRights(this.getWellRights());
        return role;
    }
}
