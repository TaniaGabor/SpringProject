package database;
import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class Constants {

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String ACCOUNT = "account";
        public static final String CLIENT = "client";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{ACCOUNT,CLIENT,USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, ACCOUNT};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";
        public static final String CUSTOMER = "customer";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE, CUSTOMER};
    }

    public static class Rights {
        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";

        public static final String CREATE_ACCOUNT = "create_account";
        public static final String DELETE_ACCOUNT= "delete_account";
        public static final String UPDATE_ACCOUNT = "update_account";

       public static final String RETURN_ACCOUNT =  "return_account";
       public static final String ADD_ACCOUNT =  "add_account";

      public static final String ADD_CLIENT =  "add_client";
      public static final String UPDATE_CLIENT =  "update_client";
      public static final String VIEW_CLIENT =  "view_client";

        public static final String VIEW_MONEYAMOUNT =  "view_moneyamount";
        public static final String[] RIGHTS = new String[]{CREATE_USER, DELETE_USER, UPDATE_USER,CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, RETURN_ACCOUNT,ADD_ACCOUNT,ADD_CLIENT,UPDATE_CLIENT,
        VIEW_CLIENT,VIEW_MONEYAMOUNT};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> rolesRights = new HashMap<>();
        for (String role : ROLES) {
            rolesRights.put(role, new ArrayList<>());
        }
        rolesRights.get(ADMINISTRATOR).addAll(Arrays.asList(RIGHTS));

        rolesRights.get(EMPLOYEE).addAll(Arrays.asList(CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, RETURN_ACCOUNT,ADD_ACCOUNT,
                ADD_CLIENT,UPDATE_CLIENT,VIEW_CLIENT   ));

        rolesRights.get(CUSTOMER).addAll(Arrays.asList(VIEW_MONEYAMOUNT));

        return rolesRights;
    }

}