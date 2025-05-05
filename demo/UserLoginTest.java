package socialMediaSite.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginTest {

    @Autowired
    private MockMvc mockMvc;

    // Valid login
    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "testuser")
                .param("password", "testpass"))
                .andExpect(status().is3xxRedirection()); // Expected redirect on success
    }

    // Invalid credentials
    @Test
    public void testLoginInvalidCredentials() throws Exception {
        mockMvc.perform(post("/login")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "wronguser")
                .param("password", "wrongpass"))
                .andExpect(status().isUnauthorized()); // Expect 401 if login fails
    }

    // Missing username
    @Test
    public void testLoginMissingUsername() throws Exception {
        mockMvc.perform(post("/login")
                .contentType("application/x-www-form-urlencoded")
                .param("password", "testpass"))
                .andExpect(status().isBadRequest()); // Expect 400 for missing param
    }

    //  Missing password
    @Test
    public void testLoginMissingPassword() throws Exception {
        mockMvc.perform(post("/login")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "testuser"))
                .andExpect(status().isBadRequest()); // Expect 400 for missing param
    }

    // Empty fields
    @Test
    public void testLoginEmptyFields() throws Exception {
        mockMvc.perform(post("/login")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "")
                .param("password", ""))
                .andExpect(status().isBadRequest()); // Or 401 depending on your logic
    }
}

