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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Normal signup
    @Test
    public void testSignup() throws Exception {
        mockMvc.perform(post("/signup")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "testuser")
                .param("password", "testpass"))
                .andExpect(status().is3xxRedirection());
    }

    // Missing username
    @Test
    public void testSignupMissingUsername() throws Exception {
        mockMvc.perform(post("/signup")
                .contentType("application/x-www-form-urlencoded")
                .param("password", "testpass"))
                .andExpect(status().isBadRequest()); // Adjust based on your controller's behavior
    }

    // Missing password
    @Test
    public void testSignupMissingPassword() throws Exception {
        mockMvc.perform(post("/signup")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "testuser"))
                .andExpect(status().isBadRequest());
    }

    // Empty fields
    @Test
    public void testSignupEmptyFields() throws Exception {
        mockMvc.perform(post("/signup")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "")
                .param("password", ""))
                .andExpect(status().isBadRequest());
    }

    // Attempting to sign up with an already taken username
    @Test
    public void testSignupDuplicateUsername() throws Exception {
        // First signup
        mockMvc.perform(post("/signup")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "existinguser")
                .param("password", "pass123"))
                .andExpect(status().is3xxRedirection());

        // Second attempt with same username
        mockMvc.perform(post("/signup")
                .contentType("application/x-www-form-urlencoded")
                .param("username", "existinguser")
                .param("password", "pass456"))
                .andExpect(status().isConflict()); // Assuming your backend handles this with 409
    }
}

