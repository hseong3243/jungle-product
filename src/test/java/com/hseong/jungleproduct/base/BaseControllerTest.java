package com.hseong.jungleproduct.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hseong.jungleproduct.auth.JwtProvider;
import com.hseong.jungleproduct.auth.TokenResponse;
import com.hseong.jungleproduct.config.SecurityConfig;
import com.hseong.jungleproduct.domain.MemberRole;
import com.hseong.jungleproduct.service.LoginService;
import com.hseong.jungleproduct.service.OrderService;
import com.hseong.jungleproduct.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@Import({TestContextConfiguration.class, SecurityConfig.class})
public abstract class BaseControllerTest {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ProductService productService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected LoginService loginService;

    @Autowired
    protected JwtProvider jwtProvider;

    protected String accessToken;

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider(
                "test",
                600,
                1200,
                "asdfasdfadsfasdfasfasdfasdafdfaafsd",
                "asdfasdfasdfasdfasdfasdfadsfasdfafdfasdfas"
        );
        TokenResponse token = jwtProvider.createToken(1L, MemberRole.ADMIN);
        accessToken = "Bearer " + token.accessToken();
    }
}
