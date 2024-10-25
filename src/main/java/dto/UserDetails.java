package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetails {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String userRole;
}
