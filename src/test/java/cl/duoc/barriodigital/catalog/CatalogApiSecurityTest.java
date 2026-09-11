package cl.duoc.barriodigital.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CatalogApiSecurityTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    JwtDecoder jwtDecoder;

    @Test
    void listar_sin_token_401() throws Exception {
        mvc.perform(get("/catalog/procedures")).andExpect(status().isUnauthorized());
    }

    @Test
    void listar_con_token_cualquier_rol_200() throws Exception {
        mvc.perform(get("/catalog/procedures")
                .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_Vecino"))))
           .andExpect(status().isOk());
    }

    @Test
    void crear_sin_rol_admin_403() throws Exception {
        mvc.perform(post("/catalog/procedures")
                .contentType("application/json")
                .content("{\"nombre\":\"Poda de arbol\",\"requisitos\":\"foto\",\"cupoDiario\":10}")
                .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_Funcionario"))))
           .andExpect(status().isForbidden());
    }

    @Test
    void crear_con_rol_admin_201() throws Exception {
        mvc.perform(post("/catalog/procedures")
                .contentType("application/json")
                .content("{\"nombre\":\"Poda de arbol\",\"requisitos\":\"foto\",\"cupoDiario\":10}")
                .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_Admin"))))
           .andExpect(status().isCreated());
    }
}
